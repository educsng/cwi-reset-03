package br.com.cwi.reset.eduardocassanego;

public enum StatusCarreira {
    EM_ATIVIDADE("Em atividade"),
    APOSENTADO("Aposentado");

    private String descricao;

    StatusCarreira(String descricao) {
        this.descricao = descricao;
    }
}
