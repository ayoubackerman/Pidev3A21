/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Trajet;
import com.mycompany.myapp.entities.User;
import static com.mycompany.myapp.services.ServiceTrajet.BASE_URL;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Wael
 */
public class ServiceUser {
        public ArrayList<User> lists;
    public static ServiceUser instance ; 
    public boolean resultOK;
    private  ConnectionRequest req; 
 public static final String BASE_URL="http://127.0.0.1:8000/trajet/api";
 private ServiceUser() {
        req = new ConnectionRequest() ; 
         }
    /* Singleton patron de conception de creation */
    public static ServiceUser getInstance() {
        if (instance == null)
        {
            instance = new ServiceUser();
        }
         return instance;
    }
    
     public ArrayList<User> parseEntitie(String jsonText){
        try {
            lists= new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> CategorieListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           List< Map<String,Object>> list =(List< Map<String,Object>>) CategorieListJson.get("root");
           for ( Map<String,Object> obj: list){
             User c = new User();
             float id = Float.parseFloat(obj.get("idUser").toString());
              
             c.setIdUser((int)id);
             c.setNom(obj.get("nom").toString());
             c.setPrenom(obj.get("prenom").toString());
             
             c.setNomd(obj.get("nomd").toString());
             c.setMail(obj.get("mail").toString());
             c.setStatuts(obj.get("statuts").toString());
             c.setNum(obj.get("num").toString());
               System.out.println(c);
             lists.add(c);
         
        } }
           catch (IOException ex) {
//            Logger.getLogger(ServiceOeuvre.class.getName()).log(Level.SEVERE, null, ex);
             
        }
          return lists;
 }
     
          public ArrayList<User> getAll(){
          String url = BASE_URL+"/usersApi";
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
}
