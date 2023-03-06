/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author MediaCenter Zaghouan
 */
public class voiture_urgence {
    private int id;
    private String modele;
    private String marque;
    private String matricule;
    private int nbPlace;
    private int status;
    private String image;

    public voiture_urgence() {
    }

    public voiture_urgence(int id, String modele, String marque, String matricule, int nbPlace, int status, String image) {
        this.id = id;
        this.modele = modele;
        this.marque = marque;
        this.matricule = matricule;
        this.nbPlace = nbPlace;
        this.status = status;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "voiture_urgence{" + "id=" + id + ", modele=" + modele + ", marque=" + marque + ", matricule=" + matricule + ", nbPlace=" + nbPlace + ", status=" + status + ", image=" + image + '}';
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
        final voiture_urgence other = (voiture_urgence) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.nbPlace != other.nbPlace) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.modele, other.modele)) {
            return false;
        }
        if (!Objects.equals(this.marque, other.marque)) {
            return false;
        }
        if (!Objects.equals(this.matricule, other.matricule)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
}
