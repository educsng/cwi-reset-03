package br.com.cwi.reset.projeto1.service;

import br.com.cwi.reset.projeto1.domain.Ator;
import br.com.cwi.reset.projeto1.exception.*;
import br.com.cwi.reset.projeto1.repository.AtorRepositoryDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class AtorService {

    @Autowired
    private AtorRepositoryDb atorRepositoryDb;

    public Ator salvarAtor(Ator ator) throws AtorJaExistenteException {
        Ator atorJaExistente = atorRepositoryDb.findByNome(ator.getNome());

        if (atorJaExistente != null) {
            throw new AtorJaExistenteException("Ator com o nome " + ator.getNome() + " já existe");
        }
        atorRepositoryDb.save(ator);
        return ator;
    }

    public List<Ator> listarTodos() {
        return atorRepositoryDb.findAll();
    }

    public Ator buscarAtorPorNome(String nomeAtor) throws Exception {
        if (atorRepositoryDb.findByNomeContainingIgnoringCase(nomeAtor) != null) {
            return atorRepositoryDb.findByNomeContainingIgnoringCase(nomeAtor);
        } else {
            throw new AtorNaoExistenteException("Não existem atores cadastrados com o nome ={" +nomeAtor + "}.");
        }
    }

    public List<Ator> buscarAtorCustomizado(Integer numOscars, Integer anoNascimento) {
        LocalDate date = LocalDate.of(anoNascimento, Month.JANUARY, 1);
        return atorRepositoryDb.findByNumeroOscarsGreaterThanAndDataNascimentoGreaterThan(numOscars, date);

    }


    public void deletarAtor(Integer id) throws AtorNaoExistenteException {
        Ator ator = atorRepositoryDb.findById(id).orElse(null);

        if (ator == null) {
            throw new AtorNaoExistenteException("Ator com o id " + id + " não existe");
        }
        atorRepositoryDb.delete(ator);
    }

    public Ator atualizarAtor(Ator ator) throws AtorNaoExistenteException {
        Ator atorJaCadastrado = atorRepositoryDb.findByNome(ator.getNome());
        if (atorJaCadastrado == null) {
            throw new AtorNaoExistenteException("Pet com o nome " + ator.getNome() + " não existe");
        }
        return atorRepositoryDb.save(ator);
    }

}

