package com.edu.rafaelsaito.debu.Modelo;

/**
 * Created by mariana on 23/10/17.
 */

import android.widget.EditText;

import com.edu.rafaelsaito.debu.CadastroContato_Scene.CadastroContatoActivity;
import com.edu.rafaelsaito.debu.Modelo.Contato;
import com.edu.rafaelsaito.debu.R;

public class CadastroHelper {
    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final EditText campoEmail;

    private Contato contato;

    public CadastroHelper(CadastroContatoActivity activity){
        campoNome = (EditText) activity.findViewById(R.id.nome_contato);
        campoEndereco = (EditText) activity.findViewById(R.id.endereco_contato);
        campoTelefone = (EditText) activity.findViewById(R.id.telefone_contato);
        campoEmail = (EditText) activity.findViewById(R.id.email);
        contato = new Contato();
    }

    public Contato getContato() {
        contato.setNome(campoNome.getText().toString());
        contato.setEndereco(campoEndereco.getText().toString());
        contato.setTelefone(campoTelefone.getText().toString());
        contato.setEmail(campoEmail.getText().toString());

        return contato;
    }

    public void preencheCadastro(Contato contato) {
        campoNome.setText(contato.getNome());
        campoEndereco.setText(contato.getEndereco());
        campoTelefone.setText(contato.getTelefone());
        campoEmail.setText(contato.getEmail());
        this.contato = contato;
    }
}
