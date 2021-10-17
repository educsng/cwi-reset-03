package br.com.cwi.reset.primeiroprojetospring.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Pessoa {

    private String nome;
    private LocalDate dataNascimento;
    private Genero genero;


    // Construtor

    public Pessoa(String nome, LocalDate dataNascimento, Genero genero) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
    }

    // métodos
    public void imprimeCaracteristicas() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Gênero: " + this.genero.getDescricao());
        System.out.println("Idade: " + this.calcularIdade());
    }

    public long calcularIdade() {
        LocalDate now = LocalDate.now();
        long diferencaEmAnos = ChronoUnit.YEARS.between(this.dataNascimento, now);
        return diferencaEmAnos;
    }



    // Getters e setters
    public Genero getGenero() {
        return genero;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}
