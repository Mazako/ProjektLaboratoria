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

    public static int RECT_WIDTH = 5;
    public static int RECT_HEIGHT = 5;


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
                    ((Movable) p).move(random.nextInt(10)-5, random.nextInt(10)-5); ;
                }
                draw(p,gc);
            }
    }

    public void test2(Lion lion, GraphicsContext gc) {
        gc.clearRect(0,0,Safari.MAX_WIDTH+100,Safari.MAX_HEIGHT+100);
        System.out.println("a");
            gc.fillRect(lion.getX(), lion.getY(), 4,4);
            ((Movable) lion).move(10,4);
        System.out.println(lion.getY());
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


        gc.fillRect(p.getX(),p.getY(),RECT_WIDTH,RECT_HEIGHT);
    }


    public static void setInitializator(Initializator initializator) {
        simulationWindowController.initializator = initializator;

    }
}
