package br.com.cwi.reset.projeto1;

import java.util.Arrays;
import java.util.List;

public class Exercicios1 {

    public Integer somarLista(List<Integer> numeros) {

        Integer sum = 0;
        for (Integer num : numeros) {
            sum += num;
        }
        return sum;
    }

    public Double calcularMedia(List<Integer> numeros) {
        if (numeros.isEmpty()) {
            return 0.0;
        }
        return (double) (somarLista(numeros) / numeros.size());
    }

    public Integer obterMaiorNumero(List<Integer> numeros) {
        int maior = 0;
        for (int i = 0; i < numeros.size(); i++) {
            if (numeros.get(i) > maior) {
                maior = numeros.get(i);
            }
        }
        return maior;
    }

    public String obterPalavraInvertida(String palavra) {
        String palavraInvertida = "";
        for (int i = (palavra.length() - 1); i >= 0; i--) {
            palavraInvertida += palavra.charAt(i);
        }
        return palavraInvertida;
    }

    public List<Integer> ordenarLista(List<Integer> numeros) {
        Integer[] vet = numeros.toArray(new Integer[numeros.size()]);
        Integer aux = 0;

        for (int i = 0; i < numeros.size(); i++) {
            for (int j = 0; j < numeros.size() - 1; j++) {
                if (vet[j] > vet[j + 1]) {
                    aux = vet[j];
                    vet[j] = vet[j + 1];
                    vet[j + 1] = aux;
                }
            }
        }

        return Arrays.asList(vet);
    }
}

