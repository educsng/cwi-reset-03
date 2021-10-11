public class Filme {

    // Atributos
    private String nome;
    private String descricao;
    private int duracao;
    private int ano;
    private Double avaliacao;
    private Diretor diretor;

    // Construtor


    public Filme(String nome, String descricao, int duracao, int ano, Double avaliacao, Diretor diretor) throws AvaliacaoForaDoPadraoException {
        if (avaliacao < 1 || avaliacao > 5) {
            throw new AvaliacaoForaDoPadraoException();
        }

        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.ano = ano;
        this.diretor = diretor;
        this.avaliacao = avaliacao;

    }

    public void reproduzirFilme() {
        System.out.println("=========Filme=========");
        System.out.println("Nome do filme: " + nome + "\nDescrição: " + descricao + "\nDuração: " + duracao + " minutos" + "\nDiretor: " + diretor.getNome());
    }


}
