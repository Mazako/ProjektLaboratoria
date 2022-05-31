module ProjektLaboratoria {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.media;

    opens controller to javafx.fxml;
}