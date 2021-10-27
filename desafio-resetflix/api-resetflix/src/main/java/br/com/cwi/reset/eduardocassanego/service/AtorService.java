package br.com.cwi.reset.eduardocassanego.service;

import br.com.cwi.reset.eduardocassanego.exception.*;
import br.com.cwi.reset.eduardocassanego.model.Ator;
import br.com.cwi.reset.eduardocassanego.model.PersonagemAtor;
import br.com.cwi.reset.eduardocassanego.model.StatusCarreira;
import br.com.cwi.reset.eduardocassanego.repository.AtorRepositoryDb;
import br.com.cwi.reset.eduardocassanego.request.AtorRequest;
import br.com.cwi.reset.eduardocassanego.response.AtorEmAtividade;
import br.com.cwi.reset.eduardocassanego.validator.ValidacoesPadroes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class AtorService {

    @Autowired
    private AtorRepositoryDb atorRepositoryDb;
    @Autowired
    private PersonagemService personagemService;

    // Demais métodos da classe
    public void criarAtor(AtorRequest atorRequest) throws
            CampoObrigatorioNaoInformadoException,
            DeveConterNomeESobrenomeException,
            DataNascimentoMaiorQueDataAtualException,
            AnoInicioAtividadeMenorQueDataAtualException,
            NomeJaExistenteException {

        // VERIFICAÇÕES
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
        Ator atorJaExistente = atorRepositoryDb.findByNomeIgnoringCase(atorRequest.getNome());
        if (atorJaExistente != null) {
            throw new NomeJaExistenteException("ator", atorRequest.getNome());
        } else {
            atorRepositoryDb.save(new Ator(atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade()));
        }
    }

    public List<AtorEmAtividade> listarAtoresEmAtividade(String filtroNome) throws NenhumObjetoCadastradoException, FiltroDeObjetoNaoEncontradoException {

        List<AtorEmAtividade> retorno = new ArrayList<>();

        if (atorRepositoryDb.findAll().isEmpty()) {
            throw new NenhumObjetoCadastradoException("ator");
        }

        if (filtroNome != null) {
            for (Ator ator : atorRepositoryDb.findAll()) {
                if (ator.getNome().toLowerCase(Locale.ROOT).contains(filtroNome.toLowerCase(Locale.ROOT))) {
                    if (ator.getStatusCarreira().equals(StatusCarreira.EM_ATIVIDADE)) {
                        retorno.add(new AtorEmAtividade(ator.getId(), ator.getNome(), ator.getDataNascimento()));
                    }
                } else {
                    throw new FiltroDeObjetoNaoEncontradoException("Ator", filtroNome);
                }
            }
        } else {
            for (Ator ator : atorRepositoryDb.findAll()) {
                if (ator.getStatusCarreira().equals(StatusCarreira.EM_ATIVIDADE)) {
                    retorno.add(new AtorEmAtividade(ator.getId(), ator.getNome(), ator.getDataNascimento()));
                }
            }
        }

        if (retorno.isEmpty()) {
            throw new FiltroDeObjetoNaoEncontradoException("Ator", filtroNome);
        }
        return retorno;
    }

    public Ator consultarAtor(Integer id) throws IdNaoCorrespondeException {
        Ator atorEncontrado = atorRepositoryDb.findById(id).orElse(null);
        if (atorEncontrado == null) {
            throw new IdNaoCorrespondeException("ator", id);
        }
        return atorEncontrado;
    }

    public List<Ator> consultarAtores() throws NenhumObjetoCadastradoException {
        if (atorRepositoryDb.findAll().isEmpty()) {
            throw new NenhumObjetoCadastradoException("ator");
        }
        return atorRepositoryDb.findAll();
    }

    public void atualizarAtor(Integer id, AtorRequest atorRequest) throws IdNaoCorrespondeException, DeveConterNomeESobrenomeException, DataNascimentoMaiorQueDataAtualException, CampoObrigatorioNaoInformadoException, AnoInicioAtividadeMenorQueDataAtualException, NomeJaExistenteException, AtorVinculadoAUmOuMaisPersonagensException {
        Ator atorAtualizado = new Ator(atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());
        Ator atorEncontrado = atorRepositoryDb.findById(id).orElse(null);
        if (atorEncontrado == null) {
            throw new IdNaoCorrespondeException("ator", id);
        }
        atorAtualizado.setId(id);
        atorRepositoryDb.save(atorAtualizado);
    }

    public void removerAtor(Integer id) throws IdNaoCorrespondeException, AtorVinculadoAUmOuMaisPersonagensException {
        Ator atorEncontrado = atorRepositoryDb.findById(id).orElse(null);
        if (atorEncontrado == null) {
            throw new IdNaoCorrespondeException("ator", id);
        }
        for (PersonagemAtor personagemAtor : personagemService.consultarPersonagens()) {
            if (personagemAtor.getAtor().equals(atorEncontrado)) {
                throw new AtorVinculadoAUmOuMaisPersonagensException();
            }
        }
        atorRepositoryDb.delete(atorEncontrado);
    }
}
