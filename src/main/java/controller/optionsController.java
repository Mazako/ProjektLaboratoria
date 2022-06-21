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

    /**
     *checkbox na pokazywanie dystansu
     */
    @FXML
    private CheckBox showDistancesCheckBox;
    /**
     *
     */
    @FXML
    private TextField attackCooldownLabel;

    /**
     *
     */
    @FXML
    private TextField hungerPerTickLabel;

    /**
     *ilosc roslin na jeden tick
     */
    @FXML
    private TextField plantsPerTickLabel;

    /**
     *
     */
    @FXML
    private TextField plantsCooldownLabel;

    /**
     *pole tekstowe na wpisanie ilosci zyraf
     */
    @FXML
    private TextField giraffeCountValue;

    /**
     * Wartosc zdrowia zyrafy
     */
    @FXML
    private TextField giraffeHpValue;


    /**
     * Wartosc predkosci zyrafy
     */
    @FXML
    private TextField giraffeSpeedValue;

    /**
     * wartosc ataku lwa
     */
    @FXML
    private TextField lionAttackValue;

    /**
     *pole tekstowe na wpisanie ilosci lwa
     */
    @FXML
    private TextField lionCountValue;

    /**
     * wartosc zdrowia lwa
     */
    @FXML
    private TextField lionHpValue;


    /**
     * wartosc predkosci lwa
     */
    @FXML
    private TextField lionSpeedValue;

    /**
     *pole tekstowe na wpisywanie ilosci roslin
     */
    @FXML
    private TextField plantCountValue;

    /**
     * wartosc maksymalnego zdrowia rosliny
     */
    @FXML
    private TextField plantMaxHpValue;

    /**
     * wartosc minimalnego zdrowia
     */
    @FXML
    private TextField plantMinHpValue;

    /**
     * wartosc ataku weza
     */
    @FXML
    private TextField snakeAttackValue;

    /**
     *pole tekstowe na wpisanie ilosci wezy
     */
    @FXML
    private TextField snakeCountValue;

    /**
     * wartosc zdrowia weza
     */
    @FXML
    private TextField snakeHpValue;


    /**
     * wartosc predkosci weza
     */
    @FXML
    private TextField snakeSpeedValue;

    /**
     *Przycisk startujacy
     */
    @FXML
    private Button startingButton;

    /**
     *pole tekstowe na wpisanie ilosci zebr
     */
    @FXML
    private TextField zebraCountValue;

    /**
     * wartosc zdrowia zebry
     */
    @FXML
    private TextField zebraHpValue;

    /**
     * wartosc predkosci zebry
     */
    @FXML
    private TextField zebraSpeedValue;


    /**
     * Metoda inicjalizacji
     */
    public void initialize() {
        Initializator init = new Initializator();
        startingButton.addEventFilter(ActionEvent.ACTION,event -> {
            saveValues(init);
            openNewWindow(init, event);

        });
    }

    /** Metoda otworzenia okna aplikacji
     * @param init inicjalizator
     * @param event event
     */
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

    /** metoda przekazujaca wartosci do obiektu inicjalizator
     * @param init inicjalizator
     */
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
