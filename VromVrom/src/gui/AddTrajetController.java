/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Trajet;
import model.User;
import service.ServiceTrajet;
import database.Database;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AddTrajetController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private Connection cnx;
    @FXML
    private DatePicker datp;

    public AddTrajetController(){
        cnx = Database.getInstance().getCnx();
    }
    
      @FXML
    private ComboBox<String> villeD;

    @FXML
    private ComboBox<String> villeA;

    @FXML
    private TextField prix;

    @FXML
    private TextField nbrP;

    @FXML
    private TextField id_user;

    private TextField DPose;
    
   ServiceTrajet tSU = new ServiceTrajet();
   

    @FXML
    private Button butAjouter;
    
      @FXML
    private RadioButton radioCarte;

    @FXML
    private RadioButton radioEspece;


    private boolean NoDate() {

         LocalDate currentDate = LocalDate.now();
         
         LocalDate myDate = datp.getValue(); 
         int comparisonResult = myDate.compareTo(currentDate);    
        
         boolean test = true;

        if (comparisonResult < 0) {
        // myDate est antérieure à currentDate
        test = true;
        } else if (comparisonResult > 0) {
         // myDate est postérieure à currentDate
         test = false;
        }
        return test;
    }
   
    
    @FXML 
private void Retour(ActionEvent event) throws IOException {
    Node node = (Node) event.getSource();
    node.getScene().getWindow().hide(); 
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("affTrajet.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage affTrajetStage = new Stage();
    affTrajetStage.setScene(scene);
    affTrajetStage.setTitle("Affichage des trajets");
    affTrajetStage.show();
}
//
//    private void Retouree(ActionEvent event) throws IOException {
//    FXMLLoader loader = new FXMLLoader(getClass().getResource("message.fxml"));
//    Parent root = loader.load();
//    Scene scene = new Scene(root);
//    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//    stage.setScene(scene);
//    stage.setTitle("trajet");
//    stage.show();
//}
@FXML
    private void Retouur(ActionEvent event) throws IOException {
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("dashbord.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.setTitle("trajet");
    stage.show();
}  
    
  
    @FXML
    private void AddTrajet() throws SQLException{
    
    String modePaiement = "";
    LocalDate date = datp.getValue();
    int nombreDePlace = Integer.parseInt(nbrP.getText());
//    String dateString = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    if (radioCarte.isSelected()) {
        modePaiement = "Enligne";
    } else if (radioEspece.isSelected()) {
        modePaiement = "espèces";
    }
if (villeD.getValue() == null || villeA.getValue() == null) {
    // Afficher un message d'erreur si l'une des deux villes n'a pas été sélectionnée
    Alert al = new Alert(Alert.AlertType.WARNING);
    al.setTitle("Controle de saisie");
    al.setHeaderText("Erreur de saisie !");
    al.setContentText("Les deux villes doivent être sélectionnées.");
    al.show();
} else if (villeD.getValue().equals(villeA.getValue())) {
    // Afficher un message d'erreur si les deux villes sont identiques
    Alert al = new Alert(Alert.AlertType.WARNING);
    al.setTitle("Controle de saisie");
    al.setHeaderText("Erreur de saisie !");
    al.setContentText("La ville de départ ne peut pas être identique à la ville d'arrivée.");
    al.show();}
else if (
             prix.getText().isEmpty()
            || nbrP.getText().isEmpty()
            || id_user.getText().isEmpty()
//            || datp.getText().isEmpty()
            || (!radioCarte.isSelected() && !radioEspece.isSelected()))
        
    {
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Controle de saisie");
            al.setHeaderText("Erreur de saisie !");
            al.setContentText("Les données sont vides !");
            al.show();
}
 


else if (nombreDePlace <= 0) {
        Alert al = new Alert(Alert.AlertType.WARNING);
        al.setTitle("Controle de saisie");
        al.setHeaderText("Erreur de saisie !");
        al.setContentText("Le nombre de place doit être supérieur à 0.");
        al.show();
    } else if (nombreDePlace > 4) {
        Alert al = new Alert(Alert.AlertType.WARNING);
        al.setTitle("Controle de saisie");
        al.setHeaderText("Erreur de saisie !");
        al.setContentText("Le nombre de place ne peut pas dépasser 10.");
        al.show();
    }
    else if (NoDate() == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Error!");
            alert.setContentText("la date date doit être aprés la date d'aujourd'hui");
            alert.showAndWait();
         }
    else {
        String dateString = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        User u = new User(Integer.parseInt(id_user.getText()));
        float f = Float.parseFloat(prix.getText());
        System.out.println(f);
    Trajet tur = new Trajet(villeD.getValue(), villeA.getValue(), f, Integer.parseInt(nbrP.getText()), u, dateString, modePaiement);
    System.out.println(prix.getText());
        tSU.createOne(tur);
//         table();
         villeD.setValue(null);
         villeA.setValue(null);
           prix.clear();
           nbrP.clear();
           id_user.clear();
         datp.setValue(null);
       radioCarte.setSelected(false);
            radioEspece.setSelected(false);

//        table();

}
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           List<String> gouvernorats = Arrays.asList(
            "Ariana", "Béja", "Ben Arous", "Bizerte", "Gabès",
            "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kébili",
            "La Manouba", "Le Kef", "Mahdia", "Médenine", "Monastir",
            "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse",
            "Tataouine", "Tozeur", "Tunis", "Zaghouan"
        );
            List<String> gouvernoratsD = Arrays.asList(
            "Ariana", "Béja", "Ben Arous", "Bizerte", "Gabès",
            "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kébili",
            "La Manouba", "Le Kef", "Mahdia", "Médenine", "Monastir",
            "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse",
            "Tataouine", "Tozeur", "Tunis", "Zaghouan"
        );
        villeA.setItems(FXCollections.observableArrayList(gouvernorats));
          villeD.setItems(FXCollections.observableArrayList(gouvernoratsD));
    }

        
}
