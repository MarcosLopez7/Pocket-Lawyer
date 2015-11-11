/*

    TODOS LOS ACTIVITY VAN A ESTAR EN EL PACKAGE DE VIEW
    LOS ACTIVITIES VAN HACER LAS VISTAS Y DE AQUI VAN A DESPLEGAR A LA PANTALLA
 */

package com.marcoslopez7.pocketlawyer.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.marcoslopez7.pocketlawyer.Controller.ArticuloControlador;
import com.marcoslopez7.pocketlawyer.R;

public class MainActivity extends AppCompatActivity {

    private ArticuloControlador articulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
