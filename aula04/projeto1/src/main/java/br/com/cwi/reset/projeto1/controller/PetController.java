package br.com.cwi.reset.projeto1.controller;

import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.exception.FilmeNaoExistenteException;
import br.com.cwi.reset.projeto1.exception.PetJaExistenteException;
import br.com.cwi.reset.projeto1.exception.PetNaoExistenteException;
import br.com.cwi.reset.projeto1.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    private PetService petService = new PetService();

    @GetMapping
    public List<Pet> getPet() {
        return petService.listarTodos();
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Pet> getByNome(@PathVariable String nome) {
        Pet pet = buscarPetPeloNome(nome);

        if (pet == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pet);
    }

    @PostMapping
    public ResponseEntity<Pet> cadastrarPet(@RequestBody Pet pet) {
        try {
            Pet petSalvo = petService.salvarPet(pet);
            return ResponseEntity.ok(petSalvo);
        } catch (PetJaExistenteException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Pet> atualizarPet(@RequestBody Pet pet) {
        try {
            return ResponseEntity.ok(petService.atualizarPet(pet));
        } catch (FilmeNaoExistenteException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity deletarPet(@PathVariable String nome) {
        try {
            petService.deletarPet(nome);
            return ResponseEntity.ok().build();
        } catch (PetNaoExistenteException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private Pet buscarPetPeloNome(String nome) {
        for (Pet pet : petService.listarTodos()) {
            if (pet.getNome().equals(nome)) {
                return pet;
            }
        }
        return null;
    }


}
