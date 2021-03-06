package br.com.cwi.reset.eduardocassanego.service;

import br.com.cwi.reset.eduardocassanego.exception.*;
import br.com.cwi.reset.eduardocassanego.model.*;
import br.com.cwi.reset.eduardocassanego.repository.DiretorRepositoryDb;
import br.com.cwi.reset.eduardocassanego.request.DiretorRequest;
import br.com.cwi.reset.eduardocassanego.validator.ValidacoesPadroes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiretorService {

    @Autowired
    private DiretorRepositoryDb diretorRepositoryDb;
    @Autowired
    private FilmeService filmeService;

    // Métodos
    public void cadastrarDiretor(DiretorRequest diretorRequest) throws DeveConterNomeESobrenomeException, AnoInicioAtividadeMenorQueDataNascimentoException, NomeJaExistenteException {

        new ValidacoesPadroes().validaNomeESobrenomeDiretor(diretorRequest.getNome());
        new ValidacoesPadroes().validaAnoInicioAtividade("diretor", diretorRequest.getAnoInicioAtividade(), diretorRequest.getDataNascimento().getYear());

        // Diretor de mesmo nome
        Diretor diretorJaExistente = diretorRepositoryDb.findByNomeIgnoringCase(diretorRequest.getNome());

        if (diretorJaExistente != null) {
            throw new NomeJaExistenteException("diretor", diretorRequest.getNome());
        } else {
            diretorRepositoryDb.save(new Diretor(diretorRequest.getNome(), diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade()));
        }
    }

    public List<Diretor> listarDiretores(String filtroNome) throws NenhumObjetoCadastradoException, FiltroDeObjetoNaoEncontradoException {
        List<Diretor> listaFiltrada;

        if (diretorRepositoryDb.findAll().isEmpty()) {
            throw new NenhumObjetoCadastradoException("diretor");
        }
        if (filtroNome != null) {
            listaFiltrada = diretorRepositoryDb.findByNomeContainingIgnoringCase(filtroNome);
        } else {
            return diretorRepositoryDb.findAll();
        }
        if (listaFiltrada.isEmpty()) {
            throw new FiltroDeObjetoNaoEncontradoException("Diretor", filtroNome);
        }
        return listaFiltrada;
    }

    public Diretor consultarDiretor(Integer id) throws IdNaoCorrespondeException {
        Diretor diretorEncontrado = diretorRepositoryDb.findById(id).orElse(null);

        if (diretorEncontrado == null) {
            throw new IdNaoCorrespondeException("diretor", id);
        }
        return diretorEncontrado;
    }

    public void atualizarDiretor(Integer id, DiretorRequest diretorRequest) throws IdNaoCorrespondeException, DeveConterNomeESobrenomeException, AnoInicioAtividadeMenorQueDataNascimentoException, NomeJaExistenteException {
        Diretor diretorAtualizado = new Diretor(diretorRequest.getNome(), diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade());
        Diretor diretorEncontrado = diretorRepositoryDb.findById(id).orElse(null);

        if (diretorEncontrado == null) {
            throw new IdNaoCorrespondeException("diretor", id);
        }
        // Diretor de mesmo nome
        Diretor diretorJaExistente = diretorRepositoryDb.findByNomeIgnoringCase(diretorRequest.getNome());

        if (diretorJaExistente != null) {
            throw new NomeJaExistenteException("diretor", diretorRequest.getNome());
        } else {
            diretorRepositoryDb.save(new Diretor(diretorRequest.getNome(), diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade()));
        }

        new ValidacoesPadroes().validaNomeESobrenomeDiretor(diretorRequest.getNome());
        new ValidacoesPadroes().validaAnoInicioAtividade("diretor", diretorRequest.getAnoInicioAtividade(), diretorRequest.getDataNascimento().getYear());
        diretorAtualizado.setId(id);
        diretorRepositoryDb.save(diretorAtualizado);
    }

    public void removerDiretores(Integer id) throws IdNaoCorrespondeException, DiretorVinculadoAUmOuMaisFilmesException {
        Diretor diretorEncontrado = diretorRepositoryDb.findById(id).orElse(null);

        if (diretorEncontrado == null) {
            throw new IdNaoCorrespondeException("diretor", id);
        }

        for (Filme filme : filmeService.consultarTodos()) {
            if (filme.getDiretor().equals(diretorEncontrado)) {
                throw new DiretorVinculadoAUmOuMaisFilmesException();
            }
        }
        diretorRepositoryDb.delete(diretorEncontrado);
    }

}
