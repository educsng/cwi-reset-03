package br.com.cwi.reset.eduardocassanego.exception;

public class DataNascimentoMaiorQueDataAtualException extends Exception {
    public DataNascimentoMaiorQueDataAtualException() {
        super("Não é possível cadastrar atores não nascidos.");
    }
}
