package br.com.cwi.reset.eduardocassanego;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AtorRequest {

    // Atributos
    private String nome;
    private LocalDate dataNascimento;
    private StatusCarreira statusCarreira;
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

}

