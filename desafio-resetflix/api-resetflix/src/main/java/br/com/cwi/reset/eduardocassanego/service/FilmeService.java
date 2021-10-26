package br.com.cwi.reset.eduardocassanego.service;

import br.com.cwi.reset.eduardocassanego.exception.*;
import br.com.cwi.reset.eduardocassanego.model.*;
import br.com.cwi.reset.eduardocassanego.repository.FilmeRepositoryDb;
import br.com.cwi.reset.eduardocassanego.request.FilmeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepositoryDb filmeRepositoryDb;
    @Autowired
    private DiretorService diretorService;
    @Autowired
    private EstudioService estudioService;
    @Autowired
    private PersonagemService personagemService;
    @Autowired
    private AtorService atorService;

    // Demais métodos da classe
    public void criarFilme(FilmeRequest filmeRequest) throws Exception {

        //verificando ID diretor
        Diretor diretor = diretorService.consultarDiretor(filmeRequest.getIdDiretor());
        if (diretor == null) {
            throw new IdNaoCorrespondeException("diretor", filmeRequest.getIdDiretor());
        }

        //verificando ID estúdio
        Estudio estudio = estudioService.consultarEstudio(filmeRequest.getIdEstudio());
        if (estudio == null) {
            throw new IdNaoCorrespondeException("estúdio", filmeRequest.getIdEstudio());
        }

        //verificando se existe pelos menos um gênero na lista
        if (filmeRequest.getGeneros().isEmpty()) {
            throw new NumeroDeGeneroInvalidoException();
        }

        //verificando a existência de generos repetidos
        Set<Genero> generoSet = new HashSet<>();
        for (Genero genero : filmeRequest.getGeneros()) {
            if (generoSet.contains(genero)) {
                throw new MesmoGeneroParaOMesmoFilmeException();
            } else {
                generoSet.add(genero);
            }
        }

        Diretor diretorEstanciado = diretorService.consultarDiretor(filmeRequest.getIdDiretor());
        Estudio estudioEstanciado = estudioService.consultarEstudio(filmeRequest.getIdEstudio());
        List<PersonagemAtor> personagens = personagemService.criarPersonagemFilme(filmeRequest.getPersonagens());
        filmeRepositoryDb.save(new Filme(filmeRequest.getNome(), filmeRequest.getAnoLancamento().getYear(), filmeRequest.getCapaFilme(), filmeRequest.getGeneros(), estudioEstanciado, diretorEstanciado, personagens, filmeRequest.getResumo()));
    }

    public List<Filme> consultarFilmes(String nomeFilme, String nomeDiretor, String nomePersonagem, String nomeAtor) throws Exception {
        List<Filme> filmes = (List<Filme>) filmeRepositoryDb.findAll();
        List<Filme> filmesEncontrados = new ArrayList<>();

        if (filmes.isEmpty()) {
            throw new NenhumFilmeCadastradoException();
        }
        if (nomeFilme.isEmpty() && nomeDiretor.isEmpty() && nomePersonagem.isEmpty() && nomeAtor.isEmpty()) {
            return filmes;
        } else {
            if (!nomeFilme.isEmpty()) {
                for (Filme filme : filmes) {
                    if (filme.getNome().toLowerCase(Locale.ROOT).contains(nomeFilme.toLowerCase(Locale.ROOT))) {
                        if (!filmesEncontrados.contains(filme)) {
                            filmesEncontrados.add(filme);
                        }
                    }
                }
            }
            if (!nomeDiretor.isEmpty()) {
                for (Filme filme : filmes) {
                    if (filme.getDiretor().getNome().toLowerCase(Locale.ROOT).contains(nomeDiretor.toLowerCase(Locale.ROOT))) {
                        if (!filmesEncontrados.contains(filme)) {
                            filmesEncontrados.add(filme);
                        }
                    }
                }
            }
            if (!nomePersonagem.isEmpty()) {
                List<PersonagemAtor> personagens = personagemService.consultarPersonagens();
                boolean personagemEncontrado = false;
                PersonagemAtor personagemComNomeIgual = null;

                for (PersonagemAtor personagemAtor : personagens) {
                    if (personagemAtor.getNomePersonagem().toLowerCase(Locale.ROOT).contains(nomePersonagem.toLowerCase(Locale.ROOT))) {
                        personagemEncontrado = true;
                        personagemComNomeIgual = personagemAtor;
                    }
                }
                if (personagemEncontrado) {
                    for (Filme filme : filmes) {
                        for (PersonagemAtor personagem : filme.getPersonagens()) {
                            if (personagem.getNomePersonagem().equalsIgnoreCase(personagemComNomeIgual.getNomePersonagem())) {
                                if (!filmesEncontrados.contains(filme)) {
                                    filmesEncontrados.add(filme);
                                }
                            }
                        }
                    }
                }
            }
            if (!nomeAtor.isEmpty()) {
                List<Ator> atores = atorService.consultarAtores();
                List<Ator> atoresFiltrados = new ArrayList<>();

                for (Ator ator : atores) {
                    if (ator.getNome().toLowerCase(Locale.ROOT).contains(nomeAtor.toLowerCase(Locale.ROOT))) {
                        atoresFiltrados.add(ator);
                    }
                }
                if (!atoresFiltrados.isEmpty()) {
                    for (Filme filme : filmes) {
                        for (PersonagemAtor personagemAtor : filme.getPersonagens()) {
                            for (Ator ator : atoresFiltrados) {
                                if (personagemAtor.getAtor().equals(ator)) {
                                    if (!filmesEncontrados.contains(filme)) {
                                        filmesEncontrados.add(filme);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (filmesEncontrados.isEmpty()) {
            throw new Exception(String.format("Filme não encontrado com os filtros nomeFilme={%s}, nomeDiretor={%s}, nomePersonagem={%s}, nomeAtor={%s}, favor informar outros filtros", nomeFilme, nomePersonagem, nomeDiretor, nomeAtor));
        }
        return filmesEncontrados;
    }
}
