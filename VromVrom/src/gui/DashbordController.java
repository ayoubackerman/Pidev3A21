/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DashbordController implements Initializable {

    @FXML
    private Button RetourH;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void Retour(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTrajet.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.setTitle("trajet");
    stage.show();
}
    @FXML
    private void RetourH(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Reservation.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.setTitle("trajet");
    stage.show();
}
    @FXML
     private void RetourA(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("affTrajet.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.setTitle("trajet");
    stage.show();
}
    
    @FXML
    private void Retouree(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("message.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.setTitle("trajet");
    stage.show();
}  
    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    
}
