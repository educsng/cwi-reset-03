package br.com.cwi.reset.projeto1.service;

import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.exception.FilmeNaoExistenteException;
import br.com.cwi.reset.projeto1.exception.PetJaExistenteException;
import br.com.cwi.reset.projeto1.exception.PetNaoExistenteException;
import br.com.cwi.reset.projeto1.repository.PetRepositoryDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepositoryDb petRepository;

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
        if (petJaCadastrado == null) {
            throw new FilmeNaoExistenteException("Pet com o nome " + pet.getNome() + " não existe");
        }
        return petRepository.save(pet);
    }

    public Pet buscarPetPorNome(String nomePet) {
        return petRepository.findByNome(nomePet);
    }

    public Pet buscarPorIdade(Integer idade) {
        return petRepository.findByIdade(idade);
    }

}
