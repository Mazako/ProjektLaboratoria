package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Window;
import javafx.util.Duration;
import model.board.Initializator;
import model.board.Safari;
import model.io.Log;
import model.livings.*;

import java.util.Iterator;
import java.util.Random;

public class simulationWindowController {

    public static int RADIUS = 25;



    @FXML
    private TextField fileNameTextArea;


    @FXML
    private Button saveAndQuitButton;
    @FXML
    private AnchorPane boardAnchorPane;

    @FXML
    private Label fileNameLabel;


    @FXML
    private Label giraffeCountLabel;

    @FXML
    private Label lionCountLabel;

    @FXML
    private Label plantsCountLabel;

    @FXML
    private Label snakeCountLabel;

    @FXML
    private Label zebraCountLabel;

    @FXML
    private Canvas boardCanvas;

    @FXML
    private Label stepLabel;

    @FXML
    private Button stopButton;

    private boolean program = true;

    private final Random random = new Random();

    @FXML
    private static Initializator initializator;


    public void initialize() {
        Log log = new Log();
        GraphicsContext gc = boardCanvas.getGraphicsContext2D();
        Safari safari = new Safari(initializator);
        safari.setTargets();
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            stepLoop(safari, gc, log);
            setValues(safari);
        }));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
        stopButton.addEventFilter(ActionEvent.ACTION, event -> {
            animation.stop();
            showEndElements();
        });
        stopButton.addEventFilter(Event.ANY, event -> {
            if (safari.isDone()) {
                showEndElements();
            }
        });
        saveAndQuitButton.addEventFilter(ActionEvent.ACTION, event -> saveAndQuit(log));

    }

    private void saveAndQuit(Log log) {
        String filename = fileNameTextArea.getText();
        log.saveFile(filename);
        Platform.exit();
    }

    private void showEndElements() {
        saveAndQuitButton.setVisible(true);
        fileNameTextArea.setVisible(true);
        fileNameLabel.setVisible(true);
        stepLabel.setText("KONIEC");
    }

    private void setValues(Safari safari) {
        lionCountLabel.setText(Integer.toString(safari.lionSize()));
        snakeCountLabel.setText(Integer.toString(safari.snakeSize()));
        zebraCountLabel.setText(Integer.toString(safari.zebraSize()));
        giraffeCountLabel.setText(Integer.toString(safari.giraffeSize()));
        plantsCountLabel.setText(Integer.toString(safari.plantSize()));
        stepLabel.setText(Integer.toString(safari.getTick()));
    }


    private void stepLoop(Safari safari, GraphicsContext gc, Log log) {
            gc.clearRect(0,0,Safari.MAX_WIDTH + 100 ,Safari.MAX_HEIGHT + 100);
            safari.step();
            saveToLog(log, safari);
            draw(safari.getIterator(), gc);
            if (initializator.isShowDistances()) {
                drawDistances(safari.getIterator(),gc);
            }
    }

    private void saveToLog(Log log, Safari safari) {
        log.addMessage(safari.getTick(),safari.animalSize(),safari.carnivoresSize(),safari.hebivoreSize());
    }

    private void drawDistances(Iterator<Putable> iterator, GraphicsContext gc) {
        while (iterator.hasNext()) {
            Putable p = iterator.next();
            if (p instanceof Movable && ((Movable) p).hasTarget()) {
                gc.beginPath();
                gc.moveTo(p.getX(),p.getY());
                gc.lineTo(((Movable) p).getTarget().getX(), ((Movable) p).getTarget().getY());
                gc.stroke();
            }
        }
    }

    private void draw(Iterator<Putable> iter, GraphicsContext gc) {

        while (iter.hasNext()) {
            Putable p = iter.next();
            if (p instanceof Lion)
                gc.setFill(Color.BLUE);
            else if (p instanceof Snake)
                gc.setFill(Color.RED);
            else if (p instanceof Zebra)
                gc.setFill(Color.BLACK);
            else if (p instanceof Giraffe)
                gc.setFill(Color.YELLOW);
            else if (p instanceof Plant)
                gc.setFill(Color.GREEN);


            gc.fillOval(p.getX(), p.getY(), RADIUS, RADIUS);
            drawHealth(p, gc);
        }

    }

    private void drawHealth(Putable p, GraphicsContext gc) {
        if (p instanceof Animal) {
            int healthFullLength = 45;
            double healthPercent = ((Animal) p).getHealth()/(double)((Animal) p).getMaxHp();
            healthFullLength *= healthPercent;
            gc.setFill(Color.RED);
            gc.fillRect(p.getX() - 10, p.getY() - 10, healthFullLength, 5 );
        }
    }


    public static void setInitializator(Initializator initializator) {
        simulationWindowController.initializator = initializator;

    }
}
