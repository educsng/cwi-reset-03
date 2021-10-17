package br.com.cwi.reset.eduardocassanego;

import br.com.cwi.reset.eduardocassanego.exception.NenhumAtorCadastradoException;
import br.com.cwi.reset.eduardocassanego.model.Ator;
import br.com.cwi.reset.eduardocassanego.model.StatusCarreira;
import br.com.cwi.reset.eduardocassanego.request.AtorRequest;
import br.com.cwi.reset.eduardocassanego.service.AtorService;


import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Aplicacao {

    public static void main(String[] args) {
        FakeDatabase fakeDatabase = new FakeDatabase();

        AtorService atorService = new AtorService(fakeDatabase);

        // cadastrando atores
        AtorRequest atorRequest = new AtorRequest("Will Smith", LocalDate.of(1978, Month.SEPTEMBER, 25), StatusCarreira.EM_ATIVIDADE, 1986);
        AtorRequest atorRequest2 = new AtorRequest("Will Smith Jr", LocalDate.of(1970, Month.NOVEMBER, 27), StatusCarreira.APOSENTADO, 1999);


        //Teste para criar atores
//        try {
//            atorService.criarAtor(atorRequest);
//            atorService.criarAtor(atorRequest2);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }


        //Teste para listar ator em atividade
//        try {
//            List<AtorEmAtividade> atoresEmAtividade = atorService.listarAtoresEmAtividade("l");
//            for (AtorEmAtividade ator : atoresEmAtividade) {
//                System.out.println("ID: " + ator.getId());
//                System.out.println("Nome: " + ator.getNome());
//                System.out.println("Data Nascimento: " + ator.getDataNascimento());
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }


        //Teste para consultar ator por ID
//        try {
//            System.out.println("Encontrei o ator " + atorService.consultarAtor(1).getNome());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }


        // Teste para consultar todos os atores
        try {
            for (Ator ator : atorService.consultarAtores()) {
                System.out.println("====Ator====");
                System.out.println(ator.getNome());
                System.out.println(ator.getDataNascimento());
                System.out.println(ator.getStatusCarreira());
                System.out.println(ator.getAnoInicioAtividade());
            }
        } catch (NenhumAtorCadastradoException e) {
            System.out.println(e.getMessage());
        }


//        List<Ator> atores = fakeDatabase.recuperaAtores();
//        System.out.println("Deve conter 1 ator, quantidade encontrada: " + atores.size());
//        System.out.println("Primeiro ator deve ser 'Will Smith', valor encontrado: " + atores.get(0).getNome());
//        System.out.println("Segundo ator deve ser 'Leonardo DiCaprio', valor encontrado: " + atores.get(1).getNome());
//
    }
}
