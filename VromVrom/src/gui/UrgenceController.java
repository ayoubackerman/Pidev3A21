/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import database.Database;
import model.Role;
import model.urgence;

/**
 * FXML Controller class
 *
 * @author MediaCenter Zaghouan
 */
public class UrgenceController implements Initializable {
    
        @FXML
    private TableView<urgence> tab;

    @FXML
    private TableColumn<urgence, Integer> trajet;

    @FXML
    private TableColumn<urgence, Integer> voiture;

    @FXML
    private TableColumn<urgence, String> localisation;

    @FXML
    private TableColumn<urgence, String> desc;

    @FXML
    private TableColumn<urgence, String> status;

    @FXML
    private TableColumn<urgence, String> temps;
    
    @FXML
    private TextField loc;

  

    @FXML
    private TextField stat;


    @FXML
    private TextField id_trajet;

    @FXML
    private TextField id_voiture;

    @FXML
    private TextField description;
    
     @FXML
    private Button Add;
    
     
     
     
    private void EnregistrerVersBase2() {
        
        
           java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
             
        
           try {
            String sql = "INSERT INTO urgence (id_trajet, id_voiture, localisation, description, statuts) VALUES (?,?,?,?,?)";
            
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
        
        
        
            st.setString(1, id_trajet.getText());
            st.setString(2, id_voiture.getText());
            st.setString(3, loc.getText());
            st.setString(4, description.getText());
            st.setString(5, stat.getText());

         
        
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error");
        }
    } 
       
        @FXML
       private void AddUser(ActionEvent event){
           
           EnregistrerVersBase2();
           table();
           
           id_trajet.clear();
           id_voiture.clear();
           description.clear();
           loc.clear();
           stat.clear();
           
           
         
       
       
       
       }
    
          public void table(){
         
        trajet.setCellValueFactory( new PropertyValueFactory<>("id_trajet"));
        voiture.setCellValueFactory(new PropertyValueFactory <>("id_voiture"));
        localisation.setCellValueFactory(new PropertyValueFactory <>("localisation"));
        desc.setCellValueFactory(new PropertyValueFactory <>("description"));
        status.setCellValueFactory(new PropertyValueFactory <>("status"));
        temps.setCellValueFactory(new PropertyValueFactory <>("temps"));

       
        tab.setItems(RecupBase()); 



}
               public static ObservableList<urgence> RecupBase(){
             
    ObservableList<urgence> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "select *from urgence";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    while (R.next()){
      urgence r =new urgence();
      r.setId_trajet(R.getInt(2));
      r.setId_voiture(R.getInt(3));
      r.setLocalisation(R.getString(4));
            r.setDescription(R.getString(5));
      r.setStatus(R.getString(6));
            r.setTemps(R.getString(7));


      
    
    
     
      list.add(r);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }
        


    /**
     * Initializes the controller class.
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        table();
        // TODO
    }    
    
}
