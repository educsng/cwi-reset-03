package br.com.cwi.reset.eduardocassanego.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NumeroDeGeneroInvalidoException extends Exception {
    public NumeroDeGeneroInvalidoException() {
        super("Deve ser informado pelo menos um gênero para o cadastro do filme.");
    }
}
