package br.com.cwi.reset.eduardocassanego.controller;

import br.com.cwi.reset.eduardocassanego.exception.IdNaoCorrespondeException;
import br.com.cwi.reset.eduardocassanego.model.*;
import br.com.cwi.reset.eduardocassanego.request.FilmeRequest;
import br.com.cwi.reset.eduardocassanego.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    // Demais métodos
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarFilme(@RequestBody @Valid FilmeRequest filmeRequest) throws Exception {
        filmeService.criarFilme(filmeRequest);
    }

    @GetMapping
    public List<Filme> consultarFilmes(
            @RequestParam String nomeFilme,
            @RequestParam String nomeDiretor,
            @RequestParam String nomePersonagem,
            @RequestParam String nomeAtor) throws Exception {
        return filmeService.consultarFilmes(nomeFilme, nomeDiretor, nomePersonagem, nomeAtor);
    }

    @DeleteMapping("/{id}")
    public void removerFilme(@PathVariable @Valid Integer id) throws Exception {
        filmeService.removerFilme(id);
    }



}
