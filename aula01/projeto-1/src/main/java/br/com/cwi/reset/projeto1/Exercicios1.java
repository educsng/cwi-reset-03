package br.com.cwi.reset.projeto1;

import java.util.Arrays;
import java.util.List;

public class Exercicios1 {

    public Integer somarLista(List<Integer> numeros) {

        int soma = 0;
        for (int i = 0; i < numeros.size(); i++) {
            soma = soma + numeros.get(i);
        }
        return soma;
    }

    public Double calcularMedia(List<Integer> numeros) {
        double media = somarLista(numeros) / numeros.size();
        return media;
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

