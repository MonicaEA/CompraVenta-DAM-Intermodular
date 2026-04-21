module com.retrojuegos.retrojuegos {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.retrojuegos.retrojuegos to javafx.fxml;
    exports com.retrojuegos.retrojuegos;
}