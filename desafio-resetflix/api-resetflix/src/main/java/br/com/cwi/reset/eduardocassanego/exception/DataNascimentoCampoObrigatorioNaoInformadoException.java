package br.com.cwi.reset.eduardocassanego.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataNascimentoCampoObrigatorioNaoInformadoException extends CampoObrigatorioNaoInformadoException{
    public DataNascimentoCampoObrigatorioNaoInformadoException() {
        super("data nascimento");
    }
}
