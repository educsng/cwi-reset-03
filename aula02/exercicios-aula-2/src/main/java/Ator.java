import java.time.LocalDate;

public class Ator extends Pessoa {

    private Integer numOscarsVencidos;

    public Ator(String nome, LocalDate dataNascimento, Genero genero, Integer numOscarsVencidos) {
        super(nome, dataNascimento, genero);
        this.numOscarsVencidos = numOscarsVencidos;
    }

}
