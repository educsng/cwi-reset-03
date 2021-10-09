public class Pessoa {

    private String nome;
    private Integer idade;
    private Genero genero;
    private Integer numOscarsVencidos;

    // Construtor

    public Pessoa(String nome, Integer idade, Genero genero, Integer numOscarsVencidos) {
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.numOscarsVencidos = numOscarsVencidos;
    }

    // métodos
    public boolean imprimeCaracteristicas() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Idade: " + this.idade);
        System.out.println("Gênero: " + this.genero.getDescricao());
        return false;
    }

    public Genero getGenero() {
        return genero;
    }

    public String getNome() {
        return nome;
    }
}
