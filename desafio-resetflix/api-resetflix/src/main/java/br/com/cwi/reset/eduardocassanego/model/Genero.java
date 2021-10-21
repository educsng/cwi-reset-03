package br.com.cwi.reset.eduardocassanego.model;

public enum Genero {
    ACAO("Ação"),
    AVENTURA("Aventura"),
    COMEDIA("Comédia"),
    DOCUMENTARIO("Documentário"),
    DRAMA("Drama"),
    ESPIONAGEM("Espionagem"),
    FICCAO_CIENTIFICA("Ficção Científica"),
    GUERRA("Guerra"),
    MISTERIO("Mistério"),
    MUSICAL("Musical"),
    POLICIAL("Policial"),
    ROMANCE("Romance"),
    TERROR("Terror");

    private String descricao;

    Genero(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
