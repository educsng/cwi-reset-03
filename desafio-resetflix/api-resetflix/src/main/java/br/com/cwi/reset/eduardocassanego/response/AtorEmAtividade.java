package br.com.cwi.reset.eduardocassanego.response;

import java.time.LocalDate;

public class AtorEmAtividade {

    // Atributos
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;

    // Construtor

    public AtorEmAtividade(Integer id, String nome, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    // Outros métodos


    // Getters


    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}
