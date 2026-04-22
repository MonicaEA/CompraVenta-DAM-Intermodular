package com.retrojuegos.retrojuegos;

import com.retrojuegos.retrojuegos.controller.ClientesController;
import com.retrojuegos.retrojuegos.controller.GestionController;
import com.retrojuegos.retrojuegos.controller.UsuariosController;
import com.retrojuegos.retrojuegos.controller.VideojuegosController;
import com.retrojuegos.retrojuegos.database.DBConnection;
import com.retrojuegos.retrojuegos.model.Usuarios;

import java.sql.Connection;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Connection testDB = DBConnection.getConnection();


    }
}