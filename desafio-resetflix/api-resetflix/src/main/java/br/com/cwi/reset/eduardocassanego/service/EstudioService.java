package br.com.cwi.reset.eduardocassanego.service;

import br.com.cwi.reset.eduardocassanego.exception.*;
import br.com.cwi.reset.eduardocassanego.model.Estudio;
import br.com.cwi.reset.eduardocassanego.repository.EstudioRepositoryDb;
import br.com.cwi.reset.eduardocassanego.request.EstudioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class EstudioService {

    @Autowired
    private EstudioRepositoryDb estudioRepositoryDb;

    // métodos
    public void criarEstudio(EstudioRequest estudioRequest) throws Exception {

        // verificando a existência de estúdio de mesmo nome
        for (Estudio estudio : estudioRepositoryDb.findAll()) {
            if (estudioRequest.getNome().equalsIgnoreCase(estudio.getNome())) {
                throw new NomeJaExistenteException("estúdio", estudioRequest.getNome());
            }
        }

        // verificando se data de criação é válida
        LocalDate now = LocalDate.now();
        if (estudioRequest.getDataCriacao().isAfter(now)) {
            throw new DataCriacaoEstudioMaiorQueDataAtualException();
        }
        estudioRepositoryDb.save(new Estudio(estudioRequest.getNome(), estudioRequest.getDescricao(), estudioRequest.getDataCriacao(), estudioRequest.getStatusAtividade()));
    }

    public List<Estudio> consultarEstudios(String filtroNome) throws FiltroDeObjetoNaoEncontradoException, NenhumEstudioCadastradoException {

        List<Estudio> estudiosEncontrados = new ArrayList<>();
        boolean verificacao = false;

        if (estudioRepositoryDb.findAll().isEmpty()) {
            throw new NenhumEstudioCadastradoException();
        }

        if (filtroNome != null) {
            for (Estudio estudio : estudioRepositoryDb.findAll()) {
                String verificaNome = estudio.getNome().toLowerCase(Locale.ROOT);
                if (verificaNome.contains(filtroNome.toLowerCase(Locale.ROOT))) {
                    estudiosEncontrados.add(estudio);
                } else {
                    throw new FiltroDeObjetoNaoEncontradoException("Estúdio", filtroNome);
                }
            }
        } else {
            return estudioRepositoryDb.findAll();
        }
        return estudiosEncontrados;
    }

    public Estudio consultarEstudio(Integer id) throws NenhumEstudioCadastradoException, IdNaoCorrespondeException {
        Estudio estudioEncontrado = estudioRepositoryDb.findById(id).orElse(null);
        if (estudioEncontrado == null) {
            throw new IdNaoCorrespondeException("estúdio", id);
        }
        return estudioEncontrado;
    }

}
