package br.com.cwi.reset.projeto1.controller;

import br.com.cwi.reset.projeto1.domain.Ator;
import br.com.cwi.reset.projeto1.service.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ator")
public class AtorController {

    @Autowired
    private AtorService atorService;

    @GetMapping
    public List<Ator> getAtor() {
        return atorService.listarTodos();
    }

    @GetMapping("/{nome}")
    public Ator getByNome(@PathVariable String nome) throws Exception {
        return atorService.buscarAtorPorNome(nome);
    }

    @GetMapping("/by-filter")
    public List<Ator> getByNumeroOscars(@RequestParam Integer numOscars, @RequestParam Integer anoNascimento) {
        return atorService.buscarAtorCustomizado(numOscars, anoNascimento);
    }

    @PostMapping
    public Ator cadastrarAtor(@RequestBody Ator ator) throws Exception {
        return atorService.salvarAtor(ator);
    }

    @PutMapping
    public Ator atualizarAtor(@RequestBody Ator ator) throws Exception {
        return atorService.atualizarAtor(ator);
    }

    @DeleteMapping("/{id}")
    public void deletarAtorPorId(@PathVariable Integer id) throws Exception {
        atorService.deletarAtor(id);
    }
}
