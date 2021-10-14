package br.com.cwi.reset.eduardocassanego;

public class IdNaoCorresponde extends Exception {
    public IdNaoCorresponde(Integer id) {
        super("Nenhum ator encontrado com o parâmetro '" + id + "', favor verifique os parâmetros informados.'");
    }
}
