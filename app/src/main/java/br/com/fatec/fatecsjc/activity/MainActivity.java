package br.com.fatec.fatecsjc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import br.com.fatec.fatecsjc.R;

public class MainActivity extends AppCompatActivity {

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        //inicializando firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();
        //user != logged in, current user retorna null
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        //get usuario
        FirebaseUser user = firebaseAuth.getCurrentUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.main_item1_id) {
            Intent intent = new Intent(MainActivity.this, RequerimentoActivity.class);
            startActivity(intent);
        }
        if (id == R.id.main_item2_id) {
            Intent intent = new Intent(MainActivity.this, ManutencaoActivity.class);
            startActivity(intent);
        }
        if (id == R.id.main_item3_id) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        if (id == R.id.main_item4_id) {
            FirebaseMessaging.getInstance().unsubscribeFromTopic("fatec");
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
        // para menu mensagem
//        btnMensagem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, MensagemActivity.class));
//            }
//        });
        return super.onOptionsItemSelected(item);
    }
}