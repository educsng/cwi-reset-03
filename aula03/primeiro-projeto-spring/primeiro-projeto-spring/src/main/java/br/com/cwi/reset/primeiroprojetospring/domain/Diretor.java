package br.com.cwi.reset.primeiroprojetospring.domain;

import java.time.LocalDate;

public class Diretor extends Pessoa{

    private Integer numeroFilmes;

    public Diretor(String nome, LocalDate dataNascimento, Genero genero, Integer numeroFilmes) {
        super(nome, dataNascimento, genero);
        this.numeroFilmes = numeroFilmes;
    }

    // Getters e setters
    public Integer getNumeroFilmes() {
        return numeroFilmes;
    }

    public void setNumeroFilmes(Integer numeroFilmes) {
        this.numeroFilmes = numeroFilmes;
    }
}


