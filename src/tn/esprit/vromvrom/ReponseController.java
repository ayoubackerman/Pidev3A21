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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import tn.esprit.vromvrom.Model.Reponse;
import tn.esprit.vromvrom.Model.User;
import tn.esprit.vromvrom.service.ServiceReponse;
import tn.esprit.vromvrom.service.ServiceUser;

/** 93010536
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReponseController implements Initializable {
    
     private Connection cnx;

    public ReponseController(){
        cnx = Database.getInstance().getCnx();
    }
    
          @FXML
    private TextField statuts;

    @FXML
    private TextField rreponse;
    
     @FXML
    private TextField temps;
     
      @FXML
    private TextField id_rec;
      
      
       @FXML
    private TextField id_user;
      

    @FXML
    private TableView<Reclamation> tableau;

    @FXML
    private TableColumn<Reclamation, Integer> colrep;

    @FXML
    private TableColumn<Reclamation, Integer> coliduser;

    @FXML
    private TableColumn<Reclamation, String> colnom;
    
      @FXML
    private TableColumn<Reclamation, String> colrecl;

    @FXML
    private TableColumn<Reclamation, String> coltyperec;

    @FXML
    private TableColumn<Reclamation, String> colreso;


    @FXML
    private TableColumn<Reclamation, String> colpren;

    @FXML
    private TableColumn<Reclamation, String> colrepn;

  
    @FXML
    private TableColumn<Reclamation, String> coltimerep;

    @FXML
    private Button modrep;

    @FXML
    private Button adrep;

    @FXML
    private Button suprep;
    
     @FXML
    private Button rf;
     
     
    
     public void table(){
         
        colrep.setCellValueFactory( new PropertyValueFactory<>("id_reclamation"));
        coliduser.setCellValueFactory(new PropertyValueFactory <>("id_user"));
         colnom.setCellValueFactory( new PropertyValueFactory<>("nom"));
        colpren.setCellValueFactory(new PropertyValueFactory <>("prenom"));
          colrecl.setCellValueFactory( new PropertyValueFactory<>("reclamation"));
        coltyperec.setCellValueFactory(new PropertyValueFactory <>("type_reclamation"));
         colreso.setCellValueFactory( new PropertyValueFactory<>("resolution"));
         colrepn.setCellValueFactory( new PropertyValueFactory<>("reponse"));
         coltimerep.setCellValueFactory( new PropertyValueFactory<>("time"));
       
        tableau.setItems(RecupBase()); 

 
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
        
        ServiceReponse aa = new ServiceReponse();
        
        Reponse bb = new Reponse();
       bb = aa.SelectReponse(R.getInt(1));
       
        System.out.println(bb);
        
   ServiceUser s = new ServiceUser();
        User us = new User();
        us = s.SelectUser(R.getInt(2));
        System.out.println(us);
        
    Reclamation r =new Reclamation();
    r.setId_reclamation(R.getInt(1));
      r.setId_user((R.getInt(2)));
     r.setNom(us.getNom());
     r.setPrenom(us.getPrenom());
     r.setReclamation(R.getString(3));
     r.setResolution(R.getString(4));
     r.setType_reclamation(R.getString(5));
     r.setReponse(bb.getReponse());
     r.setTime(R.getString(6));
     
   
    
     
   list.add(r);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }
       
        private void EnregistrerVersBase() {
        
        
             
           try {
            String st = "INSERT INTO reponse( id_reclamation , id_user) VALUES (?,?)";
               PreparedStatement p = cnx.prepareStatement(st);
            p.setString(1, id_rec.getText());
                        p.setString(2, id_user.getText());

            p.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error");
        }
    }

    public void Adid(ActionEvent event){
        
    EnregistrerVersBase();
    table();

    }

  

    private void onEdit() {
                 java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
     
    if (tableau.getSelectionModel().getSelectedItem() != null) {
          Reclamation f = tableau.getSelectionModel().getSelectedItem();
       int i = f.getId_reclamation();
        String n = String.valueOf(i);  
        int a = f.getId_user();
        String b= String.valueOf(a);
                   rreponse.setText(f.getReponse());
                   temps.setText(f.getTime());
                  id_rec.setText(n);
                  id_user.setText(b);                   
    }}

    
    
    
            @FXML
    public void UPPDATE(ActionEvent event){
    
    update();
    table();
     
    }
        public void update(){
           String d = id_rec.getText();
         String sql="update  reponse set reponse='"+rreponse.getText()+"' where id_reclamation='"+d+"'";
        try{
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
        st.executeUpdate();
        } catch(SQLException e){
            e.getMessage();}
   }  
        
  
    
  @FXML
    public void DELLTT(ActionEvent event){
    
    delete();
    table();
    
    }
        public void delete(){       
            String d = id_rec.getText();
         String sql="delete reponse from reponse where id_reclamation='"+d+"'";
        try{
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
        st.executeUpdate();
        } catch(SQLException e){
            e.getMessage();}
     }
      @Override
    public void initialize(URL url, ResourceBundle rb) { 
        table();
       temps.setVisible(false);
       id_user.setDisable(true);
       id_rec.setDisable(true);
      tableau.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 0) {
        onEdit();
        
    }
});
    }
    
   
   
}
