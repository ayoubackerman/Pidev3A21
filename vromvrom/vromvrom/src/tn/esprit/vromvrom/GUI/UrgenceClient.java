package tn.esprit.vromvrom.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;




















public class UrgenceClient implements Initializable {

    @FXML
    private ComboBox comb;

    @FXML
    void selectTypeUrgence(ActionEvent event){

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList("accident","panne de voiture","problème de santé","problème avec le chauffeur ou le client");
        comb.setItems(list);
    }
}
