package br.com.fatec.fatecsjc.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

import br.com.fatec.fatecsjc.R;

/**
 * Created by ricardo on 03/09/2017.
 */

public class ManutencaoActivity extends AppCompatActivity {

    private AlertDialog.Builder dialog;
    private String origem = "manutencao";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

//        btnComplemento = (Button) findViewById(R.id.btnComplemento);
//        btnMeusDados = (Button) findViewById(R.id.btnMeusDados);
//        btnExcluirUsuario = (Button) findViewById(R.id.btnExcluirUsuario);
//        btnVoltar = (Button) findViewById(R.id.btnVoltar);

//        btnMeusDados.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ManutencaoActivity.this, ConsultarDadosActivity.class));
//            }
//        });
//
//        btnComplemento.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ManutencaoActivity.this, ComplementoActivity.class);
//                intent.putExtra("origem", origem);
//                startActivity(intent);
//            }
//        });

//        btnExcluirUsuario.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog = new AlertDialog.Builder(ManutencaoActivity.this);
//                dialog.setTitle("Excluir Usuário");
//                dialog.setMessage("Confirma a exclusão da conta de usuário?");
//                dialog.setCancelable(false);
//                dialog.setIcon(android.R.drawable.ic_delete); // seleciona icones nativos do android
//                // botao negativo
//                dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                });
//
//                // botao positivo
//                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // remover usuário do tópico
//                        FirebaseMessaging.getInstance().unsubscribeFromTopic("fatec");
//
//                        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//                        // Caminho da lista de usuarios
//                        //  /usuarios/código-uui
//                        DatabaseReference usuario = FirebaseDatabase.getInstance().getReference()
//                                .child("usuarios").child(user.getUid());
//
//                        // Cria o hashmap, estrutura de chave e valor
//                        //
//                        Map<String, Object> map = new HashMap<>();
//                        map.put("status", "excluido");
//
//                        // Atualiza no firebase database
//                        //
//                        usuario.updateChildren(map);
//
//                        // remover dados do dispositivo
//                        SharedPreferences settings = getSharedPreferences("arquivo", 0);
//                        SharedPreferences.Editor editor = settings.edit();
//                        editor.clear();
//                        editor.commit();
//
//                        //
//                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                Toast.makeText(ManutencaoActivity.this, "Usuário Excluído!", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(ManutencaoActivity.this, LoginActivity.class));
//                            }
//                        });
//                    }
//                });
//                dialog.create();
//                dialog.show();
//            }
//        });

//        btnVoltar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ManutencaoActivity.this, MainActivity.class));
//            }
//        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_perfil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.perfil_item1_id) {
            Intent intent = new Intent(ManutencaoActivity.this, ConsultarDadosActivity.class);
            startActivity(intent);
        }
        if (id == R.id.perfil_item2_id) {
            Intent intent = new Intent(ManutencaoActivity.this, ComplementoActivity.class);
            intent.putExtra("origem", origem);
            startActivity(intent);
        }
        if (id == R.id.perfil_item3_id) {
            dialog = new AlertDialog.Builder(ManutencaoActivity.this);
            dialog.setTitle("Excluir Usuário");
            dialog.setMessage("Confirma a exclusão da conta de usuário?");
            dialog.setCancelable(false);
            dialog.setIcon(android.R.drawable.ic_delete); // seleciona icones nativos do android
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
                    // remover usuário do tópico
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("fatec");

                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    // Caminho da lista de usuarios
                    //  /usuarios/código-uui
                    DatabaseReference usuario = FirebaseDatabase.getInstance().getReference()
                            .child("usuarios").child(user.getUid());

                    // Cria o hashmap, estrutura de chave e valor
                    //
                    Map<String, Object> map = new HashMap<>();
                    map.put("status", "excluido");

                    // Atualiza no firebase database
                    //
                    usuario.updateChildren(map);

                    // remover dados do dispositivo
                    SharedPreferences settings = getSharedPreferences("arquivo", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.clear();
                    editor.commit();

                    //
                    user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(ManutencaoActivity.this, "Usuário Excluído!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ManutencaoActivity.this, LoginActivity.class));
                        }
                    });
                }
            });
            dialog.create();
            dialog.show();

        }
        if (id == R.id.perfil_item4_id) {
            Intent intent = new Intent(ManutencaoActivity.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}