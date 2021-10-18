package br.com.cwi.reset.eduardocassanego.exception;

public class NenhumObjetoCadastradoException extends Exception {
    public NenhumObjetoCadastradoException(String objeto) {
        super(String.format("Nenhum %s cadastrado, favor cadastar %ss.", objeto, objeto));
    }
}
