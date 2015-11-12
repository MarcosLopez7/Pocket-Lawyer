/*
    ESTE ES EL MODELO, AQUI VA ESTAR LO QUE VAMOS A GUARDAR DE CADA ARTICULO DE LA LEY,
    EN ESTE MISMO PACKAGE SE VA PONER LA CONEXION A LA BASE DE DATOS, INSERTS Y SELECTS
 */

package com.marcoslopez7.pocketlawyer.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by Marcos L on 11/11/2015.
 */
public class ArticuloModelo {
    private int id;
    private String titulo;
    private String texto;
    private String categoria;
    private int prioridad;



    public ArticuloModelo(int id, String titulo, String texto, String categoria, int prioridad, Context context){
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.categoria = categoria;
        this.prioridad = prioridad;


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

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }


}
