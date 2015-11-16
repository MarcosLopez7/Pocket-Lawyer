package com.marcoslopez7.pocketlawyer.Controller;

import android.content.Context;

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
            double porcent;
            int matches = 0;
            Vector<ArticuloModelo> articulos_query = bd.selectAllArticulos();
            boolean porcentaje_minimo = false;

            for (int i = 0; i < articulos_query.size(); i++){
                for (int j = 0; j < articulos_query.elementAt(i).getResumen().length(); j++)
                {
                    int M = keyword.length();
                    int N = articulos_query.elementAt(i).getResumen().length();
                    int x, y;

                    for (x = 0, y = 0; x < N && y < M; ++x)
                    {
                        if(articulos_query.elementAt(i).getResumen().charAt(x) == keyword.charAt(y))
                        {
                            y++;
                            matches++;
                        }
                        else
                        {
                            x -= y;
                            y = 0;
                        }
                    }
                    if(y == M) {
                        if (articulos_query.elementAt(x).getResumen().charAt(x) != keyword.charAt(j))
                        {
                            porcent = (double) (matches / keyword.length() * 100);
                            if (porcent >= 75)
                                articulos.add(articulos_query.elementAt(x - M));
                        }
                    }
                    else
                    {
                        porcent = 100.0;
                    }
                }
            }
        }

        bd.close();
        return  articulos;
    }
}
