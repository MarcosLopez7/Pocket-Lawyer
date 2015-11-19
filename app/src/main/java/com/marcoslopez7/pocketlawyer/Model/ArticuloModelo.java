/*
    ESTE ES EL MODELO, AQUI VA ESTAR LO QUE VAMOS A GUARDAR DE CADA ARTICULO DE LA LEY,
    EN ESTE MISMO PACKAGE SE VA PONER LA CONEXION A LA BASE DE DATOS, INSERTS Y SELECTS
 */

package com.marcoslopez7.pocketlawyer.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;

import com.marcoslopez7.pocketlawyer.Patrones.Prototype;

import java.sql.SQLException;

/**
 * Created by Marcos L on 11/11/2015.
 */
public class ArticuloModelo implements Prototype, Parcelable {
    private int id;
    private String titulo;
    private String resumen;
    private String categoria;
    private int id_ley;
    private int prioridad;

    public ArticuloModelo(){
        this.id = 0;
        this.titulo = "";
        this.categoria = "";
        this.prioridad = 0;
        this.resumen = "";
        this.id_ley = 0;
    }

    public ArticuloModelo(int id, String titulo, String categoria, int prioridad, String resumen,
                          int id_ley){
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.prioridad = prioridad;
        this.resumen = resumen;
        this.id_ley = id_ley;
    }

    protected ArticuloModelo(Parcel in) {
        id = in.readInt();
        titulo = in.readString();
        resumen = in.readString();
        categoria = in.readString();
        id_ley = in.readInt();
        prioridad = in.readInt();
    }

    public static final Creator<ArticuloModelo> CREATOR = new Creator<ArticuloModelo>() {
        @Override
        public ArticuloModelo createFromParcel(Parcel in) {
            return new ArticuloModelo(in);
        }

        @Override
        public ArticuloModelo[] newArray(int size) {
            return new ArticuloModelo[size];
        }
    };

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public int getId_ley() {
        return id_ley;
    }

    public void setId_ley(int id_ley) {
        this.id_ley = id_ley;
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

    public Prototype makeCopy(){
        ArticuloModelo objeto = null;

        try {
                objeto = (ArticuloModelo) super.clone();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return objeto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(titulo);
        dest.writeString(resumen);
        dest.writeString(categoria);
        dest.writeInt(id_ley);
        dest.writeInt(prioridad);
    }

}
