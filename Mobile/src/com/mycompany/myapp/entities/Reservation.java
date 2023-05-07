/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author rihem
 */
public class Reservation {
    private Trajet idTrajet;
        private User idUser;
    private int idReservation;


    public Reservation() {
    }

    public Reservation(Trajet idTrajet, User idUser, int idReservation) {
        this.idTrajet = idTrajet;
        this.idUser = idUser;
        this.idReservation = idReservation;
    }

    public Trajet getIdTrajet() {
        return idTrajet;
    }

    public User getIdUser() {
        return idUser;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdTrajet(Trajet idTrajet) {
        this.idTrajet = idTrajet;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    @Override
    public String toString() {
        return "Reservation{" + "idTrajet=" + idTrajet + ", idUser=" + idUser + ", idReservation=" + idReservation + '}';
    }

   
 
}
