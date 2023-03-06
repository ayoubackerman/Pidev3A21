/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author MediaCenter Zaghouan
 */
public class Voiture {
    private int id;
    private User user;
    private String modele;
    private String marque;
    private int matricule;
    private String img;

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getModele() {
        return modele;
    }

    public String getMarque() {
        return marque;
    }

    public int getMatricule() {
        return matricule;
    }

    public String getImg() {
        return img;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Voiture{" + "id=" + id + ", id_user=" + user.getId_user() + ", modele=" + modele + ", marque=" + marque + ", matricule=" + matricule + ", img=" + img + '}';
    }
    
    public Voiture(int id, User user, String modele, String marque, int matricule) {
        this.id = id;
        this.user = user;
        this.modele = modele;
        this.marque = marque;
        this.matricule = matricule;
    }

    public Voiture(User user, String modele, String marque, int matricule, String img) {
        this.user = user;
        this.modele = modele;
        this.marque = marque;
        this.matricule = matricule;
        this.img = img;
    }

    public Voiture(User user, String modele, String marque, int matricule) {
        this.user = user;
        this.modele = modele;
        this.marque = marque;
        this.matricule = matricule;
        this.img = "";
    }
    
    public Voiture() {
        this.user = null;
        this.modele = "";
        this.marque = "";
        this.matricule = 0;
        this.img = "";
    }
    
    
}
