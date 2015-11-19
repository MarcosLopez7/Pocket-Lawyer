/*
    ESTE ES EL CONTROLADOR, AQUI VAMOS HACER LA BUSQUEDA DEL ARTICULO
 */

package com.marcoslopez7.pocketlawyer.Controller;

import android.content.Context;

import com.marcoslopez7.pocketlawyer.Model.ArticuloModelo;
import com.marcoslopez7.pocketlawyer.Model.ConexionBD;

import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by Marcos L on 11/11/2015.
 */
public class ArticuloControlador {
    private ArticuloModelo articulo;
    private ConexionBD conexion;
    private Buscador buscador;


    //Variable para aplicar el singleton
    private static ArticuloControlador instance = null;

    private ArticuloControlador(Context context){
        conexion = ConexionBD.getInstance(context);
    }

    public static ArticuloControlador getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new ArticuloControlador(context);
        }

        return instance;
    }

    public ArticuloModelo getArticuloById(int id){
        try {
            conexion.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArticuloModelo art = conexion.selectArticuloById(id);
        conexion.close();
        return art;

    }

    public ArticuloModelo getArticuloByCategoria(String categoria){
        return articulo;
    }

    public ArticuloModelo getArticulo() {
        return articulo;
    }

    public Vector<ArticuloModelo> buscar(String keyword, Context context){
        buscador = new Buscador(keyword, context);
        return buscador.search();
    }

    public Vector<ArticuloModelo> selectArticuloByCategoria(String categoria){
        return conexion.selectArticulosByCategoria(categoria);
    }

}
