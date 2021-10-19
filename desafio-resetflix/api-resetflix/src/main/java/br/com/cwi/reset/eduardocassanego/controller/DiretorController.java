package br.com.cwi.reset.eduardocassanego.controller;

import br.com.cwi.reset.eduardocassanego.FakeDatabase;
import br.com.cwi.reset.eduardocassanego.model.Diretor;
import br.com.cwi.reset.eduardocassanego.request.DiretorRequest;
import br.com.cwi.reset.eduardocassanego.service.AtorService;
import br.com.cwi.reset.eduardocassanego.service.DiretorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/diretores")
public class DiretorController {

    private DiretorService diretorService;

    public DiretorController() {
        this.diretorService = new DiretorService(FakeDatabase.getInstance());
    }

    // demais métodos
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarDiretor(@RequestBody DiretorRequest diretorRequest) throws Exception {
        diretorService.cadastrarDiretor(diretorRequest);
    }

    @GetMapping
    @ResponseBody
    public List<Diretor> listarDiretores(@RequestParam String filtroNome) throws Exception {
        List<Diretor> diretores;
        diretores = diretorService.listarDiretores(filtroNome);
        return diretores;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Diretor consultarDiretor(@PathVariable Integer id) throws Exception {
        return diretorService.consultarDiretor(id);
    }

}
