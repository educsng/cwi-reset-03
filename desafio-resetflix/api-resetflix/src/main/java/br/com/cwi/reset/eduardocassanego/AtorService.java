package br.com.cwi.reset.eduardocassanego;

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
            System.out.println("Ator adicionado com sucesso.");
        } catch (CampoObrigatorioNaoInformado e) {
            System.out.println(e.getMessage());
        } catch (DataNascimentoMaiorQueDataAtual e) {
            System.out.println(e.getMessage());
        } catch (AnoInicioAtividadeMenorQueDataAtual e) {
            System.out.println(e.getMessage());
        } catch (DeveConterNomeESobrenome e) {
            System.out.println(e.getMessage());
        } catch (NomeJaExistente e) {
            System.out.println(e.getMessage());
        }


    }

    public FakeDatabase getFakeDatabase() {
        return fakeDatabase;
    }

}
