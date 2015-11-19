package com.marcoslopez7.pocketlawyer.Controller;

import android.content.Context;

import com.marcoslopez7.pocketlawyer.Model.ArticuloModelo;
import com.marcoslopez7.pocketlawyer.Model.ConexionBD;
import com.marcoslopez7.pocketlawyer.Model.Deber;

import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by user on 19/11/2015.
 */
public class DeberControlador {
    private Deber deber;
    private ConexionBD conexion;

    //Variable para aplicar el singleton
    private static DeberControlador instance = null;

    private DeberControlador(Context context){
        conexion = ConexionBD.getInstance(context);
    }

    public static DeberControlador getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new DeberControlador(context);
        }

        return instance;
    }

    public Vector<Deber> findDeberesByIdArticulo(int id_articulo) {
        try {
            conexion.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Vector<Deber> d = conexion.selectDeberesByArticuloId(id_articulo);
        conexion.close();
        return d;
    }
}
