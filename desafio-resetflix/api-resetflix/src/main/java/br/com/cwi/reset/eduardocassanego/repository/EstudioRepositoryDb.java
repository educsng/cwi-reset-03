package br.com.cwi.reset.eduardocassanego.repository;

import br.com.cwi.reset.eduardocassanego.model.Estudio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudioRepositoryDb extends CrudRepository<Estudio, Integer> {

    List<Estudio> findAll();
    List<Estudio> findByNomeContainingIgnoringCase(String filtro);
    Estudio findByNomeIgnoringCase(String nome);
}
