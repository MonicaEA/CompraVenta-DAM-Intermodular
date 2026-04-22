package com.retrojuegos.retrojuegos.controller;

import com.retrojuegos.retrojuegos.data.DataSet;
import com.retrojuegos.retrojuegos.model.TipoStock;
import com.retrojuegos.retrojuegos.model.Videojuegos;

import java.util.List;

public class VideojuegosController {
    private static List<Videojuegos> misJuegos = DataSet.getJuegos();

    // metodo para ver el stock.
    public static void verStock(){

        System.out.println("Inventario");
        if (misJuegos.isEmpty()){
            System.out.println("Almacen vacio");
        } else {
            for (Videojuegos v : misJuegos){
                System.out.println("ID " + v.getIdJuego() + " " + v.getTitulo());
            }
        }
    }

    public static Videojuegos buscarJuego(String titulo){
        for (Videojuegos v : misJuegos){
            if (v.getTitulo().equalsIgnoreCase(titulo)){
                return v;
            }
        }

        return null;
    }

    public static void registrarCompra(Videojuegos nuevo){
        misJuegos.add(nuevo);
        System.out.println("Compra registrada de : " +nuevo.getTitulo());
        System.out.println("Stock de: " +nuevo.getTipo());

        //todo pendiente creacion de archivo en GestionController
    }

    public static void registrarVenta(Videojuegos v){
        double pvp = v.getPrecioVentaEstimada();
        double beneficioMonica = 0;
        double beneficioBetty = 0;

        // lógica para el reparto
        if (v.getTipo() == TipoStock.COMUN){
            //aqui reparto al 50-50
            beneficioMonica = pvp * 0.50;
            beneficioBetty = pvp * 0.50;
            System.out.println("Venta conjunta");
        }else if(v.getTipo() == TipoStock.PROPIO){
            beneficioMonica = pvp * 0.70;
            beneficioBetty = pvp * 0.30;
            System.out.println("Stock de Moni, reparto 70-30");

        }
        System.out.println("Juego vendido: " + v.getTitulo());
        System.out.println("Precio de venta: " + pvp +"€");
        System.out.println("Mónica recibe : "+beneficioMonica);
        System.out.println("Betty recibe: " +beneficioBetty);


        // aqui saco el juego del listado de stock.
        misJuegos.remove(v);

        //TODO
        // Aquí llamaríamos a GestionController para guardar el archivo .dat
        // GestionController.guardarDatos();

    }


}
