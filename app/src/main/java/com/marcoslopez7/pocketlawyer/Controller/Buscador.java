package com.marcoslopez7.pocketlawyer.Controller;

import android.content.Context;
import android.util.Log;

import com.marcoslopez7.pocketlawyer.Model.ArticuloModelo;
import com.marcoslopez7.pocketlawyer.Model.ConexionBD;

import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by Marcos L on 11/11/2015.
 */
public class Buscador {
    private Vector<ArticuloModelo> articulos;
    private String keyword;
    private ConexionBD bd;

    public Buscador(String keyword, Context context){
        this.keyword = keyword;
        articulos = new Vector<>();
        bd = ConexionBD.getInstance(context);
    }


    public Vector<ArticuloModelo> search(){

        /*

            FUNCION QUE VA HACER EL ALGORITMO DE BUSQUEDA, ASI ES COMO VA FUNCIONAR:
            1.- SI KEYWORD TIENE AL MENOS 4 LETRAS, SEGUIMOS, SINO REGRESAMOS UN NULL
            2.- BUSCAMOS CON .getResumen DE CADA ARTICULO, CHECAMOS QUE HAYA UNA COINCIDENCIA DE 75% CON UNA PALABRA
            SI SE LLEGA AL 75, LA VARIABLE BOOLEANA SE VUELVE TRUE.
            SI NO HAY COINCIDENCIA AL 75% SE REGRESA UN NULL
            3.- SI EXISTE LA COINCIDENCIA, AHORA CON ESE SUBSTRING SE SIGUE BUSCANDO EN EL RESUMEN DEL ARTICULO
            Y SE VA CONTAR LAS COINCIDENCIAS
            4.- LAS COINCIDENCIAS CONTADAS SE VA PONER EN CADA ARTICULO EN UN .setPrioridad LUEGO SE HACE UN
            .addElement AL ARTICULO


         */

        try {
            bd.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (keyword.length() >= 4)
        {

            Vector<ArticuloModelo> articulos_query = bd.selectAllArticulos();
            String keyword_low = keyword.toLowerCase();

            for (int i = 0; i < articulos_query.size(); i++){
                boolean porcentaje_minimo = false;
                String articulo_resumen = articulos_query.elementAt(i).getResumen().toLowerCase();
                int word_matches = 0;

                for (int j = 0; j < articulo_resumen.length(); j++)
                {
                    int matches = 0;
                    int k = 0;
                    while (articulo_resumen.charAt(j) == keyword_low.charAt(k)){
                        matches++;
                        j++;
                        k++;

                        if (matches/keyword_low.length() * 100 >= 75)
                        {
                            porcentaje_minimo = true;
                            word_matches++;
                            break;
                        }
                    }
                }

                if (porcentaje_minimo){
                    ArticuloModelo articulo = (ArticuloModelo)articulos_query.elementAt(i).makeCopy();
                    articulo.setPrioridad(word_matches);
                    articulos.addElement(articulo);
                }
            }
        }else
            return null;

        bd.close();

        if (!articulos.isEmpty())
            return  articulos;
        else
            return null;
    }
}
