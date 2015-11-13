package com.marcoslopez7.pocketlawyer.Model;

import android.provider.BaseColumns;

/**
 * Created by Marcos L on 12/11/2015.
 * Esta clase es para describir las tablas que vamos usar en SQL
 */
public class ArticuloReader {

        private ArticuloReader(){};

        private static final String TEXT_TYPE = " TEXT";
        private static final String FOREIGN_KEY_LEY = "FOREIGN KEY(" + BD._ID_LEY + ") REFERENCES Leyes(id)";
        private static final String FOREIGN_KEY_ARTICULO = "FOREIGN KEY(" + BD._ID_ARTICULO + ") REFERENCES Articulos(id)";

        private static final String COMMA_SEP = ",";

        public static final String CREATE_TABLE_LEYES =
                "CREATE TABLE " + BD.TABLE_NAME_LEYES + " (" +
                        BD._ID_LEY + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        BD.COLUMN_NAME_TITULO_LEY + TEXT_TYPE + COMMA_SEP +
                        BD.COLUMN_NAME_FECHA_MODIFICACION + TEXT_TYPE + COMMA_SEP +
                        BD.COLUMN_NAME_NUMERO_ARTICULOS + TEXT_TYPE + COMMA_SEP +
                        BD.COLUMN_NAME_LINK + TEXT_TYPE +
                        " )";

        public static final String CREATE_TABLE_ARTICULOS =
                "CREATE TABLE " + BD.TABLE_NAME_ARTICULOS + " (" +
                        BD._ID_ARTICULO + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        BD.COLUMN_NAME_TITULO_ARTICULO + TEXT_TYPE + COMMA_SEP +
                        BD.COLUMN_NAME_RESUMEN + TEXT_TYPE + COMMA_SEP +
                        BD.COLUMN_NAME_CATEGORIA + TEXT_TYPE + COMMA_SEP +
                        BD.COLUMN_NAME_ID_LEY_F + " INTEGER" + COMMA_SEP +
                        BD.COLUMN_NAME_PRIORIDAD + " INTEGER" + COMMA_SEP +
                        FOREIGN_KEY_LEY +
                        " )";

        public static final String CREATE_TABLE_BENEFICIOS =
                "CREATE TABLE " + BD.TABLE_NAME_BENEFICIOS + " (" +
                        BD._ID_BEN_DED + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        BD.COLUMN_NAME_TEXTO + TEXT_TYPE + COMMA_SEP +
                        BD._ID_ARTICULO_F + " INTEGER" + COMMA_SEP +
                        FOREIGN_KEY_ARTICULO +
                        " )";

    public static final String CREATE_TABLE_DEBERES =
            "CREATE TABLE " + BD.TABLE_NAME_DEBERES + " (" +
                    BD._ID_BEN_DED + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    BD.COLUMN_NAME_TEXTO + TEXT_TYPE + COMMA_SEP +
                    BD._ID_ARTICULO_F + " INTEGER" + COMMA_SEP +
                    FOREIGN_KEY_ARTICULO +
                    " )";

        public static final String SQL_DELETE_ENTRIES_LEYES = "DROP TABLE IF EXISTS " + BD.TABLE_NAME_LEYES;
        public static final String SQL_DELETE_ENTRIES_ARTICULOS = "DROP TABLE IF EXISTS " + BD.TABLE_NAME_ARTICULOS;
        public static final String SQL_DELETE_ENTRIES_BENEFICIOS = "DROP TABLE IF EXISTS " + BD.TABLE_NAME_BENEFICIOS;
        public static final String SQL_DELETE_ENTRIES_DEBERES = "DROP TABLE IF EXISTS " + BD.TABLE_NAME_DEBERES;

        public static abstract class BD implements BaseColumns {

            //NOMBRE Y COLUMNAS DE LA TABLA ARTICULOS
            public static final String TABLE_NAME_ARTICULOS = "Articulos";
            public static final String _ID_ARTICULO = "id";
            public static final String COLUMN_NAME_TITULO_ARTICULO = "titulo";
            public static final String COLUMN_NAME_RESUMEN = "resumen";
            public static final String COLUMN_NAME_CATEGORIA = "categoria";
            public static final String COLUMN_NAME_ID_LEY_F = "id_ley";
            public static final String COLUMN_NAME_PRIORIDAD = "prioridad";

            //NOMBRE Y COLUMNAS DE LA TABLA LEYES
            public static final String TABLE_NAME_LEYES = "Leyes";
            public static final String _ID_LEY = "id";
            public static final String COLUMN_NAME_TITULO_LEY = "titulo";
            public static final String COLUMN_NAME_FECHA_MODIFICACION = "fecha_ultima_modificacion";
            public static final String COLUMN_NAME_NUMERO_ARTICULOS = "numero_articulos";
            public static final String COLUMN_NAME_LINK = "link";

            //NOMBRE Y COLUMNAS DE LAS TABLAS BENEFICIOS Y DEBERES
            public static final String TABLE_NAME_BENEFICIOS = "Beneficios";
            public static final String TABLE_NAME_DEBERES = "Deberes";
            public static final String _ID_ARTICULO_F = "id_articulo";
            public static final String _ID_BEN_DED = "id"; //ID PARA TABLAS BENEFICIOS Y DEBERES
            public static final String COLUMN_NAME_TEXTO = "texto";
        }


}
