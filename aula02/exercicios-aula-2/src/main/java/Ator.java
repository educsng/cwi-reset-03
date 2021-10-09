public class Ator extends Pessoa {

    private Integer numOscarsVencidos;

    public Ator(String nome, Integer idade, Genero genero, Integer numOscarsVencidos) {
        super(nome, idade, genero);
        this.numOscarsVencidos = numOscarsVencidos;
    }

}
