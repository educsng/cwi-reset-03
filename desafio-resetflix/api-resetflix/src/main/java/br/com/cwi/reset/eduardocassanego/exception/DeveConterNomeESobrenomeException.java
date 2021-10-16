package br.com.cwi.reset.eduardocassanego.exception;

public class DeveConterNomeESobrenomeException extends Exception {
    public DeveConterNomeESobrenomeException() {
        super("Deve ser informado no mínimo nome e sobrenome para o ator.");
    }
}
