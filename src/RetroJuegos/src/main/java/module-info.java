module com.retrojuegos.retrojuegos {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;


    opens com.retrojuegos.retrojuegos to javafx.fxml;
    exports com.retrojuegos.retrojuegos;
}