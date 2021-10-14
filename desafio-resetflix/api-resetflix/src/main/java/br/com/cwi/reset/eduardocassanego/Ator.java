package br.com.cwi.reset.eduardocassanego;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Ator {

    // Atributos
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private StatusCarreira statusCarreira;
    private Integer anoInicioAtividade;
    private AtorService atorService;

    // Construtor
    public Ator(Integer id, String nome, LocalDate dataNascimento, StatusCarreira statusCarreira, Integer anoInicioAtividade)
            throws DataNascimentoMaiorQueDataAtual, CampoObrigatorioNaoInformado, AnoInicioAtividadeMenorQueDataAtual,
            DeveConterNomeESobrenome, NomeJaExistente {


        // Verificações
        if (!verificaCampoObrigatorio()) {
            throw new CampoObrigatorioNaoInformado();
        }

        if (verificaIdade(dataNascimento)) {
            throw new DataNascimentoMaiorQueDataAtual();
        }

        if (verificaAnoInicioAtividade(anoInicioAtividade)) {
            throw new AnoInicioAtividadeMenorQueDataAtual();
        }

        if (!verificaNomeESobrenome(nome)) {
            throw new DeveConterNomeESobrenome();
        }

//        if (verificaNomeJaExistente(nome)) {
//            throw new NomeJaExistente();
//        }

        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.statusCarreira = statusCarreira;
        this.anoInicioAtividade = anoInicioAtividade;
    }

    // Outros métodos
    public boolean verificaCampoObrigatorio() {
        return "".equals(nome) || null == dataNascimento || null == statusCarreira || null == anoInicioAtividade;
    }

    public boolean verificaIdade(LocalDate dataNascimento) {
        LocalDate now = LocalDate.now();
        long diferencaEmAnos = ChronoUnit.YEARS.between(dataNascimento, now);
        return diferencaEmAnos <= 0;
    }

    public boolean verificaAnoInicioAtividade(Integer anoInicioAtividade) {
        Integer anoAtual = LocalDate.now().getYear();
        return anoInicioAtividade >= anoAtual;
    }

//    public boolean verificaNomeJaExistente(String nome) {
//        return atorService.getFakeDatabase().recuperaAtores().contains(nome);
//    }

    public boolean verificaNomeESobrenome(String nome) {
        return nome.contains(" ");
    }




    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
