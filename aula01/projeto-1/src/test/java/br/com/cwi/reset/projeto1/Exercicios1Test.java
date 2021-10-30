package br.com.cwi.reset.projeto1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Exercicios1Test {

    Exercicios1 exercicios1 = new Exercicios1();

    @Test
    public void testSomarCincoNumerosInteirosPositivos() {
        // Arrange
        List<Integer> listaInteiros = new ArrayList<>();
        listaInteiros.add(2);
        listaInteiros.add(2);
        listaInteiros.add(2);
        listaInteiros.add(2);
        listaInteiros.add(2);
        Integer expected = 10;

        // Action
        Integer result = exercicios1.somarLista(listaInteiros);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testSomarQuatroNumerosInteirosPositivosEUmNumeroInteiroNegativo() {
        // Arrange
        List<Integer> listaInteiros = new ArrayList<>();
        listaInteiros.add(2);
        listaInteiros.add(2);
        listaInteiros.add(2);
        listaInteiros.add(2);
        listaInteiros.add(-2);
        Integer expected = 6;

        // Action
        Integer result = exercicios1.somarLista(listaInteiros);

        // Assert
        Assertions.assertEquals(expected, result);
    }
    @Test
    public void testSomarTresElementosNegativos() {
        // Arrange
        List<Integer> listaInteiros = new ArrayList<>();
        listaInteiros.add(-2);
        listaInteiros.add(-2);
        listaInteiros.add(-2);
        Integer expected = -6;

        // Action
        Integer result = exercicios1.somarLista(listaInteiros);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testSomarTodosElementosComoZero() {
        // Arrange
        List<Integer> listaInteiros = new ArrayList<>();
        listaInteiros.add(0);
        listaInteiros.add(0);
        listaInteiros.add(0);
        listaInteiros.add(0);
        listaInteiros.add(0);
        listaInteiros.add(0);
        Integer expected = 0;

        // Action
        Integer result = exercicios1.somarLista(listaInteiros);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testSomarListaVazia() {
        // Arrange
        List<Integer> listaInteiros = new ArrayList<>();
        Integer expected = 0;

        // Action
        Integer result = exercicios1.somarLista(listaInteiros);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testMediaCincoNumerosInteirosPositivos() {
        // Arrange
        List<Integer> listaInteiros = new ArrayList<>();
        listaInteiros.add(5);
        listaInteiros.add(10);
        listaInteiros.add(2);
        listaInteiros.add(5);
        listaInteiros.add(4);
        Double expected = 5.0;

        // Action
        Double result = exercicios1.calcularMedia(listaInteiros);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testMediaQuatroNumerosInteirosPositivosEUmNumeroInteiroNegativo() {
        // Arrange
        List<Integer> listaInteiros = new ArrayList<>();
        listaInteiros.add(10);
        listaInteiros.add(10);
        listaInteiros.add(10);
        listaInteiros.add(10);
        listaInteiros.add(-10);
        Double expected = 6.0;

        // Action
        Double result = exercicios1.calcularMedia(listaInteiros);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testMediaTresElementosNegativos() {
        // Arrange
        List<Integer> listaInteiros = new ArrayList<>();
        listaInteiros.add(-10);
        listaInteiros.add(-10);
        listaInteiros.add(-10);
        Double expected = -10.0;

        // Action
        Double result = exercicios1.calcularMedia(listaInteiros);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testMediaListaVazia() {
        // Arrange
        List<Integer> listaInteiros = new ArrayList<>();
        Double expected = 0.0;

        // Action
        Double result = exercicios1.calcularMedia(listaInteiros);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testObterPalavraInvertidaAbacate() {
        // Arrange
        String palavra = "Abacate";
        String expected = "etacabA";

        // Action
        String result = exercicios1.obterPalavraInvertida(palavra);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testObterPalavraInvertidaBanana() {
        // Arrange
        String palavra = "Banana";
        String expected = "ananaB";

        // Action
        String result = exercicios1.obterPalavraInvertida(palavra);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testObterPalavraInvertidaPessego() {
        // Arrange
        String palavra = "Pessego";
        String expected = "ogesseP";

        // Action
        String result = exercicios1.obterPalavraInvertida(palavra);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testObterPalavraInvertidaMorango() {
        // Arrange
        String palavra = "Morango";
        String expected = "ognaroM";

        // Action
        String result = exercicios1.obterPalavraInvertida(palavra);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testOrdenarListaComCincoNumerosInteirosPositivos() {
        // Arrange
        List<Integer> numeros = Arrays.asList(10, 8, 5, 3, 1);
        List<Integer> expected = Arrays.asList(1, 3, 5, 8, 10);

        // Action
        List<Integer> result = exercicios1.ordenarLista(numeros);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testOrdenarListaComQuatroNumerosInteirosPositivosEDoisNegativos() {
        // Arrange
        List<Integer> numeros = Arrays.asList(10, 8, 5, 3, -1, -20);
        List<Integer> expected = Arrays.asList(-20, -1, 3, 5, 8, 10);

        // Action
        List<Integer> result = exercicios1.ordenarLista(numeros);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testOrdenarListaSomenteComZeros() {
        // Arrange
        List<Integer> numeros = Arrays.asList(0, 0, 0, 0, 0, 0, 0);
        List<Integer> expected = Arrays.asList(0, 0, 0, 0, 0, 0, 0);

        // Action
        List<Integer> result = exercicios1.ordenarLista(numeros);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testOrdenarListaVazia() {
        // Arrange
        List<Integer> numeros = new ArrayList<>();
        List<Integer> expected = Collections.emptyList();

        // Action
        List<Integer> result = exercicios1.ordenarLista(numeros);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testOrdenarListaJaOrdenada() {
        // Arrange
        List<Integer> numeros = Arrays.asList(0, 20, 1329, 99920);
        List<Integer> expected = Arrays.asList(0, 20, 1329, 99920);

        // Action
        List<Integer> result = exercicios1.ordenarLista(numeros);

        // Assert
        Assertions.assertEquals(expected, result);
    }

}
