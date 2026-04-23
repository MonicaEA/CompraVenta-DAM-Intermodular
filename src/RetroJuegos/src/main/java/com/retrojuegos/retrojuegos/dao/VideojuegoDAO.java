package com.retrojuegos.retrojuegos.dao;

import com.retrojuegos.retrojuegos.database.DBConnection;
import com.retrojuegos.retrojuegos.model.Videojuegos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VideojuegoDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void insertarJuego(Videojuegos juego) throws SQLException {
        connection = DBConnection.getConnection();
        String query = "INSERT INTO videojuegos (titulo, precio_compra, precio_venta, id_plataforma, id_genero, estado, tipo_stock, id_usuario) VALUES (?,?,?,?,?,?,?,?)";

        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, juego.getTitulo());
        preparedStatement.setDouble(2,juego.getPrecioCompra());
        preparedStatement.setDouble(3,juego.getPrecioVentaEstimada());
        preparedStatement.setInt(4,juego.getIdPlataforma());
        preparedStatement.setInt(5,juego.getIdGenero());

        preparedStatement.setString(6,juego.getEstado().name());
        preparedStatement.setString(7,juego.getTipo().name());
        preparedStatement.setInt(8,juego.getUsuarioRegistro());

        preparedStatement.executeUpdate();
        preparedStatement.close();



    }


}
