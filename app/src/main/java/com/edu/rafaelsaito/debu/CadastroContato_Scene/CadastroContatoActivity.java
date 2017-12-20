package com.edu.rafaelsaito.debu.CadastroContato_Scene;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.FileProvider;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.edu.rafaelsaito.debu.ListaContatos_Scene.ListaContatosActivity;
import com.edu.rafaelsaito.debu.Modelo.ContatoEntity;
import com.edu.rafaelsaito.debu.Modelo.ContatoListEntity;
import com.edu.rafaelsaito.debu.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class CadastroContatoActivity extends AppCompatActivity implements CadastroContatoView{

    @BindView(R.id.text_input_layout_name) TextInputLayout nameTextInputLayout;
    @BindView(R.id.nome_contato) TextInputEditText nameEditText;
    @BindView(R.id.text_input_layout_adress) TextInputLayout enderecoTextInputLayout;
    @BindView(R.id.endereco_contato) TextInputEditText enderecoEditText;
    @BindView(R.id.text_input_layout_email) TextInputLayout emailTextInputLayout;
    @BindView(R.id.email) TextInputEditText emailEditText;
    @BindView(R.id.text_input_layout_phone) TextInputLayout telefoneTextInputLayout;
    @BindView(R.id.telefone_contato) TextInputEditText telefoneEditText;
    @BindView(R.id.botao_camera) ImageButton imagem;

    CadastroContatoPresenter cadastroContatoPresenter;
    String selectedImagePath;
    String caminho_foto;

    private static final int REQUEST_CAMERA = 123;
    private Context context;
    ContatoListEntity contatoListEntity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contato);

        ButterKnife.bind(this);
        selectedImagePath = new String();

        contatoListEntity = (ContatoListEntity) this.getIntent().getSerializableExtra("contatos");

        cadastroContatoPresenter = new CadastroContatoPresenter(this);
    }

    @OnClick(R.id.botao_camera)
    public void ligarCamera(){
        cadastroContatoPresenter.ligarCamera();
    }

    @Override
    public void camera(){
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intentCamera.resolveActivity(getPackageManager()) != null) {
            selectedImagePath = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
            File arquivoFoto = new File(selectedImagePath);
            Uri fileUri = FileProvider.getUriForFile(this, "com.edu.rafaelsaito.debu.fileprovider", arquivoFoto);
            intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

            startActivityForResult(intentCamera, REQUEST_CAMERA);
        }else {
            Toast toast = Toast.makeText(CadastroContatoActivity.this, "Impossível abrir o recurso", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            try {

                Glide.with(imagem.getContext()).load(selectedImagePath).asBitmap().centerCrop().into(new BitmapImageViewTarget(imagem) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(imagem.getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imagem.setImageDrawable(circularBitmapDrawable);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Unable to open image", Toast.LENGTH_LONG).show();
            }
            caminho_foto = selectedImagePath;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contato, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_salvar:
                cadastroContatoPresenter.cadastrarContato();
            default:
                return super.onOptionsItemSelected(item);
        }
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
        if (TextUtils.isEmpty(emailEditText.getText().toString())) {
            emailTextInputLayout.setErrorEnabled(true);
            emailTextInputLayout.setError("Email inválido");
            return;
        }
        if (TextUtils.isEmpty(telefoneEditText.getText().toString())) {
            telefoneTextInputLayout.setErrorEnabled(true);
            telefoneTextInputLayout.setError("Telefone inválido");
            return;
        }

        ContatoEntity contato = new ContatoEntity();
        contato.setName(nameEditText.getText().toString());
        contato.setAddress(enderecoEditText.getText().toString());
        contato.setTelephone(telefoneEditText.getText().toString());
        contato.setImage(caminho_foto);

        if(contatoListEntity == null)
            contatoListEntity = new ContatoListEntity();

        contatoListEntity.addContatos(contato);

        Intent intent = new Intent(CadastroContatoActivity.this, ListaContatosActivity.class);
        intent.putExtra("contato", (Serializable) contatoListEntity);
        startActivity(intent);
        //finish();
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
    public void checaEmail(){
        emailTextInputLayout.setErrorEnabled(false);
        emailTextInputLayout.setError("");
    }

    @OnTextChanged(R.id.telefone_contato)
    public void checaTelefone(){
        telefoneTextInputLayout.setErrorEnabled(false);
        telefoneTextInputLayout.setError("");
    }
}
