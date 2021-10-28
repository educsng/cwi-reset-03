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

import java.util.ArrayList;
import java.util.List;

@Service
public class AtorService {

    @Autowired
    private AtorRepositoryDb atorRepositoryDb;
    @Autowired
    private PersonagemService personagemService;

    // Demais métodos da classe
    public void criarAtor(AtorRequest atorRequest) throws DeveConterNomeESobrenomeException, AnoInicioAtividadeMenorQueDataNascimentoException, NomeJaExistenteException {

        new ValidacoesPadroes().validaNomeESobrenomeAtor(atorRequest.getNome());
        new ValidacoesPadroes().validaAnoInicioAtividade("ator", atorRequest.getAnoInicioAtividade(), atorRequest.getDataNascimento().getYear());

        // Ator de mesmo nome
        Ator atorJaExistente = atorRepositoryDb.findByNomeIgnoringCase(atorRequest.getNome());
        if (atorJaExistente != null) {
            throw new NomeJaExistenteException("ator", atorRequest.getNome());
        } else {
            atorRepositoryDb.save(new Ator(atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade()));
        }
    }

    public List<AtorEmAtividade> listarAtoresEmAtividade(String filtroNome) throws NenhumObjetoCadastradoException, FiltroDeObjetoNaoEncontradoException {
        List<Ator> listaFiltrada;
        List<AtorEmAtividade> retorno = new ArrayList<>();

        if (atorRepositoryDb.findAll().isEmpty()) {
            throw new NenhumObjetoCadastradoException("ator");
        }
        if (filtroNome != null) {
            listaFiltrada = atorRepositoryDb.findByNomeContainingIgnoringCase(filtroNome);
            if (!listaFiltrada.isEmpty()) {
                for (Ator ator : listaFiltrada) {
                    if (ator.getStatusCarreira().equals(StatusCarreira.EM_ATIVIDADE)) {
                        retorno.add(new AtorEmAtividade(ator.getId(), ator.getNome(), ator.getDataNascimento()));
                    }
                }
            } else {
                throw new FiltroDeObjetoNaoEncontradoException("Ator", filtroNome);
            }
        } else {
            for (Ator ator : atorRepositoryDb.findAll()) {
                retorno.add(new AtorEmAtividade(ator.getId(), ator.getNome(), ator.getDataNascimento()));
            }
            return retorno;
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

    public void atualizarAtor(Integer id, AtorRequest atorRequest) throws IdNaoCorrespondeException, DeveConterNomeESobrenomeException, DataNascimentoMaiorQueDataAtualException, CampoObrigatorioNaoInformadoException, AnoInicioAtividadeMenorQueDataNascimentoException, NomeJaExistenteException, AtorVinculadoAUmOuMaisPersonagensException {
        Ator atorAtualizado = new Ator(atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());
        Ator atorEncontrado = atorRepositoryDb.findById(id).orElse(null);

        if (atorEncontrado == null) {
            throw new IdNaoCorrespondeException("ator", id);
        }
        // Ator de mesmo nome
        Ator atorJaExistente = atorRepositoryDb.findByNomeIgnoringCase(atorRequest.getNome());
        if (atorJaExistente != null) {
            throw new NomeJaExistenteException("ator", atorRequest.getNome());
        } else {
            atorRepositoryDb.save(new Ator(atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade()));
        }

        new ValidacoesPadroes().validaNomeESobrenomeAtor(atorRequest.getNome());
        new ValidacoesPadroes().validaAnoInicioAtividade("ator", atorRequest.getAnoInicioAtividade(), atorRequest.getDataNascimento().getYear());
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
