/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import test.*;
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
import database.Database;
import model.Role;
import model.User;
import service.ServiceUser;


/**
 *
 * @author MediaCenter Zaghouan
 */
public class Vromvrom  extends Application{
    
     @Override
     public void start(Stage primaryStage) {
      try {
            Parent root = FXMLLoader.load(getClass().getResource("../gui/AfficherVoitures.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("ajout");
            primaryStage.setScene(scene);
            primaryStage.show();           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
     }

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
