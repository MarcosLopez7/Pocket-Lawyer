package com.marcoslopez7.pocketlawyer.Controller;

import android.content.Context;

import com.marcoslopez7.pocketlawyer.Model.Beneficios;
import com.marcoslopez7.pocketlawyer.Model.ConexionBD;
import com.marcoslopez7.pocketlawyer.Model.Ley;

import java.sql.SQLException;

/**
 * Created by user on 19/11/2015.
 */
public class LeyControlador {
    private Ley beneficios;
    private ConexionBD conexion;

    //Variable para aplicar el singleton
    private static LeyControlador instance = null;

    private LeyControlador(Context context){
        conexion = ConexionBD.getInstance(context);
    }

    public static LeyControlador getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new LeyControlador(context);
        }

        return instance;
    }

    public Ley findLeyById(int id){
        try {
            conexion.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Ley l = conexion.selectLeyById(id);
        conexion.close();

        return l;
    }
}
