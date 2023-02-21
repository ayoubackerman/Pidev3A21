/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tn.esprit.vromvrom.Model.Reclamation;
import tn.esprit.vromvrom.Model.Role;
import tn.esprit.vromvrom.Model.User;
import tn.esprit.vromvrom.service.ServiceReclamation;
import tn.esprit.vromvrom.service.ServiceReponse;
import tn.esprit.vromvrom.service.ServiceRole;
import tn.esprit.vromvrom.service.ServiceUser;

/**
 *
 * @author MediaCenter Zaghouan
 */
public class tryy extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ServiceRole r = new ServiceRole();
        ServiceUser u = new ServiceUser();
          ServiceReclamation R = new ServiceReclamation();
ServiceReponse rep = new ServiceReponse();
        
        Role r1 = new Role(1,"dd");
        User u1=new User(r.SelectRole(1),"yoyo","ss","ss","ss","ss");
//        Reclamation h = new Reclamation(13,"s","s","s");
System.out.println(rep.SelectReponse(20));
//            R.ajouter(h);
//            System.out.println(u.readAll()); 
//
//           System.out.println(u.login("ss", "s", "s"));
        
        launch(args);
    }
    
}
