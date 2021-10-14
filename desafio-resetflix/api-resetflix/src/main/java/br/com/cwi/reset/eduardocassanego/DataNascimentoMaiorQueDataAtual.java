package br.com.cwi.reset.eduardocassanego;

public class DataNascimentoMaiorQueDataAtual extends Exception {
    public DataNascimentoMaiorQueDataAtual() {
        super("Não é possível cadastrar atores não nascidos.");
    }
}
