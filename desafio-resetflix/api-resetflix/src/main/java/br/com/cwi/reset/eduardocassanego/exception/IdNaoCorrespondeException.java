package br.com.cwi.reset.eduardocassanego.exception;

public class IdNaoCorrespondeException extends Exception {
    public IdNaoCorrespondeException(Integer id) {
        super("Nenhum ator encontrado com o parâmetro '" + id + "', favor verifique os parâmetros informados.'");
    }
}
