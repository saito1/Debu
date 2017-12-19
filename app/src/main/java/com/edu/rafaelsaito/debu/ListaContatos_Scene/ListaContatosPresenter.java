package com.edu.rafaelsaito.debu.ListaContatos_Scene;

import com.edu.rafaelsaito.debu.Modelo.ContatoEntity;
import com.edu.rafaelsaito.debu.Modelo.ListaContatoEntity;

import java.util.List;

/**
 * Created by mariana on 23/10/17.
 */

public class ListaContatosPresenter {

    ListaContatosView listaContatosView;
    ListaContatoEntity listaContatoEntity;
    private List<ContatoEntity> contatoList;

    public ListaContatosPresenter (ListaContatosView listaContatosView){
        this.listaContatosView = listaContatosView;
    }

//    public void clicarItem(int position){
//        listaContatosView.abrirItem(position);
//    }

    public void cadastrarContato() {
        listaContatosView.cadastro();
    }

    public void carregaLista() {
        if(listaContatoEntity.getContatos() != null)
            listaContatosView.carregaLista(listaContatoEntity.getContatos());
    }

    ContatoEntity getContatoId(int position) {
        return listaContatoEntity.getContatos().get(position);
    }
}
