package br.com.cwi.reset.eduardocassanego;

import java.util.ArrayList;
import java.util.List;

public class FakeDatabase {

    //Atributos
    private List<Ator> atores = new ArrayList<>();
    private List<Diretor> diretores = new ArrayList<>();


    // Métodos
    public void persisteAtor(Ator ator) {
        atores.add(ator);
    }

    public List<Ator> recuperaAtores() {
        return atores;
    }

    public void persisteDiretor(Diretor diretor) {
        diretores.add(diretor);
    }

    public List<Diretor> recuperaDiretores() {
        return diretores;
    }

}
