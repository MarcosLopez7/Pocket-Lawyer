package com.marcoslopez7.pocketlawyer.Model;

import android.provider.BaseColumns;

/**
 * Created by Marcos L on 12/11/2015.
 */
public class ArticuloReader {

        private ArticuloReader(){};

        private static final String TEXT_TYPE = " TEXT";

        private static final String COMMA_SEP = ",";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + BD.TABLE_NAME + " (" +
                        BD._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        BD.COLUMN_NAME_TITULO + TEXT_TYPE + COMMA_SEP +
                        BD.COLUMN_NAME_TEXTO + TEXT_TYPE + COMMA_SEP +
                        BD.COLUMN_NAME_CATEGORIA + TEXT_TYPE + COMMA_SEP +
                        BD.COLUMN_NAME_PRIORIDAD + " INTEGER" +
                        " )";

        public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + BD.TABLE_NAME;

        public static abstract class BD implements BaseColumns {
            public static final String TABLE_NAME = "Articulos";
            public static final String _ID = "id";
            public static final String COLUMN_NAME_TITULO = "titulo";
            public static final String COLUMN_NAME_TEXTO = "texto";
            public static final String COLUMN_NAME_CATEGORIA = "categoria";
            public static final String COLUMN_NAME_PRIORIDAD = "prioridad";
        }


}
