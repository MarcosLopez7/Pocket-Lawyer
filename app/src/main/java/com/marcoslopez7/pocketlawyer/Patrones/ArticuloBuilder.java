package com.marcoslopez7.pocketlawyer.Patrones;

import com.marcoslopez7.pocketlawyer.Model.ArticuloModelo;

/**
 * Created by user on 23/11/2015.
 */
public interface ArticuloBuilder {
    public void buildId();
    public void buildTitulo();
    public void buildResumen();
    public void buildCategoria();
    public void buildIdLey();
    public void buildPrioridad();

    public ArticuloModelo getArticulo();
}
