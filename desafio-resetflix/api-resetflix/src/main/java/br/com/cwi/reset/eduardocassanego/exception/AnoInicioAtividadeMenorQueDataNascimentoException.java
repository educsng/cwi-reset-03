package br.com.cwi.reset.eduardocassanego.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AnoInicioAtividadeMenorQueDataNascimentoException extends Exception {
    public AnoInicioAtividadeMenorQueDataNascimentoException(String objeto) {
        super(String.format("Ano início atividade inválido para o %s cadastrado.", objeto));
    }
}
