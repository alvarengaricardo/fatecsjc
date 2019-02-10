package br.com.fatec.fatecsjc.model;

/**
 * Created by ricardo on 23/04/2017.
 */

public class Mensagem {

    private String tituloMensagem;
    private String corpoMensagem;

    public String getTituloMensagem() {
        return tituloMensagem;
    }

    public void setTituloMensagem(String tituloMensagem) {
        this.tituloMensagem = tituloMensagem;
    }

    public String getCorpoMensagem() {
        return corpoMensagem;
    }

    public void setCorpoMensagem(String corpoMensagem) {
        this.corpoMensagem = corpoMensagem;
    }

    public Mensagem() {
    }

    public Mensagem(String tituloMensagem, String corpoMensagem) {
        this.tituloMensagem = tituloMensagem;
        this.corpoMensagem = corpoMensagem;
    }
}