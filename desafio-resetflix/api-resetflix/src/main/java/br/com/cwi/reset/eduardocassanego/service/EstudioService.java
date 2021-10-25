package br.com.cwi.reset.eduardocassanego.service;

import br.com.cwi.reset.eduardocassanego.FakeDatabase;
import br.com.cwi.reset.eduardocassanego.exception.*;
import br.com.cwi.reset.eduardocassanego.model.Estudio;
import br.com.cwi.reset.eduardocassanego.repository.EstudioRepositoryDb;
import br.com.cwi.reset.eduardocassanego.request.EstudioRequest;
import br.com.cwi.reset.eduardocassanego.validator.ValidacoesPadroes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class EstudioService {

    @Autowired
    private EstudioRepositoryDb estudioRepositoryDb;

    // Construtor
    public EstudioService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    // métodos
    public void criarEstudio(EstudioRequest estudioRequest) throws Exception {

        // verificando campos obrigatórios
        new ValidacoesPadroes().validaCamposObrigatoriosEstudio(estudioRequest.getNome(), estudioRequest.getDescricao(), estudioRequest.getDataCriacao(), estudioRequest.getStatusAtividade());

        // verificando a existência de estúdio de mesmo nome
        List<Estudio> estudios;
        estudios = fakeDatabase.recuperaEstudios();

        for (Estudio estudio : estudios) {
            if (estudioRequest.getNome().equalsIgnoreCase(estudio.getNome())) {
                throw new NomeJaExistenteException("estúdio", estudioRequest.getNome());
            }
        }

        // verificando se data de criação é válida
        LocalDate now = LocalDate.now();
        if (estudioRequest.getDataCriacao().isAfter(now)) {
            throw new DataCriacaoEstudioMaiorQueDataAtualException();
        }
        Estudio estudio = new Estudio(GeradorIdEstudio.proximoId(), estudioRequest.getNome(), estudioRequest.getDescricao(), estudioRequest.getDataCriacao(), estudioRequest.getStatusAtividade());
        fakeDatabase.persisteEstudio(estudio);
    }

    public List<Estudio> consultarEstudios(String filtroNome) throws FiltroDeObjetoNaoEncontradoException, NenhumEstudioCadastradoException {

        List<Estudio> estudios = fakeDatabase.recuperaEstudios();
        List<Estudio> estudiosEncontrados = new ArrayList<>();
        boolean verificacao = false;

        if (estudios.isEmpty()) {
            throw new NenhumEstudioCadastradoException();
        }

        if (filtroNome != null) {
            for (Estudio estudio : estudios) {
                String verificaNome = estudio.getNome().toLowerCase(Locale.ROOT);
                if (verificaNome.contains(filtroNome.toLowerCase(Locale.ROOT))) {
                    estudiosEncontrados.add(estudio);
                } else {
                    verificacao = true;
                }
            }
            if (verificacao) {
                throw new FiltroDeObjetoNaoEncontradoException("Estúdio", filtroNome);
            }
        }

        return estudiosEncontrados;
    }

    public Estudio consultarEstudioPorID(Integer id) throws
            CampoObrigatorioNaoInformadoException, IdNaoCorrespondeException,
            NenhumEstudioCadastradoException {

        List<Estudio> estudios = fakeDatabase.recuperaEstudios();
        boolean exception = false;

        if (estudios.isEmpty()) {
            throw new NenhumEstudioCadastradoException();
        }

        if (id != null) {
            for (Estudio estudio : estudios) {
                if (!Objects.equals(estudio.getId(), id)) {
                    exception = true;
                } else {
                    return estudio;
                }
            }
        } else {
            throw new CampoObrigatorioNaoInformadoException("id");
        }

        if (exception) {
            throw new IdNaoCorrespondeException("estúdio", id);
        }
        return null;
    }

}
