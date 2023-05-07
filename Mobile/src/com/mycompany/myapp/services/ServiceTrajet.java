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
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author rihem
 */
public class ServiceTrajet {
    public ArrayList<Trajet> lists;
    public static ServiceTrajet instance ; 
    public boolean resultOK;
    private  ConnectionRequest req; 
 public static final String BASE_URL="http://127.0.0.1:8000/trajet/api";
 private ServiceTrajet() {
        req = new ConnectionRequest() ; 
         }
    /* Singleton patron de conception de creation */
    public static ServiceTrajet getInstance() {
        if (instance == null)
        {
            instance = new ServiceTrajet();
        }
         return instance;
    }
 
    
//    afficher
 public ArrayList<Trajet> parseEntitie(String jsonText){
        try {
            lists= new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> CategorieListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           List< Map<String,Object>> list =(List< Map<String,Object>>) CategorieListJson.get("root");
           for ( Map<String,Object> obj: list){
             Trajet c = new Trajet();
             float id = Float.parseFloat(obj.get("idTrajet").toString());
                          float nbrPlace = Float.parseFloat(obj.get("nbrPlace").toString());
                          float prix = Float.parseFloat(obj.get("nbrPlace").toString());
             c.setPrix((double)prix);
             c.setNbrPlace((int)nbrPlace);
             c.setIdTrajet((int)id);
             c.setVilleDarrive(obj.get("villeDepart").toString());
             c.setVilleDepart(obj.get("villeDarrive").toString());
             
             c.setModePaiment(obj.get("modePaiement").toString());
             c.setDate(obj.get("date").toString());
             
             lists.add(c);
         
        } }
           catch (IOException ex) {
//            Logger.getLogger(ServiceOeuvre.class.getName()).log(Level.SEVERE, null, ex);
             
        }
          return lists;
 }
     public ArrayList<Trajet> getAll(){
          String url = BASE_URL+"/trajetApi";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                lists = parseEntitie(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return lists;
    }
     
     
     //supprimerr
        public void Supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(BASE_URL+"/deleteTrajetApi/"+id);
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
        //ajouterr
       public boolean add (TextField villeDepart,TextField villeDarrive,TextField nbrPlace,TextField modePaiment,TextField prix,int idUser )
    { 
  
       String url = BASE_URL+"/addTrajetApi?villeDepart="+villeDepart.getText()+"&villeDarrive="+villeDarrive.getText()+"&nbrPlace="+nbrPlace.getText()+"&modePaiment="+modePaiment.getText()+"&prix="+prix.getText()+"&idUser="+idUser;
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
       //modifierrrr
       public boolean update (int id,TextField villeDepart,TextField villeDarrive,TextField nbrPlace,TextField modePaiment,TextField prix,int idUser)
    { 

       String url = BASE_URL+"/editTrajetApi/"+id+"?villeDepart="+villeDepart.getText()+"&villeDarrive="+villeDarrive.getText()+"&nbrPlace="+nbrPlace.getText()+"&modePaiment="+modePaiment.getText()+"&prix="+prix.getText()+"&idUser="+idUser;
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

