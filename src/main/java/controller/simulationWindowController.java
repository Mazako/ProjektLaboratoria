package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.board.Initializator;
import model.board.Safari;
import model.livings.*;

import java.util.Iterator;
import java.util.Random;

public class simulationWindowController {

    public static int RADIUS = 25;


    @FXML
    private AnchorPane boardAnchorPane;

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
        GraphicsContext gc = boardCanvas.getGraphicsContext2D();
        Safari safari = new Safari(initializator);
        safari.setTargets();
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            testloop(safari, gc);
            stepLabel.setText(Integer.toString(Integer.parseInt(stepLabel.getText()) +  1));
        }));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
        stopButton.addEventFilter(ActionEvent.ACTION, event -> animation.stop());

    }


    private void testloop(Safari safari, GraphicsContext gc) {
            gc.clearRect(0,0,Safari.MAX_WIDTH + 100 ,Safari.MAX_HEIGHT + 100);
            Iterator<Putable> iterator = safari.getIterator();
            while(iterator.hasNext()) {
                Putable p = iterator.next();
                if (p instanceof Movable) {
                    if (((Movable) p).hasTarget())
                        ((Movable) p).moveToTarget();
                    else {
                        ((Movable) p).randomMove();
                        ((Movable) p).findTarget(safari.getObjects());
                    }
                }
                if (p instanceof Plant && ((Plant) p).isEaten()) {
                    iterator.remove();
                }
                draw(p,gc);
            }
            drawDistances(safari,gc);
    }

    private void drawDistances(Safari safari, GraphicsContext gc) {
        Iterator<Putable> iterator = safari.getIterator();
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


    private void draw(Putable p, GraphicsContext gc) {

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


        gc.fillOval(p.getX(),p.getY(),RADIUS,RADIUS);

    }


    public static void setInitializator(Initializator initializator) {
        simulationWindowController.initializator = initializator;

    }
}
