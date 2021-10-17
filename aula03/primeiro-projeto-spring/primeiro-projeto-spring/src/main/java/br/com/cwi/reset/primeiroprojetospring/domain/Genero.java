package br.com.cwi.reset.primeiroprojetospring.domain;

public enum Genero {
    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    NAO_BINARIO("Não Binário");

    private String descricao;

    Genero(String descricao) {
        this.descricao = descricao;
    }

    // Getters e setters
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
