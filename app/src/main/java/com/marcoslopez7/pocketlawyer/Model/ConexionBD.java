package com.marcoslopez7.pocketlawyer.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marcoslopez7.pocketlawyer.Patrones.Creador;

import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.Vector;


/**
 * Created by Marcos L on 12/11/2015.
 * CLASE PARA CONEXION CON SQLite
 */
public final class ConexionBD {

    private ArticuloDBHelper dbHelper;
    private SQLiteDatabase bd;

    //ESTA VARIABLE ES PARA USAR EL SINGLETON
    private static ConexionBD instance = null;

    private ConexionBD(Context context){
        dbHelper = new ArticuloDBHelper(context);

    }

    public static ConexionBD getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new ConexionBD(context);
        }

        return instance;
    }


    //ABRIR BASE DE DATOS
    public void open() throws SQLException {
        bd = dbHelper.getWritableDatabase();
    }

    //CERRAR LA BASE DE DATOS
    public void close(){
        bd .close();
    }


    //SELECT TODA LA TABLA, ESTO SE VA PARA LA FUNCION BUSCADOR

    public Vector<ArticuloModelo> selectAllArticulos(){
        Cursor cursor = bd.query(
                ArticuloReader.BD.TABLE_NAME_ARTICULOS,
                new String[]{ ArticuloReader.BD._ID_ARTICULO,
                        ArticuloReader.BD.COLUMN_NAME_TITULO_ARTICULO,
                              ArticuloReader.BD.COLUMN_NAME_RESUMEN,
                              ArticuloReader.BD.COLUMN_NAME_CATEGORIA,
                              ArticuloReader.BD.COLUMN_NAME_PRIORIDAD,
                                ArticuloReader.BD.COLUMN_NAME_ID_LEY_F},
                null, //CLAUSULA WHERE
                null, // PARAMETRO WHERE
                null, // GROUPBY
                null, // HAVING
                null // ORDERBY
        );

        return ponerAlCursor(cursor);
    }



    public Vector<ArticuloModelo> selectArticulosByCategoria(String categoria){
        Cursor cursor = bd.query(
                ArticuloReader.BD.TABLE_NAME_ARTICULOS,
                new String[]{ ArticuloReader.BD._ID_ARTICULO,
                        ArticuloReader.BD.COLUMN_NAME_TITULO_ARTICULO,
                        ArticuloReader.BD.COLUMN_NAME_RESUMEN,
                        ArticuloReader.BD.COLUMN_NAME_CATEGORIA,
                        ArticuloReader.BD.COLUMN_NAME_PRIORIDAD,
                        ArticuloReader.BD.COLUMN_NAME_ID_LEY_F},
                ArticuloReader.BD.COLUMN_NAME_CATEGORIA + " = '" + categoria + "'", //CLAUSULA WHERE
                null, // PARAMETRO WHERE
                null, // GROUPBY
                null, // HAVING
                null // ORDERBY
        );

        return ponerAlCursor(cursor);
    }

    public ArticuloModelo selectArticuloById(int id){
        Cursor cursor = bd.query(
                ArticuloReader.BD.TABLE_NAME_ARTICULOS,
                new String[]{ ArticuloReader.BD._ID_ARTICULO,
                        ArticuloReader.BD.COLUMN_NAME_TITULO_ARTICULO,
                        ArticuloReader.BD.COLUMN_NAME_RESUMEN,
                        ArticuloReader.BD.COLUMN_NAME_CATEGORIA,
                        ArticuloReader.BD.COLUMN_NAME_PRIORIDAD,
                        ArticuloReader.BD.COLUMN_NAME_ID_LEY_F},
                ArticuloReader.BD._ID_ARTICULO + " = " + id, //CLAUSULA WHERE
                null, // PARAMETRO WHERE
                null, // GROUPBY
                null, // HAVING
                null // ORDERBY
        );

        cursor.moveToFirst();

        ArticuloModelo articulo_temporal = new ArticuloModelo();
        articulo_temporal.setId(cursor.getInt(cursor.getColumnIndex(ArticuloReader.BD._ID_ARTICULO)));
        articulo_temporal.setTitulo(cursor.getString(cursor.getColumnIndex(ArticuloReader.BD.COLUMN_NAME_TITULO_ARTICULO)));
        articulo_temporal.setResumen(cursor.getString(cursor.getColumnIndex(ArticuloReader.BD.COLUMN_NAME_RESUMEN)));
        articulo_temporal.setCategoria(cursor.getString(cursor.getColumnIndex(ArticuloReader.BD.COLUMN_NAME_CATEGORIA)));
        articulo_temporal.setPrioridad(cursor.getInt(cursor.getColumnIndex(ArticuloReader.BD.COLUMN_NAME_PRIORIDAD)));
        articulo_temporal.setId_ley(cursor.getInt(cursor.getColumnIndex(ArticuloReader.BD.COLUMN_NAME_ID_LEY_F)));

        return articulo_temporal;
    }

    public Vector<Beneficios> selectBeneficiosByIdArticulo(int id_articulo){
        Cursor cursor = bd.query(
                ArticuloReader.BD.TABLE_NAME_BENEFICIOS,
                new String[]{ ArticuloReader.BD._ID_BEN_DED,
                        ArticuloReader.BD.COLUMN_NAME_TEXTO,
                        ArticuloReader.BD._ID_ARTICULO_F},
                ArticuloReader.BD._ID_ARTICULO_F + " = " + id_articulo, //CLAUSULA WHERE
                null, // PARAMETRO WHERE
                null, // GROUPBY
                null, // HAVING
                null // ORDERBY
        );

        Vector<Beneficios> nuevos_beneficios = new Vector<>();

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Beneficios beneficio_temporal = new Beneficios();
            beneficio_temporal.setId(cursor.getInt(cursor.getColumnIndex(ArticuloReader.BD._ID_BEN_DED)));
            beneficio_temporal.setTexto(cursor.getString(cursor.getColumnIndex(ArticuloReader.BD.COLUMN_NAME_TEXTO)));
            beneficio_temporal.setId_articulo(cursor.getInt(cursor.getColumnIndex(ArticuloReader.BD._ID_ARTICULO_F)));

            nuevos_beneficios.addElement(beneficio_temporal);
            cursor.moveToNext();
        }

        return nuevos_beneficios;
    }

    public Vector<Deber> selectDeberesByArticuloId(int id_articulo){
        Cursor cursor = bd.query(
                ArticuloReader.BD.TABLE_NAME_DEBERES,
                new String[]{ ArticuloReader.BD._ID_BEN_DED,
                        ArticuloReader.BD.COLUMN_NAME_TEXTO,
                        ArticuloReader.BD._ID_ARTICULO_F},
                ArticuloReader.BD._ID_ARTICULO_F + " = " + id_articulo, //CLAUSULA WHERE
                null, // PARAMETRO WHERE
                null, // GROUPBY
                null, // HAVING
                null // ORDERBY
        );

        Vector<Deber> nuevos_deberes = new Vector<>();

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Deber deber_temporal = new Deber();
            deber_temporal.setId(cursor.getInt(cursor.getColumnIndex(ArticuloReader.BD._ID_BEN_DED)));
            deber_temporal.setTexto(cursor.getString(cursor.getColumnIndex(ArticuloReader.BD.COLUMN_NAME_TEXTO)));
            deber_temporal.setId_articulo(cursor.getInt(cursor.getColumnIndex(ArticuloReader.BD._ID_ARTICULO_F)));

            nuevos_deberes.addElement(deber_temporal);
            cursor.moveToNext();
        }

        return nuevos_deberes;
    }

    public Ley selectLeyById(int id){
        Cursor cursor = bd.query(
                ArticuloReader.BD.TABLE_NAME_LEYES,
                new String[]{ ArticuloReader.BD._ID_LEY,
                        ArticuloReader.BD.COLUMN_NAME_TITULO_LEY,
                        ArticuloReader.BD.COLUMN_NAME_FECHA_MODIFICACION,

                        ArticuloReader.BD.COLUMN_NAME_NUMERO_ARTICULOS, ArticuloReader.BD.COLUMN_NAME_LINK},
                ArticuloReader.BD._ID_LEY + " = " + id, //CLAUSULA WHERE
                null, // PARAMETRO WHERE
                null, // GROUPBY
                null, // HAVING
                null // ORDERBY
        );

        cursor.moveToFirst();

        Ley ley_temporal = new Ley();

        ley_temporal.setId(cursor.getInt(cursor.getColumnIndex(ArticuloReader.BD._ID_LEY)));
        ley_temporal.setTitulo(cursor.getString(cursor.getColumnIndex(ArticuloReader.BD.COLUMN_NAME_TITULO_LEY)));
        ley_temporal.setFecha_ultima_modificacion(cursor.getString(cursor.getColumnIndex(ArticuloReader.BD.COLUMN_NAME_FECHA_MODIFICACION)));
        ley_temporal.setNumero_articulos(cursor.getInt(cursor.getColumnIndex(ArticuloReader.BD.COLUMN_NAME_NUMERO_ARTICULOS)));
        ley_temporal.setLink(cursor.getString(cursor.getColumnIndex(ArticuloReader.BD.COLUMN_NAME_LINK)));

        return ley_temporal;
    }

    private Vector<ArticuloModelo> ponerAlCursor(Cursor cursor){
        Vector<ArticuloModelo> nuevos_articulos = new Vector<>();

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Creador articulo_temporal = new Creador();
            articulo_temporal.creaArticulo(
                    cursor.getInt(cursor.getColumnIndex(ArticuloReader.BD._ID_ARTICULO)),
                    cursor.getString(cursor.getColumnIndex(ArticuloReader.BD.COLUMN_NAME_TITULO_ARTICULO)),
                    cursor.getString(cursor.getColumnIndex(ArticuloReader.BD.COLUMN_NAME_RESUMEN)),
                    cursor.getString(cursor.getColumnIndex(ArticuloReader.BD.COLUMN_NAME_CATEGORIA)),
                    cursor.getInt(cursor.getColumnIndex(ArticuloReader.BD.COLUMN_NAME_ID_LEY_F)),
                    cursor.getInt(cursor.getColumnIndex(ArticuloReader.BD.COLUMN_NAME_PRIORIDAD))
            );

            nuevos_articulos.addElement(articulo_temporal.getArticulo());
            cursor.moveToNext();
        }

        return nuevos_articulos;
    }



}
