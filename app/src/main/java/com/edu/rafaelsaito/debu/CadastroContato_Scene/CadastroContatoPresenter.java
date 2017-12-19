package com.edu.rafaelsaito.debu.CadastroContato_Scene;

import com.edu.rafaelsaito.debu.Main_Scene.MainView;

/**
 * Created by mariana on 23/10/17.
 */

public class CadastroContatoPresenter {

    CadastroContatoView cadastroContatoView = null;

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

    public void getInformacoes(long id_contato) {
    }
}
