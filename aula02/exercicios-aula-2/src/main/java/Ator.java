public class Ator {
    private String nome;
    private Integer idade;
    private Integer numOscarsVencidos;
    private Genero genero;

    // métodos
    public void imprimeCaracteristicas() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Idade: " + this.idade);
        System.out.println("Gênero: " + this.genero.getDescricao());
    }

}
