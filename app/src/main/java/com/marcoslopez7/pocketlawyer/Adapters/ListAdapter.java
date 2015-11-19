package com.marcoslopez7.pocketlawyer.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.marcoslopez7.pocketlawyer.Model.ArticuloModelo;
import com.marcoslopez7.pocketlawyer.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Monzalvo on 18/11/2015.
 */
public class ListAdapter extends ArrayAdapter{
    private List lista = new ArrayList();

    public ListAdapter(Context context, int resource) {
        super(context, resource);
    }

    public ListAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public ListAdapter(Context context, int resource, Object[] objects) {
        super(context, resource, objects);
    }

    public ListAdapter(Context context, int resource, int textViewResourceId, Object[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public ListAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    public ListAdapter(Context context, int resource, int textViewResourceId, List objects) {
        super(context, resource, textViewResourceId, objects);
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
            layoutHandler.tituloLey = (TextView) row.findViewById(R.id.titulo_ley);
            layoutHandler.resumen = (TextView) row.findViewById(R.id.resumen_articulo);
            row.setTag(layoutHandler);
        }else{
            layoutHandler = (LayoutHandler)row.getTag();

        }

        ArticuloModelo articulo = (ArticuloModelo)this.getItem(position);
        layoutHandler.titulo.setText(articulo.getTitulo());
        layoutHandler.tituloLey.setText(articulo.getId_ley() + "");
        layoutHandler.resumen.setText(articulo.getResumen());

        return row;
    }

    static class LayoutHandler{
        TextView titulo, tituloLey, resumen;

    }
}
