package br.com.cwi.reset.eduardocassanego.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NomeJaExistenteException extends Exception {
    public NomeJaExistenteException(String objeto, String nome) {
        super(String.format("Já existe um %s cadastrado para o nome %s.", objeto, nome));
    }
}
