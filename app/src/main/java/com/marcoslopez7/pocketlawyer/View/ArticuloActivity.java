package com.marcoslopez7.pocketlawyer.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.marcoslopez7.pocketlawyer.Adapters.ListAdapterDeberes;
import com.marcoslopez7.pocketlawyer.Adapters.ListAdapterDerechos;
import com.marcoslopez7.pocketlawyer.Controller.ArticuloControlador;
import com.marcoslopez7.pocketlawyer.Controller.BeneficioControlador;
import com.marcoslopez7.pocketlawyer.Controller.DeberControlador;
import com.marcoslopez7.pocketlawyer.Model.ArticuloModelo;
import com.marcoslopez7.pocketlawyer.Model.Beneficios;
import com.marcoslopez7.pocketlawyer.Model.Deber;
import com.marcoslopez7.pocketlawyer.R;

import org.w3c.dom.Text;

import java.util.Vector;

/*
    Aqui se va poner el articulo de manera completa
 */

public class ArticuloActivity extends AppCompatActivity {

    private TextView titulo, titulo_ley, resumen;
    private ListView lista_deberes, lista_derechos;
    private ListAdapterDeberes lista_adaptador_deberes;
    private ListAdapterDerechos lista_adaptador_derechos;
    private ArticuloControlador controlador;
    private ArticuloModelo articulo;
    private DeberControlador deberControlador;
    private BeneficioControlador beneficioControlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulo);
        titulo = (TextView) findViewById(R.id.tv_titulo);
        titulo_ley = (TextView) findViewById(R.id.titulo_ley_grande);
        resumen = (TextView) findViewById(R.id.resumen_grande);
        lista_deberes = (ListView) findViewById(R.id.list_deberes);
        lista_derechos = (ListView) findViewById(R.id.list_derechos);
        lista_adaptador_deberes = new ListAdapterDeberes(getApplicationContext(), R.layout.list_deberes_row);
        lista_adaptador_derechos = new ListAdapterDerechos(getApplicationContext(), R.layout.list_derechos_row);

        lista_deberes.setAdapter(lista_adaptador_deberes);
        lista_derechos.setAdapter(lista_adaptador_derechos);

        controlador = ArticuloControlador.getInstance(getApplicationContext());
        deberControlador = DeberControlador.getInstance(getApplicationContext());
        beneficioControlador = BeneficioControlador.getInstance(getApplicationContext());

        Intent intent = getIntent();
        int id_articulo = intent.getIntExtra("id_Articulo", 0);

        articulo = controlador.getArticuloById(id_articulo);

        titulo.setText(articulo.getTitulo());
        titulo_ley.setText(articulo.getId_ley() + "");
        resumen.setText(articulo.getResumen());

        Vector<Beneficios> beneficios = beneficioControlador.findBeneficiosByIdArticulo(articulo.getId());
        Vector<Deber> deberes = deberControlador.findDeberesByIdArticulo(articulo.getId());

        for (int i = 0; i < beneficios.size(); i++)
            lista_adaptador_derechos.add((Beneficios)beneficios.elementAt(i).makeCopy());

        for (int i = 0; i < deberes.size(); i++)
            lista_adaptador_deberes.add((Deber)deberes.elementAt(i).makeCopy());
    }

}
