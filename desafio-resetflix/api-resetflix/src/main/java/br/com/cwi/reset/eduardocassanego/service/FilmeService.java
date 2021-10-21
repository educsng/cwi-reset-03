package br.com.cwi.reset.eduardocassanego.service;

import br.com.cwi.reset.eduardocassanego.FakeDatabase;
import br.com.cwi.reset.eduardocassanego.exception.*;
import br.com.cwi.reset.eduardocassanego.model.*;
import br.com.cwi.reset.eduardocassanego.request.FilmeRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public void criarFilme(FilmeRequest filmeRequest) throws CampoObrigatorioNaoInformadoException, IdNaoCorrespondeException, NumeroDeGeneroInvalidoException, MesmoGeneroParaOMesmoFilmeException, NenhumEstudioCadastradoException {

        //verificando campos obrigatórios
        verificaCampoObrigatorio(filmeRequest.getNome(),
                filmeRequest.getAnoLancamento(), filmeRequest.getCapaFilme(), filmeRequest.getGeneros(), filmeRequest.getIdDiretor(), filmeRequest.getIdEstudio(), filmeRequest.getResumo(), filmeRequest.getPersonagens());

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

        //verificando a existência de pelo menos um gênero
        List<Genero> generos = filmeRequest.getGeneros();
        if (generos.size() < 1) {
            throw new NumeroDeGeneroInvalidoException();
        } else if (generos.size() > 1){
            for (int i = 0; i < generos.size() - 1; i++) {
                for (int j = 1; j < generos.size(); j++) {
                    if (Objects.equals(generos.get(i).getDescricao(), generos.get(j + 1).getDescricao())) {
                        throw new MesmoGeneroParaOMesmoFilmeException();
                    }
                }
            }
        }

        Diretor diretor = diretorService.consultarDiretor(filmeRequest.getIdDiretor());
        Estudio estudio = estudioService.consultarEstudioPorID(filmeRequest.getIdEstudio());
        List<PersonagemAtor> personagens = fakeDatabase.recuperaPersonagens();

        // precisa reber no ultimo campo um List<PersonagemAtor>
        Filme filme = new Filme(GeradorIdFilme.proximoId(), filmeRequest.getNome(), filmeRequest.getAnoLancamento(), filmeRequest.getCapaFilme(), filmeRequest.getGeneros(), diretor, estudio, personagens, filmeRequest.getResumo());
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
