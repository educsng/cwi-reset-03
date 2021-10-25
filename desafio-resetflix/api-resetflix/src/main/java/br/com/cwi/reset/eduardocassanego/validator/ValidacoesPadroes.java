package br.com.cwi.reset.eduardocassanego.validator;

import br.com.cwi.reset.eduardocassanego.exception.*;
import br.com.cwi.reset.eduardocassanego.model.*;
import br.com.cwi.reset.eduardocassanego.request.PersonagemRequest;

import java.time.LocalDate;
import java.util.List;

public class ValidacoesPadroes {

    public void validaCamposObrigatoriosAtor(String nome, LocalDate dataNascimento, StatusCarreira statusCarreira, Integer anoInicioAtividade) throws CampoObrigatorioNaoInformadoException {
        if (verificaCampoObrigatorioNome(nome)) {
            throw new CampoObrigatorioNaoInformadoException(nome);
        }
        if (verificaCampoObrigatorioDataNascimento(dataNascimento)) {
            throw new DataNascimentoCampoObrigatorioNaoInformadoException();
        }
        if (verificaCampoObrigatorioStatus(statusCarreira)) {
            throw new StatusCarreiraCampoObrigatorioNaoInformadoException();
        }
        if (verificaCampoObrigatorioAnoInicioAtividade(anoInicioAtividade)) {
            throw new AnoInicioAtividadeCampoObrigatorioNaoInformadoException();
        }
    }

    public void validaCamposObrigatoriosDiretor(String nome, LocalDate dataNascimento, Integer anoInicioAtividade) throws CampoObrigatorioNaoInformadoException {
        if (verificaCampoObrigatorioNome(nome)) {
            throw new CampoObrigatorioNaoInformadoException(nome);
        }
        if (verificaCampoObrigatorioDataNascimento(dataNascimento)) {
            throw new DataNascimentoCampoObrigatorioNaoInformadoException();
        }
        if (verificaCampoObrigatorioAnoInicioAtividade(anoInicioAtividade)) {
            throw new AnoInicioAtividadeCampoObrigatorioNaoInformadoException();
        }
    }

    public void validaCamposObrigatoriosFilme(String nome, LocalDate anoLancamento, String capaFilme, List<Genero> generos, Integer idDiretor, Integer idEstudio, String resumo, List<PersonagemRequest> personagens) throws CampoObrigatorioNaoInformadoException {
        if (verificaCampoObrigatorioNome(nome)) {
            throw new NomeCampoObrigatorioNaoInformadoException();
        }
        if (anoLancamento == null) {
            throw new CampoObrigatorioNaoInformadoException("ano lançamento");
        }
        if (capaFilme == null) {
            throw new CampoObrigatorioNaoInformadoException("capa filme");
        }
        if (generos == null) {
            throw new CampoObrigatorioNaoInformadoException("generos");
        }
        if (idDiretor == null) {
            throw new CampoObrigatorioNaoInformadoException("id diretor");
        }
        if (idEstudio == null) {
            throw new CampoObrigatorioNaoInformadoException("id estúdio");
        }
        if (resumo == null) {
            throw new CampoObrigatorioNaoInformadoException("resumo");
        }
        if (personagens == null) {
            throw new CampoObrigatorioNaoInformadoException("personagens");
        }
    }

    public void validaCamposObrigatoriosEstudio(String nome, String descricao, LocalDate dataCriacao, StatusAtividade statusAtividade) throws CampoObrigatorioNaoInformadoException {
        if (verificaCampoObrigatorioNome(nome)) {
            throw new NomeCampoObrigatorioNaoInformadoException();
        }
        if (descricao == null) {
            throw new CampoObrigatorioNaoInformadoException("descrição");
        }
        if (dataCriacao == null) {
            throw new CampoObrigatorioNaoInformadoException("data criação");
        }
        if (statusAtividade == null) {
            throw new CampoObrigatorioNaoInformadoException("status atividade");
        }
    }

    public void validaCamposObrigatoriosPersonagemAtor(Integer id, String nome, String descricao, TipoAtuacao tipoAtuacao) throws CampoObrigatorioNaoInformadoException {
        if (id == null) {
            throw new CampoObrigatorioNaoInformadoException("id");
        }
        if (verificaCampoObrigatorioNome(nome)) {
            throw new NomeCampoObrigatorioNaoInformadoException();
        }
        if (descricao == null) {
            throw new CampoObrigatorioNaoInformadoException("descrição");
        }
        if (tipoAtuacao == null) {
            throw new CampoObrigatorioNaoInformadoException("tipo atuação");
        }
    }

    public void validaNomeESobrenomeAtor(String nomeObjeto) throws DeveConterNomeESobrenomeException {
        if (!nomeObjeto.contains(" ")) {
            throw new DeveConterNomeESobrenomeException("ator");
        }
    }

    public void validaNomeESobrenomeDiretor(String nomeObjeto) throws DeveConterNomeESobrenomeException {
        if (!nomeObjeto.contains(" ")) {
            throw new DeveConterNomeESobrenomeException("diretor");
        }
    }

    public boolean verificaCampoObrigatorioNome(String campo) {
        return campo.isEmpty();
    }
    public boolean verificaCampoObrigatorioDataNascimento(LocalDate campo) {
        return campo == null;
    }
    public boolean verificaCampoObrigatorioStatus(StatusCarreira campo) {
        return campo == null;
    }
    public boolean verificaCampoObrigatorioAnoInicioAtividade(Integer campo) {
        return campo == null;
    }
}
