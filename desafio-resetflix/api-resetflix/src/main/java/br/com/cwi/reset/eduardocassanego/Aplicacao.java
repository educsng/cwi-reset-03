package br.com.cwi.reset.eduardocassanego;

import br.com.cwi.reset.eduardocassanego.exception.*;
import br.com.cwi.reset.eduardocassanego.model.Ator;
import br.com.cwi.reset.eduardocassanego.model.Diretor;
import br.com.cwi.reset.eduardocassanego.model.StatusCarreira;
import br.com.cwi.reset.eduardocassanego.request.AtorRequest;
import br.com.cwi.reset.eduardocassanego.request.DiretorRequest;
import br.com.cwi.reset.eduardocassanego.service.AtorService;
import br.com.cwi.reset.eduardocassanego.service.DiretorService;


import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Aplicacao {

    public static void main(String[] args) {
        FakeDatabase fakeDatabase = new FakeDatabase();

        AtorService atorService = new AtorService(fakeDatabase);
        DiretorService diretorService = new DiretorService(fakeDatabase);

        // cadastrando atores
        AtorRequest atorRequest = new AtorRequest("Will Smith", LocalDate.of(1978, Month.SEPTEMBER, 25), StatusCarreira.EM_ATIVIDADE, 1986);
        AtorRequest atorRequest2 = new AtorRequest("Will Smith Jr", LocalDate.of(1970, Month.NOVEMBER, 27), StatusCarreira.APOSENTADO, 1999);
        DiretorRequest diretorRequest = new DiretorRequest("Christopher Nolan", LocalDate.of(1970, Month.SEPTEMBER, 27), 1980);
        DiretorRequest diretorRequest2 = new DiretorRequest("Christopher Nolan Jr", LocalDate.of(1970, Month.SEPTEMBER, 27), 1999);


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
//        try {
//            for (Ator ator : atorService.consultarAtores()) {
//                System.out.println("====Ator====");
//                System.out.println(ator.getNome());
//                System.out.println(ator.getDataNascimento());
//                System.out.println(ator.getStatusCarreira());
//                System.out.println(ator.getAnoInicioAtividade());
//            }
//        } catch (NenhumAtorCadastradoException e) {
//            System.out.println(e.getMessage());
//        }




        // Teste para criar diretor
        try {
            diretorService.cadastrarDiretor(diretorRequest);
            diretorService.cadastrarDiretor(diretorRequest2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

//        try {
//            for (Diretor diretores : diretorService.listarDiretores("c")) {
//                System.out.println(diretores.getNome());
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

//        try {
//            List<Diretor> diretores = fakeDatabase.recuperaDiretores();
//            System.out.println(diretorService.consultarDiretor(1).getNome());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

//        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
//        System.out.println("Deve conter 1 diretor, quantidade encontrada: " + diretores.size());
//        System.out.println("Primeiro diretor deve ser 'Christopher Nolan', valor encontrado: " + diretores.get(0).getNome());
//        System.out.println("Id do 1º diretor deve ser 1, valor encontrado: " + diretores.get(0).getId());


//        List<Ator> atores = fakeDatabase.recuperaAtores();
//        System.out.println("Deve conter 1 ator, quantidade encontrada: " + atores.size());
//        System.out.println("Primeiro ator deve ser 'Will Smith', valor encontrado: " + atores.get(0).getNome());
//        System.out.println("Segundo ator deve ser 'Leonardo DiCaprio', valor encontrado: " + atores.get(1).getNome());
//
    }
}
