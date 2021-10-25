package br.com.cwi.reset.eduardocassanego.request;

import br.com.cwi.reset.eduardocassanego.model.StatusCarreira;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AtorRequest {

    // Atributos
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo nome")
    private String nome;
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo data nascimento")
    private LocalDate dataNascimento;
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo status carreira")
    private StatusCarreira statusCarreira;
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo ano início atividade")
    private Integer anoInicioAtividade;

    // Construtor padrão
    public AtorRequest(String nome, LocalDate dataNascimento, StatusCarreira statusCarreira, Integer anoInicioAtividade) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.statusCarreira = statusCarreira;
        this.anoInicioAtividade = anoInicioAtividade;
    }


    // Getters
    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public StatusCarreira getStatusCarreira() {
        return statusCarreira;
    }

    public Integer getAnoInicioAtividade() {
        return anoInicioAtividade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setStatusCarreira(StatusCarreira statusCarreira) {
        this.statusCarreira = statusCarreira;
    }

    public void setAnoInicioAtividade(Integer anoInicioAtividade) {
        this.anoInicioAtividade = anoInicioAtividade;
    }
}

