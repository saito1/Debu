package com.edu.rafaelsaito.debu.Main_Scene;

import android.text.TextUtils;

/**
 * Created by mariana on 18/10/17.
 */

public class MainPresenter {

    MainView mainView = null;

    public MainPresenter(MainView mainView){
        this.mainView = mainView;
    }

    public void login(String username, String password){
        mainView.showLoading();
        if(TextUtils.isEmpty(username)){
            //set erro username
            mainView.setErrorUsername();
        }else if(TextUtils.isEmpty(password)){
            //set erro password
            mainView.setErrorPassword();
        }else if(TextUtils.equals(password, username)){
            //efetuar login
            mainView.efetuaLogin();
        }else{
            mainView.setErrorLogin();
        }
        mainView.hideLoading();
    }
}
