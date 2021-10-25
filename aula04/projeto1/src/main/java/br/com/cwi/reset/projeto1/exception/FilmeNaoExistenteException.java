package br.com.cwi.reset.projeto1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FilmeNaoExistenteException extends Exception {
    public FilmeNaoExistenteException(String message) {
        super(message);
    }
}
