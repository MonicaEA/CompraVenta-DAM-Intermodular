package com.retrojuegos.retrojuegos.ControllerView;

import com.retrojuegos.retrojuegos.dao.VentasDAO;
import com.retrojuegos.retrojuegos.dao.VideojuegoDAO;
import com.retrojuegos.retrojuegos.model.Usuarios;
import com.retrojuegos.retrojuegos.model.Videojuegos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class VentasViewController implements Initializable {

    @FXML private Button btnEliminar;
    @FXML private Button btnFinalizar;
    @FXML private Button btnVolver;
    @FXML private TableColumn<Videojuegos, Double> colCarritoPrecio;
    @FXML private TableColumn<Videojuegos, String> colCarritoTitulo;
    @FXML private TableColumn<Videojuegos, String> colTabPlataforma;
    @FXML private TableColumn<Videojuegos, Double> colTabPrecio;
    @FXML private TableColumn<Videojuegos, String> colTabTitulo;
    @FXML private Label lblTotal;
    @FXML private TableView<Videojuegos> tablaCarrito;
    @FXML private TableView<Videojuegos> tablaCatalogo;
    @FXML private TextField txtBuscar;

    private ObservableList<Videojuegos> listaCatalogo = FXCollections.observableArrayList();
    private ObservableList<Videojuegos> listaCarrito = FXCollections.observableArrayList();
    private VideojuegoDAO videojuegoDAO = new VideojuegoDAO();
    private VentasDAO ventasDAO = new VentasDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        actions();
    }

    //acciones de la ventana
    private void actions() {
        configurarColumnas();
        cargarCatalogo();
        configurarBuscador();
        configurarCarrito();
        configurarBotones();
    }

    private void configurarBotones() {
        btnEliminar.setOnAction(event->{
            Videojuegos juegoSeleccionado = tablaCarrito.getSelectionModel().getSelectedItem();
            if (juegoSeleccionado !=null){
                listaCarrito.remove(juegoSeleccionado);
                listaCatalogo.add(juegoSeleccionado);

                calcularTotal();
            }else{
                mostrarAlerta("NO HAY NADA SELECCIONADO","Selecciona un juego del carrito para eliminar");
            }
        });

        btnVolver.setOnAction(event->{
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/retrojuegos/retrojuegos/main-view.fxml"));

                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setTitle("MENU PRINCIPAL");
                stage.setScene(new Scene(root));
                stage.show();
                ((Stage) btnVolver.getScene().getWindow()).close();



            } catch (IOException e) {
                System.out.println("Error al volver al menú principal");
            }


        });

        btnFinalizar.setOnAction(event->{
            if (listaCarrito.isEmpty()){
                mostrarAlerta("Carrito vacio", "Añade juegos para continuar");
                return;
            }
            Usuarios usuarios = UsuarioActualController.getUsuarioLogueado();
            boolean exito = ventasDAO.registrarVenta(listaCarrito,usuarios.getIdUsuario());

            if (exito){
                mostrarAlerta("Venta realizada", "Registrada correctamente");
                listaCarrito.clear();
                calcularTotal();
                cargarCatalogo();
            }else {
                mostrarAlerta("Error", "No se ha podido guardar la venta");
            }

        });

    }

    private void mostrarAlerta(String titulo,String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // metodos de la ventana

    private void configurarColumnas() {
        colTabTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colTabPlataforma.setCellValueFactory(new PropertyValueFactory<>("nombrePlataforma"));
        colTabPrecio.setCellValueFactory(new PropertyValueFactory<>("precioVentaEstimada"));

        colCarritoTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colCarritoPrecio.setCellValueFactory(new PropertyValueFactory<>("precioVentaEstimada"));
    }

    private void cargarCatalogo() {
        try {
            listaCatalogo.clear();
            listaCatalogo.addAll(videojuegoDAO.obtenerDisponibles());

        } catch (SQLException e) {
            System.out.println("Error al cargar el catalogo de juegos: " + e.getMessage());
        }
    }

    private void configurarBuscador() {
       // lista con filtro
        FilteredList<Videojuegos> listaFiltrada = new FilteredList<>(listaCatalogo, b -> true);


        txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            listaFiltrada.setPredicate(juego -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String palabraBuscada = newValue.toLowerCase();
                return juego.getTitulo().toLowerCase().contains(palabraBuscada);
            });
        });


        SortedList<Videojuegos> listaOrdenada = new SortedList<>(listaFiltrada);
        listaOrdenada.comparatorProperty().bind(tablaCatalogo.comparatorProperty());
        tablaCatalogo.setItems(listaOrdenada);
    }

    private void configurarCarrito() {

        tablaCarrito.setItems(listaCarrito);

        // doble click pasa al carrito
        tablaCatalogo.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && tablaCatalogo.getSelectionModel().getSelectedItem() != null) {
                Videojuegos juegoSeleccionado = tablaCatalogo.getSelectionModel().getSelectedItem();

                listaCarrito.add(juegoSeleccionado);
                listaCatalogo.remove(juegoSeleccionado);

                calcularTotal();
            }
        });
    }

    private void calcularTotal() {
        double sumaTotal = 0.0;
        for (Videojuegos juego : listaCarrito) {
            sumaTotal += juego.getPrecioVentaEstimada();
        }
        lblTotal.setText(String.format("Total: %.2f €", sumaTotal));
    }
}