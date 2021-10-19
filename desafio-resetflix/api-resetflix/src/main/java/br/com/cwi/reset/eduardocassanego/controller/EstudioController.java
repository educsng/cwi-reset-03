package br.com.cwi.reset.eduardocassanego.controller;

import br.com.cwi.reset.eduardocassanego.FakeDatabase;
import br.com.cwi.reset.eduardocassanego.model.Estudio;
import br.com.cwi.reset.eduardocassanego.request.EstudioRequest;
import br.com.cwi.reset.eduardocassanego.service.EstudioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudios")
public class EstudioController {

    private EstudioService estudioService;

    public EstudioController() {
        this.estudioService = new EstudioService(FakeDatabase.getInstance());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarEstudio(@RequestBody EstudioRequest estudioRequest) throws Exception{
        estudioService.criarEstudio(estudioRequest);
    }

    @GetMapping
    public List<Estudio> consultarEstudios(@RequestParam String filtroNome) throws Exception {
        List<Estudio> estudios;
        estudios = estudioService.consultarEstudios(filtroNome);
        return estudios;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Estudio consultarEstudio(@PathVariable Integer id) throws Exception {
        return estudioService.consultarEstudioPorID(id);
    }



}
