/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.JOptionPane;
import model.Trajet;
import model.User;
import service.ServiceTrajet;
import database.Database;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AffTrajetController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
      @FXML
    private TableView<Trajet> tab;

    @FXML
    private TableColumn<Trajet, String> VD;

    @FXML
    private TableColumn<Trajet, String> VA;

    @FXML
    private TableColumn<Trajet, Float> prix;

    @FXML
    private TableColumn<Trajet, Integer> place;

    @FXML
    private TableColumn<Trajet, Integer> pose;

    @FXML
    private TableColumn<Trajet, String> paiement;
    
     @FXML
    private Button retour;
     
          @FXML
    private Button supp;
   @FXML
    private Button ref;

     @FXML
    private ComboBox<String> ville_DE;

    @FXML
    private ComboBox<String> ville_DA;

    @FXML
    private TextField Prixx;

    @FXML
    private TextField nbrplace;

    private TextField dureepose;

    @FXML
    private TextField modepaiement;
    @FXML
    private Button butnmod;
    @FXML
    private TextField idTR;
    @FXML
    private TableColumn<Trajet, Integer> idTRJ;
    @FXML
    private TextField idS;
    @FXML
    private DatePicker datM;
       @FXML
    private TextField Recherche;
       
       
ServiceTrajet tSU = new ServiceTrajet();

    ObservableList<Trajet> Chercheuser ;
    
    @FXML
    private TableColumn<Trajet, Integer> idTRJ1;
    public void table() throws SQLException{
         
        VD.setCellValueFactory( new PropertyValueFactory<>("Ville_depart"));
        VA.setCellValueFactory(new PropertyValueFactory <>("Ville_darrive"));
          prix.setCellValueFactory(new PropertyValueFactory <>("prix"));
          place.setCellValueFactory(new PropertyValueFactory <>("Nbr_place"));
          idTRJ1.setCellValueFactory(new PropertyValueFactory <>("id_user"));
          idTRJ1.setCellValueFactory((CellDataFeatures<Trajet, Integer> cellData) -> {
            User u = cellData.getValue().getUser();
            return new SimpleObjectProperty<>(u.getId_user());
            });
//            idTRJ1.setCellValueFactory(new PropertyValueFactory <>("id_user"));

            idTRJ.setCellValueFactory(new PropertyValueFactory <>("id_trajet"));
            
        pose.setCellValueFactory(new PropertyValueFactory <>("date"));
        paiement.setCellValueFactory(new PropertyValueFactory <>("Mode_paiement"));

     tab.setItems(RecupBase());
        System.out.println(RecupBase());
      //  tab.setItems(tSU.selectAll());


    }
    
     @FXML
    public void Refresh(ActionEvent event) throws SQLException{
    
   table();
  
    }
    @FXML
    private void Supp(ActionEvent event) throws SQLException {
//int idf = Integer.parseInt(idS.getText());
int idf = Integer.parseInt(idS.getText());
Trajet tur = new Trajet(idf);
        System.out.println(idf);
tSU.delet(idf);
table();
   }
    

    
   @FXML
