package br.com.cwi.reset.primeiroprojetospring.controller;

import br.com.cwi.reset.primeiroprojetospring.domain.Diretor;
import br.com.cwi.reset.primeiroprojetospring.domain.Filme;
import br.com.cwi.reset.primeiroprojetospring.domain.Genero;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;

@RestController
@RequestMapping("/filme")
public class FilmeController {

    @GetMapping
    public Filme getFilme() {
        try {
            return new Filme("Interestelar", "Filme de ficção científica no espaço", 120, 2014, 5.0, new Diretor("Christopher Nolan", LocalDate.of(1970, Month.JULY, 30), Genero.MASCULINO, 20));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
