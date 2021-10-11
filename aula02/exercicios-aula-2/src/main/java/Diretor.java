import java.time.LocalDate;

public class Diretor extends Pessoa{

    private Integer numeroFilmes;

    public Diretor(String nome, LocalDate dataNascimento, Genero genero, Integer numeroFilmes) {
        super(nome, dataNascimento, genero);
        this.numeroFilmes = numeroFilmes;
    }


}
