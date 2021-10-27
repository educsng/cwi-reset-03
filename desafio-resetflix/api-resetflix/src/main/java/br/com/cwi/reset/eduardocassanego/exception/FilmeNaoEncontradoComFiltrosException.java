package br.com.cwi.reset.eduardocassanego.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FilmeNaoEncontradoComFiltrosException extends Exception {
    public FilmeNaoEncontradoComFiltrosException(String nomeFilme, String nomePersonagem, String nomeDiretor, String nomeAtor) {
        super(String.format("Filme não encontrado com os filtros nomeFilme={%s}, nomeDiretor={%s}, nomePersonagem={%s}, nomeAtor={%s}, favor informar outros filtros", nomeFilme, nomePersonagem, nomeDiretor, nomeAtor));
    }
}
