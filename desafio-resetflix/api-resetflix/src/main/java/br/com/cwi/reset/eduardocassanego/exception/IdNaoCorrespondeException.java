package br.com.cwi.reset.eduardocassanego.exception;

public class IdNaoCorrespondeException extends Exception {
    public IdNaoCorrespondeException(String objeto, Integer id) {
        super(String.format("Nenhum %s encontrado com o parâmetro '%d', favor verifique os parâmetros informados.", objeto, id));
    }
}
