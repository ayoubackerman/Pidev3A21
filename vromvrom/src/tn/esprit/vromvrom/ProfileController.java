/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.User;
import tn.esprit.vromvrom.service.ServiceUser;

/**
 * FXML Controller class
 *
 * @author MediaCenter Zaghouan
 */
public class ProfileController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField Email;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nd;
    @FXML
    private PasswordField oldpass;
    @FXML
    private PasswordField newpass;
    @FXML
    private PasswordField confpass;

  private Connection cnx;
    @FXML
    private Button up;
  
     public ProfileController(){
        cnx = Database.getInstance().getCnx();
    }
    

    /**
     * Initializes the controller class.
     */
     
       @FXML
       private void UpdatePassword(ActionEvent event) throws SQLException{
           
                    if (oldpass.getText().isEmpty()|| newpass.getText().isEmpty() || confpass.getText().isEmpty())
         {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("Please fill all input");
            Optional<ButtonType> result = alert.showAndWait();}
           
            else if(!Verifpass()){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("Incorrect password");
            Optional<ButtonType> result = alert.showAndWait();
             }
           
          
           else if(newpass.getText().length()<8){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("Password is too weak");
            Optional<ButtonType> result = alert.showAndWait();
           
           }
             else if(!newpass.getText().equals(confpass.getText())){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("confirm your password");
            Optional<ButtonType> result = alert.showAndWait();
             }
          

         else {
           
           Modifier();

           oldpass.clear();
           newpass.clear();
           confpass.clear();

           
           
         
           }
       
       
       }
     
     
     
     
     
     ServiceUser us = new ServiceUser();
     
          void Modifier() {
        String n = us.encryptThisString(newpass.getText());
     int i = User.connecte.getId_user();
        String sql ="update user set mdp='"+n+"' where id_user='"+i+"'";
        try {
         PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
         st.executeUpdate();
          JOptionPane.showMessageDialog(null,"Password updates successfully");
    }catch(SQLException ex){
        ex.getMessage();
    }
        

    }
     
     
      public boolean Verifpass() throws SQLException{
      
            String sql = "select * from user where mdp = ?";
            PreparedStatement ps =  cnx.prepareStatement(sql);
            ps.setString(1,us.encryptThisString(oldpass.getText()));
            
            ResultSet rs = ps.executeQuery();
             boolean ok=false;
            if(rs.next()){
         if (rs.getString(7).equals(us.encryptThisString(oldpass.getText())))
             ok=true;
     }
     return ok;
          }
    
    
    
    
      
      
      
      
      
      
      
      
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    
        // TODO
        
        nom.setText(User.connecte.getNom());
        prenom.setText(User.connecte.getPrenom());
        Email.setText(User.connecte.getMail());
        nd.setText(User.connecte.getNomd());

    }    

}
