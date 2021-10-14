package br.com.cwi.reset.eduardocassanego;

public class FiltroDeAtorNaoEncontrado extends Exception {
    public FiltroDeAtorNaoEncontrado(String filtro) {
        super("Ator não encontrado com o filtro '" + filtro + "', favor informar outro filtro");
    }
}
