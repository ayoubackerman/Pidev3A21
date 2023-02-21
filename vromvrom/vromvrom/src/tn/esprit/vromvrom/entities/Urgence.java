package tn.esprit.vromvrom.entities;

import java.util.Objects;

public class Urgence {
    private int id_urgence;
    private int id_trajet;
    private int id_voiture;
    private String localisation;
    private String description;
    private String statuts;
    private String temps;


    public Urgence() {
    }

    public Urgence(int id_urgence, int id_trajet, int id_voiture, String localisation, String description, String status, String temps) {
        this.id_urgence = id_urgence;
        this.id_trajet = id_trajet;
        this.id_voiture = id_voiture;
        this.localisation = localisation;
        this.description = description;
        this.statuts = status;
        this.temps = temps;
    }

    public Urgence(int id_trajet, int id_voiture, String localisation, String description, String status, String temps) {
        this.id_trajet = id_trajet;
        this.id_voiture = id_voiture;
        this.localisation = localisation;
        this.description = description;
        this.statuts = status;
        this.temps = temps;
    }


    public int getId_urgence() {
        return id_urgence;
    }

    public void setId_urgence(int id_urgence) {
        this.id_urgence = id_urgence;
    }

    public int getId_Trajet() {
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
        return statuts;
    }

    public void setStatus(String status) {
        this.statuts = status;
    }

    public String getTemps() {
        return temps;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }


    @Override
    public String toString() {
        return "Urgence{" +
                "id_urgence=" + id_urgence +
                ", id_trajet=" + id_trajet +
                ", id_voiture=" + id_voiture +
                ", localisation='" + localisation + '\'' +
                ", description='" + description + '\'' +
                ", status='" + statuts + '\'' +
                ", temps='" + temps + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Urgence urgence = (Urgence) o;
        return id_urgence == urgence.id_urgence && id_trajet == urgence.id_trajet && id_voiture == urgence.id_voiture && Objects.equals(localisation, urgence.localisation) && Objects.equals(description, urgence.description) && Objects.equals(statuts, urgence.statuts) && Objects.equals(temps, urgence.temps);
    }


}
