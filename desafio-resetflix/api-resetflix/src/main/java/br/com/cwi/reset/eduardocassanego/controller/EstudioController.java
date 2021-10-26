package br.com.cwi.reset.eduardocassanego.controller;

import br.com.cwi.reset.eduardocassanego.model.Estudio;
import br.com.cwi.reset.eduardocassanego.request.EstudioRequest;
import br.com.cwi.reset.eduardocassanego.service.EstudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estudios")
public class EstudioController {

    @Autowired
    private EstudioService estudioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarEstudio(@RequestBody @Valid EstudioRequest estudioRequest) throws Exception{
        estudioService.criarEstudio(estudioRequest);
    }

    @GetMapping
    public List<Estudio> consultarEstudios(@RequestParam String filtroNome) throws Exception {
        return estudioService.consultarEstudios(filtroNome);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Estudio consultarEstudio(@PathVariable @Valid Integer id) throws Exception {
        return estudioService.consultarEstudio(id);
    }



}
