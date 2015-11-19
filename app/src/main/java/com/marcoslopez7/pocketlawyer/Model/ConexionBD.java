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
    private Vector<Beneficios> beneficios;
    private Vector<Deber> deberes;
    private ArticuloDBHelper dbHelper;
    private SQLiteDatabase bd;

    //ESTA VARIABLE ES PARA USAR EL SINGLETON
    private static ConexionBD instance = null;

    private ConexionBD(Context context){
        dbHelper = new ArticuloDBHelper(context);
        articulos = new Vector<>();
        leyes = new Vector<>();
        beneficios = new Vector<>();
        deberes = new Vector<>();
        rellenaLeyes();
        rellenaArticulos();
        rellenaDeberes();
        rellenaBeneficios();
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

    //INSERTAR EN LA BASE DE DATOS

    public void insertar(){
        bd.beginTransaction();

        try {
            for (int i = 0; i < leyes.size(); i++){
                ContentValues values = new ContentValues();

                values.put(ArticuloReader.BD.COLUMN_NAME_TITULO_LEY, leyes.elementAt(i).getTitulo());
                values.put(ArticuloReader.BD.COLUMN_NAME_FECHA_MODIFICACION, leyes.elementAt(i).getFecha_ultima_modificacion());
                values.put(ArticuloReader.BD.COLUMN_NAME_NUMERO_ARTICULOS, leyes.elementAt(i).getNumero_articulos());
                values.put(ArticuloReader.BD.COLUMN_NAME_LINK, leyes.elementAt(i).getLink());

                bd.insert(ArticuloReader.BD.TABLE_NAME_LEYES, null, values);
            }

            for (int i = 0; i < articulos.size(); i++) {
                ContentValues values = new ContentValues();

                values.put(ArticuloReader.BD.COLUMN_NAME_TITULO_ARTICULO, articulos.elementAt(i).getTitulo());
                values.put(ArticuloReader.BD.COLUMN_NAME_ID_LEY_F, articulos.elementAt(i).getId_ley());
                values.put(ArticuloReader.BD.COLUMN_NAME_RESUMEN, articulos.elementAt(i).getResumen());
                values.put(ArticuloReader.BD.COLUMN_NAME_CATEGORIA, articulos.elementAt(i).getCategoria());
                values.put(ArticuloReader.BD.COLUMN_NAME_PRIORIDAD, articulos.elementAt(i).getPrioridad());

                bd.insert(ArticuloReader.BD.TABLE_NAME_ARTICULOS, null, values);
            }

            for (int i = 0; i < deberes.size(); i++){
                ContentValues values = new ContentValues();

                values.put(ArticuloReader.BD.COLUMN_NAME_TEXTO, deberes.elementAt(i).getTexto());
                values.put(ArticuloReader.BD._ID_ARTICULO_F, deberes.elementAt(i).getId_articulo());

                bd.insert(ArticuloReader.BD.TABLE_NAME_DEBERES, null, values);
            }

            for (int i = 0; i < beneficios.size(); i++){
                ContentValues values = new ContentValues();

                values.put(ArticuloReader.BD.COLUMN_NAME_TEXTO, deberes.elementAt(i).getTexto());
                values.put(ArticuloReader.BD._ID_ARTICULO_F, deberes.elementAt(i).getId_articulo());

                bd.insert(ArticuloReader.BD.TABLE_NAME_BENEFICIOS, null, values);
            }
            bd.setTransactionSuccessful();
        }
        finally {
            bd.endTransaction();
        }


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
                        ArticuloReader.BD.COLUMN_NAME_NUMERO_ARTICULOS,
                        ArticuloReader.BD.COLUMN_NAME_LINK},
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
            ArticuloModelo articulo_temporal = new ArticuloModelo();
            articulo_temporal.setId(cursor.getInt(cursor.getColumnIndex(ArticuloReader.BD._ID_ARTICULO)));
            articulo_temporal.setTitulo(cursor.getString(cursor.getColumnIndex(ArticuloReader.BD.COLUMN_NAME_TITULO_ARTICULO)));
            articulo_temporal.setResumen(cursor.getString(cursor.getColumnIndex(ArticuloReader.BD.COLUMN_NAME_RESUMEN)));
            articulo_temporal.setCategoria(cursor.getString(cursor.getColumnIndex(ArticuloReader.BD.COLUMN_NAME_CATEGORIA)));
            articulo_temporal.setPrioridad(cursor.getInt(cursor.getColumnIndex(ArticuloReader.BD.COLUMN_NAME_PRIORIDAD)));
            articulo_temporal.setId_ley(cursor.getInt(cursor.getColumnIndex(ArticuloReader.BD.COLUMN_NAME_ID_LEY_F)));

            nuevos_articulos.addElement(articulo_temporal);
            cursor.moveToNext();
        }

        return nuevos_articulos;
    }


    private void rellenaLeyes(){

        Ley ley1 = new Ley(0, "Constitucion de los Estados Unidos Mexicanos", "2015-07-10", 136, "http://www.diputados.gob.mx/LeyesBiblio/htm/1.htm");
        Ley ley2 = new Ley(0, "REGLAMENTO DE TRÁNSITO DEL DISTRITO FEDERAL", "2015-08-17", 70, "http://www.consejeria.df.gob.mx/portal_old/uploads/gacetas/0dfe0f2c2728da104e72f26974d2ad23.pdf");

        leyes.addElement(ley1);
        leyes.addElement(ley2);
    }

    private void rellenaArticulos(){


        ArticuloModelo articulo1 = new ArticuloModelo(0, "Artículo 1o.", "Derechos Humanos", 1, " Igualdad ante " +
                "la Ley, prohibición de la esclavitud y de la discriminación. ",  1);

        ArticuloModelo articulo2 = new ArticuloModelo(0, "Artículo 3o.", "Educacion", 3, "TODO INDIVIDUO " +
                "TIENE DERECHO A RECIBIR EDUCACION. EL ESTADO GARANTIZARA LA CALIDAD EN LA EDUCACION OBLIGATORIA " +
                "DE MANERA QUE LOS MATERIALES Y METODOS EDUCATIVOS, LA ORGANIZACION ESCOLAR, LA INFRAESTRUCTURA" +
                " EDUCATIVA Y LA IDONEIDAD DE LOS DOCENTES Y LOS DIRECTIVOS GARANTICEN EL MAXIMO LOGRO " +
                "DE APRENDIZAJE DE LOS EDUCANDOS. LA LIBERTAD DE CREENCIAS, DICHA EDUCACION SERA LAICA Y, " +
                "POR TANTO, SE MANTENDRA POR COMPLETO AJENA A CUALQUIER DOCTRINA RELIGIOSA.  SERA NACIONAL " +
                "EVITANDO LOS PRIVILEGIOS DE RAZAS, DE RELIGION, DE GRUPOS, DE SEXOS O DE INDIVIDUOS, TODA LA " +
                "EDUCACION QUE EL ESTADO IMPARTA SERA GRATUITA; ", 1);

        ArticuloModelo articulo3 = new ArticuloModelo(0, "Artículo 4o.", "Trabajo", 2, "Este articulo " +
                "consagra la garantía de libertad refiriéndose a la libertad de Trabajo señalándonos en " +
                "su primer párrafo que: “a ninguna persona se le podrá negar el derecho de dedicarse a " +
                "la profesión, industria, comercio o trabajo que le    acomode al individuo, a condición de " +
                "que sea lícito, prohíbe que se obligue a una persona a prestar trabajos personales sin la justa retribución y sin su pleno consentimiento " +
                "El Estado debe impedir que se celebre contrato o pacto que tenga por objeto el menoscabo, " +
                "la pérdida o el irrevocable sacrificio de la libertad de la persona.", 1);

        ArticuloModelo articulo4 = new ArticuloModelo(0, "Articulo 8", "Transito", 2, "Deberes de los " +
                "conductores de todo tipo", 2);

        ArticuloModelo articulo5 = new ArticuloModelo(0, "Articulo 50", "Transito", 1, "Limites de los " +
                "niveles de alcohol en conductores de vehiculos motorizados", 2);

        articulos.addElement(articulo1);
        articulos.addElement(articulo2);
        articulos.addElement(articulo3);
        articulos.addElement(articulo4);
        articulos.addElement(articulo5);
    }

    private void rellenaDeberes()
    {
        Deber deber1 = new Deber(0, "No discriminar a ninguna persona", 1);
        Deber deber2 = new Deber(0, "Tener un trabajo licito", 3);
        Deber deber3 = new Deber(0, "Obedecer señalamientos", 4);
        Deber deber4 = new Deber(0, "Obeder personas que dan apoyo vial", 4);
        Deber deber5 = new Deber(0, "Precaucion con los peatones en la via", 4);
        Deber deber6 = new Deber(0, "Compartir carrir de circulacion, cambiar de carril de manera escalonada " +
                "tomar el carril extremo de manera anticipada para dar vuelta", 4);
        Deber deber7 = new Deber(0, "Rebasar otro vehículo por el carril izquierdo", 4);
        Deber deber8 = new Deber(0, "Alinearse a la derecha y reducir la velocidad cuando otro vehículo " +
                "intente adelantarlo", 4);
        Deber deber9 = new Deber(0, "Queda prohibido conducir vehículos motorizados cuando se tenga una cantidad de alcohol en la sangre " +
                "superior a 0.8 gramos por litro o de alcohol en aire espirado superior a 0.4 miligramos por litro" +
                " En incumplir esto, se sanciona con Arresto administrativo" +
                " inconmutable de 20 a 36 horas y 6 puntos de penalizacion", 5);

        deberes.addElement(deber1);
        deberes.addElement(deber2);
        deberes.addElement(deber3);
        deberes.addElement(deber4);
        deberes.addElement(deber5);
        deberes.addElement(deber6);
        deberes.addElement(deber7);
        deberes.addElement(deber8);
        deberes.addElement(deber9);
    }

    private void rellenaBeneficios()
    {
        Beneficios beneficio1 = new Beneficios(0, "Tienes garantizados tus derechos humanos por parte " +
                "de las autoridades", 1);
        Beneficios beneficio2 = new Beneficios(0, "No existe la esclavitud", 1);
        Beneficios beneficio3 = new Beneficios(0, "No puedes ser discriminado", 1);
        Beneficios beneficio4 = new Beneficios(0, "Educacion de calidad garantizada por el estado", 2);
        Beneficios beneficio5 = new Beneficios(0, "Educacion gratuita", 2);
        Beneficios beneficio6 = new Beneficios(0, "Nadie puede impedir a alguien ejercer su profesion", 3);
        Beneficios beneficio7 = new Beneficios(0, "Tienen que pagar y retribuir de manera justa", 3);
        Beneficios beneficio8 = new Beneficios(0, "Tienen que repestar el paso peatonal los conductores " +
                "de caulquier vehiculo", 4);

        beneficios.addElement(beneficio1);
        beneficios.addElement(beneficio2);
        beneficios.addElement(beneficio3);
        beneficios.addElement(beneficio4);
        beneficios.addElement(beneficio5);
        beneficios.addElement(beneficio6);
        beneficios.addElement(beneficio7);
        beneficios.addElement(beneficio8);
    }
}
