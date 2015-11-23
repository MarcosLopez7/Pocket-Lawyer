package com.marcoslopez7.pocketlawyer.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.marcoslopez7.pocketlawyer.Controller.LeyControlador;
import com.marcoslopez7.pocketlawyer.Model.ArticuloModelo;
import com.marcoslopez7.pocketlawyer.R;
import com.marcoslopez7.pocketlawyer.View.ArticuloActivity;
import com.marcoslopez7.pocketlawyer.View.ListaArticulosActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Monzalvo on 18/11/2015.
 */
public class ListAdapter extends ArrayAdapter{
    private List lista = new ArrayList();
    private Context context;
    private LeyControlador leyControlador;

    public ListAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        leyControlador = LeyControlador.getInstance(context);
    }



    @Override
    public void add(Object object) {
        super.add(object);
        lista.add(object);
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        LayoutHandler layoutHandler;

        if (row == null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.list_articulos, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.titulo = (TextView) row.findViewById(R.id.titulo_articulo);
            layoutHandler.tituloLey = (TextView) row.findViewById(R.id.titulo_ley_grande);
            layoutHandler.resumen = (TextView) row.findViewById(R.id.resumen_articulo);
            layoutHandler.boton = (Button) row.findViewById(R.id.boton_articulo);
            row.setTag(layoutHandler);
        }else{
            layoutHandler = (LayoutHandler)row.getTag();

        }

        final ArticuloModelo articulo = (ArticuloModelo)this.getItem(position);
        layoutHandler.titulo.setText(articulo.getTitulo());
        layoutHandler.tituloLey.setText(leyControlador.findLeyById(articulo.getId_ley()).getTitulo());
        layoutHandler.resumen.setText(articulo.getResumen());
        layoutHandler.boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ArticuloActivity.class);
                intent.putExtra("id_Articulo", articulo.getId());
                context.startActivity(intent);
            }
        });

        return row;
    }

    static class LayoutHandler{
        TextView titulo, tituloLey, resumen;
        Button boton;
    }
}
