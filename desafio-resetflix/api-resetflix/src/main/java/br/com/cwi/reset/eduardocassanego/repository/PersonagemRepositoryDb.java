package br.com.cwi.reset.eduardocassanego.repository;

import br.com.cwi.reset.eduardocassanego.model.Ator;
import br.com.cwi.reset.eduardocassanego.model.PersonagemAtor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonagemRepositoryDb extends CrudRepository<PersonagemAtor, Integer> {

}
