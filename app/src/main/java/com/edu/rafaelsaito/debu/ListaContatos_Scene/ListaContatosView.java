package com.edu.rafaelsaito.debu.ListaContatos_Scene;

import com.edu.rafaelsaito.debu.Modelo.ContatoEntity;

import java.util.List;

public interface ListaContatosView {
    //void abrirItem(int position);
    void cadastro();

   //void carregaLista();

    void carregaLista(List<ContatoEntity> contatoList);
}
