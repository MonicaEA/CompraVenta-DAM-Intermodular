package com.retrojuegos.retrojuegos;

import com.retrojuegos.retrojuegos.controller.VideojuegosController;
import com.retrojuegos.retrojuegos.database.DBConnection;
import com.retrojuegos.retrojuegos.model.EstadoJuego;
import com.retrojuegos.retrojuegos.model.TipoStock;
import com.retrojuegos.retrojuegos.model.Videojuegos;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {


        Connection testDB = DBConnection.getConnection();

    }



}
