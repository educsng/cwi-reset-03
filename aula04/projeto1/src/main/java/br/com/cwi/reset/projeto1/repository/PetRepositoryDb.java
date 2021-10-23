package br.com.cwi.reset.projeto1.repository;

import br.com.cwi.reset.projeto1.domain.Especie;
import br.com.cwi.reset.projeto1.domain.Filme;
import br.com.cwi.reset.projeto1.domain.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PetRepositoryDb extends CrudRepository<Pet, Integer> {

    Pet findByNome(String nome);

    Pet findByIdade(Integer idade);

    Pet findByEspecie(Especie especie);

    List<Pet> findAll();

}
