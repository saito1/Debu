package com.edu.rafaelsaito.debu.Main_Scene;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.edu.rafaelsaito.debu.ListaContatosActivity;
import com.edu.rafaelsaito.debu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.text_input_layout_username) TextInputLayout usernameTextInputLayout;
    @BindView(R.id.edit_text_username) TextInputEditText usernameEditText;
    @BindView(R.id.text_input_layout_password) TextInputLayout passwordTextInputLayout;
    @BindView(R.id.edit_text_password) TextInputEditText passwordEditText;
    @BindView(R.id.button_login) Button buttonLogin;

    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mainPresenter = new MainPresenter(this); //MainActivity.this
    }


    @OnTextChanged(R.id.edit_text_username)
    public void checaUsuario(){
        usernameTextInputLayout.setErrorEnabled(false);
        usernameTextInputLayout.setError("");
    }

    @OnTextChanged(R.id.edit_text_password)
    public void checaSenha(){
        usernameTextInputLayout.setErrorEnabled(false);
        usernameTextInputLayout.setError("");
    }

    @OnClick(R.id.button_login)
    public void login(){
        mainPresenter.login(usernameEditText.getText().toString(), passwordEditText.getText().toString());
    }

    @Override
    public void setErrorUsername() {
        usernameTextInputLayout.setErrorEnabled(true);
        usernameTextInputLayout.setError(getString(R.string.invalid_username));
    }

    @Override
    public void setErrorPassword() {
        passwordTextInputLayout.setErrorEnabled(true);
        passwordTextInputLayout.setError(getString(R.string.invalid_password));
    }

    @Override
    public void efetuaLogin() {
        Intent abrirListaContatosActivity = new Intent(MainActivity.this, ListaContatosActivity.class);
        startActivity(abrirListaContatosActivity);
    }
}
