package com.retrojuegos.retrojuegos.controller;

import com.retrojuegos.retrojuegos.data.DataSet;
import com.retrojuegos.retrojuegos.model.*;
import lombok.*;

import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;



public class GestionController {

    private static Usuarios usuarioLogueado;
    private static final String rutaVideojuegos = "src/main/java/resources/videojuegos.csv";
    private static final String rutaClientes = "src/main/java/resources/clientes.csv";
    private static Scanner sc = new Scanner(System.in);


// TODO
    // Metodos de logica para crear juegos, etc

    // metodo para crearCliente

    public static void crearCliente(){
        System.out.println("REGISTRO NUEVO CLIENTE");
        System.out.println("Id cliente: " );
        int idCliente = sc.nextInt();
        sc.nextLine();
        System.out.println("Nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Apellido: ");
        String apellidos = sc.nextLine();
        System.out.println("DNI: ");
        String dni = sc.nextLine();
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Teléfono: ");
        String telefono = sc.nextLine();
        System.out.println("Selecciona tipo de cliente: 1: COMPRADOR , 2: VENDEDOR , 3 : AMBOS. Escribe el número seleccionado");
        int opcion = sc.nextInt();
        sc.nextLine();

        TipoCliente tipoCliente;
        switch (opcion){
            case 1 -> tipoCliente = TipoCliente.COMPRADOR;
            case 2 -> tipoCliente = TipoCliente.VENDEDOR;
            case 3 -> tipoCliente = TipoCliente.AMBOS;
            default -> {
                System.out.println("Opción no válida. Asignamos por defecto AMBOS");
                tipoCliente = TipoCliente.AMBOS;
            }
        }

        Clientes nuevoCliente = new Clientes(idCliente,nombre,apellidos,dni,email,telefono,tipoCliente);
        ClientesController.registrarCliente(nuevoCliente);
        System.out.println("Cliente registrado correctamente.");


    }

    public static void setUsuarioLogueado(Usuarios u) {
        usuarioLogueado = u;
    }


}
