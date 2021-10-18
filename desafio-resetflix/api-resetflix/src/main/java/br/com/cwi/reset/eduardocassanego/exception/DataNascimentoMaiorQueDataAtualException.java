package br.com.cwi.reset.eduardocassanego.exception;

public class DataNascimentoMaiorQueDataAtualException extends Exception {
    public DataNascimentoMaiorQueDataAtualException(String objeto) {
        super(String.format("Não é possível cadastrar %s não nascidos.", objeto));
    }
}
