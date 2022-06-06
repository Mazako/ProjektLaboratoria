package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class App extends Application {

    @FXML
    private MediaView mediaView = new MediaView();

    public void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = FXMLLoader.load(getClass().getResource("/mainMenu.fxml"));
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Menu główne");
        /*
        Media media = new Media(getClass().getResource("/soundtrack.mp3").toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        player.play();
         */


    }
}
