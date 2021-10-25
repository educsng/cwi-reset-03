package br.com.cwi.reset.eduardocassanego.service;

import br.com.cwi.reset.eduardocassanego.FakeDatabase;
import br.com.cwi.reset.eduardocassanego.exception.*;
import br.com.cwi.reset.eduardocassanego.model.*;
import br.com.cwi.reset.eduardocassanego.repository.FilmeRepositoryDb;
import br.com.cwi.reset.eduardocassanego.request.FilmeRequest;
import br.com.cwi.reset.eduardocassanego.validator.ValidacoesPadroes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepositoryDb filmeRepositoryDb;

    //Atributos
    private FakeDatabase fakeDatabase;
    private AtorService atorService;
    private DiretorService diretorService;
    private EstudioService estudioService;
    private PersonagemService personagemService;

    // Construtor
    public FilmeService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.atorService = new AtorService(FakeDatabase.getInstance());
        this.diretorService = new DiretorService(FakeDatabase.getInstance());
        this.estudioService = new EstudioService(FakeDatabase.getInstance());
        this.personagemService = new PersonagemService(FakeDatabase.getInstance());
    }

    // Demais métodos da classe
    public void criarFilme(FilmeRequest filmeRequest) throws Exception {

        //verificando campos obrigatórios
        new ValidacoesPadroes().validaCamposObrigatoriosFilme(filmeRequest.getNome(), filmeRequest.getAnoLancamento(), filmeRequest.getCapaFilme(), filmeRequest.getGeneros(), filmeRequest.getIdDiretor(), filmeRequest.getIdEstudio(), filmeRequest.getResumo(), filmeRequest.getPersonagens());

        //verificando ID diretor
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        boolean idDiretorEncontrado = false;
        for (Diretor diretor : diretores) {
            if (Objects.equals(filmeRequest.getIdDiretor(), diretor.getId())) {
                idDiretorEncontrado = true;
            }
        }
        if (!idDiretorEncontrado) {
            throw new IdNaoCorrespondeException("diretor", filmeRequest.getIdDiretor());
        }

        //verificando ID estúdio
        List<Estudio> estudios = fakeDatabase.recuperaEstudios();
        boolean idEstudioEncontrado = false;
        for (Estudio estudio : estudios) {
            if (Objects.equals(filmeRequest.getIdEstudio(), estudio.getId())) {
                idEstudioEncontrado = true;
            }
        }
        if (!idEstudioEncontrado) {
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

        Diretor diretor = diretorService.consultarDiretor(filmeRequest.getIdDiretor());
        Estudio estudio = estudioService.consultarEstudioPorID(filmeRequest.getIdEstudio());
        List<PersonagemAtor> personagens = personagemService.criarPersonagemFilme(filmeRequest.getPersonagens());
        Filme filme = new Filme(GeradorIdFilme.proximoId(), filmeRequest.getNome(), filmeRequest.getAnoLancamento(), filmeRequest.getCapaFilme(), filmeRequest.getGeneros(), diretor, estudio, personagens, filmeRequest.getResumo());
        fakeDatabase.persisteFilme(filme);
    }

    public List<Filme> consultarFilmes(String nomeFilme, String nomeDiretor, String nomePersonagem, String nomeAtor) throws Exception {
        List<Filme> filmes = fakeDatabase.recuperaFilmes();
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
                List<PersonagemAtor> personagens = fakeDatabase.recuperaPersonagens();
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
        }
        if (filmesEncontrados.isEmpty()) {
            throw new Exception(String.format("Filme não encontrado com os filtros nomeFilme={%s}, nomeDiretor={%s}, nomePersonagem={%s}, nomeAtor={%s}, favor informar outros filtros", nomeFilme, nomePersonagem, nomeDiretor, nomeAtor));
        }
        return filmesEncontrados;
    }

}
