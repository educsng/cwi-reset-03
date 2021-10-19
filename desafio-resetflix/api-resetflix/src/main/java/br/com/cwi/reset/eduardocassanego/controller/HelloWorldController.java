package br.com.cwi.reset.eduardocassanego.controller;

import br.com.cwi.reset.eduardocassanego.exception.NomeCampoObrigatorioNaoInformadoException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldController {
    
    @GetMapping
    public String helloWorld() throws Exception {
        throw new NomeCampoObrigatorioNaoInformadoException();
//        return "Minha API resetflix está UP!!!";
    }
}