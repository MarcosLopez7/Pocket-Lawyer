package com.marcoslopez7.pocketlawyer.Model;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        import java.util.Vector;

/**
 * Created by Marcos L on 12/11/2015.
 */
public class ArticuloDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NAME = "Articulos.db";
    private Vector<Ley> leyes;
    private Vector<Beneficios> beneficios;
    private Vector<Deber> deberes;
    private Vector<ArticuloModelo> articulos;

    public ArticuloDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        articulos = new Vector<>();
        leyes = new Vector<>();
        beneficios = new Vector<>();
        deberes = new Vector<>();
        rellenaLeyes();
        rellenaArticulos();
        rellenaDeberes();
        rellenaBeneficios();
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ArticuloReader.CREATE_TABLE_LEYES);
        db.execSQL(ArticuloReader.CREATE_TABLE_ARTICULOS);
        db.execSQL(ArticuloReader.CREATE_TABLE_BENEFICIOS);
        db.execSQL(ArticuloReader.CREATE_TABLE_DEBERES);

        db.beginTransaction();

        try {
            for (int i = 0; i < leyes.size(); i++){
                ContentValues values = new ContentValues();

                values.put(ArticuloReader.BD._ID_LEY, leyes.elementAt(i).getId());
                values.put(ArticuloReader.BD.COLUMN_NAME_TITULO_LEY, leyes.elementAt(i).getTitulo());
                values.put(ArticuloReader.BD.COLUMN_NAME_FECHA_MODIFICACION, leyes.elementAt(i).getFecha_ultima_modificacion());
                values.put(ArticuloReader.BD.COLUMN_NAME_NUMERO_ARTICULOS, leyes.elementAt(i).getNumero_articulos());
                values.put(ArticuloReader.BD.COLUMN_NAME_LINK, leyes.elementAt(i).getLink());

                db.insert(ArticuloReader.BD.TABLE_NAME_LEYES, null, values);
            }

            for (int i = 0; i < articulos.size(); i++) {
                ContentValues values = new ContentValues();

                values.put(ArticuloReader.BD._ID_ARTICULO, articulos.elementAt(i).getId());
                values.put(ArticuloReader.BD.COLUMN_NAME_TITULO_ARTICULO, articulos.elementAt(i).getTitulo());
                values.put(ArticuloReader.BD.COLUMN_NAME_ID_LEY_F, articulos.elementAt(i).getId_ley());
                values.put(ArticuloReader.BD.COLUMN_NAME_RESUMEN, articulos.elementAt(i).getResumen());
                values.put(ArticuloReader.BD.COLUMN_NAME_CATEGORIA, articulos.elementAt(i).getCategoria());
                values.put(ArticuloReader.BD.COLUMN_NAME_PRIORIDAD, articulos.elementAt(i).getPrioridad());

                db.insert(ArticuloReader.BD.TABLE_NAME_ARTICULOS, null, values);
            }

            for (int i = 0; i < deberes.size(); i++){
                ContentValues values = new ContentValues();

                values.put(ArticuloReader.BD._ID_BEN_DED, deberes.elementAt(i).getId());
                values.put(ArticuloReader.BD.COLUMN_NAME_TEXTO, deberes.elementAt(i).getTexto());
                values.put(ArticuloReader.BD._ID_ARTICULO_F, deberes.elementAt(i).getId_articulo());

                db.insert(ArticuloReader.BD.TABLE_NAME_DEBERES, null, values);
            }

            for (int i = 0; i < beneficios.size(); i++){
                ContentValues values = new ContentValues();

                values.put(ArticuloReader.BD._ID_BEN_DED, beneficios.elementAt(i).getId());
                values.put(ArticuloReader.BD.COLUMN_NAME_TEXTO, beneficios.elementAt(i).getTexto());
                values.put(ArticuloReader.BD._ID_ARTICULO_F, beneficios.elementAt(i).getId_articulo());

                db.insert(ArticuloReader.BD.TABLE_NAME_BENEFICIOS, null, values);
            }
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(ArticuloReader.SQL_DELETE_ENTRIES_LEYES);
        db.execSQL(ArticuloReader.SQL_DELETE_ENTRIES_ARTICULOS);
        db.execSQL(ArticuloReader.SQL_DELETE_ENTRIES_BENEFICIOS);
        db.execSQL(ArticuloReader.SQL_DELETE_ENTRIES_DEBERES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private void rellenaLeyes(){

        Ley ley1 = new Ley(1, "Constitucion de los Estados Unidos Mexicanos", "2015-07-10", 136, "http://www.diputados.gob.mx/LeyesBiblio/htm/1.htm");
        Ley ley2 = new Ley(2, "REGLAMENTO DE TRÁNSITO DEL DISTRITO FEDERAL", "2015-08-17", 70, "http://www.consejeria.df.gob.mx/portal_old/uploads/gacetas/0dfe0f2c2728da104e72f26974d2ad23.pdf");

        leyes.addElement(ley1);
        leyes.addElement(ley2);
    }

    private void rellenaArticulos(){


        ArticuloModelo articulo1 = new ArticuloModelo(1, "Artículo 1o.", "Derechos Humanos", 1, " Igualdad ante " +
                "la Ley, prohibición de la esclavitud y de la discriminación. ",  1);

        ArticuloModelo articulo2 = new ArticuloModelo(2, "Artículo 3o.", "Educacion", 3, "TODO INDIVIDUO " +
                "TIENE DERECHO A RECIBIR EDUCACION. EL ESTADO GARANTIZARA LA CALIDAD EN LA EDUCACION OBLIGATORIA " +
                "DE MANERA QUE LOS MATERIALES Y METODOS EDUCATIVOS, LA ORGANIZACION ESCOLAR, LA INFRAESTRUCTURA" +
                " EDUCATIVA Y LA IDONEIDAD DE LOS DOCENTES Y LOS DIRECTIVOS GARANTICEN EL MAXIMO LOGRO " +
                "DE APRENDIZAJE DE LOS EDUCANDOS. LA LIBERTAD DE CREENCIAS, DICHA EDUCACION SERA LAICA Y, " +
                "POR TANTO, SE MANTENDRA POR COMPLETO AJENA A CUALQUIER DOCTRINA RELIGIOSA.  SERA NACIONAL " +
                "EVITANDO LOS PRIVILEGIOS DE RAZAS, DE RELIGION, DE GRUPOS, DE SEXOS O DE INDIVIDUOS, TODA LA " +
                "EDUCACION QUE EL ESTADO IMPARTA SERA GRATUITA; ", 1);

        ArticuloModelo articulo3 = new ArticuloModelo(3, "Artículo 4o.", "Trabajo", 2, "Este articulo " +
                "consagra la garantía de libertad refiriéndose a la libertad de Trabajo señalándonos en " +
                "su primer párrafo que: “a ninguna persona se le podrá negar el derecho de dedicarse a " +
                "la profesión, industria, comercio o trabajo que le    acomode al individuo, a condición de " +
                "que sea lícito, prohíbe que se obligue a una persona a prestar trabajos personales sin la justa retribución y sin su pleno consentimiento " +
                "El Estado debe impedir que se celebre contrato o pacto que tenga por objeto el menoscabo, " +
                "la pérdida o el irrevocable sacrificio de la libertad de la persona.", 1);

        ArticuloModelo articulo4 = new ArticuloModelo(4, "Articulo 8", "Transito", 2, "Deberes de los " +
                "conductores de todo tipo", 2);

        ArticuloModelo articulo5 = new ArticuloModelo(5, "Articulo 50", "Transito", 1, "Limites de los " +
                "niveles de alcohol en conductores de vehiculos motorizados", 2);

        articulos.addElement(articulo1);
        articulos.addElement(articulo2);
        articulos.addElement(articulo3);
        articulos.addElement(articulo4);
        articulos.addElement(articulo5);
    }

    private void rellenaDeberes()
    {
        Deber deber1 = new Deber(1, "No discriminar a ninguna persona", 1);
        Deber deber2 = new Deber(2, "Tener un trabajo licito", 3);
        Deber deber3 = new Deber(3, "Obedecer señalamientos", 4);
        Deber deber4 = new Deber(4, "Obeder personas que dan apoyo vial", 4);
        Deber deber5 = new Deber(5, "Precaucion con los peatones en la via", 4);
        Deber deber6 = new Deber(6, "Compartir carrir de circulacion, cambiar de carril de manera escalonada " +
                "tomar el carril extremo de manera anticipada para dar vuelta", 4);
        Deber deber7 = new Deber(7, "Rebasar otro vehículo por el carril izquierdo", 4);
        Deber deber8 = new Deber(8, "Alinearse a la derecha y reducir la velocidad cuando otro vehículo " +
                "intente adelantarlo", 4);
        Deber deber9 = new Deber(9, "Queda prohibido conducir vehículos motorizados cuando se tenga una cantidad de alcohol en la sangre " +
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
        Beneficios beneficio1 = new Beneficios(1, "Tienes garantizados tus derechos humanos por parte " +
                "de las autoridades", 1);
        Beneficios beneficio2 = new Beneficios(2, "No existe la esclavitud", 1);
        Beneficios beneficio3 = new Beneficios(3, "No puedes ser discriminado", 1);
        Beneficios beneficio4 = new Beneficios(4, "Educacion de calidad garantizada por el estado", 2);
        Beneficios beneficio5 = new Beneficios(5, "Educacion gratuita", 2);
        Beneficios beneficio6 = new Beneficios(6, "Nadie puede impedir a alguien ejercer su profesion", 3);
        Beneficios beneficio7 = new Beneficios(7, "Tienen que pagar y retribuir de manera justa", 3);
        Beneficios beneficio8 = new Beneficios(8, "Tienen que repestar el paso peatonal los conductores " +
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

