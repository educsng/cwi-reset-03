package br.com.cwi.reset.eduardocassanego.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NenhumObjetoCadastradoException extends Exception {
    public NenhumObjetoCadastradoException(String objeto) {
        super(String.format("Nenhum %s cadastrado, favor cadastar %ses.", objeto, objeto));
    }
}
