/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.Role;
import tn.esprit.vromvrom.Model.User;
import tn.esprit.vromvrom.service.ServiceUser;


/**
 *
 * @author MediaCenter Zaghouan
 */
public class Vromvrom  extends Application{
    
     @Override
     public void start(Stage primaryStage) {
      try {
            Parent root = FXMLLoader.load(getClass(). getResource("../gui.AfficherVoituress.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.show();           
        } catch (IOException ex) {
            Logger.getLogger(Vromvrom.class.getName()).log(Level.SEVERE, null, ex);
        }    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**Database data = Database.getInstance();
        Role role = new Role();
        role.setRole("Admin");
        role.setId(1);
        User user = new User(role, "aezr", "aezr", "aezr", "azer", "azer","azer");
        
        ServiceUser su = new ServiceUser();
        try {
            su.ajouter(user);
        } catch (SQLException ex) {
            Logger.getLogger(Vromvrom.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        launch(args);
    }

  
    
}
