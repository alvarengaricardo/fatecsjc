package br.com.fatec.fatecsjc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import br.com.fatec.fatecsjc.R;

/**
 * Created by ricardo on 07/09/2017.
 */

public class ResetActivity extends AppCompatActivity {

    private Button btnReset;
    private Button btnVoltar;
    private EditText editTextEmail;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        btnReset = (Button) findViewById(R.id.btnReset);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        firebaseAuth = FirebaseAuth.getInstance();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.sendPasswordResetEmail(editTextEmail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    editTextEmail.setText(""); // limpar o campo
                                    Toast.makeText(ResetActivity.this, R.string.sucessoEmail, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ResetActivity.this, R.string.erroEmail, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResetActivity.this, LoginActivity.class));
            }
        });
    }
}