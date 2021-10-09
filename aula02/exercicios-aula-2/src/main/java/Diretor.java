public class Diretor {
    public String nome;
    private int idade;
    private int quantidadeDeFilmes;
    private Genero genero;

    // Construtor
    public Diretor(String nome, int idade, int quantidadeDeFilmes, Genero genero) {
        this.nome = nome;
        this.idade = idade;
        this.quantidadeDeFilmes = quantidadeDeFilmes;
        this.genero = genero;
    }

    // métodos
    public void imprimeCaracteristicas() {
        System.out.println(this.genero.getDescricao());
    }
}
