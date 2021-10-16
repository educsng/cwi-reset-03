package br.com.cwi.reset.eduardocassanego.exception;

public class NomeJaExistenteException extends Exception {
    public NomeJaExistenteException(String nome) {
        super("Já existe um ator cadastrado para o nome " + nome);
    }
}
