package br.com.cwi.reset.eduardocassanego.model;

public class GeradorIdAtor {
    private static Integer id = 1;

    public static Integer proximoId() {
        return id++;
    }

}