package com.tibaes.listexample.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tibaes.listexample.database.DatabaseFactory;
import com.tibaes.listexample.model.TShirt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by julia on 26/03/2017.
 */

public class TShirtDAO extends DatabaseFactory {

    public TShirtDAO(Context context) {
        super(context);
    }

    public void insertTShirt(TShirt shirt) {
        //String sql = "INSERT INTO Shirt (modelo, descricao, valor, quantidade) VALUES (" + shirt.getModelo() +')';
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("modelo", shirt.getModelo());
        dados.put("descricao", shirt.getDescricao());
        dados.put("valor", shirt.getValor());
        dados.put("quantidade", shirt.getQuantidade());
        dados.put("caminho_foto", shirt.getCaminhoFoto()); // novo
        db.insert("Shirt", null, dados);
    }

    public void updateTShirt(TShirt shirt) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("modelo", shirt.getModelo());
        dados.put("descricao", shirt.getDescricao());
        dados.put("valor", shirt.getValor());
        dados.put("quantidade", shirt.getQuantidade());
        dados.put("caminho_foto", shirt.getCaminhoFoto()); // novo

        String[] params ={shirt.getId().toString()};
        db.update("Shirt", dados, "id = ?", params);
    }

    public void deleteTShirt(TShirt shirt) {
        SQLiteDatabase db = getWritableDatabase();

        String [] params = {shirt.getId().toString()};
        db.delete("Shirt", "id = ?", params);
    }

    public List<TShirt> searchTShirt()  {
        String sql = "SELECT * FROM Shirt;";
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(sql, null);

        List<TShirt> shirts = new ArrayList<TShirt>();
        while (c.moveToNext()) {
            TShirt shirt = new TShirt();
            shirt.setId(c.getLong(c.getColumnIndex("id")));
            shirt.setModelo(c.getString(c.getColumnIndex("modelo")));
            shirt.setDescricao(c.getString(c.getColumnIndex("descricao")));
            shirt.setValor(c.getFloat(c.getColumnIndex("valor")));
            shirt.setQuantidade(c.getInt(c.getColumnIndex("quantidade")));
            shirt.setCaminhoFoto(c.getString(c.getColumnIndex("caminho_foto"))); // novo
            shirts.add(shirt);
        }
        c.close();

        return shirts;
    }
}
