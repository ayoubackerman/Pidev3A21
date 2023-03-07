/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.vromvrom;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.solde;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AffichersController implements Initializable {



    
    @FXML
    private TableView<solde> tab;
    
    @FXML
    private TableColumn<solde,Integer> idaffi;

    private TableColumn<solde,Integer> idpai;
   
        @FXML
    private TableColumn<solde,Integer> idmontant;
        
    @FXML
    private TableColumn<solde,String> idnom;
    
    @FXML
    private TableColumn<solde,String> idpr;
    
    @FXML
    private TableColumn<solde,String> iddate;
    
    
        ObservableList<solde> List;
    @FXML
    private TableColumn<?, ?> idmontantt;

    
         public void table(){
         
        idaffi.setCellValueFactory( new PropertyValueFactory<>("id_user"));
        idpai.setCellValueFactory(new PropertyValueFactory <>("id_paiement"));
        idnom.setCellValueFactory(new PropertyValueFactory <>("idnom"));
        idpr.setCellValueFactory(new PropertyValueFactory <>("idpr"));
   
        iddate.setCellValueFactory(new PropertyValueFactory <>("date"));
                idmontant.setCellValueFactory(new PropertyValueFactory <>("montant"));

       
        tab.setItems(RecupBase()); 



}      public static ObservableList<solde> RecupBase(){
             
    ObservableList<solde> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "select *from solde";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    while (R.next()){
      solde r =new solde();
     r.setId_user((R.getInt(1)));
     r.setId_paiement(R.getInt(2));
      r.setMontant((R.getDouble(3)));
     r.setDate(R.getString(4));
      
    
     
      list.add(r);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
}

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        table();
    }    
    

}