public class ItensPorQuantidade {

    // Usado como estoque
    static int pao = 3600;
    static int fatiaDeTorta = 64;
    static int sanduiche = 20;
    static int leite = 20;
    static int cafe = 20;

    public static boolean estoqueInsuficiente(String item, int qtd) {
        if ("pao".equals(item)) {
            return qtd > ItensPorQuantidade.pao;
        }

        if ("torta".equals(item)) {
            return qtd > ItensPorQuantidade.fatiaDeTorta;
        }

        if ("sanduiche".equals(item)) {
            return qtd > ItensPorQuantidade.sanduiche;
        }

        if ("leite".equals(item)) {
            return qtd > ItensPorQuantidade.leite;
        }

        if ("cafe".equals(item)) {
            return qtd > ItensPorQuantidade.cafe;
        }

        return false;
    }

    public static void diminuirItem(String item, int quantidade) {
        if ("pao".equals(item)) {
            ItensPorQuantidade.pao -= quantidade * 60;
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
