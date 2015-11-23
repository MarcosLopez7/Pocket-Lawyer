package com.marcoslopez7.pocketlawyer.Patrones;

import com.marcoslopez7.pocketlawyer.Model.ArticuloModelo;

/**
 * Created by user on 23/11/2015.
 */
public class Creador {
    private ArticuloConstructor articuloConstructor;

    public Creador(){
       this.articuloConstructor = new ArticuloConstructor();
    }

    public ArticuloModelo getArticulo(){
        return this.articuloConstructor.getArticulo();
    }

    public void creaArticulo(int i, String t, String r, String c, int il, int p){
        this.articuloConstructor.buildId(i);
        this.articuloConstructor.buildTitulo(t);
        this.articuloConstructor.buildResumen(r);
        this.articuloConstructor.buildCategoria(c);
        this.articuloConstructor.buildIdLey(il);
        this.articuloConstructor.buildPrioridad(p);
    }
}
