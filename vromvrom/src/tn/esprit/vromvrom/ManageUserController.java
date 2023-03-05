/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom;

import com.jfoenix.controls.JFXComboBox;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import static tn.esprit.vromvrom.DashboardAdminController.RecupCombo;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.User;
import tn.esprit.vromvrom.service.ServiceRole;
import tn.esprit.vromvrom.service.ServiceUser;

/**
 * FXML Controller class
 *
 * @author MediaCenter Zaghouan
 */
public class ManageUserController implements Initializable {

    @FXML
    private TableView<User> tab_User;
    @FXML
    private TableColumn<User, String> r;
    @FXML
    private TableColumn<User, String> n;
    @FXML
    private TableColumn<User, String> p;
    @FXML
    private TableColumn<User, String> e;
    @FXML
    private TableColumn<User, String> u;
    @FXML
    private TableColumn<User, String> s;
    @FXML
    private TableColumn<User, Integer> user;
  
@FXML
    private TableColumn<User, String> Photo;
    /**
     * Initializes the controller class.
     */
    ServiceUser usr = new ServiceUser();
    
                    ObservableList<User> Chercheuser;

    @FXML
    private TextField recherche;
    public ManageUserController(){
        Connection cnx = Database.getInstance().getCnx();
    }
      private Connection cnx;
    
    @FXML
    private Button Add2;
    @FXML
    private Button Edi;
    @FXML
    private Button dd;
    @FXML
    private TextField uu;
    @FXML
    private Button img;
    @FXML
    private TextField urlx;
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
    private JFXComboBox<String> comm;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        table2();
         user.setVisible(false);
        uu.setVisible(false);
        urlx.setVisible(false);
                comm.setItems(usr.RecupCombo());
                ChercheFichier();

                 tab_User.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 0) {
        onEdit2();
        
    }
});
        
    }    

    
        public void onEdit2() {
               
               java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
     
    if (tab_User.getSelectionModel().getSelectedItem() != null) {
          User f = tab_User.getSelectionModel().getSelectedItem();
          int i = f.getId_user();
        String n = String.valueOf(i);
         
          nom.setText(f.getNom());
          prenom.setText(f.getPrenom());
          Email.setText(f.getMail());
          nd.setText(f.getNomd());   
          uu.setText(n);
    }
}
           public void table2(){
         
//        r.setCellValueFactory( new PropertyValueFactory<>("Role"));
r.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User,String>,ObservableValue<String>>(){
    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                            return new SimpleStringProperty(param.getValue().getId_role().getRole());
    }
            });
        n.setCellValueFactory( new PropertyValueFactory<>("nom"));
        p.setCellValueFactory(new PropertyValueFactory <>("prenom"));
        e.setCellValueFactory( new PropertyValueFactory<>("mail"));
        u.setCellValueFactory(new PropertyValueFactory <>("Nomd"));
        s.setCellValueFactory(new PropertyValueFactory <>("status"));
        user.setCellValueFactory(new PropertyValueFactory <>("id_user"));
       
       
        tab_User.setItems(usr.RecupBase2()); 
        
       }
    @FXML
    private void AddUser(ActionEvent event) throws SQLException {
                if (nd.getText().isEmpty()|| nom.getText().isEmpty() || Email.getText().isEmpty() || prenom.getText().isEmpty() || pass.getText().isEmpty() || comm.getValue().isEmpty())
         {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Vérifier les paramétres");
            
            alert.setContentText("Remplir tous les paramétres");
            Optional<ButtonType> result = alert.showAndWait();}
           
           else if(VerifNomDutil())  {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Changer votre nom d'utilisateur");
            
            alert.setContentText("Nom d'utilisateur déja utilisé ");
            Optional<ButtonType> result = alert.showAndWait();}
           
           else if(!usr.VerifEmail(Email.getText())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Email incorrecte");
            
            alert.setContentText("Veuillez verifier la structure d'email");
            Optional<ButtonType> result = alert.showAndWait();
           }
           else if(urlx.getText().isEmpty()){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ajouter une image");
            
            alert.setContentText("Ajouter une image");
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
                
             else{
             
             
             }
    }

    
     public void ChercheFichier(){
      User f = new User();
        n.setCellValueFactory( new PropertyValueFactory<>("nom"));
        
        p.setCellValueFactory( new PropertyValueFactory<>("prenom"));
                e.setCellValueFactory( new PropertyValueFactory<>("mail"));

    
       
    Chercheuser = usr.RecupBase2();
    tab_User.setItems(usr.RecupBase2());
    FilteredList<User> filtreddata;
     filtreddata = new FilteredList<>(Chercheuser ,b ->true);
    recherche.textProperty().addListener((observable,oldValue,newValue)->{
      filtreddata.setPredicate((u  ->  {
          
          if((newValue ==null) || newValue.isEmpty())
          { return true;}
      
      String lowerCaseFilter = newValue.toLowerCase();
      if (u.getNom().toLowerCase().contains(lowerCaseFilter)){
      return true;
      } else if (u.getPrenom().toLowerCase().contains(lowerCaseFilter))
          {return true;}
         else if (u.getMail().toLowerCase().contains(lowerCaseFilter))
          {return true;}
     
        
      return false;
      })); 
    });
    
    SortedList<User> srt = new SortedList<>(filtreddata);
    srt.comparatorProperty().bind(tab_User.comparatorProperty());
    tab_User.setItems(srt);
    }
    


    @FXML
    private void Modifier2(ActionEvent event) {
    }

    @FXML
    private void dell(ActionEvent event) {
    }

    @FXML
    private void Ajoutefichier(ActionEvent event) {
             FileChooser f = new FileChooser();
        File S = f.showOpenDialog(null);
         if(S!=null){
       
        
         String n = S.getAbsolutePath();
        urlx.setText(S.getAbsolutePath());
        
         }
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
         
    
}
