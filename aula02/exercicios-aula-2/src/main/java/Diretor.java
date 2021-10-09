public class Diretor extends Pessoa{

    private Integer numeroFilmes;

    public Diretor(String nome, Integer idade, Genero genero, Integer numeroFilmes) {
        super(nome, idade, genero);
        this.numeroFilmes = numeroFilmes;
    }


}
