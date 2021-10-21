package br.com.cwi.reset.eduardocassanego.controller;

import br.com.cwi.reset.eduardocassanego.FakeDatabase;
import br.com.cwi.reset.eduardocassanego.model.*;
import br.com.cwi.reset.eduardocassanego.request.FilmeRequest;
import br.com.cwi.reset.eduardocassanego.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private FilmeService filmeService;

    public FilmeController() {
        this.filmeService = new FilmeService(FakeDatabase.getInstance());
    }

    // Demais métodos














//    @PostMapping
//    @ResponseBody
//    public Filme criarFilme() throws Exception {
//        List<Genero> generos = new ArrayList<>();
//        List<PersonagemAtor> personagens = new ArrayList<>();
//        generos.add(1, Genero.AVENTURA);
//        Diretor diretor = new Diretor(1, "Christopher Nolan", LocalDate.now(), 1990);
//        Estudio estudio = new Estudio(1, "estudio1", "qualquer coisa", LocalDate.now(), StatusAtividade.EM_ATIVIDADE);
//        Ator ator = new Ator(1, "Matthew McConaughey", LocalDate.now(), StatusCarreira.EM_ATIVIDADE, 1999);
//        PersonagemAtor personagemAtor = new PersonagemAtor(1, "Cooper", "piloto", TipoAtuacao.PRINCIPAL);
//        personagens.add(personagemAtor);
//
//        return new Filme(1, "Interestelar", LocalDate.now(), "algo", generos, diretor, estudio, personagens, "qualquer coisa");
//    }

}
