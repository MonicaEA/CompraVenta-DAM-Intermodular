package com.retrojuegos.retrojuegos.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Ventas {


        private int idVenta;
        private String titulo; // Asegúrate de que sea String y esté en esta posición
        private double precioVenta;
        private double beneficioMonica;
        private double beneficioBetty;
        private java.time.LocalDate fechaVenta;



    public String toCSV() {
        return idVenta + ";" + titulo + ";" + precioVenta + ";" + beneficioMonica + ";" + beneficioBetty + ";" + fechaVenta;
    }

}
