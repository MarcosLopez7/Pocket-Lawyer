package com.marcoslopez7.pocketlawyer.Controller;

import android.content.Context;

import com.marcoslopez7.pocketlawyer.Model.ArticuloModelo;
import com.marcoslopez7.pocketlawyer.Model.Beneficios;
import com.marcoslopez7.pocketlawyer.Model.ConexionBD;

import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by user on 19/11/2015.
 */
public class BeneficioControlador {
    private Beneficios beneficios;
    private ConexionBD conexion;

    //Variable para aplicar el singleton
    private static BeneficioControlador instance = null;

    private BeneficioControlador(Context context){
        conexion = ConexionBD.getInstance(context);
    }

    public static BeneficioControlador getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new BeneficioControlador(context);
        }

        return instance;
    }

    public Vector<Beneficios> findBeneficiosByIdArticulo(int id_articulo){
        try {
            conexion.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Vector<Beneficios> b = conexion.selectBeneficiosByIdArticulo(id_articulo);
        conexion.close();
        return b;
    }
}
