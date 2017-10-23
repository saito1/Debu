package com.edu.rafaelsaito.debu.ListaContatos_Scene;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.edu.rafaelsaito.debu.CadastroContatoActivity;
import com.edu.rafaelsaito.debu.DAO.ContatoDAO;
import com.edu.rafaelsaito.debu.Main_Scene.MainActivity;
import com.edu.rafaelsaito.debu.Main_Scene.MainPresenter;
import com.edu.rafaelsaito.debu.Modelo.Contato;
import com.edu.rafaelsaito.debu.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnTextChanged;

public class ListaContatosActivity extends AppCompatActivity implements ListaContatosView {

    @BindView(R.id.lista_contatos) ListView listaContatos;
    @BindView(R.id.botao_novo_contato) Button botaoNovoContato;

    ListaContatosPresenter listaContatosPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);

        ButterKnife.bind(this);

        listaContatosPresenter = new ListaContatosPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Contato contato = (Contato) listaContatos.getItemAtPosition(info.position);
                Toast.makeText(ListaContatosActivity.this, "Deletar o contato " + contato.getNome(), Toast.LENGTH_SHORT).show();
                ContatoDAO dao = new ContatoDAO(ListaContatosActivity.this);
                dao.deleta(contato);
                dao.close();
                carregaLista();

                return false;
            }
        });
    }

    private void carregaLista() {

        ContatoDAO dao = new ContatoDAO(this);
        List<Contato> contatos = dao.buscaContato();
        dao.close();

        ArrayAdapter<Contato> adapter = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, contatos);
        listaContatos.setAdapter(adapter);
    }

    @OnClick(R.id.botao_novo_contato)
    public void cadastro(){
        listaContatosPresenter.cadastro();
    }

    @Override
    public void cadastrarContato() {
        Intent intentVaiPraCadastro = new Intent(ListaContatosActivity.this, CadastroContatoActivity.class);
        startActivity(intentVaiPraCadastro);
    }

    @OnItemClick(R.id.lista_contatos)
    public void clicarItem(int position) {
        listaContatosPresenter.clicarItem(position);
    }

    @Override
    public void abrirItem(int position) {
        Contato contato = (Contato) listaContatos.getItemAtPosition(position);
        Intent intentVaiProFormulario = new Intent(ListaContatosActivity.this, CadastroContatoActivity.class);
        intentVaiProFormulario.putExtra("contato", contato);
        startActivity(intentVaiProFormulario);
        registerForContextMenu(listaContatos);
    }
}
