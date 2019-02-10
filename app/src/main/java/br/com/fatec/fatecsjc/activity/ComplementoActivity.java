package br.com.fatec.fatecsjc.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.fatec.fatecsjc.R;
import br.com.fatec.fatecsjc.model.Curso;
import br.com.fatec.fatecsjc.model.Usuario;

public class ComplementoActivity extends Activity {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private EditText ra;
    private EditText nome;
    private AlertDialog.Builder dialog;
    private ListView listaCursos;
    private String mRa;
    private String mNome;
    private String mCurso;

    Curso curso = new Curso();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complemento);

        ra = (EditText) findViewById(R.id.editRaId);
        nome = (EditText) findViewById(R.id.editNomeId);
        listaCursos = (ListView) findViewById(R.id.listaCursosId);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_2,
                android.R.id.text2,
                curso.getNomeCurso()) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text2);
                text.setTextColor(Color.BLACK);
                return view;
            }
        };

        listaCursos.setAdapter(adaptador);
        listaCursos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                dialog = new AlertDialog.Builder(ComplementoActivity.this);
                if ((ra.getText().length() == 0) || (nome.getText().length() == 0) || (curso.getNomeCurso()[position].length() == 0)) {
                    dialog.setTitle("Atenção!");
                    String msgDialog = "Dados Incompletos";
                    dialog.setMessage(msgDialog);
                    dialog.setCancelable(false);
                    dialog.setIcon(android.R.drawable.ic_dialog_alert); // seleciona icones nativos do android
                    // botao negativo
                    dialog.setNegativeButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    dialog.create();
                    dialog.show();
                } else if (validarRa(String.valueOf(ra.getText()))) {
                    mRa = String.valueOf(ra.getText());
                    mNome = String.valueOf(nome.getText());
                    mCurso = String.valueOf(curso.getNomeCurso()[position]);
                    String msgDialog = "RA: " + mRa + " / Nome: " + mNome + " / Curso: " + mCurso;
                    dialog.setTitle("Confirma Dados?");
                    dialog.setMessage(msgDialog);
                    dialog.setCancelable(false);
                    dialog.setIcon(android.R.drawable.ic_menu_info_details); // seleciona icones nativos do android
                    // botao negativo
                    dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(ComplementoActivity.this, "Pressionado botão Não", Toast.LENGTH_SHORT).show();
                        }
                    });
                    // botao positivo
                    dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mFirebaseInstance = FirebaseDatabase.getInstance();
                            mFirebaseDatabase = mFirebaseInstance.getReference("usuarios");
                            firebaseAuth = FirebaseAuth.getInstance();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            String uid = (user.getUid());
                            String email = (user.getEmail());
                            String numCurso = curso.getCodCurso()[position]; // recupera o código do curso
                            Usuario usuario = new Usuario(uid, mRa, mNome, email, numCurso, "ativo");
                            mFirebaseDatabase.child(usuario.getUid()).setValue(usuario);
//                            grava dados do usuario no dispositivo
                            gravarDispositivo(mNome, mRa, mCurso, uid, email);
                            // registrando dispositivo no tópico fatec
                            FirebaseMessaging.getInstance().subscribeToTopic("fatec");

//                            verifica a origem da chamada à activity
                            Bundle extra = getIntent().getExtras();
                            String origem = extra.getString("origem");
                            if (origem.equals("manutencao")) {
                                Intent intent = new Intent(ComplementoActivity.this, ManutencaoActivity.class);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(ComplementoActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                    dialog.create();
                    dialog.show();
                } else {
                    dialog.setTitle("Atenção!");
                    String msgDialog = "Dados Inconsistentes!";
                    dialog.setMessage(msgDialog);
                    dialog.setCancelable(false);
                    dialog.setIcon(android.R.drawable.ic_dialog_alert); // seleciona icones nativos do android
                    // botao negativo
                    dialog.setNegativeButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    dialog.create();
                    dialog.show();
                }
            }
        });
    }

    private void gravarDispositivo(String mNome, String mRa, String mCurso, String uid, String email) {

        SharedPreferences settings = getSharedPreferences("arquivo", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("nome", mNome);
        editor.putString("ra", mRa);
        editor.putString("curso", mCurso);
        editor.putString("uid", uid);
        editor.putString("email", email);
        editor.commit();
    }

    private boolean validarRa(String ra) {

        // comprimento do RA
        if (ra.length() != 13) {
            return false;
        }

        // Unidade do RA
        String unidade = ra.substring(0, 3);
        if (unidade.equals("146") != true) {
            return false;
        }

        // Curso do RA
        boolean flag = false; // boolean auxiliar
        String cursoRa = ra.substring(3, 6);
        Curso curso = new Curso();
        for (String s : curso.getCodCurso()) {
            if (cursoRa.equals(s)) {
                flag = true;
            }
        }
        if (!flag) {
            return false;
        }

        // Ano do RA
        /*
         * Regra: até 5 anos de curso até 1 ano de intercâmbio até 1 ano trancado
         * somente aceitar RAs nesta faixa
         */
        DateFormat df = new SimpleDateFormat("yy"); // ano em 2 dígitos
        String anoAtual = df.format(Calendar.getInstance().getTime());
        int iAnoAtual = Integer.parseInt(anoAtual);

        String anoRa = ra.substring(6, 8);
        int iAnoRa = Integer.parseInt(anoRa);
        int intervalo = iAnoAtual - iAnoRa;

        // intervalo será negativo se o ano informado no RA
        // for maior que ano atual
        if ((intervalo > 6) || (intervalo < 0)) {
            return false;
        }

        // Semestre do RA
        // podem ser apenas 1 ou 2
        // pode ser 2, para ano corrente, se estivermos no segundo semestre

        String semestre = ra.substring(8, 9);
        if (semestre.equals("1") || semestre.equals("2")) {
            // se semestre válido na 1a verificação, verifica se estamos no 2º sem. do ano
            // atual
            GregorianCalendar calendar = new GregorianCalendar();
            int mes = calendar.get(GregorianCalendar.MONTH);
            mes = mes + 1; // correção do calendário java onde Jan = 0;
            if (semestre.equals("2") && anoAtual.equals(anoRa) && mes < 7) {
                return false;
            }
        } else {
            return false;
        }

        // Período do RA
        /*
         * 1 - Manhã 2 - Tarde 3 - Noite 4 - Integral ou Especial 7 - EAD Construir
         * lista com valores inválidos, verificar se período está na lista
         */
        String[] periodoInvalido = new String[]{"0", "5", "6", "8", "9"};
        List<String> lista = Arrays.asList(periodoInvalido);
        if (lista.contains(ra.substring(9, 10))) {
            return false;
        }

        // Sequêncial do Aluno
        // Verifica se o valor informado é dif de zero
        String sequencial = ra.substring(10, 13);
        int iSequencial = Integer.parseInt(sequencial);
        if (iSequencial == 0) {
            return false;
        }

        return true; // retorno da função, se acionado o RA é válido
    }
}