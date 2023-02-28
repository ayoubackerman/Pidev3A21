/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.tests;

/**
 *
 * @author USER
 */



 import tn.esprit.vromvrom.entities.*;
 import tn.esprit.vromvrom.services.ServiceConversation;
 import tn.esprit.vromvrom.services.ServiceMessages;
 import tn.esprit.vromvrom.services.ServiceUrgence;
 import tn.esprit.vromvrom.services.ServiceVoiture_urgence;


 import java.sql.SQLException;
 import java.time.LocalDateTime;

public class Vromvrom {

    public static void main(String[] args) {
        
   //  tn.esprit.vromvrom.utils.MaConnexion cn1 = tn.esprit.vromvrom.utils.MaConnexion.getInstance();
//        tn.esprit.vromvrom.utils.MaConnexion cn2 = tn.esprit.vromvrom.utils.MaConnexion.getInstance();
//        tn.esprit.vromvrom.utils.MaConnexion cn3 = tn.esprit.vromvrom.utils.MaConnexion.getInstance();
//        tn.esprit.vromvrom.utils.MaConnexion cn4 = tn.esprit.vromvrom.utils.MaConnexion.getInstance();
//
//   //   System.out.println(cn1.hashCode());
//        System.out.println(cn2.hashCode());
//        System.out.println(cn3.hashCode());
//        System.out.println(cn4.hashCode());


        
      // Participation pp = new Participation(1, "14");
       // Evenement pt = new Evenement(1, "sarra","bf");
        // Ticket t = new Ticket(2, 145,,0);

        //Urgence ur = new Urgence(1,1,"ddddd","fff","ttttt","oooooo");
        //Urgence ur2 = new Urgence(1,1,"bizerte","belle ville","occupé","2H");
        //Urgence ur3 = new Urgence(1,1,"tunis","centre ville","occupé","1H");
        Voiture_urgence vu1 = new Voiture_urgence(4,1,"X5","bmw","TUN233","imm");
        Voiture_urgence vu2 = new Voiture_urgence(5,1,"benz","mercedes","TUN999","imm");
        vu1.setId_voiture(2);
        ServiceUrgence sp = new ServiceUrgence();
        ServiceVoiture_urgence vu = new ServiceVoiture_urgence();
        //ServiceEvent sv = new ServiceEvent();
          //ServiceTicket st = new ServiceTicket();
        user user1 = new user(13);
        user user2 = new user(14);

        Conversation conversation = new Conversation(user1,user2);
        Conversation conversation2 = new Conversation(1);
        ServiceConversation serviceConversation = new ServiceConversation();

        Messages m = new Messages(conversation2,"ali","admin","help please");
        LocalDateTime heureEnvoi = LocalDateTime.of(2023, 2, 27, 01, 58, 48);
        m.setHeureEnvoi(heureEnvoi);
        ServiceMessages serviceMessages = new ServiceMessages();

        try {

            //sp.createOne(ur2);
           // System.out.println(sp.selectAll());
             //sp.createOne(p5);
            // sp.updateOne(ur3,10);
            //vu.updateOne(vu2,1);
            //vu.deletOne(vu1);
            // sp.deletOne(ur2);
            // sv.createOne(pt);
            // sp.updateOne(p);
            //serviceConversation.createOne(conversation);
            //serviceConversation.updateOne(conversation2);
            //System.out.println(serviceConversation.selectAll());
            //serviceMessages.createOne(m);
            //serviceMessages.deletOne(m);
            System.out.println(serviceMessages.selectAll());

            //vu.createOne(vu2);
           
            //System.out.println(vu.selectAll());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

}
