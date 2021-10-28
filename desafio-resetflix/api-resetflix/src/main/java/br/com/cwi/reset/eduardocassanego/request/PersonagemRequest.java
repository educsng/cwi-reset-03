package br.com.cwi.reset.eduardocassanego.request;

import br.com.cwi.reset.eduardocassanego.model.TipoAtuacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class PersonagemRequest {

    //Atributos
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo id ator.")
    private Integer idAtor;
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo nome personagem.")
    @NotBlank(message = "Campo obrigatório não informado, favor informar o campo nome personagem.")
    private String nomePersonagem;
    @NotBlank(message = "Campo obrigatório não informado, favor informar o campo descrição personagem.")
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo descrição personagem.")
    private String descricaoPersonagem;
    @NotNull(message = "Campo obrigatório não informado, favor informar o campo tipo atuação.")
    private TipoAtuacao tipoAtuacao;

    // Construtor
    public PersonagemRequest(Integer idAtor, String nomePersonagem, String descricaoPersonagem, TipoAtuacao tipoAtuacao) {
        this.idAtor = idAtor;
        this.nomePersonagem = nomePersonagem;
        this.descricaoPersonagem = descricaoPersonagem;
        this.tipoAtuacao = tipoAtuacao;
    }

    // Getters e setters
    public Integer getIdAtor() {
        return idAtor;
    }

    public void setIdAtor(Integer idAtor) {
        this.idAtor = idAtor;
    }

    public String getNomePersonagem() {
        return nomePersonagem;
    }

    public void setNomePersonagem(String nomePersonagem) {
        this.nomePersonagem = nomePersonagem;
    }

    public String getDescricaoPersonagem() {
        return descricaoPersonagem;
    }

    public void setDescricaoPersonagem(String descricaoPersonagem) {
        this.descricaoPersonagem = descricaoPersonagem;
    }

    public TipoAtuacao getTipoAtuacao() {
        return tipoAtuacao;
    }

    public void setTipoAtuacao(TipoAtuacao tipoAtuacao) {
        this.tipoAtuacao = tipoAtuacao;
    }



    // sobreescrevendo equals e hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonagemRequest that = (PersonagemRequest) o;
        return Objects.equals(idAtor, that.idAtor) && Objects.equals(nomePersonagem, that.nomePersonagem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAtor, nomePersonagem);
    }
}
