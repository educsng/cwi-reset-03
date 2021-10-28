package br.com.cwi.reset.eduardocassanego.service;

import br.com.cwi.reset.eduardocassanego.exception.*;
import br.com.cwi.reset.eduardocassanego.model.Estudio;
import br.com.cwi.reset.eduardocassanego.repository.EstudioRepositoryDb;
import br.com.cwi.reset.eduardocassanego.request.EstudioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudioService {

    @Autowired
    private EstudioRepositoryDb estudioRepositoryDb;

    // métodos
    public void criarEstudio(EstudioRequest estudioRequest) throws Exception {

        // verificando a existência de estúdio de mesmo nome
        Estudio estudioJaExistente = estudioRepositoryDb.findByNomeIgnoringCase(estudioRequest.getNome());
        if (estudioJaExistente != null) {
            throw new NomeJaExistenteException("estúdio", estudioRequest.getNome());
        } else {
            estudioRepositoryDb.save(new Estudio(estudioRequest.getNome(), estudioRequest.getDescricao(), estudioRequest.getDataCriacao(), estudioRequest.getStatusAtividade()));
        }
    }

    public List<Estudio> consultarEstudios(String filtroNome) throws FiltroDeObjetoNaoEncontradoException, NenhumEstudioCadastradoException {
        List<Estudio> estudioEncontrado;

        if (estudioRepositoryDb.findAll().isEmpty()) {
            throw new NenhumEstudioCadastradoException();
        }
        if (filtroNome != null) {
            estudioEncontrado = estudioRepositoryDb.findByNomeContainingIgnoringCase(filtroNome);
        } else {
            return estudioRepositoryDb.findAll();
        }
        if (estudioEncontrado.isEmpty()) {
            throw new FiltroDeObjetoNaoEncontradoException("Estúdio", filtroNome);
        }
        return estudioEncontrado;
    }

    public Estudio consultarEstudio(Integer id) throws IdNaoCorrespondeException {
        Estudio estudioEncontrado = estudioRepositoryDb.findById(id).orElse(null);
        if (estudioEncontrado == null) {
            throw new IdNaoCorrespondeException("estúdio", id);
        }
        return estudioEncontrado;
    }

}
