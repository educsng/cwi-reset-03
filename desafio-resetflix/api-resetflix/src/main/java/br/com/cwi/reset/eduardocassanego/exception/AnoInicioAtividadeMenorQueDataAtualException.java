package br.com.cwi.reset.eduardocassanego.exception;

public class AnoInicioAtividadeMenorQueDataAtualException extends Exception {
    public AnoInicioAtividadeMenorQueDataAtualException() {
        super("Ano início atividade inválido para o ator cadastrado");
    }
}
