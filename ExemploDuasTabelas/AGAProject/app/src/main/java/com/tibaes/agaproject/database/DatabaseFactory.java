package com.tibaes.agaproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Juliana Tibães on 07/04/2017.
 */

public class DatabaseFactory extends SQLiteOpenHelper {

    private static final String AGA_DB = "aga_db";

    public static final String CAMISETA_TABLE = "camiseta";
    public static final String CONTATO_TABLE = "contato";

    public static final String ID_CAMISETA = "id";
    public static final String MODELO_CAMISETA = "modelo";
    public static final String VALOR_CAMISETA = "valor";
    public static final String DESCRICAO_CAMISETA = "descricao";
    public static final String QUANTIDADE_CAMISETA = "quantidade";
    public static final String CAMINHO_FOTO_CAMISETA = "caminho_foto";

    public static final String ID_CONTATO = "id";
    public static final String NOME_CONTATO = "nome";
    public static final String EMAIL_CONTATO = "email";
    public static final String CELULAR_CONTATO = "phone";
    public static final String DT_NASCIMENTO_CONTATO = "nascimento";
    public static final String CAMINHO_FOTO_CONTATO = "caminho_foto";

    public DatabaseFactory(Context context) {
        super(context, AGA_DB, null, 8);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTShirt = "CREATE TABLE IF NOT EXISTS "+CAMISETA_TABLE+"( " +
                ID_CAMISETA+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                MODELO_CAMISETA+" TEXT NOT NULL, " +
                DESCRICAO_CAMISETA+" TEXT, " +
                VALOR_CAMISETA+" REAL, " +
                QUANTIDADE_CAMISETA+" INTEGER," +
                CAMINHO_FOTO_CAMISETA+" TEXT" +
                ");" ;

        String sqlContact = " CREATE TABLE "+CONTATO_TABLE+" ( " +
                ID_CONTATO+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOME_CONTATO+" TEXT NOT NULL, " +
                EMAIL_CONTATO+" TEXT, " +
                CELULAR_CONTATO+" TEXT, " +
                DT_NASCIMENTO_CONTATO+" TEXT, " +
                CAMINHO_FOTO_CONTATO+" TEXT" +
                ");";

        db.execSQL(sqlTShirt);
        db.execSQL(sqlContact);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlTShirt = "DROP TABLE IF EXISTS "+CAMISETA_TABLE+";";
        String sqlContact = "DROP TABLE IF EXISTS "+CONTATO_TABLE+";";
        db.execSQL(sqlTShirt);
        db.execSQL(sqlContact);
        onCreate(db);
    }
}
