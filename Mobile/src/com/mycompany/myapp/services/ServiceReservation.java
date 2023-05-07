/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Trajet;
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rihem
 */
public class ServiceReservation {
     public ArrayList<Reservation> reservations;
    public static ServiceReservation instance ; 
    public boolean resultOK;
    private  ConnectionRequest req; 
 public static final String BASE_URL="http://127.0.0.1:8000/reservation/api";
 private ServiceReservation() {
        req = new ConnectionRequest() ; 
         }
    
    public static ServiceReservation getInstance() {
        if (instance == null)
        {
            instance = new ServiceReservation();
        }
         return instance;
    }
 
 public ArrayList<Reservation> parseEntitie(String jsonText){
        try {
            reservations= new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> ProduitListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           List< Map<String,Object>> list =(List< Map<String,Object>>) ProduitListJson.get("root");
           for ( Map<String,Object> obj: list){
             Reservation p = new Reservation();
                     Map<String,Object> idUserObj = (Map<String,Object>) obj.get("idUser");
                     Map<String,Object> idTrajetObj = (Map<String,Object>) obj.get("idTrajet");

            // float idTrajet = Float.parseFloat(obj.get("idTrajet").toString());
float idReservation = Float.parseFloat(obj.get("idReservation").toString());
p.setIdReservation((int)idReservation);
          //   p.setIdReservation((int)idReservation);
          /* SET USER */   

          User u = new User();
               float id = Float.parseFloat(idUserObj.get("idUser").toString());
              
             u.setIdUser((int)id);
             u.setNom(idUserObj.get("nom").toString());
             u.setPrenom(idUserObj.get("prenom").toString());
             
             u.setNomd(idUserObj.get("nomd").toString());
             u.setMail(idUserObj.get("mail").toString());
             u.setStatuts(idUserObj.get("statuts").toString());
             u.setNum(idUserObj.get("num").toString());
               p.setIdUser(u);
               /* End Set User */
               /*SET Trajet*/
                         float idTrajet = Float.parseFloat(idTrajetObj.get("idTrajet").toString());
                         Trajet t = new Trajet();
                          float nbrPlace = Float.parseFloat(idTrajetObj.get("nbrPlace").toString());
                          float prix = Float.parseFloat(idTrajetObj.get("nbrPlace").toString());
             t.setPrix((double)prix);
             t.setNbrPlace((int)nbrPlace);
             t.setIdTrajet((int)idTrajet);
             t.setVilleDarrive(idTrajetObj.get("villeDepart").toString());
             t.setVilleDepart(idTrajetObj.get("villeDarrive").toString());
             
             t.setModePaiment(idTrajetObj.get("modePaiement").toString());
             t.setDate(idTrajetObj.get("date").toString());
             p.setIdTrajet(t);
             /* End Set Trajet */
           
             reservations.add(p);
         
        } }
           catch (IOException ex) {
//            Logger.getLogger(ServiceOeuvre.class.getName()).log(Level.SEVERE, null, ex);
             
        }
          return reservations;
 }
     public ArrayList<Reservation> getAll(){
          String url = BASE_URL+"/reservationsApi";
        req.setUrl(url);
        req.setPost(false);

                System.out.println(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reservations = parseEntitie(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservations;
    }
        public void Supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(BASE_URL+"/deleteReservation/"+id);
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
       public boolean add (int idUser,int idTrajet)
    { 

       String url = BASE_URL+"/addReservation?idUser="+idUser+"&idTrajet="+idTrajet;
       req.setUrl(url);
       req.addResponseListener(new ActionListener<NetworkEvent>(){ 
           @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
             }
    });
        System.out.println(""+resultOK);
       NetworkManager.getInstance().addToQueue(req);
        return resultOK;
    }
           public boolean update (int idUser,int idTrajet,int id)
    { 

       String url = BASE_URL+"/editReservation/"+id+"?idUser="+idUser+"&idTrajet="+idTrajet;;
       req.setUrl(url);
       req.addResponseListener(new ActionListener<NetworkEvent>(){ 
           @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
             }
    });
        System.out.println(""+resultOK);
       NetworkManager.getInstance().addToQueue(req);
        return resultOK;
    }
}
