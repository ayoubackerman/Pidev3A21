/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author rihem
 */
public class Trajet {
     private int idTrajet;
     
     private int idUser;
      private String villeDepart;
      private String villeDarrive;
      private int nbrPlace;
      private String date;
      private String modePaiment;
      private double prix;

    public Trajet() {
    }

    public Trajet(int idTrajet, int idUser, String villeDepart, String villeDarrive, int nbrPlace, String date, String modePaiment, double prix) {
        this.idTrajet = idTrajet;
        this.idUser = idUser;
        this.villeDepart = villeDepart;
        this.villeDarrive = villeDarrive;
        this.nbrPlace = nbrPlace;
        this.date = date;
        this.modePaiment = modePaiment;
        this.prix = prix;
    }

    public int getIdTrajet() {
        return idTrajet;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public String getVilleDarrive() {
        return villeDarrive;
    }

    public int getNbrPlace() {
        return nbrPlace;
    }

    public String getDate() {
        return date;
    }

    public String getModePaiment() {
        return modePaiment;
    }

    public double getPrix() {
        return prix;
    }

    public void setIdTrajet(int idTrajet) {
        this.idTrajet = idTrajet;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public void setVilleDarrive(String villeDarrive) {
        this.villeDarrive = villeDarrive;
    }

    public void setNbrPlace(int nbrPlace) {
        this.nbrPlace = nbrPlace;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setModePaiment(String modePaiment) {
        this.modePaiment = modePaiment;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Trajet{" + "idTrajet=" + idTrajet + ", idUser=" + idUser + ", villeDepart=" + villeDepart + ", villeDarrive=" + villeDarrive + ", nbrPlace=" + nbrPlace + ", date=" + date + ", modePaiment=" + modePaiment + ", prix=" + prix + '}';
    }
    

     
}
