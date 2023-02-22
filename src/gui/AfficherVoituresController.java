/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.User;
import tn.esprit.vromvrom.Model.Voiture;
import tn.esprit.vromvrom.service.ServiceVoiture;

/**
 * FXML Controller class
 *
 * @author mehdi
 */
public class AfficherVoituresController implements Initializable {

    @FXML
    private TableView<Voiture> table;
    @FXML
    private TableColumn<Voiture, Integer> idVoiture;
    @FXML
    private TableColumn<Voiture, String> modele;
    @FXML
    private TableColumn<Voiture, String> marque;
    @FXML
    private TableColumn<Voiture, Integer> matricule;
    @FXML
    private TableColumn<Voiture, String> Nom;
    @FXML
    private TableColumn<Voiture, String> Prenom;
    @FXML
    private TextField up_id;
    @FXML
    private TextField up_modele;
    @FXML
    private TextField up_marque;
    @FXML
    private TextField up_matricule;
    @FXML
    private TextField up_img;
    @FXML
    private TableColumn<?, ?> img;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    public void table() {

        idVoiture.setCellValueFactory(new PropertyValueFactory<>("id"));
        Nom.setCellValueFactory((CellDataFeatures<Voiture, String> cellData) -> {
            User user = cellData.getValue().getUser();
            return new SimpleStringProperty(user.getNom());
        });
        Prenom.setCellValueFactory((CellDataFeatures<Voiture, String> cellData) -> {
            User user = cellData.getValue().getUser();
            return new SimpleStringProperty(user.getNom());
        });
        modele.setCellValueFactory(new PropertyValueFactory<>("modele"));
        marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        ObservableList<Voiture> list = FXCollections.observableArrayList();
        ServiceVoiture sv = new ServiceVoiture();
        try {
            list = sv.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherVoituresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(list);
        TableColumn<Voiture, Integer> idColumn = new TableColumn<>("ID");
        table.getColumns().add(idColumn);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setComparator(Comparator.comparingInt(Integer::intValue));
        table.getSortOrder().add(idColumn);
        table.sort();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        table();

    }

    @FXML
    private void supprimer(ActionEvent event) {

        try {
            Voiture voiture = table.getSelectionModel().getSelectedItem();
            ServiceVoiture sv = new ServiceVoiture();
            sv.delete(voiture);
            table();
        } catch (Exception ex) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Erreur");
            al.setHeaderText("Veuillez selectionner une ligne");
            al.setContentText(ex.getMessage());
            al.show();
        }
    }

    @FXML
    private void update(ActionEvent event) {
        
        if (((((up_id.getText().isEmpty() || up_id.getText().isEmpty()) || up_modele.getText().isEmpty()) || up_marque.getText().isEmpty()) || up_matricule.getText().isEmpty())
                || up_img.getText().isEmpty()) {
            
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Controle de saisie");
            al.setHeaderText("Erreur de saisie !");
            al.setContentText("Les données sont vides !");
            al.show();
        }else
        {
            Voiture voiture = table.getSelectionModel().getSelectedItem();
            User user = new User(Integer.parseInt(up_id.getText()));
            voiture.setUser(user);
            voiture.setImg(up_img.getText());
            voiture.setModele(up_modele.getText());
            voiture.setMarque(up_marque.getText());
            voiture.setMatricule(Integer.parseInt(up_matricule.getText()));

            ServiceVoiture sv = new ServiceVoiture();
            try {
                sv.update(voiture);
            } catch (SQLException ex) {
                Logger.getLogger(AfficherVoituresController.class.getName()).log(Level.SEVERE, null, ex);
            }
            table();
            up_id.clear();
            up_modele.clear();
            up_marque.clear();
            up_matricule.clear();
            up_img.clear();
        }
    }

    @FXML
    private void retrieve(MouseEvent event) {
        Voiture voiture = table.getSelectionModel().getSelectedItem();
        up_id.setText(Integer.toString(voiture.getUser().getId()));
        up_modele.setText(voiture.getModele());
        up_marque.setText(voiture.getMarque());
        up_matricule.setText(Integer.toString(voiture.getMatricule()));
        up_img.setText(voiture.getImg());
    }

}
