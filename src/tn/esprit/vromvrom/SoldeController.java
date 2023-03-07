/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.vromvrom;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.solde;
import tn.esprit.vromvrom.Model.verifcode;
import tn.esprit.vromvrom.service.ServiceVerifcode;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SoldeController implements Initializable {

    /**
     * Initializes the controller class.
     */
        Connection cnx;
    @FXML
    private ComboBox<Integer> comm;
    @FXML
    private TextField codepromo;
    @FXML
    private Label msgverif;
    @FXML
    private Label msgmontant;
     public SoldeController() {
         cnx = Database.getInstance().getCnx();
    }
    @FXML
    private Button zt;
      @FXML
    private TextField montant;
      @FXML
    private Button ajouter;
    ServiceVerifcode sv = new ServiceVerifcode();
    
    private void back  (ActionEvent event) throws IOException { 
                    
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("solde.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("aff");
                    
                    stage.show();
          
    }
    @FXML
    private void Go(ActionEvent event) throws IOException { 
             
                    
                        
                    
                 Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("afficherSolde.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("aff");
                    
                    stage.show();
          
    }
    
   private void EnregistrerVersBase() throws SQLException {
       
               
       float m =Float.parseFloat(montant.getText());

                   
       verifcode ver =  sv.VerifyTheCode(codepromo.getText());
        if(ver == null)
        {
            msgverif.setText("Code invalide");
        }
        else
        {
            if(ver.getEtat().equals("Disponible"))
            {
                m=m-(m/100*ver.getPourcentage());
                ver.setEtat("Non Disponible");
                sv.update(ver);

            }
        }

           try {
            String st = "INSERT INTO solde (id_user,montant) VALUES (?,?)";
            PreparedStatement p = cnx.prepareStatement(st);
            int i = comm.getValue();
            p.setInt(1, i);
            p.setFloat(2, m);
            
        
            p.executeUpdate();
            System.out.println("ajout avec succee");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error");
        }

    }
   
   

   
   
   public static ObservableList<Integer> RecupCombo(){
             
             
    ObservableList<Integer> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "SELECT id_user FROM user";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    while (R.next()){
      
     
   int r = R.getInt(1);
        System.out.println(r);
    
     
      list.add(r);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }
   
   
   
   
      @FXML
   public void AddSold(ActionEvent event) throws SQLException{
       
       if(montant.getText().isEmpty()){
                   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Vérifier les paramétres");
            
            alert.setContentText("Remplir tous les paramétres");
            Optional<ButtonType> result = alert.showAndWait();
           }
       else {
        EnregistrerVersBase();
        montant.clear();
        codepromo.clear();
       }
   }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comm.setItems(RecupCombo());   
    }    

    @FXML
    private void verifC(ActionEvent event) throws SQLException {
        verifcode ver =  sv.VerifyTheCode(codepromo.getText());
        if(ver == null)
        {
            msgverif.setText("Code invalide");
        }
        else
        {
            if(ver.getEtat().equals("Disponible"))
            {
                              
                msgverif.setText("Code de "+ver.getPourcentage()+"% est Disponible");

                if(!montant.getText().isEmpty())
                {                    
                float m =Float.parseFloat(montant.getText());
                m=m-(m/100*ver.getPourcentage());
                msgmontant.setText("Nouveau montant : "+m);

                }
            }
            else
            {
                msgverif.setText("Code deja utiliser");
            }
        }
    }
    
}
