package br.com.cwi.reset.eduardocassanego.service;

import br.com.cwi.reset.eduardocassanego.*;
import br.com.cwi.reset.eduardocassanego.exception.*;
import br.com.cwi.reset.eduardocassanego.model.Ator;
import br.com.cwi.reset.eduardocassanego.model.GeradorIdAtor;
import br.com.cwi.reset.eduardocassanego.model.StatusCarreira;
import br.com.cwi.reset.eduardocassanego.request.AtorRequest;
import br.com.cwi.reset.eduardocassanego.response.AtorEmAtividade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class AtorService {

    // Atributos
    private FakeDatabase fakeDatabase;

    // Construtor padrão
    public AtorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }


    // Demais métodos da classe
    public void criarAtor(AtorRequest atorRequest) throws
            CampoObrigatorioNaoInformadoException,
            DeveConterNomeESobrenomeException, DataNascimentoMaiorQueDataAtualException,
            AnoInicioAtividadeMenorQueDataAtualException,
            NomeJaExistenteException {

        // Campos obrigatórios
        verificaCampoObrigatorio(atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());

        // Nome e sobrenome
        if (!atorRequest.getNome().contains(" ")) {
            throw new DeveConterNomeESobrenomeException();
        }

        // Data de nascimento menor que data atual
        LocalDate now = LocalDate.now();
        if (now.isBefore(atorRequest.getDataNascimento())) {
            throw new DataNascimentoMaiorQueDataAtualException();
        }

        // Ano inicio atividade
        Integer anoNascimentoAtor = atorRequest.getDataNascimento().getYear();
        if (atorRequest.getAnoInicioAtividade() < anoNascimentoAtor) {
            throw new AnoInicioAtividadeMenorQueDataAtualException();
        }

        // Ator de mesmo nome
        List<Ator> atores;
        atores = fakeDatabase.recuperaAtores();

        for (Ator ator : atores) {
            if (ator.getNome().equalsIgnoreCase(atorRequest.getNome())) {
                throw new NomeJaExistenteException(atorRequest.getNome());
            }
        }

        // Após todas as verificações, instancia o objeto Ator e persiste na fakedatabase
        Ator ator = new Ator(GeradorIdAtor.proximoId(), atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());
        fakeDatabase.persisteAtor(ator);
        System.out.println("Ator '" + ator.getNome() + "' adicionado com sucesso.");
    }

    public List<AtorEmAtividade> listarAtoresEmAtividade(String filtroNome) throws NenhumAtorCadastradoException,
            FiltroDeAtorNaoEncontradoException {
        List<AtorEmAtividade> retorno = new ArrayList<>();

        if (verificaAtorBancoDeDadosVazio(fakeDatabase.recuperaAtores())) {
            throw new NenhumAtorCadastradoException();
        }

        if (filtroNome != null) {
            for (Ator ator : fakeDatabase.recuperaAtores()) {
                String verificaNome = ator.getNome().toLowerCase(Locale.ROOT);
                if (verificaNome.contains(filtroNome.toLowerCase(Locale.ROOT))) {
                    if (ator.getStatusCarreira().equals(StatusCarreira.EM_ATIVIDADE)) {
                        AtorEmAtividade atorEmAtividade1 = new AtorEmAtividade(ator.getId(), ator.getNome(), ator.getDataNascimento());
                        retorno.add(atorEmAtividade1);
                    }
                } else {
                    throw new FiltroDeAtorNaoEncontradoException(filtroNome);
                }
            }

        }
        return retorno;
    }


    public Ator consultarAtor(Integer id) throws IdNaoCorrespondeException, CampoObrigatorioNaoInformadoException {
        List<Ator> atores = fakeDatabase.recuperaAtores();
        boolean exception = false;

        if (id != null) {
            for (Ator ator : atores) {
                if (!Objects.equals(ator.getId(), id)) {
                    exception = true;
                } else {
                    return ator;
                }
            }
        } else {
            throw new CampoObrigatorioNaoInformadoException("id");
        }

        if (exception) { throw new IdNaoCorrespondeException(id); }
        return null;
    }


    public List<Ator> consultarAtores() throws NenhumAtorCadastradoException {
        List<Ator> atoresConsultados = fakeDatabase.recuperaAtores();
        if (verificaAtorBancoDeDadosVazio(atoresConsultados)) {
            throw new NenhumAtorCadastradoException();
        } else {
            return atoresConsultados;
        }
    }



    //    Outros métodos
    public void verificaCampoObrigatorio(String nome, LocalDate dataNascimento, StatusCarreira statusCarreira, Integer anoInicioAtividade) throws
            NomeCampoObrigatorioNaoInformadoException,
            DataNascimentoCampoObrigatorioNaoInformadoException,
            StatusCarreiraCampoObrigatorioNaoInformadoException,
            AnoInicioAtividadeCampoObrigatorioNaoInformadoException {

        if (verificaCampoObrigatorioNome(nome)) {
            throw new NomeCampoObrigatorioNaoInformadoException();
        }
        if (verificaCampoObrigatorioDataNascimento(dataNascimento)) {
            throw new DataNascimentoCampoObrigatorioNaoInformadoException();
        }
        if (verificaCampoObrigatorioStatus(statusCarreira)) {
            throw new StatusCarreiraCampoObrigatorioNaoInformadoException();
        }
        if (verificaCampoObrigatorioAnoInicioAtividade(anoInicioAtividade)) {
            throw new AnoInicioAtividadeCampoObrigatorioNaoInformadoException();
        }
    }

    public boolean verificaCampoObrigatorioNome(String campo) {
        return campo.isEmpty();
    }
    public boolean verificaCampoObrigatorioDataNascimento(LocalDate campo) {
        return campo == null;
    }
    public boolean verificaCampoObrigatorioStatus(StatusCarreira campo) {
        return campo == null;
    }
    public boolean verificaCampoObrigatorioAnoInicioAtividade(Integer campo) {
        return campo == null;
    }
    public boolean verificaAtorBancoDeDadosVazio(List<Ator> atores) {return atores.isEmpty();}
}
