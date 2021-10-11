import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Aplicacao {

    public static void adicionaFilme(List<Filme> filmes, Diretor diretor, String nome, String descricao, Integer duracao, Integer ano, Double avaliacao) {
        try {
            Filme filme = new Filme(nome, descricao, duracao, ano, avaliacao, diretor);
            filmes.add(filme);
        } catch (AvaliacaoForaDoPadraoException e) {
            System.out.println("Erro ao adicionar filme " + nome);
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        List<Filme> filmes = new ArrayList<>() {};

        Diretor diretor1 = new Diretor("Christopher Nolan", LocalDate.of(1970, Month.JULY, 30), Genero.MASCULINO, 52);

        adicionaFilme(filmes, diretor1, "Interestelar", "Filme de ficção científica no espaço", 169, 2014, 4.9);
        adicionaFilme(filmes, diretor1, "Tenet", "Filme de ficção científica com paradoxo temporal", 150, 2020, 3.9);
        adicionaFilme(filmes, diretor1, "A Origem", "Filme de ficção científica sobre sonhos", 148, 2010, 4.8);
        adicionaFilme(filmes, diretor1, "Batman: Cavaleiro das Trevas", "Filme baseado nos quadrinhos do Batman", 152, 2008, 4.9);
        adicionaFilme(filmes, diretor1, "Filme fora do padrão", "Filme teste", 152, 2008, 5.1);

        for (Filme f : filmes) {
            f.reproduzirFilme();
        }
        System.out.println("Tamanho da lista: " + filmes.size());















    }

}
