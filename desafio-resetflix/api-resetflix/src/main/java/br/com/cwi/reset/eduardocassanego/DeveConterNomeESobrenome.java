package br.com.cwi.reset.eduardocassanego;

public class DeveConterNomeESobrenome extends Exception {
    public DeveConterNomeESobrenome() {
        super("Deve ser informado no mínimo nome e sobrenome para o ator.");
    }
}
