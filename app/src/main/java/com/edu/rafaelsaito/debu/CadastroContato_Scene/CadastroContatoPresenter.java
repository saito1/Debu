package com.edu.rafaelsaito.debu.CadastroContato_Scene;

import com.edu.rafaelsaito.debu.Modelo.ContatoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mariana on 23/10/17.
 */

public class CadastroContatoPresenter {

    CadastroContatoView cadastroContatoView;
    private List<ContatoEntity> contatoList;

    public CadastroContatoPresenter(CadastroContatoView cadastroContatoView){
        this.cadastroContatoView = cadastroContatoView;
    }

    public void ligarCamera(){
        cadastroContatoView.camera();
    }

    public void abrirMapa(){
        cadastroContatoView.mapa();
    }

    public void cadastrarContato() {
        cadastroContatoView.salvar();
    }
}
