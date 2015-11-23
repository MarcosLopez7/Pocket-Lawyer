package com.marcoslopez7.pocketlawyer.Patrones;

import com.marcoslopez7.pocketlawyer.Model.ArticuloModelo;

/**
 * Created by user on 23/11/2015.
 */
public class ArticuloConstructor {
    private ArticuloModelo articulo;

    public ArticuloConstructor() {
        this.articulo = new ArticuloModelo();
    }

    public void buildId(int id){
        articulo.setId(id);
    }
    public void buildTitulo(String titulo)
    {
        articulo.setTitulo(titulo);
    }
    public void buildResumen(String resumen) {
        articulo.setResumen(resumen);
    }
    public void buildCategoria(String categoria){
        articulo.setCategoria(categoria);
    }
    public void buildIdLey(int id){
        articulo.setId_ley(id);
    }
    public void buildPrioridad(int prioridad)
    {
        articulo.setPrioridad(prioridad);
    }

    public ArticuloModelo getArticulo(){
        return this.articulo;
    }

}

