public class Ator {
    private String nome;
    private Integer idade;
    private Integer numOscarsVencidos;
    private Genero genero;

    // métodos
    public void imprimeCaracteristicas() {
        System.out.println(this.genero.getDescricao());
    }

}
