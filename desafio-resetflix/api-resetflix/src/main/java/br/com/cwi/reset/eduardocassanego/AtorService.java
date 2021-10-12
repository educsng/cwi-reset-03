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
        Ator ator = new Ator(GeradorIdAtor.proximoId(), atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());
        fakeDatabase.persisteAtor(ator);

    }

}
