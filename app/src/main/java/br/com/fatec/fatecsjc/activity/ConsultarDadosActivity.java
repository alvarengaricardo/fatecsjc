package br.com.fatec.fatecsjc.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.fatec.fatecsjc.R;

/**
 * Created by ricardo on 07/11/2017.
 */

public class ConsultarDadosActivity extends AppCompatActivity {

    private Button btnVoltar;

    private TextView nomet;
    private TextView rat;
    private TextView emailt;
    private TextView cursot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultardados);

        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        nomet = (TextView) findViewById(R.id.nomeId);
        rat =  (TextView) (findViewById(R.id.raId));
        emailt =  (TextView) (findViewById(R.id.emailId));
        cursot =  (TextView) (findViewById(R.id.cursoId));

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConsultarDadosActivity.this, ManutencaoActivity.class));
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("arquivo", Context.MODE_PRIVATE);
        String nome = sharedPreferences.getString("nome", "");
        String ra = sharedPreferences.getString("ra", "");
        String email = sharedPreferences.getString("email", "");
        String curso = sharedPreferences.getString("curso", "");

        nomet.setText(String.valueOf(nome));
        rat.setText(String.valueOf(ra));
        emailt.setText(String.valueOf(email));
        cursot.setText(String.valueOf(curso));

    }
}