private void Retour(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("dashbord.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.setTitle("trajet");
    stage.show();
}
   @FXML
private void Retour2(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("personReserver.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.setTitle("trajet");
    stage.show();
}
  //affichter  
    public static ObservableList<Trajet> RecupBase(){
             
    ObservableList<Trajet> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "select *from trajet where nbr_place>0";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    while (R.next()){
      Trajet r =new Trajet();
     r.setVille_depart((R.getString(1)));
     r.setVille_darrive(R.getString(2));
     r.setPrix((int) R.getFloat(3));
     r.setNbr_place(R.getInt(4));
    User u = new User();
     u.setId_user(R.getInt(5));
     r.setUser(u);
     r.setId_trajet(R.getInt(6));
     r.setDate(R.getString(7));
     r.setMode_paiement(R.getString(8));
//     System.out.println(R);
// if (Nbr_place == 0) {
//                r.delet(idt);
//            } 
// ServiceTrajet rK =new ServiceTrajet();
// if (r.getNbr_place() == 0) {
//                rK.delet(r.getId_trajet());
//            
// }
    
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
            Trajet trajet = tab.getSelectionModel().getSelectedItem();
int i = trajet.getNbr_place();
        String n = String.valueOf(i);
        int ii = trajet.getId_trajet();
        String nn = String.valueOf(ii);
        float i2 = trajet.getPrix();
        String n2 = String.valueOf(i2);
        
  ville_DE.setValue(trajet.getVille_depart());
ville_DA.setValue(trajet.getVille_darrive());
        Prixx.setText(n2);
            nbrplace.setText(n);
            idTR.setText(nn);
           datM.setValue(LocalDate.parse(trajet.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
modepaiement.setText(trajet.getMode_paiement());

idS.setText(Integer.toString(trajet.getId_trajet()));
        }}
  
   private TextField id_user;
  
  @FXML
void Modifier(ActionEvent event) throws SQLException {
    
  
    
  LocalDate date = datM.getValue();
     String dateString = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

     String vd = ville_DE.getValue();
     String va = ville_DA.getValue();
     float l = Float.parseFloat(Prixx.getText());

     int d = Integer.parseInt(nbrplace.getText());
      int ddd = Integer.parseInt(idS.getText());

//   int idtrjet = Integer.parseInt(idTR.getText()); // convertir en entier   idtrje
     String s = dateString;
     String da = modepaiement.getText();
     Trajet tur = new Trajet();
     tur.setDate(s);
          tur.setId_trajet(ddd);
     tur.setMode_paiement(da);
     tur.setNbr_place(d);
     tur.setPrix(l);
     tur.setVille_darrive(va);
     tur.setVille_depart(vd);

     
     
      System.out.println(tur);
     tSU.updateOne(tur);
     table();  
    
}
public void ChercheFichier() throws SQLException{
        paiement.setCellValueFactory( new PropertyValueFactory<>("Mode_paiement"));
        
        VD.setCellValueFactory( new PropertyValueFactory<>("ville_depart"));
                VA.setCellValueFactory( new PropertyValueFactory<>("ville_darrive"));


    
       
    Chercheuser = tSU.selectAll();
    tab.setItems(tSU.selectAll());
    FilteredList<Trajet> filtreddata;
     filtreddata = new FilteredList<>(Chercheuser ,b ->true);
    Recherche.textProperty().addListener((observable,oldValue,newValue)->{
      filtreddata.setPredicate((u  ->  {
          
          if((newValue ==null) || newValue.isEmpty())
          { return true;}
      
      String lowerCaseFilter = newValue.toLowerCase();
      if (u.getMode_paiement().toLowerCase().contains(lowerCaseFilter)){
      return true;
      }else if (u.getVille_depart().toLowerCase().contains(lowerCaseFilter)){
      return true;
      }else if (u.getVille_darrive().toLowerCase().contains(lowerCaseFilter)){
      return true;
      } 
     
        
      return false;
      })); 
    });
    
    SortedList<Trajet> srt = new SortedList<>(filtreddata);
    srt.comparatorProperty().bind(tab.comparatorProperty());
    tab.setItems(srt);
    System.out.println(srt);
    }
  
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          try {
            
              
              
              table();
              ChercheFichier();
              
              // lhjett ily nhbhom yadhhroo au debut de runn d'applicat  TODO
              //selection
              tab.setOnMouseClicked((MouseEvent event) -> {
                  if (event.getClickCount() > 0) {
                      onEdit();
                      
                  }
                  
              });
              idTR.setVisible(false);
              
              
              
              
              List<String> gouvernorats = Arrays.asList(
                      "Ariana", "Béja", "Ben Arous", "Bizerte", "Gabès",
                      "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kébili",
                      "La Manouba", "Le Kef", "Mahdia", "Médenine", "Monastir",
                      "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse",
                      "Tataouine", "Tozeur", "Tunis", "Zaghouan"
              );
              List<String> gouvernoratsD = Arrays.asList(
                      "Ariana", "Béja", "Ben Arous", "Bizerte", "Gabès",
                      "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kébili",
                      "La Manouba", "Le Kef", "Mahdia", "Médenine", "Monastir",
                      "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse",
                      "Tataouine", "Tozeur", "Tunis", "Zaghouan"
              );
              ville_DE.setItems(FXCollections.observableArrayList(gouvernorats));
              ville_DA.setItems(FXCollections.observableArrayList(gouvernoratsD)); 
//        
          } catch (SQLException ex) {
              Logger.getLogger(AffTrajetController.class.getName()).log(Level.SEVERE, null, ex);
          }

        
    }    
    
}
