package com.marcoslopez7.pocketlawyer.Model;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Marcos L on 12/11/2015.
 */
public class ArticuloDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "Articulos.db";

    public ArticuloDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ArticuloReader.CREATE_TABLE_LEYES);
        db.execSQL(ArticuloReader.CREATE_TABLE_ARTICULOS);
        db.execSQL(ArticuloReader.CREATE_TABLE_BENEFICIOS);
        db.execSQL(ArticuloReader.CREATE_TABLE_DEBERES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(ArticuloReader.SQL_DELETE_ENTRIES_LEYES);
        db.execSQL(ArticuloReader.SQL_DELETE_ENTRIES_ARTICULOS);
        db.execSQL(ArticuloReader.SQL_DELETE_ENTRIES_BENEFICIOS);
        db.execSQL(ArticuloReader.SQL_DELETE_ENTRIES_DEBERES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


}

