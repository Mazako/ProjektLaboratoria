package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.board.Initializator;
import view.App;

import java.io.IOException;

public class optionsController {

    @FXML
    private CheckBox showDistancesCheckBox;
    @FXML
    private TextField attackCooldownLabel;

    @FXML
    private TextField hungerPerTickLabel;

    @FXML
    private TextField plantsPerTickLabel;

    @FXML
    private TextField plantsCooldownLabel;

    @FXML
    private TextField giraffeCountValue;

    @FXML
    private TextField giraffeHpValue;


    @FXML
    private TextField giraffeSpeedValue;

    @FXML
    private TextField lionAttackValue;

    @FXML
    private TextField lionCountValue;

    @FXML
    private TextField lionHpValue;


    @FXML
    private TextField lionSpeedValue;

    @FXML
    private TextField plantCountValue;

    @FXML
    private TextField plantMaxHpValue;

    @FXML
    private TextField plantMinHpValue;

    @FXML
    private TextField snakeAttackValue;

    @FXML
    private TextField snakeCountValue;

    @FXML
    private TextField snakeHpValue;


    @FXML
    private TextField snakeSpeedValue;

    @FXML
    private Button startingButton;

    @FXML
    private TextField zebraCountValue;

    @FXML
    private TextField zebraHpValue;

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
        init.setLionSpeed(Integer.parseInt(lionSpeedValue.getText()));
        init.setLionAttackValue(Integer.parseInt(lionAttackValue.getText()));
        init.setLions(Integer.parseInt(lionCountValue.getText()));

        init.setSnakeHealth(Integer.parseInt(snakeHpValue.getText()));
        init.setSnakeSpeed(Integer.parseInt(snakeSpeedValue.getText()));
        init.setSnakeAttackValue(Integer.parseInt(snakeAttackValue.getText()));
        init.setSnakes(Integer.parseInt(snakeCountValue.getText()));

        init.setGiraffeHealth(Integer.parseInt(giraffeHpValue.getText()));
        init.setGiraffeSpeed(Integer.parseInt(giraffeSpeedValue.getText()));
        init.setGiraffes(Integer.parseInt(giraffeCountValue.getText()));

        init.setZebraHealth(Integer.parseInt(zebraHpValue.getText()));
        init.setZebraSpeed(Integer.parseInt(zebraSpeedValue.getText()));
        init.setZebras(Integer.parseInt(zebraCountValue.getText()));

        init.setHealValueMin(Integer.parseInt(plantMinHpValue.getText()));
        init.setHealValueMax(Integer.parseInt(plantMaxHpValue.getText()));
        init.setPlants(Integer.parseInt(plantCountValue.getText()));

        init.setPlantsPerTick(Integer.parseInt(plantsPerTickLabel.getText()));
        init.setTicksPerPlantSpawn(Integer.parseInt(plantsCooldownLabel.getText()));
        init.setAttackCooldown(Integer.parseInt(attackCooldownLabel.getText()));
        init.setHungerPerTick(Integer.parseInt(hungerPerTickLabel.getText()));

        init.setShowDistances(showDistancesCheckBox.isSelected());


    }

}
