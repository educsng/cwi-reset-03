package br.com.cwi.reset.eduardocassanego.request;

import br.com.cwi.reset.eduardocassanego.model.Genero;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class FilmeRequest {

    // Atributos
    @NotBlank(message = "Campo obrigatório não informado, favor informar o campo nome.")
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo nome.")
    private String nome;
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo ano lançamento.")
    private LocalDate anoLancamento;
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo capa filme.")
    @NotBlank(message = "Campo obrigatório não informado, favor informar o campo capa filme.")
    private String capaFilme;
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo gêneros.")
    private List<Genero> generos;
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo id diretor.")
    private Integer idDiretor;
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo id estúdio.")
    private Integer idEstudio;
    @NotBlank(message = "Campo obrigatório não informado, favor informar o campo resumo.")
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo resumo.")
    private String resumo;
    @Valid
    private List<PersonagemRequest> personagens;

    // Construtor
    public FilmeRequest(String nome, LocalDate anoLancamento, String capaFilme, List<Genero> generos, Integer idDiretor, Integer idEstudio, String resumo, List<PersonagemRequest> personagens) {
        this.nome = nome;
        this.anoLancamento = anoLancamento;
        this.capaFilme = capaFilme;
        this.generos = generos;
        this.idDiretor = idDiretor;
        this.idEstudio = idEstudio;
        this.resumo = resumo;
        this.personagens = personagens;
    }

    //Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(LocalDate anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getCapaFilme() {
        return capaFilme;
    }

    public void setCapaFilme(String capaFilme) {
        this.capaFilme = capaFilme;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public Integer getIdDiretor() {
        return idDiretor;
    }

    public void setIdDiretor(Integer idDiretor) {
        this.idDiretor = idDiretor;
    }

    public Integer getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(Integer idEstudio) {
        this.idEstudio = idEstudio;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public List<PersonagemRequest> getPersonagens() {
        return personagens;
    }

    public void setPersonagens(List<PersonagemRequest> personagens) {
        this.personagens = personagens;
    }
}
