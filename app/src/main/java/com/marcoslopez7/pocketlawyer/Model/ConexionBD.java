package com.marcoslopez7.pocketlawyer.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;


/**
 * Created by Marcos L on 12/11/2015.
 * CLASE PARA CONEXION CON SQLite
 */
public final class ConexionBD {
    private ArticuloDBHelper dbHelper;
    private SQLiteDatabase bd;

    public ConexionBD(Context context){
        dbHelper = new ArticuloDBHelper(context);
    }

    //ABRIR BASE DE DATOS
    public void open() throws SQLException {
        bd = dbHelper.getWritableDatabase();
    }

    //CERRAR LA BASE DE DATOS
    public void close(){
        bd .close();
    }
}
