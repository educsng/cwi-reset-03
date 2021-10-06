public class QuantidadeMinimaItem {

    public static boolean precisaReposicao(String item) {
        if ("pao".equals(item)) {
            return ItensPorQuantidade.pao < 600;
        }

        if ("torta".equals(item)) {
            return ItensPorQuantidade.fatiaDeTorta < 10;
        }

        if ("sanduiche".equals(item)) {
            return ItensPorQuantidade.sanduiche == 1;
        }

        if ("cafe".equals(item)) {
            return ItensPorQuantidade.leite < 12;
        }

        if ("leite".equals(item)) {
            return ItensPorQuantidade.cafe < 12;
        }

        return false;
    }

    public static boolean verificaEstoque(String item, int quantidade) {
        if ("pao".equals(item)) {
            return ItensPorQuantidade.pao < quantidade;
        }

        if ("torta".equals(item)) {
            return ItensPorQuantidade.fatiaDeTorta < quantidade;
        }

        if ("sanduiche".equals(item)) {
            return ItensPorQuantidade.sanduiche < quantidade;
        }

        if ("cafe".equals(item)) {
            return ItensPorQuantidade.leite < quantidade;
        }

        if ("leite".equals(item)) {
            return ItensPorQuantidade.cafe < quantidade;
        }

        return false;
    }
}
