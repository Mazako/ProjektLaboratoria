package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.board.Initializator;
import view.App;

import java.io.IOException;

public class optionsController {

    @FXML
    private TextField giraffeCountValue;

    @FXML
    private TextField giraffeHpValue;

    @FXML
    private TextField giraffeHungerValue;

    @FXML
    private TextField giraffeSpeedValue;

    @FXML
    private TextField lionAttackValue;

    @FXML
    private TextField lionCountValue;

    @FXML
    private TextField lionHpValue;

    @FXML
    private TextField lionHungerValue;

    @FXML
    private TextField lionSpeedValue;

    @FXML
    private TextField plantCountValue;

    @FXML
    private TextField plantMaxHpValue;

    @FXML
    private TextField plantMaxHungerValue;
    @FXML
    private TextField plantMinHungerValue;

    @FXML
    private TextField plantMinHpValue;

    @FXML
    private TextField snakeAttackValue;

    @FXML
    private TextField snakeCountValue;

    @FXML
    private TextField snakeHpValue;

    @FXML
    private TextField snakeHungerValue;

    @FXML
    private TextField snakeSpeedValue;

    @FXML
    private Button startingButton;

    @FXML
    private TextField zebraCountValue;

    @FXML
    private TextField zebraHpValue;

    @FXML
    private TextField zebraHungerValue;

    @FXML
    private TextField zebraSpeedValue;


    public void initialize() {
        Initializator init = new Initializator();
        startingButton.addEventFilter(ActionEvent.ACTION,event -> {
            saveValues(init);
            openNewWindow(init, event);

        });
    }

    private void openNewWindow(Initializator init, ActionEvent event) {
        try {
            simulationWindowController.setInitializator(init);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/simulationWindow.fxml"));
            ((Node)(event.getSource())).getScene().getWindow().hide();
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setTitle("SafariSim");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveValues(Initializator init) {
        init.setLionHealth(Integer.parseInt(lionHpValue.getText()));
        init.setLionHunger(Integer.parseInt(lionHungerValue.getText()));
        init.setLionSpeed(Integer.parseInt(lionSpeedValue.getText()));
        init.setLionAttackValue(Integer.parseInt(lionAttackValue.getText()));
        init.setLions(Integer.parseInt(lionCountValue.getText()));

        init.setSnakeHealth(Integer.parseInt(snakeHpValue.getText()));
        init.setSnakeHunger(Integer.parseInt(snakeHungerValue.getText()));
        init.setSnakeSpeed(Integer.parseInt(snakeSpeedValue.getText()));
        init.setSnakeAttackValue(Integer.parseInt(snakeAttackValue.getText()));
        init.setSnakes(Integer.parseInt(snakeCountValue.getText()));

        init.setGiraffeHealth(Integer.parseInt(giraffeHpValue.getText()));
        init.setGiraffeHunger(Integer.parseInt(giraffeHungerValue.getText()));
        init.setGiraffeSpeed(Integer.parseInt(giraffeSpeedValue.getText()));
        init.setGiraffes(Integer.parseInt(giraffeCountValue.getText()));

        init.setZebraHealth(Integer.parseInt(zebraHpValue.getText()));
        init.setZebraHunger(Integer.parseInt(zebraHungerValue.getText()));
        init.setZebraSpeed(Integer.parseInt(zebraSpeedValue.getText()));
        init.setZebras(Integer.parseInt(zebraCountValue.getText()));

        init.setHealValueMin(Integer.parseInt(plantMinHpValue.getText()));
        init.setHealValueMax(Integer.parseInt(plantMaxHpValue.getText()));
        init.setHungerValueMin(Integer.parseInt(plantMinHungerValue.getText()));
        init.setHungerValueMax(Integer.parseInt(plantMaxHungerValue.getText()));
        init.setPlants(Integer.parseInt(plantCountValue.getText()));
    }

}
