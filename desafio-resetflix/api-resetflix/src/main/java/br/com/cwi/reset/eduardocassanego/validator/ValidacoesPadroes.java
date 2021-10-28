package br.com.cwi.reset.eduardocassanego.validator;

import br.com.cwi.reset.eduardocassanego.exception.*;

public class ValidacoesPadroes {

    public void validaNomeESobrenomeAtor(String nomeObjeto) throws DeveConterNomeESobrenomeException {
        if (!nomeObjeto.contains(" ")) {
            throw new DeveConterNomeESobrenomeException("ator");
        }
    }

    public void validaNomeESobrenomeDiretor(String nomeObjeto) throws DeveConterNomeESobrenomeException {
        if (!nomeObjeto.contains(" ")) {
            throw new DeveConterNomeESobrenomeException("diretor");
        }
    }
}
