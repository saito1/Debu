package com.edu.rafaelsaito.debu.ListaContatos_Scene;

import com.edu.rafaelsaito.debu.Modelo.ContatoEntity;
import com.edu.rafaelsaito.debu.Modelo.ContatoListEntity;

import java.util.List;

public interface ListaContatosView {
    void carregaLista(ContatoListEntity contatoList);

    void cadastro();
}
