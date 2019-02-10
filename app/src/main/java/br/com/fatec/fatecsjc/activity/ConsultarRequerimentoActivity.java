package br.com.fatec.fatecsjc.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.fatec.fatecsjc.R;
import br.com.fatec.fatecsjc.model.Requerimento;
import br.com.fatec.fatecsjc.model.RequerimentoList;

public class ConsultarRequerimentoActivity extends AppCompatActivity {

    //view objects
    ListView listViewRequerimento;

    // botão voltar
    Button btnVoltar;

    //lista para armazenar banco firebase
    List<Requerimento> requerimentoList;

    //database object
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_requerimento);

        //getting views
        listViewRequerimento = (ListView) findViewById(R.id.listViewRequerimento);

        //lista de mensagens
        requerimentoList = new ArrayList<>();

        //botão voltar
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(ConsultarRequerimentoActivity.this, RequerimentoActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // recupera RA do dispositivo
        SharedPreferences sharedPreferences = getSharedPreferences("arquivo", Context.MODE_PRIVATE);
        String ra = sharedPreferences.getString("ra", "");
        //referencia ao nó requerimento
        ref = FirebaseDatabase.getInstance().getReference("requerimentos");
        ref.orderByChild("ra").equalTo(ra).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                requerimentoList.clear();
                for (DataSnapshot requerimentoSnapshot : dataSnapshot.getChildren()) {
                    Requerimento requerimento = requerimentoSnapshot.getValue(Requerimento.class);
                        requerimentoList.add(requerimento);
                }
                RequerimentoList adapter = new RequerimentoList(ConsultarRequerimentoActivity.this, requerimentoList);
                listViewRequerimento.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}