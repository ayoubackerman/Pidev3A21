/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import database.Database;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static gui.AffTrajetController.RecupBase;
import model.Reservation;
import model.Trajet;
import model.User;
import service.ServiceTrajet;
import database.Database;

/**
 * FXML Controller class
 *
 * @author user
 */


 
public class PersonReserverController implements Initializable {
    private TableColumn<Reservation, Integer> idrs;

    private TableColumn<Reservation, Integer> id_trr;
  @FXML
    private TableColumn<Reservation, String> vdr;
    @FXML
    private TableColumn<Reservation, String> vad;
    @FXML
    private TableColumn<Reservation, Integer> plr;
    @FXML
    private TableColumn<Reservation, String> n;

    @FXML
    private TableColumn<Reservation, String> p;
       @FXML
    private TableColumn<Reservation, String> m;
      @FXML
    private TableView<Reservation> tabR;
    @FXML
    private TextField maail;
    @FXML
    private Button ret;
     @FXML
    private Button butnmail;
      @FXML
    private Button but;
    @FXML
    private TableColumn<Reservation, Integer> plr1;
    @FXML
    private TextField trtr;
    
        private Connection cnx;

    public PersonReserverController(){
        cnx = Database.getInstance().getCnx();
    }
    int nombre_place ;
 
           public void table() throws SQLException{
         
   
vdr.setCellValueFactory(new Callback<CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
    @Override
    public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
return new SimpleStringProperty(param.getValue().getTrajet().getVille_depart());
    }
   
    
  
             
            });
vad.setCellValueFactory(new Callback<CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
    @Override
    public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
return new SimpleStringProperty(param.getValue().getTrajet().getVille_darrive());
    }
  
            });
plr.setCellValueFactory(new Callback<CellDataFeatures<Reservation,Integer>,ObservableValue<Integer>>(){
    @Override
    public ObservableValue<Integer> call(CellDataFeatures<Reservation, Integer> param) {
return new SimpleObjectProperty<Integer>(param.getValue().getTrajet().getNbr_place());
    }
  
            });
plr1.setCellValueFactory(new Callback<CellDataFeatures<Reservation,Integer>,ObservableValue<Integer>>(){
    @Override
    public ObservableValue<Integer> call(CellDataFeatures<Reservation, Integer> param) {
return new SimpleObjectProperty<Integer>(param.getValue().getTrajet().getId_trajet());
    }
  
            });
n.setCellValueFactory(new Callback<CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
    @Override
    public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
return new SimpleStringProperty(param.getValue().getUser().getNom());
    }
            });
p.setCellValueFactory(new Callback<CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
    @Override
    public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
return new SimpleStringProperty(param.getValue().getUser().getPrenom());
    }
            });
m.setCellValueFactory(new Callback<CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
    @Override
    public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
return new SimpleStringProperty(param.getValue().getUser().getMail());
    }
            });

     //tab.setItems(RecupBase());
        tabR.setItems(RecupBase());


    }
           
public void onEdit() {
    // Récupérer la connexion à la base de données
    java.sql.Connection cnx;
    cnx = Database.getInstance().getCnx();

    // Vérifier que l'utilisateur a sélectionné une ligne dans le TableView
   if (tabR.getSelectionModel().getSelectedItem() != null) {
    Reservation reservation = tabR.getSelectionModel().getSelectedItem();
    String mail = reservation.getUser().getMail();
    String tr =  String.valueOf(reservation.getTrajet().getId_trajet());
    maail.setText(mail);
     trtr.setText(tr);
    
    
    }
}    

public int RecupCombo(){
             
             
    
    int id = Integer.valueOf(trtr.getText());
    
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "SELECT nbr_place FROM trajet where id_trajet = '"+ id +"' ";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    while (R.next()){
      
     
   int r = R.getInt(1);
        System.out.println(r);
        
    nombre_place = r ;
     
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return nombre_place;
    }
//           @FXML
//private void onEdit(MouseEvent event) {
//    // Récupérer la ligne sélectionnée
//    Reservation p = tabR.getSelectionModel().getSelectedItem();
//
//    if (p != null) {
//        // Remplir le champ "mail" avec la valeur correspondante
//        maail.setText(p.getUser().getMail());
//    }
//}

    @FXML
    public  void Mail( ) throws MessagingException, SQLException{
    String m=maail.getText();
  Updatenbr();
  sendMail(m);
    
    
}
    @FXML
    public  void MailR( ) throws MessagingException{
    String mr=maail.getText();
    sendMailR(mr);
    
    
    
}
    
    public void Updatenbr() throws SQLException{
        
    int idt = Integer.valueOf(trtr.getText());
    int newnbr = nombre_place -1 ;
    
String req =  " UPDATE trajet SET nbr_place='"+ newnbr +"'  WHERE id_trajet='"+ idt +"' ; ";
//      if(nombre_place=0){}
            PreparedStatement st = cnx.prepareStatement(req);


           
            st.executeUpdate();
    
    
    
    }


public static void sendMail(String recepient) throws MessagingException{
        System.out.println("Prepared to send email");
        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        String myEmailAccount="abrarbouslahi100@gmail.com";
        String password="p r ps grxmmbkvv fhf";
        Session session=Session.getInstance(properties,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myEmailAccount,password);
                
            }
        });
        
        Message message=prepareMessage(session,myEmailAccount,recepient);
        Transport.send(message);
        System.out.println("Message sent succesfully");
    }
        private static Message prepareMessage(Session session, String myEmailAccount,String recepient){
        try {
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmailAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Reservation Accepte");
            message.setText("Bonjour cher(e) abonne(é),votre Reservation est accepte 🙂");
            return message;
                    } catch (MessagingException ex) {
//            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
System.out.println(ex.getMessage());
        }
        return null;
        }
        
        
