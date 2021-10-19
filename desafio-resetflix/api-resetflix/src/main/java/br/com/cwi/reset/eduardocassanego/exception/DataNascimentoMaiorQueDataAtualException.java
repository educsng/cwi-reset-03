package br.com.cwi.reset.eduardocassanego.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataNascimentoMaiorQueDataAtualException extends Exception {
    public DataNascimentoMaiorQueDataAtualException(String objeto) {
        super(String.format("Não é possível cadastrar %s não nascidos.", objeto));
    }
}
