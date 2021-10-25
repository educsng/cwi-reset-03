package br.com.cwi.reset.eduardocassanego.controller;

import br.com.cwi.reset.eduardocassanego.model.Ator;
import br.com.cwi.reset.eduardocassanego.request.AtorRequest;
import br.com.cwi.reset.eduardocassanego.response.AtorEmAtividade;
import br.com.cwi.reset.eduardocassanego.service.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/atores")
public class AtorController {

    @Autowired
    private AtorService atorService;

    //demais métodos
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAtor(@RequestBody @Valid AtorRequest atorRequest) throws Exception {
        atorService.criarAtor(atorRequest);
    }

    @GetMapping("/em_atividade")
    @ResponseBody
    public List<AtorEmAtividade> listarAtoresEmAtividade(@RequestParam String filtroNome) throws Exception {
        return atorService.listarAtoresEmAtividade(filtroNome);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Ator consultarAtor(@PathVariable @Valid Integer id) throws Exception {
        return atorService.consultarAtor(id);
    }

    @GetMapping
    @ResponseBody
    public List<Ator> consultarAtores() throws Exception {
        return atorService.consultarAtores();
    }
}

