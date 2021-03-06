package br.com.cwi.reset.eduardocassanego.repository;

import br.com.cwi.reset.eduardocassanego.model.Ator;
import br.com.cwi.reset.eduardocassanego.model.StatusCarreira;
import br.com.cwi.reset.eduardocassanego.response.AtorEmAtividade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AtorRepositoryDb extends CrudRepository<Ator, Integer> {

    Ator findByNomeIgnoringCase(String nome);
    List<Ator> findByNomeContainingIgnoringCase(String filtroNome);
    List<Ator> findAll();
    List<Ator> findByStatusCarreira(String nome);
}
