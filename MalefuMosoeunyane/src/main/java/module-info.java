module com.example.malefumosoeunyane {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.malefumosoeunyane to javafx.fxml;
    exports com.example.malefumosoeunyane;
}