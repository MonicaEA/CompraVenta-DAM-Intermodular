package com.retrojuegos.retrojuegos.ControllerView;

import com.retrojuegos.retrojuegos.dao.ComprasDAO;
import com.retrojuegos.retrojuegos.dao.VentasDAO;
import com.retrojuegos.retrojuegos.model.TipoStock;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FinanzasViewController implements Initializable {

    @FXML private Label lblGastado;
    @FXML private Label lblIngresado;
    @FXML private Label lblBetty;
    @FXML private Label lnblMoni; // Asegúrate que en Scene Builder sea lnblMoni
    @FXML private Button btnVolver;

    private ComprasDAO comprasDAO;
    private VentasDAO ventasDAO;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instancias();
        initGUI();
        acciones();
    }

    private void instancias() {
        comprasDAO = new ComprasDAO();
        ventasDAO = new VentasDAO();
    }

    private void initGUI() {
        calcularFinanzas();
    }

    private void acciones() {
        btnVolver.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/retrojuegos/retrojuegos/main-view.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) btnVolver.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("MENÚ PRINCIPAL");
                stage.show();
            } catch (IOException e) {
                System.out.println("Error al volver al menú principal: " + e.getMessage());
            }
        });
    }

    private void calcularFinanzas() {
        try {
            // total gastado e ingresado
            double totalGastado = comprasDAO.obtenerSumaTotalCompras();
            double totalIngresado = ventasDAO.obtenerSumaTotalVentas();

            // beneficio según tipo de stocl
            double beneficioComun = ventasDAO.obtenerBeneficioNeto(TipoStock.COMUN.name());
            double beneficioPropio = ventasDAO.obtenerBeneficioNeto(TipoStock.PROPIO.name());



            // reparto 50/50
            double parteComunBetty = beneficioComun * 0.50;
            double parteComunMoni = beneficioComun * 0.50;

            // reparto propio
            double partePropiaMoni = beneficioPropio * 0.70;
            double partePropiaBetty = beneficioPropio * 0.30;

            // total beneficios
            double totalMoni = parteComunMoni + partePropiaMoni;
            double totalBetty = parteComunBetty + partePropiaBetty;

            // mostrarlo
            lblGastado.setText(String.format("TOTAL GASTADO: %.2f €", totalGastado));
            lblIngresado.setText(String.format("TOTAL INGRESADO: %.2f €", totalIngresado));

            lblBetty.setText(String.format("Beneficios Betty: %.2f €", totalBetty));
            lnblMoni.setText(String.format("Beneficios Moni: %.2f €", totalMoni));


        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}