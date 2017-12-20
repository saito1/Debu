package com.edu.rafaelsaito.debu.ContatoDetails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.rafaelsaito.debu.Modelo.ContatoEntity;
import com.edu.rafaelsaito.debu.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContatoDetailActivity extends AppCompatActivity implements ContatoDetailView{

    @BindView(R.id.image_view_header) ImageView imgHeader;
    @BindView(R.id.text_view_nome) TextView tvNome;
    @BindView(R.id.text_view_endereco) TextView tvEndereco;
    @BindView(R.id.text_view_telefone) TextView tvTelefone;

    ContatoDetailPresenter contatoDetailPresenter;

    ContatoEntity contatoEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato_detail);

        ButterKnife.bind(this);

        contatoDetailPresenter = new ContatoDetailPresenter(this);

        contatoEntity = (ContatoEntity) getIntent().getSerializableExtra("contato");

        contatoDetailPresenter.getContatoDetails(contatoEntity);
    }


    @Override
    public void showDetail(ContatoEntity contatoDetailEntity) {
        tvEndereco.setText(contatoDetailEntity.getAddress());
        tvNome.setText(contatoDetailEntity.getName());
        tvTelefone.setText(contatoDetailEntity.getTelephone());
        Picasso.with(this)
                .load(contatoDetailEntity.getImage())
                .centerCrop()
                .fit()
                .into(imgHeader);
        setTitle(contatoDetailEntity.getName());
    }
}
