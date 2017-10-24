package com.edu.rafaelsaito.debu.CadastroContato_Scene;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import com.edu.rafaelsaito.debu.DAO.ContatoDAO;
import com.edu.rafaelsaito.debu.Modelo.CadastroHelper;
import com.edu.rafaelsaito.debu.Modelo.Contato;
import com.edu.rafaelsaito.debu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class CadastroContatoActivity extends AppCompatActivity implements CadastroContatoView{

    @BindView(R.id.text_input_layout_name) TextInputLayout nameTextInputLayout;
    @BindView(R.id.nome_contato) TextInputEditText nameEditText;
    @BindView(R.id.text_input_layout_adress) TextInputLayout enderecoTextInputLayout;
    @BindView(R.id.endereco_contato) TextInputEditText enderecoEditText;
    @BindView(R.id.text_input_layout_email) TextInputLayout nome_responsavelTextInputLayout;
    @BindView(R.id.email) TextInputEditText nome_responsavelEditText;
    @BindView(R.id.text_input_layout_phone) TextInputLayout telefoneTextInputLayout;
    @BindView(R.id.telefone_contato) TextInputEditText telefoneEditText;
    @BindView(R.id.botao_salvar) Button botaoSalvar;
    @BindView(R.id.botao_camera) Button botaoCamera;
    @BindView(R.id.botao_mapa) Button botaoMapa;

    private CadastroHelper helper;

    CadastroContatoPresenter cadastroContatoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contato);

        ButterKnife.bind(this);

        cadastroContatoPresenter = new CadastroContatoPresenter(this);

        helper = new CadastroHelper(this);

        final Intent intent = getIntent();
        Contato contato = (Contato) intent.getSerializableExtra("contato");
        if (contato != null) {
            helper.preencheCadastro(contato);
        }
    }

    @OnClick(R.id.botao_camera)
    public void ligarCamera(){
        cadastroContatoPresenter.ligarCamera();
    }

    @Override
    public void camera(){
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intentCamera.resolveActivity(getPackageManager()) != null) {
            startActivity(intentCamera);
        }else {
            Toast toast = Toast.makeText(CadastroContatoActivity.this, "Impossível abrir o recurso", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @OnClick(R.id.botao_mapa)
    public void abrirMapa(){
        cadastroContatoPresenter.abrirMapa();
    }

    @Override
    public void mapa(){
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
        intentMapa.setData(Uri.parse("geo:0,0?q=" + enderecoEditText.getText().toString()));
        if(intentMapa.resolveActivity(getPackageManager()) != null) {
            startActivity(intentMapa);
        }else {
            Toast toast = Toast.makeText(CadastroContatoActivity.this, "Impossível abrir o recurso", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @OnClick(R.id.botao_salvar)
    public void salvarContato(){
        cadastroContatoPresenter.salvarContato();
    }

    @Override
    public void salvar(){
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

    @OnTextChanged(R.id.nome_contato)
    public void checaNome(){
        nameTextInputLayout.setErrorEnabled(false);
        nameTextInputLayout.setError("");
    }

    @OnTextChanged(R.id.endereco_contato)
    public void checaEndereco(){
        enderecoTextInputLayout.setErrorEnabled(false);
        enderecoTextInputLayout.setError("");
    }

    @OnTextChanged(R.id.email)
    public void checaNomeResponsavel(){
        nome_responsavelTextInputLayout.setErrorEnabled(false);
        nome_responsavelTextInputLayout.setError("");
    }

    @OnTextChanged(R.id.telefone_contato)
    public void checaTelefone(){
        telefoneTextInputLayout.setErrorEnabled(false);
        telefoneTextInputLayout.setError("");
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        //vazio
    }

    @Override
    public void afterTextChanged(Editable editable) {
        //vazio
    }
}
