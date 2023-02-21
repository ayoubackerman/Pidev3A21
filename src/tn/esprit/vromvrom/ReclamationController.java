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
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.Reclamation;
import tn.esprit.vromvrom.Model.User;
import tn.esprit.vromvrom.service.ServiceUser;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReclamationController implements Initializable {

    
    
     private Connection cnx;

    public ReclamationController(){
        cnx = Database.getInstance().getCnx();
    }

    /**
     * Initializes the controller class.
     */
    
    
     @FXML
    private TextField prenom;

    @FXML
    private TextField resolution;

    @FXML
    private TextField type;
    

    @FXML
    private TextField reclamation;

    @FXML
    private TextField user;
    
    @FXML
    private TextField id_rec;

    @FXML
    private Button B;

    @FXML
    private Button b;

    @FXML
    private Button up;

    @FXML
    private TableView<Reclamation> tab;

    @FXML
    private TableColumn<Reclamation,Integer > colu;

    @FXML
    private TableColumn<Reclamation, String> colnom;

    @FXML
    private TableColumn<Reclamation, String> colpre;

    @FXML
    private TableColumn<Reclamation, String > colrecla;

    @FXML
    private TableColumn<Reclamation, String> colreso;

    @FXML
    private TableColumn<Reclamation, String> coltyperec;

    @FXML
    private TableColumn<Reclamation, String> coltime;

    @FXML
    private TextField nom;

    
    public void table(){
         
        colu.setCellValueFactory( new PropertyValueFactory<>("id_user"));
        colnom.setCellValueFactory(new PropertyValueFactory <>("nom"));
         colpre.setCellValueFactory( new PropertyValueFactory<>("prenom"));
        colrecla.setCellValueFactory(new PropertyValueFactory <>("reclamation"));
         colreso.setCellValueFactory( new PropertyValueFactory<>("type_reclamation"));
        coltyperec.setCellValueFactory(new PropertyValueFactory <>("resolution"));
         coltime.setCellValueFactory( new PropertyValueFactory<>("time"));
       
        tab.setItems(RecupBase()); 



}
    
    public static ObservableList<Reclamation> RecupBase(){
             
    ObservableList<Reclamation> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "select *from reclamation";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    
    while (R.next()){
        
        ServiceUser s = new ServiceUser();
        
        User us = new User();
        
        us = s.SelectUser(R.getInt(2));
        System.out.println(us);
        
      Reclamation r =new Reclamation();
     r.setId_user((R.getInt(2)));
     r.setNom(us.getNom());
     r.setPrenom(us.getPrenom());
     r.setReclamation(R.getString(3));
     r.setResolution(R.getString(4));
     r.setType_reclamation(R.getString(5));
     r.setTime(R.getString(6));
    
     
      list.add(r);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }
    
    public void onEdit() {
               
               java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
     
    if (tab.getSelectionModel().getSelectedItem() != null) {
          Reclamation f = tab.getSelectionModel().getSelectedItem();
         int i = f.getId_user();
        String n = String.valueOf(i);
      
          user.setText(n);
          reclamation.setText(f.getReclamation());
                    resolution.setText(f.getResolution());
                              type.setText(f.getType_reclamation());
                      id_rec.setText(f.getTime());
                             

          
          
          
                  
       
        
      
            
    }
}
    
    
    public void AddReclamation(ActionEvent event){
        
    EnregistrerVersBase();
    table();
    user.clear();
    reclamation.clear();
    resolution.clear();
    type.clear();
    
    
    
    }
    private void EnregistrerVersBase() {
        
        
             
           try {
            String st = "INSERT INTO reclamation (id_user,reclamation,resolution,type_reclamation) VALUES (?,?,?,?)";
               PreparedStatement p = cnx.prepareStatement(st);
            p.setString(1, user.getText());
            p.setString(2, reclamation.getText());
            p.setString(3, resolution.getText());
            p.setString(4, type.getText());
       
           
        
            p.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error");
        }
    }
  
            @FXML
    public void UPP(ActionEvent event){
 
        
                    update();
    table();
     user.clear();
    reclamation.clear();
    resolution.clear();
    type.clear();
    }
    
        
        public void update(){
            
            String d = id_rec.getText();
            
            
         String sql="update  reclamation set reclamation='"+reclamation.getText()+"',resolution='"+resolution.getText()+"',type_reclamation='"+type.getText()+"' where temps='"+d+"'";
         
        try{
        
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
        st.executeUpdate();
      
        
       
        } catch(SQLException e){
            e.getMessage();}
     
     }  
    
    @FXML
    public void DELL(ActionEvent event){
    
    delete();
    table();
    
    }
    
        
        public void delete(){
            
            String d = id_rec.getText();
            
         String sql="delete from reclamation where temps='"+d+"'";
         
        try{
        
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
        st.executeUpdate();
      
        
       
        } catch(SQLException e){
            e.getMessage();}
     
     }
            
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        // TODO
       
    table();
//    user.setDisable(true);
    id_rec.setVisible(false);
    tab.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 0) {
        onEdit();
        
    }
});
        
        
    }    
    
}
