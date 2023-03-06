/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author user
 */
public class Reservation {
    private int id_reservation;
    private Trajet trajet;
    private User user;
//     private String nom;
// private String prenom;
// private String mail;
 
    public Reservation() {
    }

    public Reservation(int id_reservation, Trajet trajet, User user) {
        this.id_reservation = id_reservation;
        this.trajet = trajet;
        this.user = user;
    }

    public Reservation(Trajet trajet, User user) {
        this.trajet = trajet;
        this.user = user;
    }

   

   

   

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

  

//    public String getNom() {
//        return nom;
//    }
//
//    public void setNom(String nom) {
//        this.nom = nom;
//    }
//
//    public String getPrenom() {
//        return prenom;
//    }
//
//    public void setPrenom(String prenom) {
//        this.prenom = prenom;
//    }
//
//    public String getMail() {
//        return mail;
//    }
//
//    public void setMail(String mail) {
//        this.mail = mail;
//    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_reservation=" + id_reservation + ", trajet=" + trajet + ", user=" + user + '}';
    }

    
    

   
}
