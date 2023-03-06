/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.GUI;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MessageController {
    // Replace with your Twilio Account SID and Auth Token
    public static final String ACCOUNT_SID = "ACd46d99d0a3a672f12be204cb449ba0dd";
    public static final String AUTH_TOKEN = "d0b0df4d032c21b93e652f815d069a29";
    public static final String TWILIO_NUMBER = "+15673131096";

    private Label statusLabel;

    @FXML
    private ComboBox<String> textfield;
  @FXML
    private void Retouur(ActionEvent event) throws IOException {
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("dashbord.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.setTitle("trajet");
    stage.show();
} 
    @FXML
    private void sendSMS() {
        String toPhoneNumber = textfield.getValue();
        if (toPhoneNumber == null || toPhoneNumber.trim().isEmpty()) {
            statusLabel.setText("Please enter a phone number.");
            return;
        }

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String messageText = "bonjourrr lyoum kharj si l admin le " + formatter.format(currentDate);
        Message message = Message.creator(new PhoneNumber(toPhoneNumber),
                new PhoneNumber(TWILIO_NUMBER),
                messageText).create();

        if (message.getSid() != null) {
            statusLabel.setText("SMS sent successfully to " + toPhoneNumber + "!");
        } else {
            statusLabel.setText("Error sending SMS to " + toPhoneNumber + ".");
        }
    }

    public void initialize() {
        // Set the initial value of the ComboBox
        textfield.setValue("+21620019190");

        // Add the desired phone numbers to the ComboBox
        List<String> nums = Arrays.asList("+21620019190");
        textfield.setItems(FXCollections.observableArrayList(nums));
    }
}
