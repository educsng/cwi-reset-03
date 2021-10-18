package br.com.cwi.reset.eduardocassanego.exception;

public class AnoInicioAtividadeMenorQueDataAtualException extends Exception {
    public AnoInicioAtividadeMenorQueDataAtualException(String objeto) {
        super(String.format("Ano início atividade inválido para o %s cadastrado", objeto));
    }
}
