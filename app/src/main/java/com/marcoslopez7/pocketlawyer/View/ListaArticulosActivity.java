package com.marcoslopez7.pocketlawyer.View;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.marcoslopez7.pocketlawyer.Adapters.ListAdapter;
import com.marcoslopez7.pocketlawyer.Model.ArticuloModelo;
import com.marcoslopez7.pocketlawyer.Model.ArticuloReader;
import com.marcoslopez7.pocketlawyer.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

/*
    Aqui se va poner en lista los articulos;
 */

public class ListaArticulosActivity extends AppCompatActivity /*implements LoaderManager.LoaderCallbacks<Cursor>*/ {

    private ListView lv;
    private ListAdapter lista_adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_articulos);
        lv = (ListView)findViewById(R.id.lv_querys);
        lista_adaptador = new ListAdapter(this, R.layout.list_articulos);

        lv.setAdapter(lista_adaptador);
        ArticuloModelo[] articulos;

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra("Art");
        articulos = Arrays.copyOf(parcelables, parcelables.length, ArticuloModelo[].class);

        for (int i = 0; i < articulos.length; i++)
            lista_adaptador.add(articulos[i]);

    }

}
