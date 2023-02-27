/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom;

import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.service.ServiceUser;

/**
 * FXML Controller class
 *
 * @author MediaCenter Zaghouan
 */
public class SignupController implements Initializable {

    @FXML
    private Button close;
    @FXML
    private Button goback;
    @FXML
    private TextField nom;
    @FXML
    private TextField Email;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nd;
    @FXML
    private PasswordField conf_pass;
    @FXML
    private PasswordField pass;
    @FXML
    private Button register;
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Label l4;
    @FXML
    private Label l5;
    @FXML
    private Label l6;
    @FXML
    private JFXComboBox<String> comm;
    @FXML
    private Label l11;

    /**
     * Initializes the controller class.
     */
    private Connection cnx;
     public SignupController(){
        cnx = Database.getInstance().getCnx();
    }
    
                 ServiceUser us = new ServiceUser();

        private void EnregistrerVersBase2() {
           java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
             
           try {
            String sql = "INSERT INTO user (id_role ,nom ,prenom ,mail ,Nomd ,mdp) VALUES (?,?,?,?,?,?)";
            
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
        
        
        
        int i = comm.getSelectionModel().getSelectedIndex() + 2;
               System.out.println(i);
            st.setInt(1, i);
            st.setString(2, nom.getText());
            st.setString(3, prenom.getText());
            st.setString(4, Email.getText());
            st.setString(5, nd.getText());
            st.setString(6, us.encryptThisString(pass.getText()));
        
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error");
        }
    } 
        
         @FXML
       private void AddUser(ActionEvent event) throws SQLException{
           
                    if (nd.getText().isEmpty()|| nom.getText().isEmpty() || Email.getText().isEmpty() || prenom.getText().isEmpty() || pass.getText().isEmpty() || comm.getValue().isEmpty())
         {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("Please fill all input");
            Optional<ButtonType> result = alert.showAndWait();}
           
           else if(VerifNomDutil())  {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("Username already existe");
            Optional<ButtonType> result = alert.showAndWait();}
           
           else if(!us.VerifEmail(Email.getText())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("invalid Email format");
            Optional<ButtonType> result = alert.showAndWait();
           }
           else if(pass.getText().length()<8){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("Password is too weak");
            Optional<ButtonType> result = alert.showAndWait();
           
           }
             else if(!pass.getText().equals(conf_pass.getText())){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("confirm your password");
            Optional<ButtonType> result = alert.showAndWait();
             }
             else if(Verifmail()){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("Email already used by another user");
            Optional<ButtonType> result = alert.showAndWait();
             }
            
            
           
           
         else {
           
           EnregistrerVersBase2();
           JOptionPane.showMessageDialog(null,"Account created successfully");

          
           
           nom.clear();
           prenom.clear();
           Email.clear();
           nd.clear();
           pass.clear();
           conf_pass.clear();
           
           
         
           }
       
       
       }
       
       
 
 //Verifier le nom d'utilisateur s'il existe ou pas 
       
         public boolean Verifmail() throws SQLException{
      
            String sql = "select * from user where mail = ?";
            PreparedStatement ps =  cnx.prepareStatement(sql);
            ps.setString(1,Email.getText());
            
            ResultSet rs = ps.executeQuery();
             boolean ok=false;
            if(rs.next()){
         if (rs.getString(5).equals(Email.getText()))
             ok=true;
     }
     return ok;
          }
       
       
  public boolean VerifNomDutil() throws SQLException{
      
            String sql = "select * from user where Nomd=?";
            PreparedStatement p  = cnx.prepareStatement(sql);
            p.setString(1,nd.getText());
            ResultSet rs = p.executeQuery();
     boolean ok=false;
     if (rs.next()) {         
         if (rs.getString(6).equals(nd.getText()))
             ok=true;
     }
     return ok;
          }
    
       @FXML
    private void exit(ActionEvent event){
        
        System.exit(0);
    }
    
       @FXML
    private void Goback(ActionEvent event) throws IOException{
        
Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Login");
                    
                    stage.show();  
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    ObservableList<String> list = FXCollections.observableArrayList("Chauffeur","Client");
    comm.setItems(list);

        
    }    

    @FXML
    private void Register(ActionEvent event) {
    }
    
}
