package br.com.cwi.reset.eduardocassanego.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DeveConterNomeESobrenomeException extends Exception {
    public DeveConterNomeESobrenomeException(String objeto) {
        super(String.format("Deve ser informado no mínimo nome e sobrenome para o %s.", objeto));
    }
}
