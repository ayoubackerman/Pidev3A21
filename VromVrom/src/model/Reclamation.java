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
public class Reclamation {
    
    private int id_reclamation;
    private User id_user;
    private String reclamation ,type_reclamation ,resolution, temps;
    
    

    public Reclamation(int id_reclamation, User id_user, String reclamation, String type_reclamation, String resolution, String temps, String Reclamation ) {
        this.id_reclamation = id_reclamation;
        this.id_user = id_user;
        this.reclamation = reclamation;
        this.type_reclamation = type_reclamation;
        this.resolution = resolution;
        this.temps = temps;
        
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_reclamation=" + id_reclamation + ", id_user=" + id_user + ", reclamation=" + reclamation + ", type_reclamation=" + type_reclamation + ", resolution=" + resolution + ", temps=" + temps + '}';
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public void setReclamation(String reclamation) {
        this.reclamation = reclamation;
    }

    public void setType_reclamation(String type_reclamation) {
        this.type_reclamation = type_reclamation;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public User getId_user() {
        return id_user;
    }

    public String getReclamation() {
        return reclamation;
    }

    public String getType_reclamation() {
        return type_reclamation;
    }

    public String getResolution() {
        return resolution;
    }

    public String getTemps() {
        return temps;
    }
    
    
}
