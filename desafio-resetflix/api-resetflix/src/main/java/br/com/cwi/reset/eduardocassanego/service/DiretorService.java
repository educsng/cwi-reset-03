package br.com.cwi.reset.eduardocassanego.service;

import br.com.cwi.reset.eduardocassanego.FakeDatabase;
import br.com.cwi.reset.eduardocassanego.exception.*;
import br.com.cwi.reset.eduardocassanego.model.*;
import br.com.cwi.reset.eduardocassanego.request.DiretorRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class DiretorService {

    // Atributos
    private FakeDatabase fakeDatabase;

    //Construtor
    public DiretorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    // Métodos
    public void cadastrarDiretor(DiretorRequest diretorRequest) throws
            DataNascimentoCampoObrigatorioNaoInformadoException,
            NomeCampoObrigatorioNaoInformadoException,
            AnoInicioAtividadeCampoObrigatorioNaoInformadoException,
            DeveConterNomeESobrenomeException,
            DataNascimentoMaiorQueDataAtualException,
            AnoInicioAtividadeMenorQueDataAtualException,
            NomeJaExistenteException {


        // VERIFICAÇÕES
        // Campos obrigatórios
        verificaCampoObrigatorio(diretorRequest.getNome(), diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade());
        // Mesmo Nome e sobrenome
        if (!diretorRequest.getNome().contains(" ")) {
            throw new DeveConterNomeESobrenomeException("diretor");
        }
        // Data Nascimento menor que data atual
        LocalDate now = LocalDate.now();
        if (now.isBefore(diretorRequest.getDataNascimento())) {
            throw new DataNascimentoMaiorQueDataAtualException("diretores");
        }
        // Ano Inicio Atividade menor que data de nascimento
        Integer anoNascimentoAtor = diretorRequest.getDataNascimento().getYear();
        if (diretorRequest.getAnoInicioAtividade() < anoNascimentoAtor) {
            throw new AnoInicioAtividadeMenorQueDataAtualException("diretor");
        }
        // Diretor de mesmo nome
        List<Diretor> diretores;
        diretores = fakeDatabase.recuperaDiretores();

        for (Diretor diretor :  diretores) {
            if (diretor.getNome().equalsIgnoreCase(diretorRequest.getNome())) {
                throw new NomeJaExistenteException("diretor", diretorRequest.getNome());
            }
        }
        // Após todas as verificações, instancia o objeto Diretor e persiste na fakedatabase
        Diretor diretor = new Diretor(GeradorIdDiretor.proximoId(), diretorRequest.getNome(), diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade());
        fakeDatabase.persisteDiretor(diretor);
    }

    public List<Diretor> listarDiretores(String filtroNome) throws
            NenhumObjetoCadastradoException,
            FiltroDeObjetoNaoEncontradoException {
        List<Diretor> retorno = new ArrayList<>();

        if (verificaDiretorBancoDeDadosVazio(fakeDatabase.recuperaDiretores())) {
            throw new NenhumObjetoCadastradoException("diretor");
        }

        if (filtroNome != null) {
            for (Diretor diretor : fakeDatabase.recuperaDiretores()) {
                String verificaNome = diretor.getNome().toLowerCase(Locale.ROOT);
                if (verificaNome.contains(filtroNome.toLowerCase(Locale.ROOT))) {
                    retorno.add(diretor);
                } else {
                    throw new FiltroDeObjetoNaoEncontradoException("Diretor", filtroNome);
                }
            }
        }
        return retorno;
    }


    public Diretor consultarDiretor(Integer id) throws CampoObrigatorioNaoInformadoException, IdNaoCorrespondeException {
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        boolean exception = false;

        if (id != null) {
            for (Diretor diretor : diretores) {
                if (!Objects.equals(diretor.getId(), id)) {
                    exception = true;
                } else {
                    return diretor;
                }
            }
        } else {
            throw new CampoObrigatorioNaoInformadoException("id");
        }
        if (exception) {
            throw new IdNaoCorrespondeException("diretor", id);
        }
        return null;
    }


    // Metodos auxiliares
    public void verificaCampoObrigatorio(String nome, LocalDate dataNascimento, Integer anoInicioAtividade) throws NomeCampoObrigatorioNaoInformadoException, DataNascimentoCampoObrigatorioNaoInformadoException, AnoInicioAtividadeCampoObrigatorioNaoInformadoException {
        if (verificaCampoObrigatorioNome(nome)) {
            throw new NomeCampoObrigatorioNaoInformadoException();
        }
        if (verificaCampoObrigatorioDataNascimento(dataNascimento)) {
            throw new DataNascimentoCampoObrigatorioNaoInformadoException();
        }
        if (verificaCampoObrigatorioAnoInicioAtividade(anoInicioAtividade)) {
            throw new AnoInicioAtividadeCampoObrigatorioNaoInformadoException();
        }
    }

    public boolean verificaCampoObrigatorioNome(String campo) {
        return campo.isEmpty();
    }
    public boolean verificaCampoObrigatorioDataNascimento(LocalDate campo) {
        return campo == null;
    }
    public boolean verificaCampoObrigatorioAnoInicioAtividade(Integer campo) {
        return campo == null;
    }
    public boolean verificaDiretorBancoDeDadosVazio(List<Diretor> diretores) {return diretores.isEmpty();}

}
