module ProjektLaboratoria {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;

    exports view;

    opens controller to javafx.fxml;
}