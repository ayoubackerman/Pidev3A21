/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.User;
import model.Ratingg;
import org.controlsfx.control.Rating;
import service.ServiceRating;

/**
 * FXML Controller class
 *
 * @author mehdi
 */
public class CardController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private ImageView imgg;
    @FXML
    private Rating rate;
    private boolean isSet = false;
    @FXML
    private TextField com;
    
    private User user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    
    
    
    public void setData(User us){
        user = new User(us);   
        nom.setText(us.getNom());
        prenom.setText(us.getPrenom());
            try{
               Image image = new Image(getClass().getResourceAsStream(us.getImage()));
               imgg.setImage(image);
            }catch(Exception ex){
                System.out.println(ex.getMessage());
                Image image = new Image(getClass().getResourceAsStream("/tn/esprit/vromvrom/resources/image/profile.PNG"));
                imgg.setImage(image);
            } 
            
            ServiceRating sr = new ServiceRating();
        
        try {
            System.out.println(sr.calculateMoyenne(user.getId_user()));
            rate.setRating(sr.calculateMoyenne(user.getId_user()));
            if (sr.check(User.connecte.getId_user())) rate.setDisable(true);
        } catch (SQLException ex) {
            Logger.getLogger(CardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void stars(MouseEvent event) {
        rate.setRating(rate.getRating());
        isSet = true;
    }

    @FXML
    private void add(ActionEvent event) {
        Ratingg r = new Ratingg();
        if(isSet) r.setEtoiles(rate.getRating());
        r.setCommentaire(com.getText());
        r.setUser(user);
        r.setUser2(User.connecte);
        ServiceRating sr = new ServiceRating();
        try 
        {
            if (!sr.search(user.getId_user(), User.connecte.getId_user())) sr.ajouter(r);
            else sr.update(r);
            com.clear();
        } catch (SQLException ex) {
            Logger.getLogger(CardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
