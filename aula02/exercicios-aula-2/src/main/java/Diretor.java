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
        System.out.println("Nome: " + this.nome);
        System.out.println("Idade: " + this.idade);
        System.out.println("Gênero: " + this.genero.getDescricao());
    }
}
