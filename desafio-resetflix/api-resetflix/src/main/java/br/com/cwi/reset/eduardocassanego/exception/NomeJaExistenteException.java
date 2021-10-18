package br.com.cwi.reset.eduardocassanego.exception;

public class NomeJaExistenteException extends Exception {
    public NomeJaExistenteException(String objeto, String nome) {
        super(String.format("Já existe um %s cadastrado para o nome %s .", objeto, nome));
    }
}
