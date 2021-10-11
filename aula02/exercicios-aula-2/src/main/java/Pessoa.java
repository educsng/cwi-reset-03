import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Pessoa {

    private String nome;
    private LocalDate dataNascimento;
    private Genero genero;


    // Construtor

    public Pessoa(String nome, LocalDate dataNascimento, Genero genero) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
    }

    // métodos
    public void imprimeCaracteristicas() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Gênero: " + this.genero.getDescricao());
        System.out.println("Idade: " + this.calcularIdade());
    }

    public long calcularIdade() {
        LocalDate now = LocalDate.now();
        long diferencaEmAnos = ChronoUnit.YEARS.between(this.dataNascimento, now);
        return diferencaEmAnos;
    }



    // Getters
    public Genero getGenero() {
        return genero;
    }

    public String getNome() {
        return nome;
    }
}
