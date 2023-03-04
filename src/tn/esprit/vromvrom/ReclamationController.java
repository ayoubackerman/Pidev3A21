/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.Reclamation;
import tn.esprit.vromvrom.Model.User;
import tn.esprit.vromvrom.service.ServiceReclamation;
import tn.esprit.vromvrom.service.ServiceUser;
import static utils.BadWords.checkWords;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReclamationController implements Initializable {
ObservableList<String> recList = FXCollections.observableArrayList("Technique","Graphique","Service","Autre");
    
    
     private Connection cnx;

    public ReclamationController(){
        cnx = Database.getInstance().getCnx();
    }


    @FXML
    private TextField resolution;

    @FXML
    private TextField type;

    @FXML
    private TextField reclamation;

    @FXML
    private TextField user;
    
    @FXML
    private TextField tems;

    @FXML
    private Button B;
    
      @FXML
    private AnchorPane inter;

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
    private ChoiceBox<String> rec;

    
     @FXML
    private TextField id_reclamation;

    
    public void table(){
         
//        colu.setCellValueFactory( new PropertyValueFactory<>("id_user"));
colu.setCellValueFactory((CellDataFeatures<Reclamation, Integer> param) -> Bindings.createObjectBinding(() -> param.getValue().getId_user().getId_user()));
//        colnom.setCellValueFactory(new PropertyValueFactory <>("nom"));
colnom.setCellValueFactory((CellDataFeatures<Reclamation, String> param) -> new SimpleStringProperty(param.getValue().getId_user().getNom()));
         //colpre.setCellValueFactory( new PropertyValueFactory<>("prenom"));
colpre.setCellValueFactory((CellDataFeatures<Reclamation, String> param) -> new SimpleStringProperty(param.getValue().getId_user().getPrenom()));
        colrecla.setCellValueFactory(new PropertyValueFactory <>("reclamation"));
         colreso.setCellValueFactory( new PropertyValueFactory<>("resolution"));
        coltyperec.setCellValueFactory(new PropertyValueFactory <>("type_reclamation"));
         coltime.setCellValueFactory( new PropertyValueFactory<>("time"));
       
        tab.setItems(RecupBase()); 



}
    
    
    public Boolean VerifUserChamps() {

        int verif = 0;

        String style = " -fx-border-color: red;";
        
        if (reclamation.getText().trim().equals("")) {
            reclamation.setStyle(style);
            verif = 1;

        }
        if (type.getText().trim().equals("")) {
            type.setStyle(style);
            verif = 1;


        if (verif == 0) {
            return true;
        }
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Verifier les champs");
        al.setHeaderText(null);
        al.show();

        return false;

    }    return null;
}
    
    
    int attention=0;
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
      User u = new User(R.getInt(2));
      r.setId_reclamation(R.getInt(1));
     r.setId_user(us);
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
         int i = f.getId_user().getId_user();
        String n = String.valueOf(i);
        
        int z = f.getId_reclamation();
        String nn = String.valueOf(z);
        
        
        
      id_reclamation.setText(nn);
          user.setText(n);
          reclamation.setText(f.getReclamation());
                    resolution.setText(f.getResolution());
                              type.setText(f.getType_reclamation());
                      tems.setText(f.getTime());
                      
                      
     
    }
}
    
    
    @FXML
    public void AddReclamation(ActionEvent event) throws SQLException, IOException{
        
    String cont = reclamation.getText();
          if (cont.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
              if(checkWords(cont).equals("false")){
                  ServiceReclamation sr = new ServiceReclamation();
                  Reclamation p = new Reclamation();
                  User u = new User(Integer.parseInt(user.getText()));
                  p.setReclamation(reclamation.getText());
                  p.setType_reclamation(type.getText());
                  p.setId_user(u);
                  System.out.println(p);
                  sr.ajouter(p);
                  table();
                  user.clear();
                  reclamation.clear();
                  type.clear();
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Done");
                  alert.setContentText("Reclamation ajouté avec succes!");
                  alert.show();
                  clean();
                  
              }else
              {
                  attention++;
                  clean();
                  Alert alert = new Alert(Alert.AlertType.WARNING);
                  alert.setTitle("Worning !! ");
                  alert.setContentText("vous ne pouvez pas ajouter ce reclamation avec ces mots ! ");
                  alert.show();
                  
                  if(attention>2)
                  {
                      System.out.println(attention);
//                          Mail.envoyer(user);
                  }
                  
              }
       
    

    }
    
    }
     
   
            @FXML
    public void UPP(ActionEvent event) throws SQLException{
 
        String cont = reclamation.getText();
          if (cont.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
              if(checkWords(cont).equals("false")){
         ServiceReclamation mm = new ServiceReclamation();
                  Reclamation m = new Reclamation();
                  m.setTime(tems.getText());
                  m.setReclamation(reclamation.getText());
                  m.setType_reclamation(type.getText());
                  
                  System.out.println(m);
                  mm.update(m);
                  table();
                  user.clear();
                  reclamation.clear();
                  type.clear();
   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Done");
                  alert.setContentText("\"Votre Reclamation modifiée avec succès!\"");
                  alert.show();
                  clean();
                  
              }else
              {
                  attention++;
                  clean();
                  Alert alert = new Alert(Alert.AlertType.WARNING);
                  alert.setTitle("Worning !! ");
                  alert.setContentText("vous ne pouvez pas ajouter ce reclamation avec ces mots ! ");
                  alert.show();
                  
                  if(attention>2)
                  {
                      System.out.println(attention);
//                          Mail.envoyer(user);
                  }
                  
              }
       
    

    }}
//    
//        
//        public void update(){
//            
//            String d = id_rec.getText();
//            
//            
//         String sql="update  reclamation set reclamation='"+reclamation.getText()+"',resolution='"+resolution.getText()+"',type_reclamation='"+type.getText()+"' where temps='"+d+"'";
//         
//        try{
//        
//        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
//        st.executeUpdate();
//      
//        
//       
//        } catch(SQLException e){
//            e.getMessage();}
//     
//     }  
    
    @FXML
    public void DELL(ActionEvent event) throws SQLException{
        
         String d = id_reclamation.getText();
         int n = Integer.valueOf(d);

        ServiceReclamation sr = new ServiceReclamation();
        Reclamation r = new Reclamation();
        
        System.out.println(n);
        r = sr.SelectReclamation(n);
        System.out.println(r);
        sr.delete(r);
    
         table();
    user.clear();
    reclamation.clear();
    type.clear();
    }
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        // TODO
       rec.setValue(" "); 
        rec.setItems(recList);
    table();
    user.setVisible(true);
    tems.setVisible(false);
    id_reclamation.setVisible(false);
    resolution.setVisible(false);
    tab.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 0) {
        onEdit();
        B.setDisable(true);
        
    }
});
   inter.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 0) {
        B.setDisable(false);
        user.clear();
    reclamation.clear();
    type.clear();
        
    }
});     
        
    }

    private void clean() {
      reclamation.setText(null);
    }





}
    
 