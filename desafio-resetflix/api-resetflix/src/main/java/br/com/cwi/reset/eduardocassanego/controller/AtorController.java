package br.com.cwi.reset.eduardocassanego.controller;

import br.com.cwi.reset.eduardocassanego.FakeDatabase;
import br.com.cwi.reset.eduardocassanego.exception.*;
import br.com.cwi.reset.eduardocassanego.model.Ator;
import br.com.cwi.reset.eduardocassanego.request.AtorRequest;
import br.com.cwi.reset.eduardocassanego.response.AtorEmAtividade;
import br.com.cwi.reset.eduardocassanego.service.AtorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/atores")
public class AtorController {

    private AtorService atorService;

    public AtorController() {
        this.atorService = new AtorService(FakeDatabase.getInstance());
    }

    //demais métodos
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAtor(@RequestBody AtorRequest atorRequest) throws Exception {
        atorService.criarAtor(atorRequest);
    }

    @GetMapping("/em_atividade")
    @ResponseBody
    public List<AtorEmAtividade> listarAtoresEmAtividade(@RequestParam String filtroNome) throws Exception {
        List<AtorEmAtividade> atoresEmAtividade;
        atoresEmAtividade = atorService.listarAtoresEmAtividade(filtroNome);
        return atoresEmAtividade;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Ator consultarAtor(@PathVariable Integer id) throws Exception {
        return atorService.consultarAtor(id);
    }

    @GetMapping
    @ResponseBody
    public List<Ator> consultarAtores() throws Exception {
        List<Ator> atores;
        atores = atorService.consultarAtores();
        return atores;
    }
}

