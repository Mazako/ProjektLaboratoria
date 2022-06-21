package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class mainMenuController {

    /**
     * Przycisk startu
     */
    @FXML
    private Button startButton;


    /** Metoda inicjalizacji
     *
     */
    public void initialize() {
        startButton.addEventFilter(ActionEvent.ACTION, event -> {
            Stage stage = new Stage();
            try {
                ((Node)(event.getSource())).getScene().getWindow().hide();
                VBox vbox = FXMLLoader.load(getClass().getResource("/options.fxml"));
                Scene scene = new Scene(vbox);
                stage.setScene(scene);
                stage.show();
                stage.setTitle("Konfiguracja");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }
}
