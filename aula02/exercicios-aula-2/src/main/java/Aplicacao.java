public class Aplicacao {
    public static void main(String[] args) {

        Diretor diretor1 = new Diretor("Christofer Nolan", 52, Genero.MASCULINO, 2);
        Ator ator1 = new Ator("Brad Pitt", 42, Genero.MASCULINO, 2);



        diretor1.imprimeCaracteristicas();
        ator1.imprimeCaracteristicas();



    }

}
