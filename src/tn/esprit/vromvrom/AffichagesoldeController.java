/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.vromvrom;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.User;
import tn.esprit.vromvrom.Model.solde;
import tn.esprit.vromvrom.service.ServiceSolde;
import tn.esprit.vromvrom.service.ServiceUser;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AffichagesoldeController implements Initializable {



   
    @FXML
    private TableView<solde> tab;

    @FXML
    private TableColumn<solde, Integer> idaffi;

    @FXML
    private TableColumn<solde, Integer> idp;

    @FXML
    private TableColumn<solde, String> idmontantt;

    @FXML
    private TableColumn<solde, String> iddate;
    @FXML
    private TableColumn<solde, String> nom;

    @FXML
    private TableColumn<solde, String> prenom;
    
    
       // ObservableList<solde> List;
    @FXML
    private TextField recharcher;
    @FXML
    private TextField supprimer;
    @FXML
    private Button supri;
    @FXML
    private TextField nomm;
    @FXML
    private TextField prenomm;
    @FXML
    private TextField montonm;
 
 private  int id;
    @FXML
    private Button mod;
    @FXML
    private Button rech;
    
         public void table(){
         
        idaffi.setCellValueFactory( new PropertyValueFactory<>("id_user"));
        idp.setCellValueFactory(new PropertyValueFactory <>("id_paiement"));

        idmontantt.setCellValueFactory(new PropertyValueFactory <>("montant"));

   
        iddate.setCellValueFactory(new PropertyValueFactory <>("date"));
                nom.setCellValueFactory(new PropertyValueFactory <>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory <>("Prenom"));

       
        tab.setItems(RecupBase()); 



         }   
         
         public static ObservableList<solde> RecupBase(){
             
    ObservableList<solde> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "select *from solde";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    while (R.next()){
        ServiceUser us = new ServiceUser();
        User u = us.SelectUser(R.getInt(1));
        System.out.println(u);
      solde r =new solde();
     r.setId_user((R.getInt(1)));
     r.setId_paiement(R.getInt(2));
      r.setMontant((R.getDouble(3)));
     r.setDate(R.getString(4));
     r.setNom(u.getNom());
     r.setPrenom(u.getPrenom());
     
      
    
     
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

    @FXML
    private void supprimer(ActionEvent event) {
        ServiceSolde ss = new ServiceSolde();
        ss.supprimer(Integer.parseInt(supprimer.getText()));
 table();    }

    @FXML
    private void select(MouseEvent event) {
        int num;
        solde c = tab.getSelectionModel().getSelectedItem();
        num = tab.getSelectionModel().getSelectedIndex();
         if(-1 > (num-1)){
             return;
         } else {
             this.id=c.getId_paiement();
           //  System.out.println("frrrrrr"+ this.id); 
             nomm.setText(String.valueOf(c.getNom()));
             prenomm.setText(c.getPrenom());
             montonm.setText(String.valueOf(c.getMontant()));
         
             
             
         }
    }

    @FXML
    private void modifier(ActionEvent event) {
        ServiceSolde ss = new ServiceSolde();
       
        ss.modifier(Double.parseDouble(montonm.getText()),this.id);
        table();
    }

    /*private void find(InputMethodEvent event) {
        ServiceSolde ss = new ServiceSolde();
        
         idaffi.setCellValueFactory( new PropertyValueFactory<>("id_user"));
        idp.setCellValueFactory(new PropertyValueFactory <>("id_paiement"));

        idmontantt.setCellValueFactory(new PropertyValueFactory <>("montant"));

   
        iddate.setCellValueFactory(new PropertyValueFactory <>("date"));
                nom.setCellValueFactory(new PropertyValueFactory <>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory <>("Prenom"));

       
        tab.setItems((ObservableList<solde>) ss.find1(recharcher.getText())); 
    }*/

    @FXML
    private void chercher(ActionEvent event) {
        
        ServiceSolde ss = new ServiceSolde();
        
         idaffi.setCellValueFactory( new PropertyValueFactory<>("id_user"));
        idp.setCellValueFactory(new PropertyValueFactory <>("id_paiement"));

        idmontantt.setCellValueFactory(new PropertyValueFactory <>("montant"));

   
        iddate.setCellValueFactory(new PropertyValueFactory <>("date"));
                nom.setCellValueFactory(new PropertyValueFactory <>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory <>("Prenom"));

       
        tab.setItems(ss.find1(Integer.parseInt(recharcher.getText()))); 
    }

   

    @FXML
    private void back(ActionEvent event) throws IOException {
         Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("solde.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("aff");
                    
                    stage.show();
    }


    
}

