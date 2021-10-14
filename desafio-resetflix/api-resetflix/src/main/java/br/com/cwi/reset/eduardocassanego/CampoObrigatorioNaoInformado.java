package br.com.cwi.reset.eduardocassanego;

public class CampoObrigatorioNaoInformado extends Exception {
    public CampoObrigatorioNaoInformado() {
        super("Campo obrigatório não informado. Favor informar o campo " );
    }
}
