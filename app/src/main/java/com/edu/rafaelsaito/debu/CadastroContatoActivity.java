package com.edu.rafaelsaito.debu;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.edu.rafaelsaito.debu.DAO.ContatoDAO;
import com.edu.rafaelsaito.debu.Modelo.Contato;

public class CadastroContatoActivity extends AppCompatActivity {


    private TextInputLayout nameTextInputLayout;
    private TextInputLayout enderecoTextInputLayout;
    private TextInputLayout nome_responsavelTextInputLayout;
    private TextInputLayout telefoneTextInputLayout;
    private TextInputEditText nameEditText;
    private TextInputEditText enderecoEditText;
    private TextInputEditText nome_responsavelEditText;
    private TextInputEditText telefoneEditText;
    private Button botaoSalvar;
    private CadastroHelper helper;
    private Button botaoCamera;
    private Button botaoMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contato);

        nameTextInputLayout = (TextInputLayout) findViewById(R.id.text_input_layout_name);
        nameEditText = (TextInputEditText) findViewById(R.id.nome_contato);
        enderecoTextInputLayout = (TextInputLayout) findViewById(R.id.text_input_layout_adress);
        enderecoEditText = (TextInputEditText) findViewById(R.id.endereco_contato);
        nome_responsavelTextInputLayout = (TextInputLayout) findViewById(R.id.text_input_layout_email);
        nome_responsavelEditText = (TextInputEditText) findViewById(R.id.email);
        telefoneTextInputLayout = (TextInputLayout) findViewById(R.id.text_input_layout_phone);
        telefoneEditText = (TextInputEditText) findViewById(R.id.telefone_contato);
        botaoSalvar = (Button) findViewById(R.id.botao_salvar);
        botaoCamera = (Button) findViewById(R.id.botao_camera);
        botaoMapa = (Button) findViewById(R.id.botao_mapa);

        helper = new CadastroHelper(this);

        Intent intent = getIntent();
        Contato contato = (Contato) intent.getSerializableExtra("contato");
        if (contato != null) {
            helper.preencheCadastro(contato);
        }

        botaoCamera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intentCamera.resolveActivity(getPackageManager()) != null) {
                    startActivity(intentCamera);
                }else {
                    Toast toast = Toast.makeText(CadastroContatoActivity.this, "Impossível abrir o recurso", Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });

        botaoMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMapa = new Intent(Intent.ACTION_VIEW);
                intentMapa.setData(Uri.parse("geo:0,0?q=" + enderecoEditText.getText().toString()));
                if(intentMapa.resolveActivity(getPackageManager()) != null) {
                    startActivity(intentMapa);
                }else {
                    Toast toast = Toast.makeText(CadastroContatoActivity.this, "Impossível abrir o recurso", Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(nameEditText.getText().toString())) {
                    nameTextInputLayout.setErrorEnabled(true);
                    nameTextInputLayout.setError("Nome inválido");
                    return;
                }

                if (TextUtils.isEmpty(enderecoEditText.getText().toString())) {
                    enderecoTextInputLayout.setErrorEnabled(true);
                    enderecoTextInputLayout.setError("Endereço inválido");
                    return;
                }
                if (TextUtils.isEmpty(nome_responsavelEditText.getText().toString())) {
                    nome_responsavelTextInputLayout.setErrorEnabled(true);
                    nome_responsavelTextInputLayout.setError("Nome inválido");
                    return;
                }
                if (TextUtils.isEmpty(telefoneEditText.getText().toString())) {
                    telefoneTextInputLayout.setErrorEnabled(true);
                    telefoneTextInputLayout.setError("Telefone inválido");
                    return;
                }
                Contato contato = helper.getContato();
                ContatoDAO dao = new ContatoDAO(CadastroContatoActivity.this);

                if (contato.getId() != null) {
                    dao.altera(contato);
                } else {
                    dao.insere(contato);
                }

                dao.close();
                Toast.makeText(CadastroContatoActivity.this, "Contato " + contato.getNome() + " salvo!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //vazio
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                nameTextInputLayout.setErrorEnabled(false);
                nameTextInputLayout.setError("");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //vazio
            }
        });

        enderecoEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //vazio
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                nameTextInputLayout.setErrorEnabled(false);
                nameTextInputLayout.setError("");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //vazio
            }
        });

        nome_responsavelEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //vazio
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                nameTextInputLayout.setErrorEnabled(false);
                nameTextInputLayout.setError("");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //vazio
            }
        });

        telefoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //vazio
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                nameTextInputLayout.setErrorEnabled(false);
                nameTextInputLayout.setError("");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //vazio
            }
        });
    }
}
