package com.edu.rafaelsaito.debu.ListaContatos_Scene;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.edu.rafaelsaito.debu.CadastroContato_Scene.CadastroContatoActivity;
import com.edu.rafaelsaito.debu.ContatoDetails.ContatoDetailActivity;
import com.edu.rafaelsaito.debu.Modelo.ContatoEntity;
import com.edu.rafaelsaito.debu.Modelo.ContatoListEntity;
import com.edu.rafaelsaito.debu.R;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaContatosActivity extends AppCompatActivity implements ListaContatosView {

    @BindView(R.id.rv_contatos) RecyclerView rvContatos;
    @BindView(R.id.linear_layout_loading) LinearLayout loadingLayout;


    ListaContatosPresenter listaContatosPresenter;

    ContatoListEntity listcontatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);

        ButterKnife.bind(this);

        listaContatosPresenter = new ListaContatosPresenter(this);


        if(this.getIntent().getExtras() != null)
            listcontatos = (ContatoListEntity) this.getIntent().getSerializableExtra("contato");

        if(listcontatos!= null) {
            listaContatosPresenter.carregaLista(listcontatos);
        }
    }

    @Override
    public void carregaLista(final ContatoListEntity contatoList) {

        ListaContatosAdapter listaContatosAdapter = new ListaContatosAdapter(contatoList, this);
        listaContatosAdapter.setOnRecyclerViewSelected(new OnRecyclerViewSelected() {

           @Override
           public void onClick(View view, int position) {
               Intent openDetailActivity = new Intent(ListaContatosActivity.this, ContatoDetailActivity.class);
               openDetailActivity.putExtra("contato", (Serializable) contatoList.getContatos().get(position));
               startActivity(openDetailActivity);
           }
       });
        rvContatos.setAdapter(listaContatosAdapter);

        // criação do gerenciador de layouts
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());

        rvContatos.setLayoutManager(layoutManager);
        rvContatos.addItemDecoration(dividerItemDecoration);
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
        intentVaiPraCadastro.putExtra("contatos",(Serializable) listcontatos);
        startActivity(intentVaiPraCadastro);
    }
}
