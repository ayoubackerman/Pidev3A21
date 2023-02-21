/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom;

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


/**
 *
 * @author MediaCenter Zaghouan
 */
public class Vromvrom extends Application {
    
//    @Override
    public void start(Stage primaryStage) {
      try {
            Parent root = FXMLLoader.load(getClass(). getResource("DashboardAdmin.fxml"));
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
    public static void main(String[] args) throws SQLException {
        launch(args);

//ServiceUser s = new ServiceUser();
//ServiceRole r = new ServiceRole();
//
//
//User u = new User(r.SelectRole(1),"sdd","dds","dds","dds","dds");
//
////s.ajouter(u);
//r.readAll();
//  
//  
    }


}
