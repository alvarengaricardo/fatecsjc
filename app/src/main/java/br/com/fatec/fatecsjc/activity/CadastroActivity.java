package br.com.fatec.fatecsjc.activity;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import br.com.fatec.fatecsjc.R;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener {
    //definição dos objects
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button btnSignup;
    private TextView textViewSignin;
    private ProgressDialog progressDialog;
    //objeto firebaseauth 
    private FirebaseAuth firebaseAuth;
    private String email;
    private String password;
    private String origem = "cadastro"; // flag a ser enviado para ativity cadastro complementar informando
    // a origem da chamada e orientando o fluxo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        //inicializando objeto firebase auth 
        firebaseAuth = FirebaseAuth.getInstance();

        //inicializando views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignin = (TextView) findViewById(R.id.textViewSignin);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        progressDialog = new ProgressDialog(this);
        //vincular listener ao button
        btnSignup.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnSignup) {
            registerUser();
        }
        if (view == textViewSignin) {
            //abrir login activity se informar usuário já cadastrado
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    private void registerUser() {
        //capturar email/password dos edit texts
        email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();
        //verifica se email/passwords estão vazios
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Informe seu e-mail", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Informe sua senha", Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Registrando, por favor aguarde...");
        progressDialog.show();
        //registar novo usuário
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //verifica success
                if (task.isSuccessful()) {
//                    gravarUsuario(email, password);
                    finish();
                    Intent intent = new Intent(CadastroActivity.this, ComplementoActivity.class);
                    intent.putExtra("origem", origem);
                    startActivity(intent);
//                    startActivity(new Intent(getApplicationContext(), ComplementoActivity.class));
                } else {
                    String erroExcecao = "";
                    try {
                        throw task.getException(); // tratamento de erro no cadastro
                    } catch (FirebaseAuthWeakPasswordException e) {
                        erroExcecao = "Digite uma senha mais forte, com mais caracteres e com letras e números!";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erroExcecao = "E-mail inválido! Digite um novo e-mail.";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erroExcecao = "E-mail já cadastrado!";
                    } catch (Exception e) {
                        erroExcecao = "Erro ao efetuar o cadastro!";
                    }
                    Toast.makeText(CadastroActivity.this, "ERRO: " + erroExcecao, Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    private void gravarUsuario(String email, String password) {
        SharedPreferences settings = getSharedPreferences("arquivo", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.commit();
    }
}