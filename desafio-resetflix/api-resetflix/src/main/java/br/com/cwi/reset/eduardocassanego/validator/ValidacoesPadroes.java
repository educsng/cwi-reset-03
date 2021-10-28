package br.com.cwi.reset.eduardocassanego.validator;

import br.com.cwi.reset.eduardocassanego.exception.*;
import br.com.cwi.reset.eduardocassanego.model.Ator;

import java.util.List;
import java.util.Objects;

public class ValidacoesPadroes {

    public void validaAnoInicioAtividade(String entidade, Integer anoInicioAtividade, Integer anoNascimento) throws AnoInicioAtividadeMenorQueDataNascimentoException {
        if (anoInicioAtividade < anoNascimento) {
            throw new AnoInicioAtividadeMenorQueDataNascimentoException(entidade);
        }
    }

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
