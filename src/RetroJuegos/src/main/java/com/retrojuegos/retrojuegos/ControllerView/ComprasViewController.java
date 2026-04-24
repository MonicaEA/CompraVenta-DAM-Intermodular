package com.retrojuegos.retrojuegos.ControllerView;

import com.retrojuegos.retrojuegos.controller.GestionController;
import com.retrojuegos.retrojuegos.dao.ClientesDAO;
import com.retrojuegos.retrojuegos.dao.ComprasDAO;
import com.retrojuegos.retrojuegos.dao.VideojuegoDAO;
import com.retrojuegos.retrojuegos.model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ComprasViewController implements Initializable {

    @FXML
    private Button btnFinalizar;

    @FXML
    private ChoiceBox<EstadoJuego> choiceEstado;

    @FXML
    private ChoiceBox<TipoStock> choiceTipo;

    @FXML
    private ComboBox<String> comboGenero;

    @FXML
    private ComboBox<String> comboPlataforma;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtDni;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecioCompra;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtPrecioVenta;


    private ClientesDAO clientesDAO = new ClientesDAO();
    private VideojuegoDAO videojuegoDAO = new VideojuegoDAO();
    private ComprasDAO comprasDAO = new ComprasDAO();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        choiceEstado.getItems().setAll(EstadoJuego.values());
        choiceTipo.getItems().setAll(TipoStock.values());

        // para autocompletar
        txtTelefono.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean nuevoValor) {
                if (nuevoValor == false){
                    buscarCliente();
                }
            }
        });
        btnFinalizar.setOnAction(event->{
            ejecutarCompra();
        });
    }



    private void buscarCliente() {

        try {
            Clientes c = clientesDAO.buscarPorTelefono(txtTelefono.getText());
            if (c != null) {
                txtDni.setText(c.getDni());
                txtNombre.setText(c.getNombre());
                txtApellidos.setText(c.getApellidos());
                txtEmail.setText(c.getEmail());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }


    }

    private void ejecutarCompra() {
        try{
            Usuarios usuario = GestionController.getUsuarioLogueado();

            Clientes cliente = clientesDAO.buscarPorTelefono(txtTelefono.getText());
                if (cliente == null){
                   //TODO corregir para añadir campo TipoCliente , ahora por defecto tengo AMBOS , si da tiempo corregir
                    cliente = new Clientes(0, txtNombre.getText(), txtApellidos.getText(), txtDni.getText(), txtEmail.getText(), txtTelefono.getText(), TipoCliente.AMBOS);
                    clientesDAO.insertarCliente(cliente);
                    cliente = clientesDAO.buscarPorTelefono(txtTelefono.getText());
                }
            //meter juego
            Videojuegos videojuego = new Videojuegos(0,txtTitulo.getText(),Double.parseDouble(txtPrecioCompra.getText()),Double.parseDouble(txtPrecioVenta.getText()),1,1,choiceEstado.getValue(),choiceTipo.getValue(), usuario.getIdUsuario());
            int idJuegoGenerado = videojuegoDAO.insertarJuego(videojuego);

            // registro compra
            Compras compra = new Compras(0,java.time.LocalDate.now(),idJuegoGenerado,usuario.getIdUsuario(),usuario.getIdUsuario());
            comprasDAO.insertarCompra(compra);

            System.out.println("Compra guardada");


        }catch (SQLException e){
            System.out.println("Error en la BBDD "+e.getMessage());
        }catch (NumberFormatException e){
            System.out.println("Que los precios sean números");
        }




    }

}
