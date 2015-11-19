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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.marcoslopez7.pocketlawyer.Controller.ArticuloControlador;
import com.marcoslopez7.pocketlawyer.Model.ArticuloModelo;
import com.marcoslopez7.pocketlawyer.Model.ConexionBD;
import com.marcoslopez7.pocketlawyer.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private ArticuloControlador articulo;
    private ConexionBD test;
    private EditText campoBusqueda;
    private Button trbajo, transito, propiedad, constitucion, educacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        articulo = ArticuloControlador.getInstance(getApplicationContext());

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
        trbajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vector<ArticuloModelo> articulos = articulo.selectArticuloByCategoria("Trabajo");
                ArticuloModelo[] art;
                if (articulos != null) {
                    art = new ArticuloModelo[articulos.size()];
                    for (int i = 0; i < articulos.size(); i++) {
                        art[i] = (ArticuloModelo) articulos.elementAt(i).makeCopy();
                    }

                    Intent intent = new Intent(getApplicationContext(), ListaArticulosActivity.class);
                    intent.putExtra("Art", art);
                    startActivity(intent);
                } else
                    Toast.makeText(getApplicationContext(), "Lo sentimos, hubo un error", Toast.LENGTH_LONG).show();
            }
        });

        transito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vector<ArticuloModelo> articulos = articulo.selectArticuloByCategoria("Transito");
                ArticuloModelo[] art;
                if (articulos != null) {
                    art = new ArticuloModelo[articulos.size()];
                    for (int i = 0; i < articulos.size(); i++) {
                        art[i] = (ArticuloModelo) articulos.elementAt(i).makeCopy();
                    }

                    Intent intent = new Intent(getApplicationContext(), ListaArticulosActivity.class);
                    intent.putExtra("Art", art);
                    startActivity(intent);
                } else
                    Toast.makeText(getApplicationContext(), "Lo sentimos, hubo un error", Toast.LENGTH_LONG).show();
            }
        });

        propiedad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vector<ArticuloModelo> articulos = articulo.selectArticuloByCategoria("Propiedad");
                ArticuloModelo[] art;
                if (articulos != null) {
                    art = new ArticuloModelo[articulos.size()];
                    for (int i = 0; i < articulos.size(); i++) {
                        art[i] = (ArticuloModelo) articulos.elementAt(i).makeCopy();
                    }

                    Intent intent = new Intent(getApplicationContext(), ListaArticulosActivity.class);
                    intent.putExtra("Art", art);
                    startActivity(intent);
                } else
                    Toast.makeText(getApplicationContext(), "Lo sentimos, hubo un error", Toast.LENGTH_LONG).show();
            }
        });

        constitucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vector<ArticuloModelo> articulos = articulo.selectArticuloByCategoria("Derechos Humanos");
                ArticuloModelo[] art;
                if (articulos != null) {
                    art = new ArticuloModelo[articulos.size()];
                    for (int i = 0; i < articulos.size(); i++) {
                        art[i] = (ArticuloModelo) articulos.elementAt(i).makeCopy();
                    }

                    Intent intent = new Intent(getApplicationContext(), ListaArticulosActivity.class);
                    intent.putExtra("Art", art);
                    startActivity(intent);
                } else
                    Toast.makeText(getApplicationContext(), "Lo sentimos, hubo un error", Toast.LENGTH_LONG).show();
            }
        });

        educacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vector<ArticuloModelo> articulos = articulo.selectArticuloByCategoria("Educacion");
                ArticuloModelo[] art;
                if (articulos != null) {
                    art = new ArticuloModelo[articulos.size()];
                    for (int i = 0; i < articulos.size(); i++) {
                        art[i] = (ArticuloModelo) articulos.elementAt(i).makeCopy();
                    }

                    Intent intent = new Intent(getApplicationContext(), ListaArticulosActivity.class);
                    intent.putExtra("Art", art);
                    startActivity(intent);
                } else
                    Toast.makeText(getApplicationContext(), "Lo sentimos, hubo un error", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init(){
        campoBusqueda = (EditText)findViewById(R.id.ET_campoBusqueda);
        trbajo = (Button)findViewById(R.id.b_fiestas);
        transito = (Button)findViewById(R.id.b_transito);
        propiedad = (Button)findViewById(R.id.b_propiedad);
        constitucion = (Button)findViewById(R.id.b_constitucion);
        educacion = (Button) findViewById(R.id.b_educacion);
    }

    public void Buscar(View view){
        // Algoritmo de busqueda
        String buscar = campoBusqueda.getText().toString();
        Vector<ArticuloModelo> articulos = articulo.buscar(buscar, getApplicationContext());
        ArticuloModelo[] art;
        if(articulos != null) {
            art = new ArticuloModelo[articulos.size()];
            for (int i = 0; i < articulos.size(); i++) {
                art[i] = (ArticuloModelo)articulos.elementAt(i).makeCopy();
            }
            Intent intent = new Intent(this, ListaArticulosActivity.class);
            intent.putExtra("Art", art);
            startActivity(intent);
        }else
            Toast.makeText(this, "Lo sentimos, hubo un error", Toast.LENGTH_LONG).show();
    }



}
