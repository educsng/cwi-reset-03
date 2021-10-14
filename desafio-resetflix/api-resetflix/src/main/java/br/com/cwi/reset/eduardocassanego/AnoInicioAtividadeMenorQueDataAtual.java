package br.com.cwi.reset.eduardocassanego;

public class AnoInicioAtividadeMenorQueDataAtual extends Exception {
    public AnoInicioAtividadeMenorQueDataAtual() {
        super("Ano início atividade inválido para o ator cadastrado");
    }
}
