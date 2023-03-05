/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tn.esprit.vromvrom.Model.Role;
import tn.esprit.vromvrom.Model.User;
import static tn.esprit.vromvrom.SMS.ACCOUNT_SID;
import static tn.esprit.vromvrom.SMS.AUTH_TOKEN;
import tn.esprit.vromvrom.service.ServiceRole;
import tn.esprit.vromvrom.service.ServiceUser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
/**
 *
 * @author MediaCenter Zaghouan
 */
public class tryy extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     * 
     * 
     */
     public static final String ACCOUNT_SID = "AC16580562286174846b76b7f9005ce1e3";
    public static final String AUTH_TOKEN = "184056f276c12e358434ce1a1701349f";

    
    public static void main(String[] args) {
        
        
//        try {
//			// Construct data
//			String apiKey = "apikey=" + "NTgzMzRkNDc2ZjVhNDE0YzY1NmI2MjYyNTk1MTU5NTA=";
//			String message = "&message=" + "This is your message";
//			String sender = "&sender=" + "Jmal";
//			String numbers = "&numbers=" + "22560031";
//			
//			// Send data
//			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
//			String data = apiKey + numbers + message + sender;
//			conn.setDoOutput(true);
//			conn.setRequestMethod("POST");
//			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
//			conn.getOutputStream().write(data.getBytes("UTF-8"));
//			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			final StringBuffer stringBuffer = new StringBuffer();
//			String line;
//			while ((line = rd.readLine()) != null) {
//				stringBuffer.append(line);
//			}
//			rd.close();
//			
//		//	return stringBuffer.toString();
//                
//		} catch (Exception e) {
//			System.out.println("Error SMS "+e);
//		//	return "Error "+e;
//		}
////        
           Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21629277414"),
                new com.twilio.type.PhoneNumber("+15673131340"),
                " ....")
            .create();

        System.out.println(message.getSid());
        
//SMS s = new SMS();
//
//
//        ServiceRole r = new ServiceRole();
//        ServiceUser u = new ServiceUser();
//        
//        Role r1 = new Role(1,"dd");
//        User u1=new User("yoyo","ss","ss","ss","ss");
//        
//        //            u.ajouter(u1);
////            System.out.println(u.readAll());
//u.SelectUser(13);
//
////           System.out.println(u.login("ss", "s", "s"));
//        
//        launch(args);
    }
    
}
