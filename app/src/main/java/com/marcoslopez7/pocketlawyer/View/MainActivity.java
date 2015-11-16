/*

    TODOS LOS ACTIVITY VAN A ESTAR EN EL PACKAGE DE VIEW
    LOS ACTIVITIES VAN HACER LAS VISTAS Y DE AQUI VAN A DESPLEGAR A LA PANTALLA
 */

package com.marcoslopez7.pocketlawyer.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.marcoslopez7.pocketlawyer.Controller.ArticuloControlador;
import com.marcoslopez7.pocketlawyer.Model.ArticuloModelo;
import com.marcoslopez7.pocketlawyer.Model.ConexionBD;
import com.marcoslopez7.pocketlawyer.R;

import java.sql.SQLException;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private ArticuloControlador articulo;
    private ConexionBD test;
    private EditText campoBusqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        articulo = ArticuloControlador.getInstance(MainActivity.this);

        /*
        DESCOMENTAR ESTAS LINEAS SI SE NECESITA HACER INSERCION DE DATOS, SIEMPRE SE HACE EN LA PRIMERA
        INSTALACION Y PRUEBA DE LA APP, AL TERMINAR VOLVER A COMENTARLAS

        test = ConexionBD.getInstance(MainActivity.this);
        try {
            test.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        test.insertat();
        Vector<ArticuloModelo> articulos = new Vector<>();

        articulos = test.selectAllArticulos();

        for (int i = 0; i < articulos.size(); i++){
            Log.d("Articles", articulos.elementAt(i).getTitulo());
        }

        test.close();
        */
        init();
    }

    private void init(){
        campoBusqueda = (EditText)findViewById(R.id.ET_campoBusqueda);
    }

    public void Buscar(View view){
        // Algoritmo de busqueda
        String buscar = campoBusqueda.getText().toString();
    }

    public void abrirLista(View view){
        Intent intent = new Intent(this,ListaArticulosActivity.class);
        int categoria;
        switch (view.getId()) {
            case R.id.b_transito:
                categoria = 1;
                break;
            case R.id.b_fiestas:
                categoria = 2;
                break;
            case R.id.b_propiedad:
                categoria = 3;
                break;
            default:
                categoria = 0;
                break;
        }
        intent.putExtra("CATEGORIA", categoria);
        startActivity(intent);
    }

}
