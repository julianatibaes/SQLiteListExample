package com.tibaes.agaproject.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.tibaes.agaproject.database.DatabaseFactory;
import com.tibaes.agaproject.model.TShirt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juliana Tibães on 07/04/2017.
 */

public class TShirtDAO extends DatabaseFactory{

    public TShirtDAO(Context context) {
        super(context);
    }

    public void insertTShirt(TShirt shirt) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValues(shirt);
        db.insert(CAMISETA_TABLE, null, dados);
    }

    public void updateTShirt(TShirt shirt) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValues(shirt);
        String[] params ={shirt.getId().toString()};
        db.update(CAMISETA_TABLE, dados, ID_CAMISETA + " = ?", params);
    }

    @NonNull
    private ContentValues getContentValues(TShirt shirt) {
        ContentValues dados = new ContentValues();
        dados.put(MODELO_CAMISETA, shirt.getModelo());
        dados.put(DESCRICAO_CAMISETA, shirt.getDescricao());
        dados.put(VALOR_CAMISETA, shirt.getValor());
        dados.put(QUANTIDADE_CAMISETA, shirt.getQuantidade());
        dados.put(CAMINHO_FOTO_CAMISETA, shirt.getCaminhoFoto());
        return dados;
    }

    public void deleteTShirt(TShirt shirt) {
        SQLiteDatabase db = getWritableDatabase();
        String [] params = {shirt.getId().toString()};
        db.delete(CAMISETA_TABLE, ID_CAMISETA +" = ?", params);
    }

    public List<TShirt> searchTShirt()  {
        String sql = "SELECT * FROM "+CAMISETA_TABLE+";";
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(sql, null);
        List<TShirt> shirts = new ArrayList<TShirt>();
        while (c.moveToNext()) {
            TShirt shirt = new TShirt();
            shirt.setId(c.getLong(c.getColumnIndex(ID_CAMISETA)));
            shirt.setModelo(c.getString(c.getColumnIndex(MODELO_CAMISETA)));
            shirt.setDescricao(c.getString(c.getColumnIndex(DESCRICAO_CAMISETA)));
            shirt.setValor(c.getFloat(c.getColumnIndex(VALOR_CAMISETA)));
            shirt.setQuantidade(c.getInt(c.getColumnIndex(QUANTIDADE_CAMISETA)));
            shirt.setCaminhoFoto(c.getString(c.getColumnIndex(CAMINHO_FOTO_CAMISETA)));
            shirts.add(shirt);
        }
        c.close();
        return shirts;
    }
}
