package br.com.cwi.reset.projeto1.controller;

import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.exception.FilmeNaoExistenteException;
import br.com.cwi.reset.projeto1.exception.PetJaExistenteException;
import br.com.cwi.reset.projeto1.exception.PetNaoExistenteException;
import br.com.cwi.reset.projeto1.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public List<Pet> getPet() {
        return petService.listarTodos();
    }

    @GetMapping("/{nome}")
    public Pet getByNome(@PathVariable String nome) {
        return petService.buscarPetPorNome(nome);
    }

    @PostMapping
    public Pet cadastrarPet(@RequestBody Pet pet) throws PetJaExistenteException {
        return petService.salvarPet(pet);
    }

    @PutMapping
    public Pet atualizarPet(@RequestBody Pet pet) throws FilmeNaoExistenteException {
        return petService.atualizarPet(pet);
    }

    @DeleteMapping("/{nome}")
    public void deletarPet(@PathVariable String nome) throws PetNaoExistenteException {
        petService.deletarPet(nome);
    }
}
