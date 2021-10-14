package br.com.cwi.reset.eduardocassanego;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AtorService {

    // Atributos
    private FakeDatabase fakeDatabase;

    // Construtor padrão
    public AtorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }


    // Demais métodos da classe
    public void criarAtor(AtorRequest atorRequest) {
        try {
            Ator ator = new Ator(GeradorIdAtor.proximoId(), atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());
            fakeDatabase.persisteAtor(ator);
            System.out.println("Ator '" + ator.getNome() + "' adicionado com sucesso.");
        } catch (CampoObrigatorioNaoInformado | DataNascimentoMaiorQueDataAtual | AnoInicioAtividadeMenorQueDataAtual | DeveConterNomeESobrenome | NomeJaExistente e) {
            System.out.println(e.getMessage());
        }
    }

    public List<AtorEmAtividade> listarAtoresEmAtividade(String filtroNome) {
        List<AtorEmAtividade> atorEmAtividade = new ArrayList<>();
        List<Ator> atoresEmAtividade = new ArrayList<>();
        try {
            if (fakeDatabase.recuperaAtores().size() <= 0) {
                throw new NenhumAtorCadastrado();
            } else {
                for (Ator ator : fakeDatabase.recuperaAtores()) {
                    if (ator.getNome().contains(filtroNome.toLowerCase(Locale.ROOT))) {
                        System.out.println("Encontrei com o filtro o ator " + ator.getNome());
                        if (ator.getStatusCarreira().equals(StatusCarreira.EM_ATIVIDADE)) {
                            atoresEmAtividade.add(ator);
                            AtorEmAtividade atorEmAtividade1 = new AtorEmAtividade(ator.getId(), ator.getNome(), ator.getDataNascimento());
                            atorEmAtividade.add(atorEmAtividade1);
                        } else {
                            throw new FiltroDeAtorNaoEncontrado(filtroNome);
                        }
                    } else {
                        throw new FiltroDeAtorNaoEncontrado(filtroNome);
                    }
                }
            }
        } catch (FiltroDeAtorNaoEncontrado | NenhumAtorCadastrado e) {
            System.out.println(e.getMessage());
        }
        return atorEmAtividade;
    }

    public Ator consultarAtor(Integer id) {
        try {
            if (id == null) {
                throw new CampoObrigatorioNaoInformado();
            } else {
                for (Ator ator : fakeDatabase.recuperaAtores()) {
                    if (id.equals(ator.getId())) {
                        return ator;
                    } else {
                        throw new IdNaoCorresponde(id);
                    }
                }
            }
        } catch (CampoObrigatorioNaoInformado | IdNaoCorresponde e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
