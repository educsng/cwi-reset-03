package br.com.cwi.reset.eduardocassanego.request;

import br.com.cwi.reset.eduardocassanego.model.StatusAtividade;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class EstudioRequest {

    // Atributos
    @NotBlank(message = "Campo obrigatório não informado, favor informar o campo nome")
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo nome")
    private String nome;
    @NotBlank(message = "Campo obrigatório não informado, favor informar o campo descrição")
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo descrição")
    private String descricao;
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo data criação")
    private LocalDate dataCriacao;
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo status atividade")
    private StatusAtividade statusAtividade;

    // Construtor
    public EstudioRequest(String nome, String descricao, LocalDate dataCriacao, StatusAtividade statusAtividade) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.statusAtividade = statusAtividade;
    }

    //Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public StatusAtividade getStatusAtividade() {
        return statusAtividade;
    }

    public void setStatusAtividade(StatusAtividade statusAtividade) {
        this.statusAtividade = statusAtividade;
    }
}
