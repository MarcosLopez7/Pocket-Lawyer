package com.marcoslopez7.pocketlawyer.Patrones;

import com.marcoslopez7.pocketlawyer.Model.ArticuloModelo;

/**
 * Created by user on 23/11/2015.
 */
public class ArticuloConstructor implements ArticuloBuilder {
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

    @Override
    public void buildId() {

    }

    @Override
    public void buildTitulo() {

    }

    @Override
    public void buildResumen() {

    }

    @Override
    public void buildCategoria() {

    }

    @Override
    public void buildIdLey() {

    }

    @Override
    public void buildPrioridad() {

    }

    public ArticuloModelo getArticulo(){
        return this.articulo;
    }

}

