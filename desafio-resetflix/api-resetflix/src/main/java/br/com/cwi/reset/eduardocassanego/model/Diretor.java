package br.com.cwi.reset.eduardocassanego.model;

import java.time.LocalDate;

public class Diretor {

    //Atributos
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private Integer anoInicioAtividade;

    // Construtor
    public Diretor(Integer id, String nome, LocalDate dataNascimento, Integer anoInicioAtividade) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.anoInicioAtividade = anoInicioAtividade;
    }

    //Demais métodos


    //Getters


}
