/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author user
 */
public class Trajet {
            private String Ville_depart;
            private String Ville_darrive;
            private float Prix;
            private int Nbr_place;
            private User user;
            private int Id_trajet;
            
            private String Date;
             private String  Mode_paiement;

    public Trajet() {
    }

    public Trajet(String Ville_depart, String Ville_darrive, float Prix, int Nbr_place, User user, int Id_trajet, String Date, String Mode_paiement) {
        this.Ville_depart = Ville_depart;
        this.Ville_darrive = Ville_darrive;
        this.Prix = Prix;
        this.Nbr_place = Nbr_place;
        this.user = user;
        this.Id_trajet = Id_trajet;
        this.Date = Date;
        this.Mode_paiement = Mode_paiement;
    }

    public Trajet(String Ville_depart, String Ville_darrive, float Prix, int Nbr_place, int Id_trajet, String Date, String Mode_paiement) {
        this.Ville_depart = Ville_depart;
        this.Ville_darrive = Ville_darrive;
        this.Prix = Prix;
        this.Nbr_place = Nbr_place;
        this.Id_trajet = Id_trajet;
        this.Date = Date;
        this.Mode_paiement = Mode_paiement;
    }

    public Trajet(String Ville_depart, String Ville_darrive, float Prix, int Nbr_place, String Date, String Mode_paiement) {
        this.Ville_depart = Ville_depart;
        this.Ville_darrive = Ville_darrive;
        this.Prix = Prix;
        this.Nbr_place = Nbr_place;
        this.Date = Date;
        this.Mode_paiement = Mode_paiement;
    }

    public Trajet(String Ville_depart, String Ville_darrive, float Prix) {
        this.Ville_depart = Ville_depart;
        this.Ville_darrive = Ville_darrive;
        this.Prix = Prix;
    }
    
    
//     public trajet(String Ville_depart, String Ville_darrive, float Prix, int Nbr_place, user user, String Date, String Mode_paiement) {
//        this.Ville_depart = Ville_depart;
//        this.Ville_darrive = Ville_darrive;
//        this.Prix = Prix;
//        this.Nbr_place = Nbr_place;
//        this.user = user;
//  
//        this.Date = Date;
//        this.Mode_paiement = Mode_paiement;
//    }
public Trajet(String Ville_depart, String Ville_darrive, float prix, int Nbr_place, User user, String Date, String Mode_paiement) {
        this.Ville_depart = Ville_depart;
        this.Ville_darrive = Ville_darrive;
        this.Prix = prix;
        this.Nbr_place = Nbr_place;
        this.user = user;
        this.Date = Date;
        this.Mode_paiement = Mode_paiement;
    }

    public Trajet(int Id_trajet) {
        this.Id_trajet = Id_trajet;
    }

    public Trajet(String Ville_depart, String Ville_darrive, int Nbr_place, int Id_trajet) {
        this.Ville_depart = Ville_depart;
        this.Ville_darrive = Ville_darrive;
        this.Nbr_place = Nbr_place;
        this.Id_trajet = Id_trajet;
    }



  
   

   

   
             
             
             
             
    public String getVille_depart() {
        return Ville_depart;
    }

    public void setVille_depart(String Ville_depart) {
        this.Ville_depart = Ville_depart;
    }

    public String getVille_darrive() {
        return Ville_darrive;
    }

    public void setVille_darrive(String Ville_darrive) {
        this.Ville_darrive = Ville_darrive;
    }

    public float getPrix() {
        return Prix;
    }

    public void setPrix(float Prix) {
        this.Prix = Prix;
    }

    public int getNbr_place() {
        return Nbr_place;
    }

    public void setNbr_place(int Nbr_place) {
        this.Nbr_place = Nbr_place;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trajet(String Ville_depart, String Ville_darrive, int Nbr_place) {
        this.Ville_depart = Ville_depart;
        this.Ville_darrive = Ville_darrive;
        this.Nbr_place = Nbr_place;
    }


   

    public int getId_trajet() {
        return Id_trajet;
    }

    public void setId_trajet(int Id_trajet) {
        this.Id_trajet = Id_trajet;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }


    public String getMode_paiement() {
        return Mode_paiement;
    }

    public void setMode_paiement(String Mode_paiement) {
        this.Mode_paiement = Mode_paiement;
    }

    @Override
    public String toString() {
        return "trajet{" + "Ville_depart=" + Ville_depart + ", Ville_darrive=" + Ville_darrive + ", Prix=" + Prix + ", Nbr_place=" + Nbr_place + ", user=" + user + ", Id_trajet=" + Id_trajet + ", Date=" + Date + ", Mode_paiement=" + Mode_paiement + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trajet other = (Trajet) obj;
        if (Float.floatToIntBits(this.Prix) != Float.floatToIntBits(other.Prix)) {
            return false;
        }
        if (this.Nbr_place != other.Nbr_place) {
            return false;
        }
        if (this.Id_trajet != other.Id_trajet) {
            return false;
        }
        if (!Objects.equals(this.Ville_depart, other.Ville_depart)) {
            return false;
        }
        if (!Objects.equals(this.Ville_darrive, other.Ville_darrive)) {
            return false;
        }
        if (!Objects.equals(this.Date, other.Date)) {
            return false;
        }
        if (!Objects.equals(this.Mode_paiement, other.Mode_paiement)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

   

    

//    public String getDtae() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public String getDtae() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
   
  
}
