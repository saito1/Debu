package com.edu.rafaelsaito.debu.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.edu.rafaelsaito.debu.Modelo.Contato;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael Saito on 18/10/2017.
 */

public class ContatoDAO extends SQLiteOpenHelper{
    public ContatoDAO(Context context) {
        super(context, "Banco Contatos", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Contatos (id INTEGER NOT NULL PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT NOT NULL, telefone TEXT NOT NULL, email TEXT NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Contatos";
        db.execSQL(sql);
        onCreate(db);
    }

    public static class ContatoTable{
        public static final String TABLE = "Contatos";
        public static final String ID = "id";
        public static final String NOME = "nome";
        public static final String ENDERECO = "endereco";
        public static final String TELEFONE = "telefone";
        public static final String EMAIL = "email";
        public static final String[] COLUMS = new String[] { ID, NOME, ENDERECO, TELEFONE, EMAIL};

    }

    public void insere(Contato contato) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosContato(contato);

        dados.put(ContatoTable.NOME, contato.getNome());
        dados.put(ContatoTable.ENDERECO, contato.getEndereco());
        dados.put(ContatoTable.TELEFONE, contato.getTelefone());
        dados.put(ContatoTable.EMAIL, contato.getEmail());

        db.insert("Contatos", null, dados);
    }

    @NonNull
    private ContentValues pegaDadosContato(Contato contato) {
        ContentValues dados = new ContentValues();
        dados.put("nome", contato.getNome());
        dados.put("endereco", contato.getEndereco());
        dados.put("telefone", contato.getTelefone());
        dados.put("email", contato.getEmail());
        return dados;
    }

    public List<Contato> buscaContato() {
        String sql = "SELECT * from Contatos;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Contato> contatos = new ArrayList<>();
        while(c.moveToNext()){
            Contato contato = new Contato();
            contato.setId(c.getLong(c.getColumnIndex("id")));
            contato.setNome(c.getString(c.getColumnIndex("nome")));
            contato.setEndereco(c.getString(c.getColumnIndex("endereco")));
            contato.setTelefone(c.getString(c.getColumnIndex("telefone")));
            contato.setEmail(c.getString(c.getColumnIndex("email")));
            contatos.add(contato);
        }
        c.close();
        return contatos;
    }

    public void deleta(Contato contato) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {contato.getId().toString()};
        db.delete("Contatos", "id = ?", params);
    }

    public void altera(Contato contato) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosContato(contato);

        String[] params = {contato.getId().toString()};
        db.update("Contatos", dados, "id = ?", params);
    }
}
