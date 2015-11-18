package com.marcoslopez7.pocketlawyer.View;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.marcoslopez7.pocketlawyer.Adapters.ListAdapter;
import com.marcoslopez7.pocketlawyer.Model.ArticuloModelo;
import com.marcoslopez7.pocketlawyer.Model.ArticuloReader;
import com.marcoslopez7.pocketlawyer.R;

import java.util.ArrayList;
import java.util.Vector;

/*
    Aqui se va poner en lista los articulos;
 */

public class ListaArticulosActivity extends AppCompatActivity /*implements LoaderManager.LoaderCallbacks<Cursor>*/ {

    private ListView lv;
    private ListAdapter lista_adaptador;
    /*
    private int categoria;
    private ArrayList q;

    SimpleCursorAdapter adapter;

    static final String[] PROJECTION = new String[]{
            ArticuloReader.BD._ID_ARTICULO, ArticuloReader.BD.COLUMN_NAME_TITULO_ARTICULO
    };

    static String[] SELECTION;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        setContentView(R.layout.activity_lista_articulos);
        if(bundle != null){
            q = (ArrayList<ArticuloModelo>)bundle.getSerializable("Art");
        }

        lv = (ListView)findViewById(R.id.lv_querys);
        String[] fromCol = {ArticuloReader.BD.COLUMN_NAME_TITULO_ARTICULO};
        int[] toViews = {android.R.id.text1};

        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, null, fromCol, toViews, 0);
        lv.setAdapter(adapter);
        getLoaderManager().initLoader(0, null, this);
        */
    }

/*
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
       // return new CursorLoader(this, );
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
    */
}
