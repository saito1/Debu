package com.edu.rafaelsaito.debu;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    TextInputLayout usernameTextInputLayout;
    TextInputEditText usernameEditText;
    TextInputLayout passwordTextInputLayout;
    TextInputEditText passwordEditText;
    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameTextInputLayout = (TextInputLayout) findViewById(R.id.text_input_layout_username);
        usernameEditText = (TextInputEditText) findViewById(R.id.edit_text_username);
        passwordTextInputLayout = (TextInputLayout) findViewById(R.id.text_input_layout_password);
        passwordEditText = (TextInputEditText) findViewById(R.id.edit_text_password);
        buttonLogin = (Button) findViewById(R.id.button_login); // faz um casting em Button porque findViewById retorna uma View

        buttonLogin.setOnClickListener(new View.OnClickListener() { //criação de classe anônima
            @Override
            public void onClick(View view) { //método obrigatorio
                if (TextUtils.isEmpty(usernameEditText.getText().toString())) {
                    usernameTextInputLayout.setErrorEnabled(true);
                    usernameTextInputLayout.setError(getString(R.string.invalid_username));
                    return;
                }

                if (TextUtils.isEmpty(passwordEditText.getText().toString())) {
                    passwordTextInputLayout.setErrorEnabled(true);
                    passwordTextInputLayout.setError(getString(R.string.invalid_password));
                    return;
                }

                Intent abrirListaContatosActivity = new Intent(MainActivity.this, ListaContatosActivity.class);
                startActivity(abrirListaContatosActivity);

            }
        });

        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //vazio
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                usernameTextInputLayout.setErrorEnabled(false);
                usernameTextInputLayout.setError("");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //vazio
            }
        });

    }
}
