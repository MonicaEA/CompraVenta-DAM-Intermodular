package com.retrojuegos.retrojuegos.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.retrojuegos.retrojuegos.model.*;

public class DataSet {

    // 1. Lista de Usuarios (Para probar el Login)
    private static ObservableList<Usuarios> listaUsuarios = FXCollections.observableArrayList(
            new Usuarios(1, "Beatriz", "Retro", "12345678A", "bea@retrojuegos.com", "1234", Perfil.ADMINISTRADOR),
            new Usuarios(2, "Monica", "Gamer", "39458713m", "monica@retrojuegos.com", "4321", Perfil.TRABAJADOR)
    );

    // 2. Lista de Clientes (Para la agenda)
    private static ObservableList<Clientes> listaClientes = FXCollections.observableArrayList(
            new Clientes(1, "Pepe", "Perez", "11122233C", "pepe@gmail.com", "600111222", TipoCliente.AMBOS),
            new Clientes(2, "Ana", "Lopez", "44455566D", "ana@hotmail.com", "655444333", TipoCliente.COMPRADOR)
    );

    // 3. Lista de Videojuegos (Para el stock)
    private static ObservableList<Videojuegos> listaJuegos = FXCollections.observableArrayList(
            // Orden: id, titulo, pCompra, pVenta, idPlat, idGen, ESTADO, TIPO, idUsu
            new Videojuegos(1, "Super Mario Bros", 15.0, 40.0, 1, 1, EstadoJuego.CAJA_SIN_MANUAL, TipoStock.PROPIO, 1),
            new Videojuegos(2, "The Legend of Zelda", 50.0, 120.0, 1, 1, EstadoJuego.COMPLETO, TipoStock.COMUN, 1)
    );

    private static ObservableList<Plataformas> listaPlataformas = FXCollections.observableArrayList();
    private static ObservableList<Generos> listaGeneros = FXCollections.observableArrayList();
    private static ObservableList<Compras> listaCompras = FXCollections.observableArrayList();
    private static ObservableList<Ventas> listaVentas = FXCollections.observableArrayList();


    //inicializar plataformas
    public static void inicializarPlataformas(){
        if (listaPlataformas.isEmpty()){
            listaPlataformas.add(new Plataformas(1,"Nintendo NES"));
            listaPlataformas.add(new Plataformas(2,"Nintendo SNES"));
            listaPlataformas.add(new Plataformas(3,"Nintendo 64"));
            listaPlataformas.add(new Plataformas(4,"Nintendo GameBoy"));
            listaPlataformas.add(new Plataformas(5,"Sega Master System"));
            listaPlataformas.add(new Plataformas(6,"Sega MegaDrive"));
            listaPlataformas.add(new Plataformas(7,"Sega DreamCast"));
            listaPlataformas.add(new Plataformas(8,"Sony Playstation 1"));
            listaPlataformas.add(new Plataformas(9,"Sony Playstation 2"));
        }

    }

    //inicializar generos

    public static void inizializarGeneros(){
        if (listaGeneros.isEmpty()){
            listaGeneros.add(new Generos(1,"Plataformas"));
            listaGeneros.add(new Generos(2,"RPG"));
            listaGeneros.add(new Generos(3,"Lucha"));
            listaGeneros.add(new Generos(4,"Shooter"));
            listaGeneros.add(new Generos(5,"Accion y Aventuras"));
            listaGeneros.add(new Generos(6,"Deportes"));
            listaGeneros.add(new Generos(7,"Survival"));
        }
    }

    // Métodos para que los controladores "pidan" los datos
    public static ObservableList<Generos> getGeneros(){
        return listaGeneros;
    }
    public static ObservableList<Plataformas> getPlataformas(){
        return listaPlataformas;
    }
    public static ObservableList<Compras> getCompras(){
        return listaCompras;
    }
    public static ObservableList<Ventas> getVentas(){
        return listaVentas;
    }



    public static ObservableList<Usuarios> getUsuarios() {
        return listaUsuarios;
    }
    public static ObservableList<Clientes> getClientes() {
        return listaClientes;
    }
    public static ObservableList<Videojuegos> getJuegos() {
        return listaJuegos;
    }
}