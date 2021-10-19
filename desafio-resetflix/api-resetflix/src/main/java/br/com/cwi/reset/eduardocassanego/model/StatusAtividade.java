package br.com.cwi.reset.eduardocassanego.model;

public enum StatusAtividade {
    EM_ATIVIDADE("Em atividade"),
    ENCERRADO("Encerrado");

    private String descricao;

    StatusAtividade(String descricao) {
        this.descricao = descricao;
    }
}
