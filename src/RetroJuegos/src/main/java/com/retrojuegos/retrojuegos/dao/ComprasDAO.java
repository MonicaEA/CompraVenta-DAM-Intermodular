package com.retrojuegos.retrojuegos.dao;

import com.retrojuegos.retrojuegos.database.DBConnection;
import com.retrojuegos.retrojuegos.model.Compras;

import java.sql.*;
import java.time.LocalDate;

public class ComprasDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void insertarCompra(Compras compra) throws SQLException {
        connection = DBConnection.getConnection();
        String query = "INSERT INTO compras (id_juego, id_cliente, id_usuario, fecha_compra) VALUES (?,?,?,?)";
        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, compra.getIdJuego());
        preparedStatement.setInt(2, compra.getIdCliente());
        preparedStatement.setInt(3, compra.getIdUsuario());
        preparedStatement.setDate(4, Date.valueOf(compra.getFechaCompra()));
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

}
