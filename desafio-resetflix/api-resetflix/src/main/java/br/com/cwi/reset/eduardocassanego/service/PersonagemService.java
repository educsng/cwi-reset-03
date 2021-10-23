package br.com.cwi.reset.eduardocassanego.service;

import br.com.cwi.reset.eduardocassanego.FakeDatabase;
import br.com.cwi.reset.eduardocassanego.exception.*;
import br.com.cwi.reset.eduardocassanego.model.Ator;
import br.com.cwi.reset.eduardocassanego.model.PersonagemAtor;
import br.com.cwi.reset.eduardocassanego.request.PersonagemRequest;
import br.com.cwi.reset.eduardocassanego.model.TipoAtuacao;

import java.util.*;

public class PersonagemService {

    // Atributos
    private FakeDatabase fakeDatabase;

    // Construtor padrão
    public PersonagemService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    //Demais métodos
    public PersonagemAtor cadastrarPersonagemAtor(PersonagemRequest personagemRequest) throws Exception {
        // verificar se existe ator com Id passado pelo request
        List<Ator> atores = fakeDatabase.recuperaAtores();
        boolean verificacao = false;
        for (Ator ator : atores) {
            if (Objects.equals(ator.getId(), personagemRequest.getIdAtor())) {
                verificacao = true;
                break;
            }
        }
        if (!verificacao) {
            throw new IdNaoCorrespondeException("ator", personagemRequest.getIdAtor());
        }
        PersonagemAtor personagemAtor = new PersonagemAtor(personagemRequest.getIdAtor(), personagemRequest.getNomePersonagem(), personagemRequest.getDescricaoPersonagem(), personagemRequest.getTipoAtuacao());
        fakeDatabase.persistePersonagem(personagemAtor);
        return personagemAtor;
    }

    public List<PersonagemAtor> criarPersonagemFilme(List<PersonagemRequest> personagens) throws Exception {
        Set<PersonagemRequest> listaDePersonagens = new HashSet<>();
        List<PersonagemAtor> personagensFilme = new ArrayList<>();
        PersonagemAtor personagemCriado = null;

        //verificações
        for (PersonagemRequest personagemRequest : personagens) {
            // verificando campos obrigatórios
            verificaCamposObrigatorios(personagemRequest.getIdAtor(), personagemRequest.getNomePersonagem(), personagemRequest.getDescricaoPersonagem(), personagemRequest.getTipoAtuacao());

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

    // métodos auxiliares
    public void verificaCamposObrigatorios(Integer idAtor, String nomePersonagem, String descricaoPersonagem, TipoAtuacao tipoAtuacao) throws CampoObrigatorioNaoInformadoException {
        if (verificaCampoId(idAtor)){
            throw new CampoObrigatorioNaoInformadoException("ID do ator");
        }
        if (verificaCampoNome(nomePersonagem)) {
            throw new CampoObrigatorioNaoInformadoException("nome do personagem");
        }
        if (verificaCampoDescricao(descricaoPersonagem)) {
            throw new CampoObrigatorioNaoInformadoException("descrição do personagem");
        }
        if (verificaCampoTipoAtuacao(tipoAtuacao)) {
            throw new CampoObrigatorioNaoInformadoException("tipo atuação");
        }
    }

    public boolean verificaCampoId(Integer campo) {
        return campo == null;
    }
    public boolean verificaCampoNome(String campo) {
        return campo == null;
    }
    public boolean verificaCampoDescricao(String campo) {
        return campo == null;
    }
    public boolean verificaCampoTipoAtuacao(TipoAtuacao campo) {
        return campo == null;
    }
}
