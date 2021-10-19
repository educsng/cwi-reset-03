package br.com.cwi.reset.eduardocassanego.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IdNaoCorrespondeException extends Exception {
    public IdNaoCorrespondeException(String objeto, Integer id) {
        super(String.format("Nenhum %s encontrado com o parâmetro '%d', favor verifique os parâmetros informados.", objeto, id));
    }
}
