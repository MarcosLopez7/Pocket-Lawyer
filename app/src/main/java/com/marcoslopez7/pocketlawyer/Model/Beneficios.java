package com.marcoslopez7.pocketlawyer.Model;

/**
 * Created by Marcos L on 13/11/2015.
 */
public class Beneficios {
    private int id;
    private String texto;
    private int id_articulo;

    public Beneficios(int id, String texto, int id_articulo){
        this.id = id;
        this.texto = texto;
        this.id_articulo = id_articulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }
}
