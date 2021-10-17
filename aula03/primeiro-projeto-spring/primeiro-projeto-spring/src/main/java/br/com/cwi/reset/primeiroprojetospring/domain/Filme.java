package br.com.cwi.reset.primeiroprojetospring.domain;

public class Filme {

    // Atributos
    private String nome;
    private String descricao;
    private int duracao;
    private int ano;
    private Double avaliacao;
    private Diretor diretor;

    // Construtor


    public Filme(String nome, String descricao, int duracao, int ano, Double avaliacao, Diretor diretor) {
//        if (avaliacao < 1 || avaliacao > 5) {
//            throw new Exception();
//        }

        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.ano = ano;
        this.diretor = diretor;
        this.avaliacao = avaliacao;

    }

    public void reproduzirFilme() {
        System.out.println("=========Filme=========");
        System.out.println("Nome do filme: " + nome + "\nDescrição: " + descricao + "\nDuração: " + duracao + " minutos" + "\nDiretor: " + diretor.getNome());
    }

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getDuracao() {
        return duracao;
    }

    public int getAno() {
        return ano;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }
}
