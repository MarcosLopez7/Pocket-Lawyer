package com.marcoslopez7.pocketlawyer.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.marcoslopez7.pocketlawyer.R;

/*
    Aqui se va poner en lista los articulos;
 */

public class ListaArticulosActivity extends AppCompatActivity {
    private int categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        setContentView(R.layout.activity_lista_articulos);

        if(bundle != null){
            categoria = bundle.getInt("CATEGORIA");
        }
    }

}
