/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.Model;

/**
 *
 * @author MediaCenter Zaghouan
 */
public class Reponse {
    private int id_reponse;
    private int id_reclamation,id_user;
    private String reponse , temps ,nom,prenom;

    public Reponse() {
        
    }

    public Reponse(int id_reponse, int id_reclamation, int id_user, String reponse, String temps, String nom, String prenom) {
        this.id_reponse = id_reponse;
        this.id_reclamation = id_reclamation;
        this.id_user = id_user;
        this.reponse = reponse;
        this.temps = temps;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Reponse(int id_reponse, int id_reclamation, int id_user, String reponse) {
        this.id_reponse = id_reponse;
        this.id_reclamation = id_reclamation;
        this.id_user = id_user;
        this.reponse = reponse;
    }

    public Reponse(int id_reponse, int id_reclamation, int id_user, String reponse, String temps) {
        this.id_reponse = id_reponse;
        this.id_reclamation = id_reclamation;
        this.id_user = id_user;
        this.reponse = reponse;
        this.temps = temps;
    }




    public int getId_reponse() {
        return id_reponse;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public int getId_user() {
        return id_user;
    }

    public String getReponse() {
        return reponse;
    }

   
    public String getTemps() {
        return temps;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setId_reponse(int id_reponse) {
        this.id_reponse = id_reponse;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id_reponse=" + id_reponse + ", id_reclamation=" + id_reclamation + ", id_user=" + id_user + ", reponse=" + reponse + ", temps=" + temps +  '}';
    }

   

  

  
   
 
    
}
