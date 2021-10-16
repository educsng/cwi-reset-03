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

public class AtorService {

    // Atributos
    private FakeDatabase fakeDatabase;

    // Construtor padrão
    public AtorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }


    // Demais métodos da classe
    public void criarAtor(AtorRequest atorRequest) throws CampoObrigatorioNaoInformadoException,
            DeveConterNomeESobrenomeException, DataNascimentoMaiorQueDataAtualException, AnoInicioAtividadeMenorQueDataAtualException,
            NomeJaExistenteException {

        // Campo obrigatório
        if (atorRequest.getNome().isEmpty()) {
            throw new CampoObrigatorioNaoInformadoException("nome");
        }
        if (atorRequest.getDataNascimento() == null) {
            throw new CampoObrigatorioNaoInformadoException("data nascimento");
        }
        if (atorRequest.getStatusCarreira() == null) {
            throw new CampoObrigatorioNaoInformadoException("status carreira");
        }
        if (atorRequest.getAnoInicioAtividade() == null) {
            throw new CampoObrigatorioNaoInformadoException("ano início atividade");
        }

        // Nome e sobrenome
        if (atorRequest.getNome().split(" ").length < 2) {
            throw new DeveConterNomeESobrenomeException();
        }

        // Data de nasimento menor que data atual
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

        Ator ator = new Ator(GeradorIdAtor.proximoId(), atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());
        fakeDatabase.persisteAtor(ator);
        System.out.println("Ator '" + ator.getNome() + "' adicionado com sucesso.");
    }

    public List<AtorEmAtividade> listarAtoresEmAtividade(String filtroNome) {
        List<AtorEmAtividade> atorEmAtividade = new ArrayList<>();
        List<Ator> atoresEmAtividade = new ArrayList<>();
        try {
            if (fakeDatabase.recuperaAtores().size() <= 0) {
                throw new NenhumAtorCadastradoException();
            } else {
                for (Ator ator : fakeDatabase.recuperaAtores()) {
                    if (ator.getNome().contains(filtroNome.toLowerCase(Locale.ROOT))) {
                        System.out.println("Encontrei com o filtro o ator " + ator.getNome());
                        if (ator.getStatusCarreira().equals(StatusCarreira.EM_ATIVIDADE)) {
                            atoresEmAtividade.add(ator);
                            AtorEmAtividade atorEmAtividade1 = new AtorEmAtividade(ator.getId(), ator.getNome(), ator.getDataNascimento());
                            atorEmAtividade.add(atorEmAtividade1);
                        } else {
                            throw new FiltroDeAtorNaoEncontradoException(filtroNome);
                        }
                    } else {
                        throw new FiltroDeAtorNaoEncontradoException(filtroNome);
                    }
                }
            }
        } catch (FiltroDeAtorNaoEncontradoException | NenhumAtorCadastradoException e) {
            System.out.println(e.getMessage());
        }
        return atorEmAtividade;
    }

    public Ator consultarAtor(Integer id) {
        try {
            if (id == null) {
                throw new CampoObrigatorioNaoInformadoException("id");
            } else {
                for (Ator ator : fakeDatabase.recuperaAtores()) {
                    if (id.equals(ator.getId())) {
                        return ator;
                    } else {
                        throw new IdNaoCorrespondeException(id);
                    }
                }
            }
        } catch (CampoObrigatorioNaoInformadoException | IdNaoCorrespondeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Ator> consultarAtores() {
        List<Ator> atoresConsultados;
        atoresConsultados = fakeDatabase.recuperaAtores();
        try {
            if (atoresConsultados.isEmpty()) {
                throw new NenhumAtorCadastradoException();
            } else {
                return atoresConsultados;
            }
        } catch (NenhumAtorCadastradoException e) {
            System.out.println(e.getMessage());
        }
        return atoresConsultados;
    }
}
