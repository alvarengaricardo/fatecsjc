package br.com.fatec.fatecsjc.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

import br.com.fatec.fatecsjc.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //views
    private Button btnSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;
    private TextView textViewReset;

    //objetos firebase auth
    private FirebaseAuth firebaseAuth;

    //progress dialog
    private ProgressDialog progressDialog;

    private String origem = "login"; // flag a ser enviado para ativity cadastro complementar informando

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //se objecto getcurrentuser != null
        //usuário já logado
        if (firebaseAuth.getCurrentUser() != null) {
            //fecha activity
            finish();
            //inicia  MainActivity
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        //inicia views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        btnSignIn = (Button) findViewById(R.id.btnSignin);
        textViewSignup = (TextView) findViewById(R.id.textViewSignUp);
        textViewReset = (TextView) findViewById(R.id.textViewReset);

        progressDialog = new ProgressDialog(this);

        //relaciona click listener
        btnSignIn.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
        textViewReset.setOnClickListener(this);
    }

    //metodo para user login
    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        //verifica se email/passwords estão vazios
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Informe seu e-mail", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Informe sua senha", Toast.LENGTH_LONG).show();
            return;
        }

        //exibe progress dialog
        progressDialog.setMessage("Logando, por favor aguarde...");
        progressDialog.show();

        //logging in
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                //se taskissuccessfull
                if (task.isSuccessful()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("fatec");
                    //inicia activity
                    SharedPreferences sharedPreferences = getSharedPreferences("arquivo", Context.MODE_PRIVATE);
                    String nome = sharedPreferences.getString("nome", "");
                    finish();
                    if (nome != "") { // verifica se os dados do usuario existem (celular novo?)
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
                        Intent intent = new Intent(LoginActivity.this, ComplementoActivity.class);
                        intent.putExtra("origem", origem);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "E-mail ou senha inválido!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == btnSignIn) {
            userLogin();
        }

        if (view == textViewSignup) {
            finish();
            startActivity(new Intent(this, CadastroActivity.class));
        }

        if (view == textViewReset) {
            finish();
            startActivity(new Intent(this, ResetActivity.class));
        }
    }
}