package br.com.cwi.reset.eduardocassanego.service;

import br.com.cwi.reset.eduardocassanego.FakeDatabase;
import br.com.cwi.reset.eduardocassanego.exception.*;
import br.com.cwi.reset.eduardocassanego.model.*;
import br.com.cwi.reset.eduardocassanego.request.FilmeRequest;
import br.com.cwi.reset.eduardocassanego.request.PersonagemRequest;

import java.time.LocalDate;
import java.util.*;

public class FilmeService {

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
        verificaCampoObrigatorio(filmeRequest.getNome(),
                filmeRequest.getAnoLancamento(), filmeRequest.getCapaFilme(), filmeRequest.getGeneros(), filmeRequest.getIdDiretor(), filmeRequest.getIdEstudio(), filmeRequest.getResumo(), filmeRequest.getPersonagens());


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

    public List<Filme> consultarFilmes(String nomeFilme, String nomeDiretor, String nomePersonagem, String nomeAtor) throws NenhumFilmeCadastradoException, CampoObrigatorioNaoInformadoException {
        List<Filme> filmes = fakeDatabase.recuperaFilmes();
        List<Filme> filmesEncontrados = new ArrayList<>();
        List<Filme> filmesEncontradosFiltrados = new ArrayList<>();
        boolean verificaNomeFilme = false;
        boolean verificaNomeDiretor = false;
        boolean verificaNomePersonagem = false;
        boolean verificaNomeAtor = false;

        if (filmes.isEmpty()) {
            throw new NenhumFilmeCadastradoException();
        }

        if (nomeFilme == null && nomeDiretor == null && nomePersonagem == null && nomeAtor == null) {
            return filmes;
        } else {
            if (nomeFilme != null) {
                for (Filme filme : filmes) {
                    if (filme.getNome().toLowerCase(Locale.ROOT).contains(nomeFilme.toLowerCase(Locale.ROOT))) {
                        filmesEncontrados.add(filme);
                    } else {
                        verificaNomeFilme = true;
                    }
                }
            }
            if (nomeDiretor != null) {
                for (Filme filme : filmes) {
                    if (filme.getDiretor().getNome().toLowerCase(Locale.ROOT).contains(nomeDiretor.toLowerCase(Locale.ROOT))) {
                        filmesEncontrados.add(filme);
                    } else {
                        verificaNomeDiretor = true;
                    }
                }
            }
            if (nomePersonagem != null) {
                List<PersonagemAtor> personagens = fakeDatabase.recuperaPersonagens();
                boolean personagemEncontrado = false;
                PersonagemAtor personagemComNomeIgual = null;

                for (PersonagemAtor personagemAtor : personagens) {
                    if (personagemAtor.getNomePersonagem().contains(nomePersonagem)) {
                        personagemEncontrado = true;
                        personagemComNomeIgual = personagemAtor;
                    } else {
                        verificaNomeAtor = true;
                    }
                }
                if (personagemEncontrado) {
                    for (Filme filme : filmes) {
                        for (PersonagemAtor personagem : filme.getPersonagens()) {
                            if (personagem.getNomePersonagem().equalsIgnoreCase(personagemComNomeIgual.getNomePersonagem())) {
                                filmesEncontrados.add(filme);
                            } else {
                                verificaNomePersonagem = true;
                            }
                        }
                    }
                }

            }
        }
        Set<Filme> filmesSet = new HashSet<>();

        for (Filme filme : filmesEncontrados) {
            if (!filmesSet.equals(filme)) {
                filmesEncontradosFiltrados.add(filme);
            }
        }
        if (filmesEncontradosFiltrados.isEmpty()) {
            filmesEncontradosFiltrados = filmes;
        }
        if (verificaNomeFilme && verificaNomeDiretor && verificaNomeAtor && verificaNomePersonagem) {
            throw new CampoObrigatorioNaoInformadoException("filtros de nomes");
        }
        return filmesEncontradosFiltrados;
    }

    // Métodos auxiliares
    public void verificaCampoObrigatorio(String nome, LocalDate anoLancamento, String capaFilme, List<Genero> genereos,
                                         Integer idDiretor, Integer idEstudio, String resumo, List<PersonagemRequest> personagens) throws CampoObrigatorioNaoInformadoException {
        if (verificaCampoObrigatorioNome(nome)) {
            throw new NomeCampoObrigatorioNaoInformadoException();
        }
        if (verificaCampoObrigatorioAno(anoLancamento)) {
            throw new CampoObrigatorioNaoInformadoException("ano lançamento");
        }
        if (verificaCampoObrigatorioCapa(capaFilme)) {
            throw new CampoObrigatorioNaoInformadoException("capa filme");
        }
        if (verificaCampoObrigatorioGeneros(genereos)) {
            throw new CampoObrigatorioNaoInformadoException("gêneros");
        }
        if (verificaCampoObrigatorioIdDiretor(idDiretor)) {
            throw new CampoObrigatorioNaoInformadoException("Id diretor");
        }
        if (verificaCampoObrigatorioIdEstudio(idEstudio)) {
            throw new CampoObrigatorioNaoInformadoException("Id estúdio");
        }
        if (verificaCampoObrigatorioResumo(resumo)) {
            throw new CampoObrigatorioNaoInformadoException("resumo");
        }
        if (verificaCampoObrigatorioPersonagens(personagens)) {
            throw new CampoObrigatorioNaoInformadoException("personagens");
        }
    }

    public boolean verificaCampoObrigatorioNome(String campo) {return campo == null;}
    public boolean verificaCampoObrigatorioAno(LocalDate campo) {return campo == null;}
    public boolean verificaCampoObrigatorioCapa(String campo) {return campo == null;}
    public boolean verificaCampoObrigatorioGeneros(List<Genero> campo) {return campo == null;}
    public boolean verificaCampoObrigatorioIdDiretor(Integer campo) {return campo == null;}
    public boolean verificaCampoObrigatorioIdEstudio(Integer campo) {return campo == null;}
    public boolean verificaCampoObrigatorioResumo(String campo) {return campo == null;}
    public boolean verificaCampoObrigatorioPersonagens(List<PersonagemRequest> campo) {return campo == null;}

}
