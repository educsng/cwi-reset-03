package br.com.cwi.reset.eduardocassanego.service;

import br.com.cwi.reset.eduardocassanego.exception.*;
import br.com.cwi.reset.eduardocassanego.model.Ator;
import br.com.cwi.reset.eduardocassanego.model.PersonagemAtor;
import br.com.cwi.reset.eduardocassanego.repository.PersonagemRepositoryDb;
import br.com.cwi.reset.eduardocassanego.request.PersonagemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepositoryDb personagemRepositoryDb;
    @Autowired
    private AtorService atorService;

    //Demais métodos
    public PersonagemAtor cadastrarPersonagemAtor(PersonagemRequest personagemRequest) throws Exception {
        Ator atorEncontrado = atorService.consultarAtor(personagemRequest.getIdAtor());
        if (atorEncontrado == null) {
            throw new IdNaoCorrespondeException("ator", personagemRequest.getIdAtor());
        }
        return personagemRepositoryDb.save(new PersonagemAtor(atorEncontrado, personagemRequest.getNomePersonagem(), personagemRequest.getDescricaoPersonagem(), personagemRequest.getTipoAtuacao()));
    }

    public List<PersonagemAtor> criarPersonagemFilme(List<PersonagemRequest> personagens) throws Exception {
        Set<PersonagemRequest> listaDePersonagens = new HashSet<>();
        List<PersonagemAtor> personagensFilme = new ArrayList<>();
        PersonagemAtor personagemCriado = null;

        //verificações
        for (PersonagemRequest personagemRequest : personagens) {

            //verificando a existência de personagem de mesmo nome e mesmo ator já cadastrado
            if (listaDePersonagens.contains(personagemRequest)) {
                throw new PersonagemDeMesmoNomeEIDException();
            } else {
                listaDePersonagens.add(personagemRequest);
            }
            personagemCriado = cadastrarPersonagemAtor(personagemRequest);
            personagensFilme.add(personagemCriado);
        }
        return personagensFilme;
    }

    public List<PersonagemAtor> consultarPersonagens() {
        return (List<PersonagemAtor>) personagemRepositoryDb.findAll();
    }
}
