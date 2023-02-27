/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author MediaCenter Zaghouan
 */
public class ManageRoleController implements Initializable {

    @FXML
    private TableView<?> tab;
    @FXML
    private TableColumn<?, ?> columnid;
    @FXML
    private TableColumn<?, ?> columnRole;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddRole(ActionEvent event) {
    }

    @FXML
    private void Supprimer(ActionEvent event) {
    }

    @FXML
    private void Modifier(ActionEvent event) {
    }
    
}
