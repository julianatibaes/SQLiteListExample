package com.tibaes.agaproject.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.tibaes.agaproject.database.DatabaseFactory;
import com.tibaes.agaproject.model.Client;
import com.tibaes.agaproject.util.ConvertData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juliana Tibães on 07/04/2017.
 */

public class ContactDAO extends DatabaseFactory {

    public ContactDAO(Context context) {
        super(context);
    }

    public void insertContact(Client client) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValues(client);
        db.insert(CONTATO_TABLE, null, dados);

    }

    public void updateContact(Client client) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getContentValues(client);

        String[] params ={client.getId().toString()};
        db.update(CONTATO_TABLE, dados, ID_CONTATO + " = ?", params);
    }

    @NonNull
    private ContentValues getContentValues(Client client) {
        ContentValues dados = new ContentValues();
        dados.put(NOME_CONTATO, client.getNome());
        dados.put(EMAIL_CONTATO, client.getEmail());
        dados.put(CELULAR_CONTATO, client.getCelular());
        dados.put(DT_NASCIMENTO_CONTATO, ConvertData.dateToString(client.getNascimento()));
        dados.put(CAMINHO_FOTO_CAMISETA, client.getCaminhoFoto());
        return dados;
    }

    public void deleteContact(Client client) {
        SQLiteDatabase db = getWritableDatabase();
        String [] params = {client.getId().toString()};
        db.delete(CONTATO_TABLE, ID_CONTATO +" = ?", params);
    }

    public List<Client> searchContact()  {
        String sql = "SELECT * FROM "+CONTATO_TABLE+";";
        SQLiteDatabase db = getReadableDatabase();

        try {
            Cursor c = db.rawQuery(sql, null);
            Log.d("DEBUG: ", c.toString());
            List<Client> clients = new ArrayList<Client>();
            while (c.moveToNext()) {
                Client client = new Client();
                client.setId(c.getLong(c.getColumnIndex(ID_CONTATO)));
                client.setNome(c.getString(c.getColumnIndex(NOME_CONTATO)));
                client.setEmail(c.getString(c.getColumnIndex(EMAIL_CONTATO)));
                client.setCelular(c.getString(c.getColumnIndex(CELULAR_CONTATO)));
                Log.d("DEBUG:", c.getString(c.getColumnIndex(DT_NASCIMENTO_CONTATO)));
                client.setNascimento(ConvertData.stringToDate(c.getString(c.getColumnIndex(DT_NASCIMENTO_CONTATO))));
                client.setCaminhoFoto(c.getString(c.getColumnIndex(CAMINHO_FOTO_CONTATO)));
                clients.add(client);
            }
            c.close();
            return clients;
        }
        catch (Exception e){
            Log.e("ERROR: ", e.getMessage());
            return null;
        }

    }
}
