/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import static java.sql.JDBCType.NULL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;
import service.ServiceUser;

/**
 * FXML Controller class
 *
 * @author MediaCenter Zaghouan
 */
public class LoginController implements Initializable {

    @FXML
    private TextField user;
    @FXML
    private PasswordField pass;
    @FXML
    private Button Login;
    @FXML
    private Hyperlink register;
    
    private ResourceBundle resources;
    
    
    
    

 
    
    @FXML
    public void Connexion(ActionEvent event) throws SQLException, IOException {

        ServiceUser met = new ServiceUser();
        User u = met.login(user.getText(), pass.getText(),user.getText());
        if (u.getId_user()== 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Connexion echoué");
            alert.setHeaderText("Connexion impossible!!");
            alert.setContentText("Vérifier vos paramétres");
            Optional<ButtonType> result = alert.showAndWait();

        }
        else if(u.getId_role().getRole().equals("Admin")) {;

           Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("DashboardUser.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Espace Utilisateur");
                    stage.show(); 
        } else if(u.getId_role().getRole().equals("admin")) {

            Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("AcceuilAdmin.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Espace Admin");
                    stage.show(); 
        } 
        else if(u.getId_role().getRole().equals("Chauffeur")) {

            Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("dashbord.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Espace Admin");
                    stage.show(); 
        } 

    }
    
    @FXML
    private void Register(ActionEvent event) throws IOException{
        
Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Signup.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Login");
                    
                    stage.show();  
    }

     
    
    @FXML
    private void exit(ActionEvent event){
        
        System.exit(0);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

         
        // TODO
    }    

    @FXML
    private void Register(MouseEvent event) {
    }
    
}
