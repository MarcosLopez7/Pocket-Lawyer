package com.marcoslopez7.pocketlawyer.Controller;

import com.marcoslopez7.pocketlawyer.Model.ArticuloModelo;

import java.util.Vector;

/**
 * Created by Marcos L on 11/11/2015.
 */
public class Buscador {
    private Vector<ArticuloModelo> articulos;
    private String keyword;

    public Buscador(String keyword){
        this.keyword = keyword;
        articulos = new Vector<ArticuloModelo>();
    }

    public Vector<ArticuloModelo> search(){
        Vector<ArticuloModelo> articulos_query = new Vector<ArticuloModelo>();

        return  articulos;
    }
}
