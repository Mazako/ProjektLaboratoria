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

/** Kontroler do okna Symulacji
 *
 */
public class simulationWindowController {

    /**
     * promień koła reprezentującego obiekt
     */
    public static int RADIUS = 25;


    /**Pole tekstowe do wpisywania nazwy pliku txt
     *
     */
    @FXML
    private TextField fileNameTextArea;


    /**
     * Przycisk do zapisania pliku tekstowego i wyjscia z symulacji
     */
    @FXML
    private Button saveAndQuitButton;
    /**
     * Głowny panel planszy
     */
    @FXML
    private AnchorPane boardAnchorPane;

    /**
     * pole pokazujące tekst "nazwa pliku"
     */
    @FXML
    private Label fileNameLabel;


    /**
     * pole pokazujac ile jest zyraf na planszy
     */
    @FXML
    private Label giraffeCountLabel;

    /**
     * pole pokazujac ile jest lwow na planszy
     */
    @FXML
    private Label lionCountLabel;

    /**
     * pole pokazujac ile jest roslin na planszy
     */
    @FXML
    private Label plantsCountLabel;

    /**
     * pole pokazujac ile jest wezow na planszy
     */
    @FXML
    private Label snakeCountLabel;

    /**
     * pole pokazujac ile jest zeber na planszy
     */
    @FXML
    private Label zebraCountLabel;

    /**
     * Canvas na którym dokonywanie jest rysowanie i wyświetlanie obiektów
     */
    @FXML
    private Canvas boardCanvas;

    /**
     * pole pokazujące krok symulacji
     */
    @FXML
    private Label stepLabel;

    /**
     * przycisk do zatrzymania symulacji
     */
    @FXML
    private Button stopButton;

    /**
     * zmienna, która pokazuje, czy symulacja trwa czy nie
     */
    private boolean program = true;

    private final Random random = new Random();

    /**
     * Inicjalizator
     */
    @FXML
    private static Initializator initializator;


    /**
     * Metoda inicjalizacji
     */
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

    /**metoda, która zapisuje dane do pliku tekstowego i wychodzi z programu
     * @param log
     */
    private void saveAndQuit(Log log) {
        String filename = fileNameTextArea.getText();
        log.saveFile(filename);
        Platform.exit();
    }

    /**
     * metoda pokazujaca koniec elementu
     */
    private void showEndElements() {
        saveAndQuitButton.setVisible(true);
        fileNameTextArea.setVisible(true);
        fileNameLabel.setVisible(true);
        stepLabel.setText("KONIEC");
    }

    /**metoda, która wyświetla aktualne ilości obiektów w symulacji
     * @param safari
     */
    private void setValues(Safari safari) {
        lionCountLabel.setText(Integer.toString(safari.lionSize()));
        snakeCountLabel.setText(Integer.toString(safari.snakeSize()));
        zebraCountLabel.setText(Integer.toString(safari.zebraSize()));
        giraffeCountLabel.setText(Integer.toString(safari.giraffeSize()));
        plantsCountLabel.setText(Integer.toString(safari.plantSize()));
        stepLabel.setText(Integer.toString(safari.getTick()));
    }


    /**metoda, która odpowiada za przeprowadzenie jednego kroku symulacji włącznie z narysowaniem obiektów na planszy
     * @param safari
     * @param gc
     * @param log
     */
    private void stepLoop(Safari safari, GraphicsContext gc, Log log) {
            gc.clearRect(0,0,Safari.MAX_WIDTH + 100 ,Safari.MAX_HEIGHT + 100);
            safari.step();
            saveToLog(log, safari);
            draw(safari.getIterator(), gc);
            if (initializator.isShowDistances()) {
                drawDistances(safari.getIterator(),gc);
            }
    }

    /**metoda zapisz
     * @param log
     * @param safari
     */
    private void saveToLog(Log log, Safari safari) {
        log.addMessage(safari.getTick(),safari.animalSize(),safari.carnivoresSize(),safari.hebivoreSize());
    }

    /**metoda, która rysuje linie między obiektem a jego celem
     * @param iterator
     * @param gc
     */
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

    /**metoda, która rysuje obiekt na koordynatach na których się znajduje
     * @param iter
     * @param gc
     */
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

    /**metoda, która rysuje pasek stanu zdrowia obiektów
     * @param p
     * @param gc
     */
    private void drawHealth(Putable p, GraphicsContext gc) {
        if (p instanceof Animal) {
            int healthFullLength = 45;
            double healthPercent = ((Animal) p).getHealth()/(double)((Animal) p).getMaxHp();
            healthFullLength *= healthPercent;
            gc.setFill(Color.RED);
            gc.fillRect(p.getX() - 10, p.getY() - 10, healthFullLength, 5 );
        }
    }


    /** setter na inicjalizator
     * @param initializator
     */
    public static void setInitializator(Initializator initializator) {
        simulationWindowController.initializator = initializator;

    }
}
