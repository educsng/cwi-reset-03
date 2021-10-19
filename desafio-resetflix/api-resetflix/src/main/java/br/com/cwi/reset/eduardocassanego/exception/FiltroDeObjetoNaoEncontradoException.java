package br.com.cwi.reset.eduardocassanego.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FiltroDeObjetoNaoEncontradoException extends Exception {
    public FiltroDeObjetoNaoEncontradoException(String objeto, String filtro) {
        super(String.format("%s não encontrado com o filtro '%s', favor informar outro filtro", objeto, filtro));
    }
}
