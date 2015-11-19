package com.marcoslopez7.pocketlawyer.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.marcoslopez7.pocketlawyer.Model.Beneficios;
import com.marcoslopez7.pocketlawyer.Model.Deber;
import com.marcoslopez7.pocketlawyer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 19/11/2015.
 */
public class ListAdapterDerechos extends ArrayAdapter {
    private List lista = new ArrayList();

    public ListAdapterDerechos(Context context, int resource) {
        super(context, resource);
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
            row = layoutInflater.inflate(R.layout.list_derechos_row, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.texto = (TextView) row.findViewById(R.id.derecho_row);
            row.setTag(layoutHandler);
        }else{
            layoutHandler = (LayoutHandler)row.getTag();

        }

        Beneficios beneficios = (Beneficios)this.getItem(position);
        layoutHandler.texto.setText(beneficios.getTexto());

        return row;
    }

    static class LayoutHandler{
        TextView texto;

    }
}
