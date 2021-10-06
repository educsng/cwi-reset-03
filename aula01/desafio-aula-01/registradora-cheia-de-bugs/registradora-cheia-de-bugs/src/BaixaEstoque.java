public class BaixaEstoque {

    static void diminuirItem(String item, int quantidade) {
        if ("pao".equals(item)) {
            ItensPorQuantidade.pao -= quantidade;
        }
        if ("torta".equals(item)) {
            ItensPorQuantidade.fatiaDeTorta -= quantidade;
        }
        if ("sanduiche".equals(item)) {
            ItensPorQuantidade.sanduiche -= quantidade;
        }

        if ("leite".equals(item)) {
            ItensPorQuantidade.leite -= quantidade;
        }

        if ("cafe".equals(item)) {
            ItensPorQuantidade.cafe -= quantidade;
        }
    }

}
