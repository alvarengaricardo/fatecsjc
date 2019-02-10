package br.com.fatec.fatecsjc.model;

/**
 * Created by ricardo on 16/04/2017.
 */

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Usuario {

    public String uid;
    public String ra;
    public String nome;
    public String email;
    public String curso;
    public String status;

    // Construtor Default para chamar DataSnapshot.getValue(User.class)

    public Usuario(String uid, String ra, String nome, String email, String curso, String status) {
        this.uid = uid;
        this.ra = ra;
        this.nome = nome;
        this.email = email;
        this.curso = curso;
        this.status = status;
    }

    public Usuario (String uid, String ra, String nome, String email, String curso){
        this.uid = uid;
        this.ra = ra;
        this.nome = nome;
        this.email = email;
        this.curso = curso;
    }

    public Usuario(){
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}