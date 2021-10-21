package br.com.cwi.reset.eduardocassanego.model;

public class GeradorIdFilme {
    private static Integer id = 1;

    public static Integer proximoId() {
        return id++;
    }

}
