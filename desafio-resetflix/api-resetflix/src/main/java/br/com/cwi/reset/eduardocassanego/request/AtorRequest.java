package br.com.cwi.reset.eduardocassanego.request;

import br.com.cwi.reset.eduardocassanego.model.StatusCarreira;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class AtorRequest {

    // Atributos
    @NotBlank(message = "Campo obrigatório não informado, favor informar o campo nome.")
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo nome.")
    private String nome;
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo data nascimento.")
    @Past(message = "Não é possível cadastrar atores não nascidos.")
    private LocalDate dataNascimento;
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo status carreira.")
    private StatusCarreira statusCarreira;
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo ano início atividade.")
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

