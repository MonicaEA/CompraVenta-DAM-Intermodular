package com.retrojuegos.retrojuegos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compras {
    private int idCompra;
    private LocalDate fechaCompra;
    private int idJuego;
    private int idUsuario;
    private int idCliente;


    public String toCSV() {
        return idCompra + ";" + fechaCompra + ";" + idJuego + ";" + idUsuario + ";" + idCliente;
    }

}
