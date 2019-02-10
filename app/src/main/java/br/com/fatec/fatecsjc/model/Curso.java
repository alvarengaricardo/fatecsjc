package br.com.fatec.fatecsjc.model;

/**
 * Created by ricardo on 01/09/2017.
 */

public class Curso {

    private String[] nomeCurso;
    private String[] codCurso;

    public String[] getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String[] nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String[] getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(String[] codCurso) {
        this.codCurso = codCurso;
    }

    public Curso() {

        nomeCurso = new String[] {"Aluno Especial", "Análise e Desenvolvimento de Sistemas",
                "Automação Aeronáutica", "Automação e Manufatura Digital",
                "Banco de Dados", "EAD - Gestão Empresarial", "Estruturas Leves",
                "Gestão da Produção Industrial",
                "Informática: Ênfase em Banco de Dados e Redes de Computadores",
                "Logistica", "Logistica e Transportes", "Manufatura Aeronáutica",
                "Manutenção de Aeronaves", "Projetos de Estruturas Aeronáuticas"};
        codCurso = new String[] {"099", "048", "107", "114", "028", "064", "106", "077", "027",
                "074", "018", "067", "068", "115"};
    }
}
