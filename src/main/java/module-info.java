module com.example.facelite {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.facelite to javafx.fxml;
    exports com.example.facelite;
}