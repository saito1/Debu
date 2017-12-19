package com.edu.rafaelsaito.debu.ListaContatos_Scene;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.edu.rafaelsaito.debu.CadastroContato_Scene.CadastroContatoActivity;
import com.edu.rafaelsaito.debu.Modelo.ContatoEntity;
import com.edu.rafaelsaito.debu.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaContatosActivity extends AppCompatActivity implements ListaContatosView {

    @BindView(R.id.rv_contatos) RecyclerView rvContatos;

    ListaContatosPresenter listaContatosPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);

        ButterKnife.bind(this);

        listaContatosPresenter = new ListaContatosPresenter(this);
        listaContatosPresenter.carregaLista();
    }

    @Override
    public void carregaLista(final List<ContatoEntity> contatoList) {

        final ListaContatosAdapter listaContatosAdapter = new ListaContatosAdapter(contatoList, this);

        listaContatosAdapter.setOnRecyclerViewSelected(new OnRecyclerViewSelected() {
           @Override
           public void onClick(View view, int position) {
               Intent openDetailActivity = new Intent(ListaContatosActivity.this, CadastroContatoActivity.class);
               ContatoEntity contatoEntity = listaContatosPresenter.getContatoId(position);
               openDetailActivity.putExtra("contato", contatoEntity);
               startActivity(openDetailActivity);
           }
       });
        rvContatos.setAdapter(listaContatosAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvContatos.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                listaContatosPresenter.cadastrarContato();

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void cadastro() {
        Intent intentVaiPraCadastro = new Intent(ListaContatosActivity.this, CadastroContatoActivity.class);
        startActivity(intentVaiPraCadastro);
    }

}
