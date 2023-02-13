/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.Model;

import java.util.Objects;

/**
 *
 * @author MediaCenter Zaghouan
 */
public class urgence {
    private int id_urgence;
    private int id_trajet;
    private int id_voiture;
    private String localisation;
    private String description;
    private String status;
    private String temps;

    public urgence() {
    }

    public urgence(int id_urgence, int id_trajet, int id_voiture, String localisation, String description, String status, String temps) {
        this.id_urgence = id_urgence;
        this.id_trajet = id_trajet;
        this.id_voiture = id_voiture;
        this.localisation = localisation;
        this.description = description;
        this.status = status;
        this.temps = temps;
    }

    public int getId_urgence() {
        return id_urgence;
    }

    public void setId_urgence(int id_urgence) {
        this.id_urgence = id_urgence;
    }

    public int getId_trajet() {
        return id_trajet;
    }

    public void setId_trajet(int id_trajet) {
        this.id_trajet = id_trajet;
    }

    public int getId_voiture() {
        return id_voiture;
    }

    public void setId_voiture(int id_voiture) {
        this.id_voiture = id_voiture;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTemps() {
        return temps;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    @Override
    public String toString() {
        return "urgence{" + "id_urgence=" + id_urgence + ", id_trajet=" + id_trajet + ", id_voiture=" + id_voiture + ", localisation=" + localisation + ", description=" + description + ", status=" + status + ", temps=" + temps + '}';
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
        final urgence other = (urgence) obj;
        if (this.id_urgence != other.id_urgence) {
            return false;
        }
        if (this.id_trajet != other.id_trajet) {
            return false;
        }
        if (this.id_voiture != other.id_voiture) {
            return false;
        }
        if (!Objects.equals(this.localisation, other.localisation)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.temps, other.temps)) {
            return false;
        }
        return true;
    }
    
    
    
}
