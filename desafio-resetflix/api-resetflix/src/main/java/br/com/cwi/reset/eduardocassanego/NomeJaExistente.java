package br.com.cwi.reset.eduardocassanego;

public class NomeJaExistente extends Exception {
    public NomeJaExistente() {
        super("Nome já existente");
    }
}
