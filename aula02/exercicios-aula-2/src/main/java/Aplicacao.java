public class Aplicacao {
    public static void main(String[] args) {

        Diretor diretor = new Diretor("Christofer Nolan", 52, 20);
        Filme filme = new Filme("Interestelar", "Ficção Científica no espaço", 280, 2018, 5,
                diretor);

        filme.reproduzirFilme();
    }

}
