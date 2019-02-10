package br.com.fatec.fatecsjc.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.fatec.fatecsjc.R;
import br.com.fatec.fatecsjc.model.Requerimento;

/**
 * Created by ricardo on 10/09/2017.
 */

public class Formulario05Activity extends AppCompatActivity {

    private EditText rg;
    private EditText telefone;
    private EditText semestre;
    private EditText turno;

    private Button btnVoltar;
    private Button btnConfirmar;

    private AlertDialog.Builder dialog;

    private String complemento = " ";
    private String tipo = "Certificado de Conclusão de Curso";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario05);

        rg = (EditText) (findViewById(R.id.editRg));
        telefone = (EditText) (findViewById(R.id.editTelefone));
        semestre = (EditText) (findViewById(R.id.editSemestre));
        turno = (EditText) (findViewById(R.id.editTurno));
        btnVoltar = (Button) (findViewById(R.id.btnVoltar));
        btnConfirmar = (Button) (findViewById(R.id.btnConfirmar));

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new AlertDialog.Builder(Formulario05Activity.this);
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
                    //                dialog.setMessage(msgDialog);
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
                            Requerimento req = new Requerimento(Formulario05Activity.this, rg, telefone, semestre, turno, complemento, tipo);
                            req.GravarRequerimento(Formulario05Activity.this, req);}
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
                startActivity(new Intent(Formulario05Activity.this, RequerimentoActivity.class));
            }
        });
    }
}