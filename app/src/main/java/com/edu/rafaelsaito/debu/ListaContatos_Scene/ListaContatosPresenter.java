package com.edu.rafaelsaito.debu.ListaContatos_Scene;

import android.util.Log;

import com.edu.rafaelsaito.debu.Modelo.ContatoEntity;
import com.edu.rafaelsaito.debu.Modelo.ContatoListEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mariana on 23/10/17.
 */

public class ListaContatosPresenter {

    private ListaContatosView listaContatosView;
    private ContatoListEntity contatoList = new ContatoListEntity();

    public ListaContatosPresenter (ListaContatosView listaContatosView){
        this.listaContatosView = listaContatosView;
    }

    public void cadastrarContato() {
        listaContatosView.cadastro();
    }

    public void carregaLista (ContatoListEntity contatos) {
        if(contatos != null)
            listaContatosView.carregaLista(contatos);
    }

    ContatoEntity getContatoId(int position) {
        return contatoList.getContatos().get(position);
    }

    public void adicionaLista(ContatoEntity contatoEntity) {
        contatoEntity.setId(contatoList.getContatos().size() + 1);
        contatoList.getContatos().add(contatoEntity);
    }
}
