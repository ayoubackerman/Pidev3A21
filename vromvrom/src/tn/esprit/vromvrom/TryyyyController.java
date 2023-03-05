/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;
import javafx.scene.control.Alert.AlertType;






/**
 * FXML Controller class
 *
 * @author MediaCenter Zaghouan
 */
public class TryyyyController implements Initializable {

    /**
     * Initializes the controller class.
     */

/**
 * Editable circle options 
 */
    
    public static final  String ACCOUNT_SID = "AC52fb1118a58d88399cd4c7909305c125";
    public static final String  AUTH_TOKEN = "fe06aef2115203ded4714f07eb37b88b";
    

    Alert alert;


    @FXML
    private JFXTextArea textArea;

    @FXML
    private JFXButton sendButton;

    @FXML
    void sendSms(ActionEvent event) {

        try{

            Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
            Message message = Message.creator(new PhoneNumber("+12762779120"),
                    new PhoneNumber("+21622560031"),"yaatel assba").create();
            System.out.println(message.getSid());

            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText("");
            alert.setContentText("SMS Send Successfully");
            alert.show();

        } catch (Exception e){

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("");
            alert.setContentText("Something went wrong:"+e.toString());
            e.printStackTrace();
            alert.show();
        }

    }

     
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
