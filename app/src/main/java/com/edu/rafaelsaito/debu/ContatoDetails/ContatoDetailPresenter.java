package com.edu.rafaelsaito.debu.ContatoDetails;

import com.edu.rafaelsaito.debu.Modelo.ContatoEntity;

/**
 * Created by Rafael Saito on 19/12/2017.
 */

public class ContatoDetailPresenter {

    public ContatoDetailPresenter(ContatoDetailView contatoDetailView) {
        this.contatoDetailView = contatoDetailView;
    }

    ContatoDetailView contatoDetailView;

    private ContatoEntity contatoDetails;

    public void getContatoDetails(ContatoEntity contatoEntity) {
        contatoDetailView.showLoading();
        contatoDetailView.showDetail(contatoEntity);
        contatoDetailView.hideLoading();
    }
}
