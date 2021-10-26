package br.com.cwi.reset.eduardocassanego.service;

import br.com.cwi.reset.eduardocassanego.exception.*;
import br.com.cwi.reset.eduardocassanego.model.*;
import br.com.cwi.reset.eduardocassanego.repository.DiretorRepositoryDb;
import br.com.cwi.reset.eduardocassanego.request.DiretorRequest;
import br.com.cwi.reset.eduardocassanego.validator.ValidacoesPadroes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class DiretorService {

    @Autowired
    private DiretorRepositoryDb diretorRepositoryDb;

    // Métodos
    public void cadastrarDiretor(DiretorRequest diretorRequest) throws
            CampoObrigatorioNaoInformadoException,
            DeveConterNomeESobrenomeException,
            DataNascimentoMaiorQueDataAtualException,
            AnoInicioAtividadeMenorQueDataAtualException,
            NomeJaExistenteException {

        // VERIFICAÇÕES
        new ValidacoesPadroes().validaNomeESobrenomeDiretor(diretorRequest.getNome());

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
        for (Diretor diretor :  diretorRepositoryDb.findAll()) {
            if (diretor.getNome().equalsIgnoreCase(diretorRequest.getNome())) {
                throw new NomeJaExistenteException("diretor", diretorRequest.getNome());
            }
        }
        diretorRepositoryDb.save(new Diretor(diretorRequest.getNome(), diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade()));
    }

    public List<Diretor> listarDiretores(String filtroNome) throws NenhumObjetoCadastradoException, FiltroDeObjetoNaoEncontradoException {
        List<Diretor> retorno = new ArrayList<>();

        if (diretorRepositoryDb.findAll().isEmpty()) {
            throw new NenhumObjetoCadastradoException("diretor");
        }

        if (filtroNome != null) {
            for (Diretor diretor : diretorRepositoryDb.findAll()) {
                String verificaNome = diretor.getNome().toLowerCase(Locale.ROOT);
                if (verificaNome.contains(filtroNome.toLowerCase(Locale.ROOT))) {
                    retorno.add(diretor);
                } else {
                    throw new FiltroDeObjetoNaoEncontradoException("Diretor", filtroNome);
                }
            }
        } else {
            return diretorRepositoryDb.findAll();
        }
        return retorno;
    }

    public Diretor consultarDiretor(Integer id) throws IdNaoCorrespondeException {
        Diretor diretorEncontrado = diretorRepositoryDb.findById(id).orElse(null);
        if (diretorEncontrado == null) {
            throw new IdNaoCorrespondeException("ator", id);
        }
        return diretorEncontrado;
    }
}
