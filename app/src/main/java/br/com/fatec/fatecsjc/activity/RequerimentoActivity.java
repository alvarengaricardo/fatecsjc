package br.com.fatec.fatecsjc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import br.com.fatec.fatecsjc.R;

/**
 * Created by ricardo on 10/09/2017.
 */

public class RequerimentoActivity extends AppCompatActivity {

//    private Button btnAtestadoMatricula;
//    private Button btnHistoricoEscolar;
//    private Button btnCertificadoConclusao;
//    private Button btnConsultarRequerimento;
//    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

//        btnAtestadoMatricula = findViewById(R.id.btnAtestadoMatricula);
//        btnHistoricoEscolar = findViewById(R.id.btnHistoricoEscolar);
//        btnCertificadoConclusao = findViewById(R.id.btnCertificadoConclusao);
//        btnConsultarRequerimento = findViewById(R.id.btnConsularRequerimento);
//        btnVoltar = findViewById(R.id.btnVoltar);

//        btnAtestadoMatricula.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//                startActivity(new Intent(RequerimentoActivity.this, Formulario01Activity.class));
//            }
//        });
//
//        btnHistoricoEscolar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//                startActivity(new Intent(RequerimentoActivity.this, Formulario02Activity.class));
//            }
//        });
//
//        btnCertificadoConclusao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//                startActivity(new Intent(RequerimentoActivity.this, Formulario05Activity.class));
//            }
//        });
//
//        btnConsultarRequerimento.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//                startActivity(new Intent(RequerimentoActivity.this, ConsultarRequerimentoActivity.class));
//            }
//        });


//        btnVoltar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//                startActivity(new Intent(RequerimentoActivity.this, MainActivity.class));
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_requerimentos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.req_item1_id) {
            Intent intent = new Intent(RequerimentoActivity.this, Formulario01Activity.class);
            startActivity(intent);
        }
        if (id == R.id.req_item2_id) {
            Intent intent = new Intent(RequerimentoActivity.this, Formulario02Activity.class);
            startActivity(intent);
        }
        if (id == R.id.req_item3_id) {
            Intent intent = new Intent(RequerimentoActivity.this, Formulario05Activity.class);
            startActivity(intent);
        }
        if (id == R.id.req_item4_id) {
            Intent intent = new Intent(RequerimentoActivity.this, ConsultarRequerimentoActivity.class);
            startActivity(intent);
        }
        if (id == R.id.req_item5_id) {
            Intent intent = new Intent(RequerimentoActivity.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

