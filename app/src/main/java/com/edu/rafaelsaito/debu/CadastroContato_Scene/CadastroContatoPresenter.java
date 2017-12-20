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
        cadastroContatoView.showLoading();
        cadastroContatoView.camera();
        cadastroContatoView.hideLoading();
    }

    public void abrirMapa(){
        cadastroContatoView.showLoading();
        cadastroContatoView.mapa();
        cadastroContatoView.hideLoading();
    }

    public void cadastrarContato() {
        cadastroContatoView.showLoading();
        cadastroContatoView.salvar();
        cadastroContatoView.hideLoading();
    }
}
