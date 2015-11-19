package com.marcoslopez7.pocketlawyer.Model;

import com.marcoslopez7.pocketlawyer.Patrones.Prototype;

/**
 * Created by Marcos L on 13/11/2015.
 */
public class Ley implements Prototype {
    private int id;
    private String titulo;
    private String fecha_ultima_modificacion;
    private int numero_articulos;
    private String link;

    public Ley(int id, String titulo, String fecha_ultima_modificacion, int numero_articulos, String link){
        this.id = id;
        this.titulo = titulo;
        this.fecha_ultima_modificacion = fecha_ultima_modificacion;
        this.numero_articulos = numero_articulos;
        this.link = link;
    }

    public Ley(){
        this.id = 0;
        this.titulo = "";
        this.fecha_ultima_modificacion = "";
        this.numero_articulos = 0;
        this.link = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha_ultima_modificacion() {
        return fecha_ultima_modificacion;
    }

    public void setFecha_ultima_modificacion(String fecha_ultima_modificacion) {
        this.fecha_ultima_modificacion = fecha_ultima_modificacion;
    }

    public int getNumero_articulos() {
        return numero_articulos;
    }

    public void setNumero_articulos(int numero_articulos) {
        this.numero_articulos = numero_articulos;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public Prototype makeCopy() {
        Ley objeto = null;

        try {
            objeto = (Ley) super.clone();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return objeto;
    }
}
