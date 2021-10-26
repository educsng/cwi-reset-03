package br.com.cwi.reset.eduardocassanego.repository;

import br.com.cwi.reset.eduardocassanego.model.Filme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepositoryDb extends CrudRepository<Filme, Integer> {

}
