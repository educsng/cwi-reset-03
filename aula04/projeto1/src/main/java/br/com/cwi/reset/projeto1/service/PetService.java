package br.com.cwi.reset.projeto1.service;

import br.com.cwi.reset.projeto1.domain.Filme;
import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.exception.FilmeNaoExistenteException;
import br.com.cwi.reset.projeto1.exception.PetJaExistenteException;
import br.com.cwi.reset.projeto1.exception.PetNaoExistenteException;
import br.com.cwi.reset.projeto1.repository.PetRepository;

import java.util.List;

public class PetService {

    private PetRepository petRepository = new PetRepository();

    public Pet salvarPet(Pet pet) throws PetJaExistenteException {
        Pet petJaExistente = petRepository.findByNome(pet.getNome());

        if (petJaExistente != null) {
            throw new PetJaExistenteException("Pet com o nome " + pet.getNome() + " já existe");
        }
        petRepository.save(pet);
        return pet;
    }

    public List<Pet> listarTodos() {
        return petRepository.findAll();
    }

    public void deletarPet(String nomePet) throws PetNaoExistenteException {
        Pet pet = buscarPetPorNome(nomePet);

        if (pet == null) {
            throw new PetNaoExistenteException("Pet com o nome " + nomePet + " não existe");
        }
        petRepository.delete(pet);
    }

    public Pet atualizarPet(Pet pet) throws FilmeNaoExistenteException {
        Pet petJaCadastrado = buscarPetPorNome(pet.getNome());
        if (pet == null) {
            throw new FilmeNaoExistenteException("Pet com o nome " + pet.getNome() + " não existe");
        }
        return petRepository.update(pet);
    }

    public Pet buscarPetPorNome(String nomePet) {
        return petRepository.findByNome(nomePet);
    }

}
