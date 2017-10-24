package com.edu.rafaelsaito.debu.CadastroContato_Scene;

import android.text.Editable;

/**
 * Created by mariana on 23/10/17.
 */

public interface CadastroContatoView {
    void camera();
    void mapa();
    void salvar();
    void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2);
    void afterTextChanged(Editable editable);
}
