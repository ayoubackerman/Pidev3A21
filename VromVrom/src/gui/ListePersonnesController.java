/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.User;
import service.ServiceUser;

/**
 * FXML Controller class
 *
 * @author mehdi
 */
public class ListePersonnesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private ObservableList <User> list; 
    @FXML
    private HBox cardLayout;
    @FXML
    private GridPane userContainer;
    @FXML
    private TextField recherche;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //User.connecte.setId_user(11);
        ServiceUser su = new ServiceUser();
        int column = 1;
        int row = 1;
        try {
            list = su.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(ListePersonnesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           for (int i = 0; i < list.size(); i++){
            try {
                if (i < 5){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController card = fxmlLoader.getController();
                card.setData(list.get(i));
                cardLayout.getChildren().add(cardBox);
                }
                else{
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Perso.fxml"));
                VBox persoBox = fxmlLoader.load();
                PersoController perso = fxmlLoader.getController();
                perso.setData(list.get(i));
                
                if(column == 4){
                    column = 1;
                    row ++;
                }
                userContainer.add(persoBox, column++, row);
                GridPane.setMargin(persoBox, new Insets(10));
                }
            } catch (IOException ex) {
                Logger.getLogger(ListePersonnesController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    }    

    @FXML
    private void search(InputMethodEvent event) throws SQLException {

        
    int numRows = userContainer.getRowConstraints().size();
    int numColumns = userContainer.getColumnConstraints().size();
    for (int row = 0; row < numRows; row++) {
        for (int col = 0; col < numColumns; col++) {
            //Node node = userContainer.getChildren().get(row * numColumns + col);
            for (Node node : userContainer.getChildren()) {
             if (node instanceof Label) {
            Label label = (Label) node;
            if (label.getText().equals(recherche.getText())) {
            // found the label!
            // do something with it, e.g. set its text color
            node.setVisible(false);
            break; // stop searching
        }
    }
}
    }
}
    }
    
}