public static void sendMailR(String recepient) throws MessagingException{
        System.out.println("Prepared to send email");
        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        String myEmailAccount="abrarbouslahi100@gmail.com";
        String password="p r ps grxmmbkvv fhf";
        Session session=Session.getInstance(properties,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myEmailAccount,password);
                
            }
        });
        
        Message message=prepareMessage(session,myEmailAccount,recepient);
        Transport.send(message);
        System.out.println("Message sent succesfully");
    }
        private static Message prepareMessageR(Session session, String myEmailAccount,String recepient){
        try {
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmailAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Reservation Accepte");
            message.setText("Bonjour cher(e) abonne(é),votre Reservation est Refusee  , Nombre de place est max 🙂");
            return message;
                    } catch (MessagingException ex) {
           System.out.println(ex.getMessage());
        }
        return null;
        }

//public List<trajet> trierParType_reclamation(List<trajet> liste, boolean ordreCroissant) {
//    Collections.sort(liste, new Comparator<trajet>() {
//        @Override
//        public int compare(trajet t1, trajet t2) {
//            int resultat = t1.getDate().compareTo(t2.getDate());
//            if (!ordreCroissant) {
//                resultat = -resultat;
//            }
//            return resultat;
//        }
//
//
//    });
//    return liste;
//}
//      public List<trajet> trierParVilleDepart(List<trajet> liste, boolean ordreCroissant) {
//    Collections.sort(liste, new Comparator<trajet>() {
//        @Override
//        public int compare(trajet t1, trajet t2) {
//            int resultat = t1.getVille_depart().compareTo(t2.getVille_depart());
//            if (!ordreCroissant) {
//                resultat = -resultat;
//            }
//            return resultat;
//        }
//    });
//    return liste;
//}
//   @FXML
//  private void trier() throws SQLException {
//    boolean ordreCroissant = tri.isSelected();
//    ServiceTrajet trj = new ServiceTrajet();
//  List<trajet> ResList = trj.selectAll();
//    List<trajet> liste = trierParVilleDepart(ResList, ordreCroissant);
//    Reservation[] tableau = liste.toArray(new Reservation[0]); // conversion en tableau
//tabR.getItems().setAll(tableau);
//   
//}
        
private void Retouree(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("mesreservation.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.setTitle("trajet");
    stage.show();
}    
        
 @FXML
private void Retour(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("affTrajet.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.setTitle("trajet");
    stage.show();
}
   public static ObservableList<Reservation> RecupBase(){
             
    ObservableList<Reservation> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          
        String req = "SELECT *" +
                "FROM reservation" +
                " JOIN trajet ON reservation.id_trajet = trajet.id_trajet" +
                " JOIN user ON reservation.id_user = user.id_user where nbr_place>0";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(req);

    ResultSet R = st.executeQuery();
    while (R.next()){
      Reservation r =new Reservation();
      
      
        User usr = new User(R.getString(14),R.getString(15),R.getString(16));
        
        Trajet t = new Trajet(R.getString(4),R.getString(5),R.getInt(7),R.getInt(9));

      
      
     r.setId_reservation(R.getInt(1));
     r.setTrajet(t);
     r.setUser(usr);
   
     
        System.out.println(r);
     
      list.add(r);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }
//   @FXML
//    public void Refresh(ActionEvent event) throws SQLException{
//    
//   table();
//  
//    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        plr1.setVisible(false);
        tabR.setOnMouseClicked((MouseEvent event) -> {
                  if (event.getClickCount() > 0) {
                      onEdit();
                      RecupCombo();
                              System.out.println(nombre_place);

                      
                  }
                  
                  
              });
        
        
        try {
           
//            trier();
            table();
        } catch (SQLException ex) {
            Logger.getLogger(PersonReserverController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

   

    }
    

