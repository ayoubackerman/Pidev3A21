/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import static gui.PersonReserverController.RecupBase;
import model.Trajet;
import model.User;
import service.ServiceReservation;
import service.ServiceTrajet;
import model.Reservation;
import model.Trajet;
import database.Database;
/**
 * FXML Controller class
 *
 * @author user
 */
public class MesreservationController implements Initializable {

 @FXML
    private TableColumn<Reservation, Integer> plr1;
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
 ServiceReservation RSU = new ServiceReservation();
    ServiceTrajet tSU = new ServiceTrajet();
//    @FXML
//    void onEdit(ActionEvent event) {
    @FXML
    private TextField idS;
    @FXML
    private Button buts;
     
        
                   public void table() throws SQLException{
         
   
vdr.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> param) {
return new SimpleStringProperty(param.getValue().getTrajet().getVille_depart());
    }  });
vad.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> param) {
return new SimpleStringProperty(param.getValue().getTrajet().getVille_darrive());
    }
  
            });
plr.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,Integer>,ObservableValue<Integer>>(){
    @Override
    public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Reservation, Integer> param) {
return new SimpleObjectProperty<Integer>(param.getValue().getTrajet().getNbr_place());
    }
  
            });
plr1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,Integer>,ObservableValue<Integer>>(){
    @Override
    public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Reservation, Integer> param) {
return new SimpleObjectProperty<Integer>(param.getValue().getTrajet().getId_trajet());
    }
  
            });
n.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> param) {
return new SimpleStringProperty(param.getValue().getUser().getNom());
    }
            });
p.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> param) {
return new SimpleStringProperty(param.getValue().getUser().getPrenom());
    }
            });
m.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,String>,ObservableValue<String>>(){
    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> param) {
return new SimpleStringProperty(param.getValue().getUser().getMail());
    }
            });

     //tab.setItems(RecupBase());
        tabR.setItems(RecupBase());


//    }
//                    public void onEdit() {
//
//        java.sql.Connection cnx;
//        cnx = MaConnexion.getInstance().getCnx();
//
//       tabR.setOnMouseClicked(event -> {
//    if (event.getClickCount() == 1) {
//        String villeDepart = tabR.getSelectionModel().getSelectedItem().getTrajet().;
//        idS.setText(villeDepart);
//    }
//});
//                    }
                   }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         try {
                table();
              
            } catch (SQLException ex) {
                Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }

//    @FXML
//    private void onEdit(SortEvent<C> event) {
//    }
}
   
