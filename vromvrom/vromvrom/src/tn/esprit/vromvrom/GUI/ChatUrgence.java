package tn.esprit.vromvrom.GUI;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import tn.esprit.vromvrom.entities.Conversation;
import tn.esprit.vromvrom.entities.Messages;
import tn.esprit.vromvrom.entities.role;
import tn.esprit.vromvrom.entities.user;
import tn.esprit.vromvrom.services.ServiceMessages;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ChatUrgence implements Initializable {

    @FXML
    private ListView<String> chatHistory ;
    @FXML
    private TextArea messageInput ;

    @FXML
    private Button sendButton ;

    // je doit la modifier lors de l'integration du travaille

    //l'admin
    user user1 = new user(2,"rzem","mohamed","med@gmail.com","meddooo","12345678","hihihi");
    //le chauffeur
    user user2 = new user(1,"ali","ali","@ali","alilouuuu","918273645","disp");

    role role1 = new role(1,"chauffeur");
    role role2 = new role(2,"admin");
    String nom = "alilouuuu";

    ServiceMessages serviceMessages =new ServiceMessages();
    List<Messages> l = serviceMessages.selectAll();

    Conversation conversation = new Conversation(user1,user2);

    public ChatUrgence() throws SQLException {
    }



    public boolean verif_Existance_Conver(){
        for (int i =0;i<l.size();i++){
            if (l.get(i).getConversation().getUtilisateur1().getNomd().equals(this.nom) && l.get(i).getConversation().getHeureFin() == null){
                return true;
            }
        }
        return false;
    }


    @FXML
        private void Envoyer () throws SQLException {
            String message = messageInput.getText().trim();
            if (!message.isEmpty()) {
                // Send message to chat server
                chatHistory.getItems().add(message);
                messageInput.clear();

                boolean verif = verif_Existance_Conver();
                if (verif==true){
                    for (int i =0;i<l.size();i++){
                        if (l.get(i).getConversation().getUtilisateur1().getNomd().equals(this.nom) && l.get(i).getConversation().getHeureFin() == null){
                            Messages messages = new Messages(l.get(i).getConversation(),l.get(i).getExpediteur(),l.get(i).getDestinataire(),message);
                            serviceMessages.createOne(messages);
                            break;
                        }
                    }
                }else {
                    System.out.println("aaaaabbbbb");
                }







            }
        };




    //l.get(i).getConversation().getUtilisateur1().getNomd().equals(nom) &&
    private void afficherMessage(){
            for (int i = 0; i < l.size(); i++) {
                if (l.get(i).getConversation().getUtilisateur1().getNomd().equals(this.nom) && l.get(i).getConversation().getHeureFin() == null) {

                    for (int j = 0; j < l.size(); j++) {
                        if (l.get(i).getConversation().getId() == l.get(j).getConversation().getId()) {
                            chatHistory.getItems().add(l.get(j).getExpediteur() + "\t\t\t\t" + l.get(j).getTexte() + "\t\t\t\t\t\t\t\t\t\t" + l.get(j).getHeureEnvoi());
                        }
                    }
                    break;
                }
            }
    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(l);
        System.out.println(l.get(2).getId());
        afficherMessage();
        //System.out.println(l.get(0).getConversation().getUtilisateur1().getNomd());


    }
}







