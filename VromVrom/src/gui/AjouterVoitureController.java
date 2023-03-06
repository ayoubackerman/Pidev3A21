/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.User;
import model.Voiture;
import service.ServiceUser;
import service.ServiceVoiture;

/**
 * FXML Controller class
 *
 * @author mehdi
 */
public class AjouterVoitureController implements Initializable {

    @FXML
    private TextField idClient;
    @FXML
    private TextField modele;
    @FXML
    private TextField marque;
    @FXML
    private TextField matricule;
    @FXML
    private ImageView img;
    @FXML
    private TextField imgUrl;
    @FXML
    private Button butAjout;
    @FXML
    private Button butImg;
    @FXML
    private Button butAfficher;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        }    
    

    @FXML
    private void ajouter(ActionEvent event) {
        if (idClient.getText().isEmpty() 
                || modele.getText().isEmpty()
                || marque.getText().isEmpty()
                || matricule.getText().isEmpty()
                || !matricule.getText().matches("\\d+")) {
            
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Controle de saisie");
            al.setHeaderText("Erreur de saisie !");
            al.setContentText("Veuillez verifier vos données !");
            al.show();
    }
        else{
            User user = new User(Integer.parseInt(idClient.getText()));
            Voiture v = new Voiture(user
                                    , modele.getText()
                                    , marque.getText()
                                    , Integer.parseInt(matricule.getText())
                                    , imgUrl.getText());
            ServiceVoiture sv = new ServiceVoiture();
            try {
                sv.ajouter(v);
                idClient.clear();
                modele.clear();
                marque.clear();
                matricule.clear();
                imgUrl.clear();
                img.setImage(null);
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setTitle("Succes");
                al.setHeaderText("Ajout réussi");
                al.show();
            } catch(SQLException ex){
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Erreur");
                al.setHeaderText("Erreur Interne");
                al.setContentText(ex.getMessage());
                al.show();
            }
        }
    }

    @FXML
    private void afficherImg(ActionEvent event) {
        FileChooser f = new FileChooser();
        File S = f.showOpenDialog(null);
         if(S!=null){
       
        
        String n = S.getAbsolutePath();
        System.out.println(S.getAbsolutePath());
        Image image;
            try {
                image = new Image(S.toURI().toURL().toExternalForm());
                img.setImage(image);
                imgUrl.setText(S.getAbsolutePath());
                System.out.println(S.getAbsolutePath());
            } catch (MalformedURLException ex) {
                Logger.getLogger(AjouterVoitureController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
         }
        
    }

    @FXML
    private void afficher(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("AfficherVoitures.fxml"));
        Scene mainScene = new Scene(main);
        Stage stage = (Stage) butAfficher.getScene().getWindow();
        stage.setScene(mainScene);
        stage.show();
    }


            
            
        
        
        
    }




    

