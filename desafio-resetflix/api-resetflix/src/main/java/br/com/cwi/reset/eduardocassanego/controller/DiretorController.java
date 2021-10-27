package br.com.cwi.reset.eduardocassanego.controller;

import br.com.cwi.reset.eduardocassanego.model.Diretor;
import br.com.cwi.reset.eduardocassanego.request.AtorRequest;
import br.com.cwi.reset.eduardocassanego.request.DiretorRequest;
import br.com.cwi.reset.eduardocassanego.service.DiretorService;
import br.com.cwi.reset.eduardocassanego.service.DiretorVinculadoAUmOuMaisFilmesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/diretores")
public class DiretorController {

    @Autowired
    private DiretorService diretorService;

    // demais métodos
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarDiretor(@RequestBody @Valid DiretorRequest diretorRequest) throws Exception {
        diretorService.cadastrarDiretor(diretorRequest);
    }

    @GetMapping
    @ResponseBody
    public List<Diretor> listarDiretores(@RequestParam String filtroNome) throws Exception {
        return diretorService.listarDiretores(filtroNome);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Diretor consultarDiretor(@PathVariable @Valid Integer id) throws Exception {
        return diretorService.consultarDiretor(id);
    }

    @PutMapping("/{id}")
    public void atualizarDiretor(@PathVariable @Valid Integer id, @RequestBody @Valid DiretorRequest diretorRequest) throws Exception {
        diretorService.atualizarDiretor(id, diretorRequest);
    }

    @DeleteMapping("/{id}")
    public void removerDiretores(@PathVariable @Valid Integer id) throws Exception {
        diretorService.removerDiretores(id);
    }

}
