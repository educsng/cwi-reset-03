import java.util.Locale;
import java.util.Scanner;

public class Registradora {

    public static void main(String[] args) {

        // métodos

        primeiroBug();

//        segundoBug();
//
//        terceiroBug();
//
//        quartoBug();
//
//        quintoBug();
//
//        sextoBug();
    }

    private static double registrarItem(String item, int quantidade) {
        Scanner sc = new Scanner(System.in);

        boolean itensDependeCozinha = "pao".equals(item) || "sanduiche".equals(item) || "torta".equals(item);
        boolean itensDependeFornecedor = "leite".equals(item) || "cafe".equals(item);


        if (QuantidadeMinimaItem.verificaEstoque(item, quantidade)) {
            System.out.println("Produto em falta.");
            System.out.println("Deseja aguardar reposição: [S ou N]");
            String resposta = sc.nextLine().toUpperCase(Locale.ROOT);
            if (resposta.equals("S")) {
                if (itensDependeCozinha) {
                    if (!DataProjeto.cozinhaEmFuncionamento()) {
                        System.out.println("Desculpe, a cozinha está fechada!");
                        throw new RuntimeException("Obrigado.");
                    } else {
                        System.out.println("OK, vou pedir reposição para a cozinha.");
                        while (ItensPorQuantidade.estoqueInsuficiente(item, quantidade)) {
                            ReposicaoCozinha.reporItem(item);
                        }
                    }
                }
                if (itensDependeFornecedor) {
                    System.out.println("OK, vou pedir reposição para o meu fornecedor.");
                    while (ItensPorQuantidade.estoqueInsuficiente(item, quantidade)) {
                        ReposicaoFornecedor.reporItem(item);
                    }
                }

            } else {
                System.out.println("Desculpa, não poderemos lhe atender então.");
            }

        } else if (QuantidadeMinimaItem.precisaReposicao(item)) {
            if ("pao".equals(item) || "sanduiche".equals(item) || "torta".equals(item)) {
                if (!DataProjeto.cozinhaEmFuncionamento()) {
                    System.out.println("Cozinha fechada!");
                }
                ReposicaoCozinha.reporItem(item);
            }

            if ("leite".equals(item) || "cafe".equals(item)) {
                ReposicaoFornecedor.reporItem(item);
            }
        }
        ItensPorQuantidade.diminuirItem(item, quantidade);

        double precoItem = RelacaoPesoPreco.retornaPrecoProduto(item, quantidade);
        return precoItem;
    }

    private static void primeiroBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "sanduiche";
        int quantidade = 4;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
        System.out.println("Estoque atual de " + item + ": " + ItensPorQuantidade.sanduiche);
    }

    private static void segundoBug() {
        DataProjeto.criarDataComCozinhaEncerradaMasComDiaUtil();
        String item = "torta";
        int quantidade = 10;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
        System.out.println("Estoque atual de " + item + ": " + ItensPorQuantidade.fatiaDeTorta);
    }

    private static void terceiroBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "cafe";
        int quantidade = 40;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
        System.out.println("Estoque atual de " + item + ": " + ItensPorQuantidade.cafe);

    }

    private static void quartoBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        // Cliente 1
        String item = "sanduiche";
        int quantidade = 20;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
        System.out.println("Estoque atual de " + item + ": " + ItensPorQuantidade.sanduiche);

        // Cliente 2
        String item2 = "sanduiche";
        int quantidade2 = 5;

        double precoTotal2 = registrarItem(item2, quantidade2);

        System.out.println(String.format("Valor total: %.2f", precoTotal2));
        System.out.println("Estoque atual de " + item + ": " + ItensPorQuantidade.sanduiche);

    }

    private static void quintoBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "pao";
        int quantidade = 10;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
        System.out.println("Estoque atual de " + item + ": " + ItensPorQuantidade.pao);

    }

    private static void sextoBug() {
        DataProjeto.criarDataComCozinhaEncerradaSemDiaUtil();
        // Cliente 1
        String item = "sanduiche";
        int quantidade = 20;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
        System.out.println("Estoque atual de " + item + ": " + ItensPorQuantidade.sanduiche);


        // Cliente 2
        String item2 = "sanduiche";
        int quantidade2 = 5;

        double precoTotal2 = registrarItem(item2, quantidade2);

        System.out.println(String.format("Valor total: %.2f", precoTotal2));
        System.out.println("Estoque atual de " + item + ": " + ItensPorQuantidade.sanduiche);

    }

}
