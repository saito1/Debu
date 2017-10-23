package com.edu.rafaelsaito.debu.ListaContatos_Scene;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.edu.rafaelsaito.debu.Main_Scene.MainView;

/**
 * Created by mariana on 23/10/17.
 */

public class ListaContatosPresenter {

    ListaContatosView listaContatosView = null;

    public ListaContatosPresenter (ListaContatosView listaContatosView){
        this.listaContatosView = listaContatosView;
    }

    public void cadastro(){
        listaContatosView.cadastrarContato();
    }

    public void clicarItem(int position){
        listaContatosView.abrirItem(position);
    }
}
