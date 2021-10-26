package br.com.cwi.reset.eduardocassanego.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class DiretorRequest {

    // Atributos
    @NotBlank(message = "Campo obrigatório não informado, favor informar o campo nome")
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo nome")
    private String nome;
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo data nascimento")
    private LocalDate dataNascimento;
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo ano início atividade")
    private Integer anoInicioAtividade;

    // Construtor
    public DiretorRequest(String nome, LocalDate dataNascimento, Integer anoInicioAtividade) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.anoInicioAtividade = anoInicioAtividade;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getAnoInicioAtividade() {
        return anoInicioAtividade;
    }

    public void setAnoInicioAtividade(Integer anoInicioAtividade) {
        this.anoInicioAtividade = anoInicioAtividade;
    }
}
