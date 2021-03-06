package br.com.cwi.reset.eduardocassanego.repository;

import br.com.cwi.reset.eduardocassanego.model.Diretor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiretorRepositoryDb extends CrudRepository<Diretor, Integer> {

    Diretor findByNome(String nome);

    List<Diretor> findByNomeContainingIgnoringCase(String filtro);

    Diretor findByNomeIgnoringCase(String nome);

    Diretor findByAnoInicioAtividade(Integer anoInicioAtividade);

    List<Diretor> findAll();

}
