/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.Role;
import tn.esprit.vromvrom.service.ServiceRole;

/**
 * FXML Controller class
 *
 * @author MediaCenter Zaghouan
 */
public class ManageRoleController implements Initializable {

    @FXML
    private TableView<Role> tab;
    @FXML
    private TableColumn<Role, Integer> columnid;
    @FXML
    private TableColumn<Role, String> columnRole;
    @FXML
    private TextField Role_txt;
    @FXML
    private Button Add;
    @FXML
    private TextField idx;
    @FXML
    private Button del;
    @FXML
    private Button Edit;

    /**
     * Initializes the controller class.
     */
    
      public void table(){
         
        columnid.setCellValueFactory( new PropertyValueFactory<>("id_role"));
        columnRole.setCellValueFactory(new PropertyValueFactory <>("role"));
        ServiceRole sr = new ServiceRole();

        tab.setItems(sr.RecupBase()); 



}
         
         
         
           public void onEdit() {
               
               java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
     
    if (tab.getSelectionModel().getSelectedItem() != null) {
          Role f = tab.getSelectionModel().getSelectedItem();
         int i = f.getId_role();
        String n = String.valueOf(i);
      
          idx.setText(n);
          Role_txt.setText(f.getRole());
          
          
          
                  
       
        
      
            
    }
}
           
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        table();
        idx.setVisible(false);
                columnid.setVisible(false);

             tab.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 0) {
        onEdit();
        
    }
});
    }    

    @FXML
    private void AddRole(ActionEvent event) {
        
            
           if(Role_txt.getText().isEmpty()){
                   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Vérifier les paramétres");
            
            alert.setContentText("Remplir tous les paramétres");
            Optional<ButtonType> result = alert.showAndWait();
           }
           else{
//           EnregistrerVersBase();
             ServiceRole sr = new ServiceRole();
             Role r = new Role();
             r.setRole(Role_txt.getText());
               try {
                   sr.ajouter(r);
               } catch (SQLException ex) {
                   Logger.getLogger(DashboardAdminController.class.getName()).log(Level.SEVERE, null, ex);
               }

           table();
           
           Role_txt.clear();
       
           }
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {
          ServiceRole sr = new ServiceRole();
        
        String idf=idx.getText();
        int i=Integer.valueOf(idf);
        Role r = new Role();
        r = sr.SelectRole(i);
         
              System.out.println(r);
              sr.delete(r);
        
                         table();

//        SuppRole(i);
     
       
        
                JOptionPane.showMessageDialog(null,"Le role a été supprimer avec succés");
    
    }

    @FXML
    private void Modifier(ActionEvent event) {
    }
    
}
