package br.com.cwi.reset.projeto1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Exercicios1Test {

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

        Exercicios1 exercicios1 = new Exercicios1();

        Integer result = exercicios1.somarLista(listaInteiros);

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

        Exercicios1 exercicios1 = new Exercicios1();

        Integer result = exercicios1.somarLista(listaInteiros);

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

        Exercicios1 exercicios1 = new Exercicios1();

        Integer result = exercicios1.somarLista(listaInteiros);

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

        Exercicios1 exercicios1 = new Exercicios1();

        Integer result = exercicios1.somarLista(listaInteiros);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testSomarListaVazia() {
        // Arrange
        List<Integer> listaInteiros = new ArrayList<>();

        Integer expected = 0;

        Exercicios1 exercicios1 = new Exercicios1();

        Integer result = exercicios1.somarLista(listaInteiros);

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

        Exercicios1 exercicios1 = new Exercicios1();

        Double result = exercicios1.calcularMedia(listaInteiros);

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

        Exercicios1 exercicios1 = new Exercicios1();

        Double result = exercicios1.calcularMedia(listaInteiros);

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

        Exercicios1 exercicios1 = new Exercicios1();

        Double result = exercicios1.calcularMedia(listaInteiros);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testMediaListaVazia() {
        // Arrange
        List<Integer> listaInteiros = new ArrayList<>();

        Double expected = null;

        Exercicios1 exercicios1 = new Exercicios1();

        Double result = exercicios1.calcularMedia(listaInteiros);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testObterPalavraInvertidaAbacate() {
        // Arrange
        String palavra = "Abacate";

        String expected = "etacabA";

        Exercicios1 exercicios1 = new Exercicios1();

        String result = exercicios1.obterPalavraInvertida(palavra);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testObterPalavraInvertidaBanana() {
        // Arrange
        String palavra = "Banana";

        String expected = "ananaB";

        Exercicios1 exercicios1 = new Exercicios1();

        String result = exercicios1.obterPalavraInvertida(palavra);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testObterPalavraInvertidaPessego() {
        // Arrange
        String palavra = "Pessego";

        String expected = "ogesseP";

        Exercicios1 exercicios1 = new Exercicios1();

        String result = exercicios1.obterPalavraInvertida(palavra);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testObterPalavraInvertidaMorango() {
        // Arrange
        String palavra = "Morango";

        String expected = "ognaroM";

        Exercicios1 exercicios1 = new Exercicios1();

        String result = exercicios1.obterPalavraInvertida(palavra);

        Assertions.assertEquals(expected, result);
    }

}
