package br.com.cwi.reset.eduardocassanego.exception;

public class FiltroDeAtorNaoEncontradoException extends Exception {
    public FiltroDeAtorNaoEncontradoException(String filtro) {
        super("Ator não encontrado com o filtro '" + filtro + "', favor informar outro filtro");
    }
}
