package br.com.cwi.reset.eduardocassanego.service;

import br.com.cwi.reset.eduardocassanego.FakeDatabase;
import br.com.cwi.reset.eduardocassanego.exception.*;
import br.com.cwi.reset.eduardocassanego.model.Estudio;
import br.com.cwi.reset.eduardocassanego.model.GeradorIdEstudio;
import br.com.cwi.reset.eduardocassanego.model.StatusAtividade;
import br.com.cwi.reset.eduardocassanego.request.EstudioRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class EstudioService {

    // Atributos
    private FakeDatabase fakeDatabase;

    // Construtor
    public EstudioService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    // métodos
    public void criarEstudio(EstudioRequest estudioRequest) throws Exception {
        LocalDate now = LocalDate.now();
        verificaCampoObrigatorio(estudioRequest.getNome(), estudioRequest.getDescricao(), estudioRequest.getDataCriacao(), estudioRequest.getStatusAtividade());
        List<Estudio> estudios;
        estudios = fakeDatabase.recuperaEstudios();

        for (Estudio estudio : estudios) {
            if (estudioRequest.getNome().equalsIgnoreCase(estudio.getNome())) {
                throw new NomeJaExistenteException("estúdio", estudioRequest.getNome());
            }
        }

        if (estudioRequest.getDataCriacao().isAfter(now)) {
            throw new DataCriacaoEstudioMaiorQueDataAtualException();
        }
        Estudio estudio = new Estudio(GeradorIdEstudio.proximoId(), estudioRequest.getNome(), estudioRequest.getDescricao(), estudioRequest.getDataCriacao(), estudioRequest.getStatusAtividade());
        fakeDatabase.persisteEstudio(estudio);
    }

    public List<Estudio> consultarEstudios(String filtroNome) throws
            FiltroDeObjetoNaoEncontradoException, NenhumEstudioCadastradoException {

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


    // métodos auxiliares
    public void verificaCampoObrigatorio(String nome, String descricao, LocalDate dataCriacao, StatusAtividade statusAtividade) throws
            NomeCampoObrigatorioNaoInformadoException,
            DescricaoCampoObrigatorioNaoInformadoException,
            DataCriacaoCampoObrigatorioNaoInformadoException,
            StatusAtividadeCampoObrigatorioNaoInformadoException {

        if (verificaCampoObrigatorioNome(nome)) {
            throw new NomeCampoObrigatorioNaoInformadoException();
        }
        if (verificaCampoObrigatorioDescricao(descricao)) {
            throw new DescricaoCampoObrigatorioNaoInformadoException();
        }
        if (verificaCampoObrigatorioDataCriacao(dataCriacao)) {
            throw new DataCriacaoCampoObrigatorioNaoInformadoException();
        }
        if (verificaCampoObrigatorioStatusAtividade(statusAtividade)) {
            throw new StatusAtividadeCampoObrigatorioNaoInformadoException();
        }
    }

    public boolean verificaCampoObrigatorioNome(String campo) {
        return campo == null;
    }
    public boolean verificaCampoObrigatorioDescricao(String campo) {
        return campo == null;
    }
    public boolean verificaCampoObrigatorioDataCriacao(LocalDate campo) {
        return campo == null;
    }
    public boolean verificaCampoObrigatorioStatusAtividade(StatusAtividade campo) {
        return campo == null;
    }
}
