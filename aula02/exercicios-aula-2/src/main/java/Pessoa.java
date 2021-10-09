public class Pessoa {

    private String nome;
    private Integer idade;
    private Genero genero;

    // Construtor

    public Pessoa(String nome, Integer idade, Genero genero) {
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
    }

    // métodos
    public void imprimeCaracteristicas() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Idade: " + this.idade);
        System.out.println("Gênero: " + this.genero.getDescricao());
    }

    public Genero getGenero() {
        return genero;
    }

    public String getNome() {
        return nome;
    }
}
