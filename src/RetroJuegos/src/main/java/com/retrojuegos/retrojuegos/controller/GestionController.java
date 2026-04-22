package com.retrojuegos.retrojuegos.controller;

import com.retrojuegos.retrojuegos.data.DataSet;
import com.retrojuegos.retrojuegos.model.Clientes;
import com.retrojuegos.retrojuegos.model.EstadoJuego;
import com.retrojuegos.retrojuegos.model.TipoStock;
import com.retrojuegos.retrojuegos.model.Videojuegos;

import java.io.*;
import java.util.Scanner;

public class GestionController {

    private static final String rutaVideojuegos = "src/main/java/resources/videojuegos.csv";
    private static final String rutaClientes = "src/main/java/resources/clientes.csv";
    private static Scanner sc = new Scanner(System.in);

// TODO
    // Metodos de logica para crear juegos, etc

    public static void guardarTodoFichero(){
        guardarJuegosFichero();
        guardarClientesFichero();
    }

    public static void cargarTodoFichero(){
        cargarVideojuegosFichero();
        cargarClientesFichero();
    }

    public static void guardarJuegosFichero(){
        File file = new File(rutaVideojuegos);
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        BufferedWriter bufferedWriter = null;

        try{
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (Videojuegos v : DataSet.getJuegos()){
                bufferedWriter.write(v.toCSV());
                bufferedWriter.newLine();
            }
            System.out.println("Fichero guardado correctamente");
        } catch (IOException e){
            System.out.println("Error al escribir: " + e.getMessage());
        }finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                ;
            }catch (IOException e){
                System.out.println("Error al cerrar el fichero");
            }
        }


    }

    public static void guardarClientesFichero(){
        File file = new File(rutaClientes);
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        BufferedWriter bufferedWriter = null;

        try{
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (Clientes c : DataSet.getClientes()){
                bufferedWriter.write(c.toCSV());
                bufferedWriter.newLine();
            }
            System.out.println("Fichero de clientes guardado");

        }   catch (IOException e){
            System.out.println("error al escribir clientes: " + e.getMessage());
        }finally {
            try{
                if (bufferedWriter !=null){
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo.");
            }
        }

    }


    //TODO ESTO ES UNA PRUEBA pendiente de hacerlo YO
    public static void cargarVideojuegosFichero() {
        File file = new File(rutaVideojuegos);
        if (!file.exists()) return; // Si no hay fichero, no hay nada que cargar

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String linea;

            // Limpiamos la lista actual para no duplicar datos
            DataSet.getJuegos().clear();

            while ((linea = bufferedReader.readLine()) != null) {
                String[] datos = linea.split(";");

                // Creamos el objeto con los datos del array (Ojo al orden)
                Videojuegos v = new Videojuegos(
                        Integer.parseInt(datos[0]),    // id
                        datos[1],                      // titulo
                        Double.parseDouble(datos[2]),  // precioCompra
                        Double.parseDouble(datos[3]),  // precioVenta
                        1, 1, null,                    // valores por defecto (idPlat, idGen, Estado)
                        TipoStock.valueOf(datos[4]),   // Convertimos texto a Enum
                        1                              // idUsuario
                );
                DataSet.getJuegos().add(v);
            }
            System.out.println("Videojuegos cargados desde CSV.");

        } catch (IOException e) {
            System.out.println("Error al cargar videojuegos: " + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el lector");
            }
        }
    }

    public static void cargarClientesFichero() {
        File file = new File(rutaClientes);
        if (!file.exists()) return;

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String linea;

            DataSet.getClientes().clear();

            while ((linea = bufferedReader.readLine()) != null) {
                String[] datos = linea.split(";");

                Clientes c = new Clientes(
                        Integer.parseInt(datos[0]), // id
                        datos[1],                   // nombre
                        datos[2],                   // apellidos
                        datos[3],                   // dni
                        datos[4],                   // email
                        datos[5],                   // telefono
                        null                        // TipoCliente (puedes poner null o cargarlo tmb)
                );
                DataSet.getClientes().add(c);
            }
            System.out.println("Clientes cargados desde CSV.");

        } catch (IOException e) {
            System.out.println("Error al cargar clientes: " + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el lector");
            }
        }
    }


}
