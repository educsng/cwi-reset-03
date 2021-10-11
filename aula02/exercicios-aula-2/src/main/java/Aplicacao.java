import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Aplicacao {
    public static void main(String[] args) {

        List<Filme> filmes = new ArrayList<>() {};

        Diretor diretor1 = new Diretor("Christopher Nolan", LocalDate.of(1970, Month.JULY, 30), Genero.MASCULINO, 52);
        try {
            Filme filme1 = new Filme("Teste", "Qualquer coisa", 120, 2019, 5, diretor1);
            Filme filme2 = new Filme("Teste2", "Outra coisa", 100, 2015, 4, diretor1);
            Filme filme3 = new Filme("Teste3", "Não sei o que escrever", 150, 2011, 2, diretor1);
            Filme filme4 = new Filme("Teste4", "Não sei o que escrever ainda", 190, 2015, 1, diretor1);

            filmes.add(filme1);
            filmes.add(filme2);
            filmes.add(filme3);
            filmes.add(filme4);

            for (Filme f : filmes) {
                f.reproduzirFilme();
            }
        } catch (AvaliacaoForaDoPadraoException e) {
            System.out.println(e.getMessage());
        }













    }

}
