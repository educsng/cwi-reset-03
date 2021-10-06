import java.util.Locale;
import java.util.Scanner;

public class Registradora {

    public static void main(String[] args) {

        // métodos

        primeiroBug();

        segundoBug();

        terceiroBug();
//
//        quartoBug();
//
//        quintoBug();
//
//        sextoBug();
    }

    private static double registrarItem(String item, int quantidade) {
        Scanner sc = new Scanner(System.in);

        double precoItem = RelacaoPesoPreco.retornaPrecoProduto(item, quantidade);

        if (QuantidadeMinimaItem.verificaEstoque(item, quantidade)) {
            System.out.println("Produto em falta.");
            System.out.println("Deseja aguardar reposição: [S ou N]");
            String resposta = sc.nextLine().toUpperCase(Locale.ROOT);
            if (resposta.equals("S")) {
                if ("pao".equals(item) || "sanduiche".equals(item) || "torta".equals(item)) {
                    if (!DataProjeto.cozinhaEmFuncionamento()) {
                        System.out.println("Desculpe, a cozinha está fechada!");
                    } else {
                        System.out.println("OK, vou pedir reposição para a cozinha.");
                        ReposicaoCozinha.reporItem(item);
                    }
                }
                if ("leite".equals(item) || "cafe".equals(item)) {
                    System.out.println("OK, vou pedir reposição para o meu fornecedor.");
                    ReposicaoFornecedor.reporItem(item);
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
        BaixaEstoque.diminuirItem(item, quantidade);
        return precoItem;
    }

    private static void primeiroBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "sanduiche";
        int quantidade = 4;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
        System.out.println("Estoque atual: " + ItensPorQuantidade.sanduiche);
    }

    private static void segundoBug() {
        DataProjeto.criarDataComCozinhaEncerradaMasComDiaUtil();
        String item = "torta";
        int quantidade = 10;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
        System.out.println("Estoque atual: " + ItensPorQuantidade.fatiaDeTorta);
    }

    private static void terceiroBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "cafe";
        int quantidade = 40;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
        System.out.println("Estoque atual: " + ItensPorQuantidade.cafe);

    }

    private static void quartoBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        // Cliente 1
        String item = "sanduiche";
        int quantidade = 20;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));

        // Cliente 2
        String item2 = "sanduiche";
        int quantidade2 = 5;

        double precoTotal2 = registrarItem(item2, quantidade2);

        System.out.println(String.format("Valor total: %.2f", precoTotal2));
    }

    private static void quintoBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "pao";
        int quantidade = 10;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
    }

    private static void sextoBug() {
        DataProjeto.criarDataComCozinhaEncerradaSemDiaUtil();
        // Cliente 1
        String item = "sanduiche";
        int quantidade = 20;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));

        // Cliente 2
        String item2 = "sanduiche";
        int quantidade2 = 5;

        double precoTotal2 = registrarItem(item2, quantidade2);

        System.out.println(String.format("Valor total: %.2f", precoTotal2));
    }

}
