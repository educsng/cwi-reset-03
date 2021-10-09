public class Aplicacao {
    public static void main(String[] args) {

        Diretor diretor = new Diretor("Christofer Nolan", 52, 20, Genero.MASCULINO);
        Diretor diretor2 = new Diretor("Martin Scorsese", 60, 40, Genero.MASCULINO);
        Filme filme1 = new Filme("Interestelar", "Ficção Científica no espaço", 280, 2018, 5, diretor);
        Filme filme2 = new Filme("Ilha do Medo", "Suspense", 200, 2010, 5, diretor2);

        filme1.reproduzirFilme();
        //diretor.imprimeCaracteristicas();
        System.out.println("==================");
        filme2.reproduzirFilme();
        //diretor.imprimeCaracteristicas();
    }

}
