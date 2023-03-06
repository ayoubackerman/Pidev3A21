/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static gui.AffTrajetController.RecupBase;
import model.Reservation;
import model.Trajet;
import model.User;
import service.ServiceReservation;
import service.ServiceTrajet;
import database.Database;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ReservationController implements Initializable {

        @FXML
    private TableView<Trajet> tab;

    @FXML
    private TableColumn<Trajet, String> VD;

    @FXML
    private TableColumn<Trajet, String> VA;

    @FXML
    private TableColumn<Trajet, Float> prix;

    @FXML
    private TableColumn<Trajet, Integer> place;

    @FXML
    private TableColumn<Trajet, Integer> pose;

    @FXML
    private TableColumn<Trajet, String> paiement;
    @FXML
    private TableColumn<Trajet, Integer> idTRJ;
      @FXML
    private TextField idTrajet;

    @FXML
    private TextField idUser;


    @FXML
    private Button buttReserver;
    @FXML
    private TableColumn<Trajet, Integer> pose1;
ObservableList<Trajet> Chercheuser1 ;

 ServiceReservation RSU = new ServiceReservation();
    ServiceTrajet tSU = new ServiceTrajet();
    @FXML
    private TextField RechercheC;
    
    
    
     public void table() throws SQLException{
         
        VD.setCellValueFactory( new PropertyValueFactory<>("Ville_depart"));
        VA.setCellValueFactory(new PropertyValueFactory <>("Ville_darrive"));
          prix.setCellValueFactory(new PropertyValueFactory <>("prix"));
          place.setCellValueFactory(new PropertyValueFactory <>("Nbr_place"));
          //idTRJ1.setCellValueFactory(new PropertyValueFactory <>("user"));
          pose1.setCellValueFactory((TableColumn.CellDataFeatures<Trajet, Integer> cellData) -> {
            User u = cellData.getValue().getUser();
            return new SimpleObjectProperty<>(u.getId_user());
            });
            idTRJ.setCellValueFactory(new PropertyValueFactory <>("id_trajet"));
        pose.setCellValueFactory(new PropertyValueFactory <>("date"));
        paiement.setCellValueFactory(new PropertyValueFactory <>("Mode_paiement"));

     //tab.setItems(RecupBase());
        tab.setItems(tSU.selectAll());


    }


     
    @FXML
       public void onEdit() {

        java.sql.Connection cnx;
        cnx = Database.getInstance().getCnx();

        if (tab.getSelectionModel().getSelectedItem() != null) {
            Trajet trajet = tab.getSelectionModel().getSelectedItem();
int i = trajet.getId_trajet();
        String n = String.valueOf(i);
        int ii = trajet.getUser().getId_user();
        String nn = String.valueOf(ii);
      
//        float i2 = trajet.getPrix();
//        String n2 = String.valueOf(i2);
        
 
        idTrajet.setText(n);
            idUser.setText(nn);

}}
       
       
       public void ChercheFichier() throws SQLException{
        paiement.setCellValueFactory( new PropertyValueFactory<>("Mode_paiement"));
        
        VD.setCellValueFactory( new PropertyValueFactory<>("ville_depart"));
                VA.setCellValueFactory( new PropertyValueFactory<>("ville_darrive"));


    
       
    Chercheuser1 = tSU.selectAll();
    tab.setItems(tSU.selectAll());
    FilteredList<Trajet> filtreddata;
     filtreddata = new FilteredList<>(Chercheuser1 ,b ->true);
    RechercheC.textProperty().addListener((observable,oldValue,newValue)->{
      filtreddata.setPredicate((u  ->  {
          
          if((newValue ==null) || newValue.isEmpty())
          { return true;}
      
      String lowerCaseFilter = newValue.toLowerCase();
      if (u.getMode_paiement().toLowerCase().contains(lowerCaseFilter)){
      return true;
      }else if (u.getVille_depart().toLowerCase().contains(lowerCaseFilter)){
      return true;
      }else if (u.getVille_darrive().toLowerCase().contains(lowerCaseFilter)){
      return true;
      } 
     
        
      return false;
      })); 
    });
    
    SortedList<Trajet> srt = new SortedList<>(filtreddata);
    srt.comparatorProperty().bind(tab.comparatorProperty());
    tab.setItems(srt);
    System.out.println(srt);
    }
  
       @FXML
     private void AddReservation(ActionEvent event) throws SQLException {
  java.sql.Connection cnx;
   cnx = Database.getInstance().getCnx();
   

        User usr = new User(Integer.parseInt(idTrajet.getText()));
        Trajet t = new Trajet(Integer.parseInt(idUser.getText()));

        Reservation ur = new Reservation(t,usr);

        RSU.createOne(ur);
        table();
        JOptionPane.showMessageDialog(null,"Bienvenue chez nous ! Nous attendons votre e-mail.");
       

        idTrajet.clear();
        idUser.clear();
        
      
    }
    
   

            

    private void Retour(ActionEvent event)throws IOException {
    
  
    Node node = (Node) event.getSource();
    node.getScene().getWindow().hide(); 
   
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("affReservation.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage affTrajetStage = new Stage();
    affTrajetStage.setScene(scene);
    affTrajetStage.setTitle("Affichage les Reservations");
    affTrajetStage.show();
}
//    private void Retourpersonne(ActionEvent event) throws IOException {
//    FXMLLoader loader = new FXMLLoader(getClass().getResource("mesreservation.fxml"));
//    Parent root = loader.load();
//    Scene scene = new Scene(root);
//    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//    stage.setScene(scene);
//    stage.setTitle("trajet");
//    stage.show();
//}
     @FXML
    private void retouur(ActionEvent event) throws IOException {
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("dashbord.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.setTitle("trajet");
    stage.show();
}  
   
         @Override
    public void initialize(URL url, ResourceBundle rb) {
            try {
                table();
                 ChercheFichier();
            } catch (SQLException ex) {
                Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
                    tab.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 0) {
        onEdit();
        
    }
    

 
});
                        pose1.setVisible(false);
     idTRJ.setVisible(false);
         idTrajet.setVisible(false);
     idUser.setVisible(false);
    }    

   

    

    

   


   

        
        
        
}

