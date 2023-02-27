/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tn.esprit.vromvrom.Model.User;
import java.io.File;
import java.net.MalformedURLException;

/**
 * FXML Controller class
 *
 * @author MediaCenter Zaghouan
 */
public class DashboardUserController implements Initializable {

    @FXML
    private HBox root;
    @FXML
    private AnchorPane side_ankerpane;
    @FXML
    private Pane inner_pane;
    @FXML
    private JFXButton Home;
    @FXML
    private Pane pane;
    @FXML
    private Pane pane1;
    @FXML
    private Button Sattings;
    @FXML
    private JFXButton Home1;
    @FXML
    private JFXButton Home2;
    @FXML
    private JFXButton Home5;

    @FXML
    private Label bvn;
    @FXML
    private ImageView img;
   
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            
            
            String s = User.connecte.getImage().toString();
            
            File file = new File(s);
            
            
            System.out.println(s);
            Image image = new Image(file.toURI().toURL().toExternalForm());
            img.setImage(image);
            
            
            
            
            bvn.setText("Welcome"+" "+User.connecte.getNom()+" "+User.connecte.getPrenom());
        } catch (MalformedURLException ex) {
            Logger.getLogger(DashboardUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
    
      @FXML
    private void Logout(ActionEvent event) {
         try {
                     Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Login");
                    stage.show();  
                    
        } catch (IOException ex) {    

            Logger.getLogger(DashboardUserController.class.getName()).log(Level.SEVERE, null, ex);

    }
    }
    
    @FXML
    public void LoadScreen(ActionEvent event) throws IOException{
     
        Parent fxml = FXMLLoader.load(getClass(). getResource("Login.fxml"));
        pane1.getChildren().removeAll();
        pane1.getChildren().setAll(fxml);
    }
        @FXML
    public void LoadSettings(ActionEvent event) throws IOException{
     
        Parent fxml = FXMLLoader.load(getClass(). getResource("Profile.fxml"));
        pane1.getChildren().removeAll();
        pane1.getChildren().setAll(fxml);
    }
          @FXML
    public void LoadRole(ActionEvent event) throws IOException{
     
        Parent fxml = FXMLLoader.load(getClass(). getResource("ManageRole.fxml"));
        pane1.getChildren().removeAll();
        pane1.getChildren().setAll(fxml);
    }

  
}
