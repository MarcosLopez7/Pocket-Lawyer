package com.marcoslopez7.pocketlawyer.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.Vector;


/**
 * Created by Marcos L on 12/11/2015.
 * CLASE PARA CONEXION CON SQLite
 */
public final class ConexionBD {

    private Vector<ArticuloModelo> articulos;
    private Vector<Ley> leyes;
    private ArticuloDBHelper dbHelper;
    private SQLiteDatabase bd;

    public ConexionBD(Context context){
        dbHelper = new ArticuloDBHelper(context);
        articulos = new Vector<ArticuloModelo>();
        leyes = new Vector<Ley>();
        rellenaArticulos();
    }

    //ABRIR BASE DE DATOS
    public void open() throws SQLException {
        bd = dbHelper.getWritableDatabase();
    }

    //CERRAR LA BASE DE DATOS
    public void close(){
        bd .close();
    }

    //INSERTAR EN LA BASE DE DATOS
    /*
    public void insertar(){
        bd.beginTransaction();

        try {
            for (int i = 0; i < articulos.size(); i++) {
                ContentValues values = new ContentValues();

                values.put(ArticuloReader.BD.COLUMN_NAME_TITULO, articulos.elementAt(i).getTitulo());
                values.put(ArticuloReader.BD.COLUMN_NAME_TEXTO, articulos.elementAt(i).getTexto());
                values.put(ArticuloReader.BD.COLUMN_NAME_CATEGORIA, articulos.elementAt(i).getCategoria());
                values.put(ArticuloReader.BD.COLUMN_NAME_PRIORIDAD, articulos.elementAt(i).getPrioridad());

                bd.insert(ArticuloReader.BD.TABLE_NAME, null, values);
            }
            bd.setTransactionSuccessful();
        }
        finally {
            bd.endTransaction();
        }


    }
*/
    //SELECT TODA LA TABLA, ESTO SE VA PARA LA FUNCION BUSCADOR
    /*
    public Vector<ArticuloModelo> selectAllArticulos(){
        Cursor cursor = bd.query(
                ArticuloReader.BD.TABLE_NAME,
                new String[]{ ArticuloReader.BD.COLUMN_NAME_TITULO,
                              ArticuloReader.BD.COLUMN_NAME_TEXTO,
                              ArticuloReader.BD.COLUMN_NAME_CATEGORIA,
                              ArticuloReader.BD.COLUMN_NAME_PRIORIDAD},
                null, //CLAUSULA WHERE
                null, // PARAMETRO WHERE
                null, // GROUPBY
                null, // HAVING
                null // ORDERBY
        );

        Vector<ArticuloModelo> articulos = new Vector<ArticuloModelo>();

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            int i = cursor.getColumnIndex(ArticuloReader.BD._ID);
        }

        return articulos;
    }
*/
    private void rellenaArticulos(){

        /*

        public ArticuloModelo(int id, String titulo, String texto, String categoria, int prioridad, String resumen,
                          String beneficios, String deberes, int id_ley){
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.categoria = categoria;
        this.prioridad = prioridad;
        this.resumen = resumen;
        this.beneficios = beneficios;
        this.deberes = deberes;
        this.id_ley = id_ley;
    }
         */

        ArticuloModelo articulo1 = new ArticuloModelo(0, "Artículo 1o.", "Constitucion", 1, "Derechos" +
                " humanos y abolición de la esclavitud",  1);

        articulos.addElement(articulo1);

        /*ArticuloModelo articulo2 = new ArticuloModelo(0, "Artículo 2do.", "La Nación tiene una composición" +
                " pluricultural sustentada originalmente en sus pueblos indígenas que son aquellos que " +
                "descienden de poblaciones que habitaban en el territorio actual del país al iniciarse la" +
                " colonización y que conservan sus propias instituciones sociales, económicas, culturales " +
                "y políticas, o parte de ellas.", "Constitucion", 2);

        articulos.addElement(articulo2);

        ArticuloModelo articulo3 = new ArticuloModelo(0, "Artículo 3ro.", "Todo individuo tiene derecho a " +
                "recibir educación. El Estado –Federación, Estados, Distrito Federal y Municipios–, impartirá " +
                "educación preescolar, primaria, secundaria y media superior. La educación preescolar, primaria " +
                "y secundaria conforman la educación básica; ésta y la media superior serán " +
                "obligatorias.", "Constitucion", 2);

        articulos.addElement(articulo3);*/

    }
}
