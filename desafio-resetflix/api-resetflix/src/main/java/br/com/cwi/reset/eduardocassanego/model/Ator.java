package br.com.cwi.reset.eduardocassanego.model;

import br.com.cwi.reset.eduardocassanego.exception.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Ator {

    // Atributos
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private StatusCarreira statusCarreira;
    private Integer anoInicioAtividade;

    // Construtor
    public Ator(Integer id, String nome, LocalDate dataNascimento, StatusCarreira statusCarreira, Integer anoInicioAtividade) {

        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.statusCarreira = statusCarreira;
        this.anoInicioAtividade = anoInicioAtividade;
    }


    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public Integer getId() {
        return id;
    }

    public StatusCarreira getStatusCarreira() {
        return statusCarreira;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Integer getAnoInicioAtividade() {
        return anoInicioAtividade;
    }
}
