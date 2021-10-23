package br.com.cwi.reset.projeto1.repository;

import br.com.cwi.reset.projeto1.domain.Filme;
import br.com.cwi.reset.projeto1.domain.Pet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PetRepositoryImpl implements PetRepository {

    private List<Pet> pets = new ArrayList<>();

    public Pet findByNome(String nome) {
        for (Pet pet : pets) {
            if (pet.getNome().equals(nome)) {
                return pet;
            }
        }
        return null;
    }

    public Pet save(Pet pet) {
        pets.add(pet);
        return pet;
    }

    public void delete(Pet pet) {
        pets.remove(pet);
    }

    public Pet update(Pet pet) {
        Pet petEncontrado = findByNome(pet.getNome());

        if (petEncontrado != null) {
            pets.remove(petEncontrado);
            pets.add(pet);
            return pet;
        }
        return null;
    }

    public List<Pet> findAll() {
        return pets;
    }
}
