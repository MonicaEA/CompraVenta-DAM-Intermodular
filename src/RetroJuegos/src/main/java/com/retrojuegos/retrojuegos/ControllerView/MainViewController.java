package com.retrojuegos.retrojuegos.ControllerView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private Button btnComprar, btnVender, btnStock, btnFinanzas;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        actions();





    }

    private void actions() {
        configurarBotones();



    }

    private void configurarBotones() {
        btnComprar.setOnAction(event->{
            System.out.println("PAsando a pantalla de compras...");
            abrirVentanaCompra();
        });

        btnVender.setOnAction(event->{
            System.out.println("Pantalla de ventas...");
            abrirVentanaVenta();
        });

        btnStock.setOnAction(event->{
            System.out.println("Pantalla de stock");
            abrirVentanaStock();

        });

        btnFinanzas.setOnAction(event->{
            System.out.println("Pantalla de los euritos...");
        });

    }

    private void abrirVentanaCompra(){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/retrojuegos/retrojuegos/compra-view.fxml"));

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("REGISTRO DE COMPRA");
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage) btnComprar.getScene().getWindow()).close();
        }catch (IOException e){
            System.out.println("Error al cargar ventana de compras: "+e.getMessage());
            e.printStackTrace();
        }


    }

    private void abrirVentanaVenta(){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/retrojuegos/retrojuegos/ventas-view.fxml"));

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("REGISTRO DE VENTAS");
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage) btnVender.getScene().getWindow()).close();
        }catch (IOException e){
            System.out.println("Error al cargar ventana de ventas: "+e.getMessage());
            e.printStackTrace();
        }


    }

    private void abrirVentanaStock(){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/retrojuegos/retrojuegos/stock-view.fxml"));

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("LISTADO STOCK");
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage) btnStock.getScene().getWindow()).close();
        }catch (IOException e){
            System.out.println("Error al cargar ventana de Stock: "+e.getMessage());
            e.printStackTrace();
        }


    }
}
