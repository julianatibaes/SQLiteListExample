package com.tibaes.listexample.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tibaes.listexample.model.TShirt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by julia on 26/03/2017.
 */

public class TShirtDAO extends SQLiteOpenHelper {
    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use to open or create the database
     * @param //name    of the database file, or null for an in-memory database
     * @param //factory to use for creating cursor objects, or null for the default
     * @param //version number of the database (starting at 1); if the database is older,
     *                {@link #onUpgrade} will be used to upgrade the database; if the database is
     *                newer, {@link #onDowngrade} will be used to downgrade the database
     */
    public TShirtDAO(Context context) {
        super(context, "Catalogo", null, 1);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Shirt (id INTEGER PRIMARY KEY," +
                " modelo TEXT NOT NULL, descricao TEXT, valor REAL, quantidade INTEGER);";
        db.execSQL(sql);

    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Shirt";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insertTShirt(TShirt shirt) {
        //String sql = "INSERT INTO Shirt (modelo, descricao, valor, quantidade) VALUES (" + shirt.getModelo() +')';
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("modelo", shirt.getModelo());
        dados.put("descricao", shirt.getDescricao());
        dados.put("valor", shirt.getValor());
        dados.put("quantidade", shirt.getQuantidade());

        db.insert("Shirt", null, dados );
    }

    public void updateTShirt(TShirt shirt) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("modelo", shirt.getModelo());
        dados.put("descricao", shirt.getDescricao());
        dados.put("valor", shirt.getValor());
        dados.put("quantidade", shirt.getQuantidade());

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
            shirts.add(shirt);
        }
        c.close();

        return shirts;
    }
}
