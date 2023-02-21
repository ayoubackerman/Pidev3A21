/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tn.esprit.vromvrom.Model.Voiture;
import tn.esprit.vromvrom.service.ServiceVoiture;

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
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        if (idClient.getText().isEmpty() 
                || modele.getText().isEmpty()
                || marque.getText().isEmpty()
                || matricule.getText().isEmpty()) {
            
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Controle de saisie");
            al.setHeaderText("Erreur de saisie !");
            al.setContentText("Les données sont vides !");
            al.show();
    }
        else{
            Voiture v = new Voiture(Integer.parseInt(idClient.getText())
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
        try{
            String url = imgUrl.getText();
            Image image = new Image(url);
            img.setImage(image);
        }catch (Exception ex){
            System.out.println("Veuillez insérer un url valide");
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
