package br.com.cwi.reset.eduardocassanego.exception;

public class FiltroDeObjetoNaoEncontradoException extends Exception {
    public FiltroDeObjetoNaoEncontradoException(String objeto, String filtro) {
        super(String.format("%s não encontrado com o filtro '%s', favor informar outro filtro", objeto, filtro));
    }
}
