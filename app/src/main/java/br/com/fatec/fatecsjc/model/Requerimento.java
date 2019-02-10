package br.com.fatec.fatecsjc.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.fatec.fatecsjc.R;

/**
 * Created by ricardo on 10/09/2017.
 */

public class Requerimento {
    public String key;
    public String ra;
    public String identificacao;
    public String tipo;
    public String complemento;
    public String data;
    public String status;


    public Requerimento() {
    }

    public Requerimento(final Activity activity, EditText rg, EditText telefone, EditText semestre, EditText turno, String complemento, String tipo){
        // recuperar dados dispositivo
        SharedPreferences sharedPreferences = activity.getSharedPreferences("arquivo", Context.MODE_PRIVATE);
        String nome = sharedPreferences.getString("nome", "");
        String ra = sharedPreferences.getString("ra", "");
        String email = sharedPreferences.getString("email", "");
        String curso = sharedPreferences.getString("curso", "");
        // converter / gerar valores
        String mRg = String.valueOf(rg.getText());
        String mTelefone = String.valueOf(telefone.getText());
        String mSemestre = String.valueOf(semestre.getText());
        String mTurno = String.valueOf(turno.getText());
        String status = "requerido";
        // data
        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        // preparar dados
        String identificacao = "Nome: " + nome + " / " + "RG: " + mRg + " / " + "e-mail: " + email + " / " + "Telefone: " + mTelefone + " / " + "Curso: " + curso + " / " + "Semestre: " + mSemestre + " / " + "Turno: " + mTurno;
        //banco de dados
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("requerimentos");
        String tagKey = ref.push().getKey();

        this.key = tagKey;
        this.ra = ra;
        this.identificacao = identificacao;
        this.tipo = tipo;
        this.complemento = complemento;
        this.data = data;
        this.status = status;
    }

    public Requerimento(String key, String ra, String identificacao, String tipo, String complemento, String data, String status) {
        this.key = key;
        this.ra = ra;
        this.identificacao = identificacao;
        this.tipo = tipo;
        this.complemento = complemento;
        this.data = data;
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void GravarRequerimento(final Activity activity, Requerimento requerimento) {
        //banco de dados
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("requerimentos");
        String tagKey = this.getKey();
        ref.child(tagKey).setValue(requerimento).addOnCompleteListener(new OnCompleteListener<Void>() {
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(activity, R.string.sucessoRequerimento, Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(activity, R.string.erroRequerimento, Toast.LENGTH_LONG).show();
                }
            }

        });
    }
}