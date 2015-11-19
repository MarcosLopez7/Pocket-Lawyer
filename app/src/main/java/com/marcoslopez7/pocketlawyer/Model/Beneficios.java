package com.marcoslopez7.pocketlawyer.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.marcoslopez7.pocketlawyer.Patrones.Prototype;

/**
 * Created by Marcos L on 13/11/2015.
 */
public class Beneficios implements Parcelable, Prototype{
    private int id;
    private String texto;
    private int id_articulo;

    public Beneficios(int id, String texto, int id_articulo){
        this.id = id;
        this.texto = texto;
        this.id_articulo = id_articulo;
    }

    public Beneficios(){
        this.id = 0;
        this.texto = "";
        this.id_articulo = 0;
    }

    protected Beneficios(Parcel in) {
        id = in.readInt();
        texto = in.readString();
        id_articulo = in.readInt();
    }

    public static final Creator<Beneficios> CREATOR = new Creator<Beneficios>() {
        @Override
        public Beneficios createFromParcel(Parcel in) {
            return new Beneficios(in);
        }

        @Override
        public Beneficios[] newArray(int size) {
            return new Beneficios[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(texto);
        dest.writeInt(id_articulo);
    }

    @Override
    public Prototype makeCopy() {
        Beneficios objeto = null;

        try {
            objeto = (Beneficios) super.clone();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return objeto;
    }
}
