package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.board.Initializator;

public class simulationWindowController {

    @FXML
    private Button saveButton;

    @FXML
    private Label stepCountLabel;

    @FXML
    private Button stopButton;

    @FXML
    private static Initializator initializator;

    public void initialize() {
        System.out.println(initializator);
    }

    public static void setInitializator(Initializator initializator) {
        simulationWindowController.initializator = initializator;
        System.out.println("X");
    }
}
