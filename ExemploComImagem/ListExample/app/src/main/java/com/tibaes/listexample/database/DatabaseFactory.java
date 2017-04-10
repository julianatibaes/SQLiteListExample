package com.tibaes.listexample.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by julia on 07/04/2017.
 */

public class DatabaseFactory extends SQLiteOpenHelper {

    public DatabaseFactory(Context context) {
        super(context, "Catalogo", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Shirt (id INTEGER PRIMARY KEY," +
                " modelo TEXT NOT NULL, " +
                "descricao TEXT," +
                "valor REAL, " +
                "quantidade INTEGER," +
                "caminho_foto TEXT" + // nova coluna
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "";
        switch (oldVersion) {
            case 1:
                sql = "ALTER TABLE Shirt ADD COLUMN caminho_foto TEXT";
                db.execSQL(sql); // indo para versao 2
                break;

            default:
                sql = "DROP TABLE IF EXISTS Shirt";
                db.execSQL(sql);
                onCreate(db);
                break;

        }
    }
}
