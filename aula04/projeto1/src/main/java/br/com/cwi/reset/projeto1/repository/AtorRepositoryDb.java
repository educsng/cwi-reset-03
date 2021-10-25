package br.com.cwi.reset.projeto1.repository;


import br.com.cwi.reset.projeto1.domain.Ator;
import br.com.cwi.reset.projeto1.domain.Especie;
import br.com.cwi.reset.projeto1.domain.Genero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AtorRepositoryDb extends CrudRepository<Ator, Integer> {

    Ator findByNome(String nome);

    Ator findByDataNascimento(LocalDate dataNascimento);

    Ator findByGenero(Genero genero);

    Ator findByNumeroOscars(Integer numeroOscars);

    List<Ator> findAll();

    List<Ator> findByNumeroOscarsGreaterThanAndDataNascimentoGreaterThan(Integer numeroOscars, LocalDate dataNascimento);

    Ator findByNomeContainingIgnoringCase(String nomeAtor);

}
