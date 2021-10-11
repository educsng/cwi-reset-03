public class Filme {

    // Atributos
    private String nome;
    private String descricao;
    private int duracao;
    private int ano;
    private int avaliacao;
    private Diretor diretor;

    // Construtor


    public Filme(String nome, String descricao, int duracao, int ano, int avaliacao, Diretor diretor) throws AvaliacaoForaDoPadraoException {
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.ano = ano;
        this.diretor = diretor;
        this.avaliacao = avaliacao;

        if (avaliacao < 1 || avaliacao > 5) {
            throw new AvaliacaoForaDoPadraoException();
        }


    }

    public void reproduzirFilme() {
        System.out.println("=========Filme=========");
        System.out.println("Nome do filme: " + nome + "\nDescrição: " + descricao + "\nDuração: " + duracao + " minutos" + "\nDiretor: " + diretor.getNome());
    }


}
