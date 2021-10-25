package br.com.cwi.reset.eduardocassanego.service;

import br.com.cwi.reset.eduardocassanego.*;
import br.com.cwi.reset.eduardocassanego.exception.*;
import br.com.cwi.reset.eduardocassanego.model.Ator;
import br.com.cwi.reset.eduardocassanego.model.GeradorIdAtor;
import br.com.cwi.reset.eduardocassanego.model.StatusCarreira;
import br.com.cwi.reset.eduardocassanego.request.AtorRequest;
import br.com.cwi.reset.eduardocassanego.response.AtorEmAtividade;
import br.com.cwi.reset.eduardocassanego.validator.ValidacoesPadroes;

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

        // VERIFICAÇÕES
        new ValidacoesPadroes().validaCamposObrigatoriosAtor(atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());
        new ValidacoesPadroes().validaNomeESobrenomeAtor(atorRequest.getNome());

        // Data de nascimento menor que data atual
        LocalDate now = LocalDate.now();
        if (now.isBefore(atorRequest.getDataNascimento())) {
            throw new DataNascimentoMaiorQueDataAtualException("atores");
        }
        // Ano inicio atividade
        Integer anoNascimentoAtor = atorRequest.getDataNascimento().getYear();
        if (atorRequest.getAnoInicioAtividade() < anoNascimentoAtor) {
            throw new AnoInicioAtividadeMenorQueDataAtualException("ator");
        }

        // Ator de mesmo nome
        List<Ator> atores;
        atores = fakeDatabase.recuperaAtores();

        for (Ator ator : atores) {
            if (ator.getNome().equalsIgnoreCase(atorRequest.getNome())) {
                throw new NomeJaExistenteException("ator", atorRequest.getNome());
            }
        }

        // Após todas as verificações, instancia o objeto Ator e persiste na fakedatabase
        Ator ator = new Ator(GeradorIdAtor.proximoId(), atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());
        fakeDatabase.persisteAtor(ator);
    }

    public List<AtorEmAtividade> listarAtoresEmAtividade(String filtroNome) throws NenhumObjetoCadastradoException,
            FiltroDeObjetoNaoEncontradoException {
        List<AtorEmAtividade> retorno = new ArrayList<>();

        if (verificaAtorBancoDeDadosVazio(fakeDatabase.recuperaAtores())) {
            throw new NenhumObjetoCadastradoException("ator");
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
                    throw new FiltroDeObjetoNaoEncontradoException("Ator", filtroNome);
                }
            }

        }
        return retorno;
    }


    public Ator consultarAtor(Integer id) throws
            IdNaoCorrespondeException,
            CampoObrigatorioNaoInformadoException {

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

        if (exception) { throw new IdNaoCorrespondeException("ator", id); }
        return null;
    }


    public List<Ator> consultarAtores() throws NenhumObjetoCadastradoException {
        List<Ator> atoresConsultados = fakeDatabase.recuperaAtores();
        if (verificaAtorBancoDeDadosVazio(atoresConsultados)) {
            throw new NenhumObjetoCadastradoException("ator");
        } else {
            return atoresConsultados;
        }
    }



    // Métodos auxiliares
    public boolean verificaAtorBancoDeDadosVazio(List<Ator> atores) {return atores.isEmpty();}
}
