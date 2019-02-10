package br.com.fatec.fatecsjc.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import br.com.fatec.fatecsjc.R;
import br.com.fatec.fatecsjc.model.Requerimento;

/**
 * Created by ricardo on 10/09/2017.
 */

public class Formulario02Activity extends AppCompatActivity {

    private EditText rg;
    private EditText telefone;
    private EditText semestre;
    private EditText turno;

    private RadioGroup grupo;
    private RadioButton radioButton;

    private Button btnVoltar;
    private Button btnConfirmar;

    private AlertDialog.Builder dialog;

    private String complemento = " ";
    private String tipo = "Histórico Escolar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario02);

        rg = (EditText) (findViewById(R.id.editRg));
        telefone = (EditText) findViewById(R.id.editTelefone);
        semestre = (EditText) findViewById(R.id.editSemestre);
        turno = (EditText) findViewById(R.id.editTurno);
        grupo = (RadioGroup) findViewById(R.id.grupo);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnConfirmar = (Button) findViewById(R.id.btnConfirmar);

        grupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                radioButton = (RadioButton) findViewById(checkedId);
                complemento = radioButton.getText().toString();
            }
        });

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new AlertDialog.Builder(Formulario02Activity.this);
                if ((rg.getText().length() == 0) || (telefone.getText().length() == 0) || (semestre.getText().length() == 0) || (turno.getText().length() == 0)) {
                    dialog.setTitle("Dados incompletos!");
                    dialog.setMessage("Favor preencher todos os campos.");
                    dialog.setCancelable(false);
                    dialog.setIcon(android.R.drawable.ic_menu_info_details); // seleciona icones nativos do android

                    // botao negativo
                    dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    dialog.create();
                    dialog.show();
                } else {
                    dialog.setTitle("Confirma Requerimento?");
                    dialog.setCancelable(false);
                    dialog.setIcon(android.R.drawable.ic_menu_help); // seleciona icones nativos do android

                    // botao negativo
                    dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    // botao positivo
                    dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Requerimento req = new Requerimento(Formulario02Activity.this, rg, telefone, semestre, turno, complemento, tipo);
                            req.GravarRequerimento(Formulario02Activity.this, req);
                        }
                    });
                    dialog.create();
                    dialog.show();
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Formulario02Activity.this, RequerimentoActivity.class));
            }
        });
    }
}