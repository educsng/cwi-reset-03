package br.com.cwi.reset.eduardocassanego;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Locale;

public class Aplicacao {

    public static void main(String[] args) {
        FakeDatabase fakeDatabase = new FakeDatabase();

        AtorService atorService = new AtorService(fakeDatabase);

        // cadastrando atores
        String nome = "Will Smith";
        LocalDate dataNascimento = LocalDate.of(1978, Month.SEPTEMBER, 25);
        StatusCarreira statusCarreira = StatusCarreira.EM_ATIVIDADE;
        Integer anoInicioAtividade = 1986;


        String nome2 = "Leonardo DiCaprio";
        LocalDate dataNascimento2 = LocalDate.of(1965, Month.JANUARY, 22);
        StatusCarreira statusCarreira2 = StatusCarreira.EM_ATIVIDADE;
        Integer anoInicioAtividade2 = 2000;


        AtorRequest atorRequest = new AtorRequest(nome, dataNascimento, statusCarreira, anoInicioAtividade);
        AtorRequest atorRequest2 = new AtorRequest(nome2, dataNascimento2, statusCarreira2, anoInicioAtividade2);


        atorService.criarAtor(atorRequest);
        atorService.criarAtor(atorRequest2);


        List<Ator> atores = fakeDatabase.recuperaAtores();
        List<AtorEmAtividade> atoresEmAtividade = atorService.listarAtoresEmAtividade("k");
        for (AtorEmAtividade ator : atoresEmAtividade) {
            System.out.println("ID: " + ator.getId());
            System.out.println("Nome: " + ator.getNome());
            System.out.println("Data Nascimento: " + ator.getDataNascimento());
        }


        System.out.println(atorService.consultarAtor(1).getNome());

        System.out.println("Deve conter 2 atores, quantidade encontrada: " + atores.size());
        System.out.println("Primeiro ator deve ser 'Will Smith', valor encontrado: " + atores.get(0).getNome());
        System.out.println("Segundo ator deve ser 'Leonardo DiCaprio', valor encontrado: " + atores.get(1).getNome());

    }
}
