package br.com.fatec.fatecsjc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import br.com.fatec.fatecsjc.model.Mensagem;
import br.com.fatec.fatecsjc.model.MensagemList;

public class MensagemActivity extends AppCompatActivity {

    private static final String TAG = "MENSAGEM";


    //view objects
    ListView listViewMensagem;

    // botão voltar
    Button btnVoltar;

    //lista para armazenar todos mensagens do banco firebase
    List<Mensagem> mensagemList;

    //database object
    DatabaseReference databaseMensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, " ********************************************************************************************************************** CONSULTA MENSAGEM");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensagem);

        //referencia ao nó mensagem
        databaseMensagem = FirebaseDatabase.getInstance().getReference("mensagem");

        //getting views
        listViewMensagem = (ListView) findViewById(R.id.listViewMensagem);

        //lista de mensagens
        mensagemList = new ArrayList<>();

        //botão voltar
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(MensagemActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseMensagem.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mensagemList.clear();
                for (DataSnapshot mensagemSnapshot : dataSnapshot.getChildren()) {
                    Mensagem mensagem = mensagemSnapshot.getValue(Mensagem.class);
                    mensagemList.add(mensagem);
                }
                MensagemList adapter = new MensagemList(MensagemActivity.this, mensagemList);
                listViewMensagem.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}