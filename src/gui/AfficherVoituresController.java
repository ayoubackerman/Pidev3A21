/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.Voiture;
import tn.esprit.vromvrom.service.ServiceVoiture;

/**
 * FXML Controller class
 *
 * @author mehdi
 */
public class AfficherVoituresController implements Initializable {

    @FXML
    private TableView<Voiture> table;
    @FXML
    private TableColumn<Integer, Voiture> idVoiture;
    @FXML
    private TableColumn<Integer, Voiture> idClient;
    @FXML
    private TableColumn<String, Voiture> modele;
    @FXML
    private TableColumn<String, Voiture> marque;
    @FXML
    private TableColumn<Integer, Voiture> matricule;

    
    public static ObservableList<Voiture> RecupBase(){
             
    ObservableList<Voiture> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "select *from voiture";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    
    while (R.next()){
        
        ServiceVoiture aa = new ServiceVoiture();
        
        Voiture r = new Voiture();
        
     
        
   
        r.setId(R.getInt(1));
    r.setId_user(R.getInt(2));
     r.setModele(R.getString(3));
          r.setMarque(R.getString(4));
               r.setMatricule(R.getInt(5));


   
     
   
    
     
   list.add(r);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    public void table(){
    
    
      idVoiture.setCellValueFactory(new PropertyValueFactory<>("id"));
            idClient.setCellValueFactory(new PropertyValueFactory<>("id_user"));
            modele.setCellValueFactory(new PropertyValueFactory<>("modele"));
            marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
            matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
     
            table.setItems(RecupBase());
     
    
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        
        table();
        
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Voiture voiture = table.getSelectionModel().getSelectedItem();
        ServiceVoiture sv = new ServiceVoiture();
        try {
            sv.delete(voiture);
            table();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherVoituresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
