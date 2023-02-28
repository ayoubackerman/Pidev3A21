package tn.esprit.vromvrom.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Try extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ChatUrgence.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("VoitureUrgence.fxml"));
        primaryStage.setTitle("URGENCE APP");
        primaryStage.setScene(new Scene(root, 1300, 850));
        primaryStage.show();
    }




}
