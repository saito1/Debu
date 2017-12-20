package com.edu.rafaelsaito.debu.Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContatoListEntity implements Serializable{

    public List<ContatoEntity> contatos = new ArrayList<ContatoEntity>();
    private static final long serialVersionUID = 46543445;

    public List<ContatoEntity> getContatos() {
        return contatos;
    }

    public void addContatos(ContatoEntity contato) {
        contatos.add(contato);
    }
}
