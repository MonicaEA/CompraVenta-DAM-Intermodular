package com.retrojuegos.retrojuegos.controller;

import com.retrojuegos.retrojuegos.data.DataSet;
import com.retrojuegos.retrojuegos.model.Clientes;
import javafx.collections.ObservableList;

public class ClientesController {
    private static ObservableList<Clientes> misClientes = DataSet.getClientes();


    // metodo para registrar los clientes
    public static void registrarCliente(Clientes nuevo){
        misClientes.add(nuevo);
        System.out.println("Cliente añadido correctamente");

        // TODO : gestionController.guardarDatos();


    }


    //metodo busqueda por dni
    public static Clientes buscarClientesPorDni(String dni){
        for (Clientes c : misClientes){

            if (c.getDni().equalsIgnoreCase(dni)){
                System.out.println("Cliente encontrado");
                return c;
            }
        }
        System.out.println("No se ha encontrado el cliente");
        return null;


    }

    //metodo listar clientes, esto posiblemente no lo ponga en la interfaz gráfica
    public static void listarClientes(){
        System.out.println("Agenda de clientes:");
        if (misClientes.isEmpty()){
            System.out.println("No hay clientes registrados");
        } else {
            for (Clientes c : misClientes){
                System.out.println(" - " + c.getNombre() + " " + c.getApellidos() + " Teléfono: " + c.getTelefono());
            }
        }
    }
}
