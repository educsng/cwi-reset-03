package br.com.cwi.reset.eduardocassanego.exception;

public class DeveConterNomeESobrenomeException extends Exception {
    public DeveConterNomeESobrenomeException(String objeto) {
        super(String.format("Deve ser informado no mínimo nome e sobrenome para o  %s .", objeto));
    }
}
