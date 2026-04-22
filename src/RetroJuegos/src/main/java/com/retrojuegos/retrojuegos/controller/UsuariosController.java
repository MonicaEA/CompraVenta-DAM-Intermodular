package com.retrojuegos.retrojuegos.controller;

import com.retrojuegos.retrojuegos.data.DataSet;
import com.retrojuegos.retrojuegos.model.Usuarios;
import javafx.collections.ObservableList;

public class UsuariosController {

    private static ObservableList<Usuarios> misUsuarios = DataSet.getUsuarios();

    //metodo del login
    public static Usuarios login (String dni, String pass){

        for (Usuarios u : misUsuarios){
            if (u.getDni().equalsIgnoreCase(dni) && u.getPass().equals(pass)){
                System.out.println("Acceso permitido. Bienvenida!");
                return u;
            }
        }
        System.out.println("Error en los datos de logado");
        return null;

    }

    //registrar nuevos usuarios, de cara al futuro, ahora tampoco estará en la interfaz gráfica.

    public static void registrarUsuario(Usuarios nuevo){
        misUsuarios.add(nuevo);
        System.out.println("Nueva usuario registrada");

        //TODO GestionController.guardarDatos();
    }
}
