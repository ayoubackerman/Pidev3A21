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
    private int id_reponce;
    private Reclamation id_reclamation;
    private String reponse , status , temps;

    public Reponse(int id_reponce, Reclamation id_reclamation, String reponse, String status, String temps) {
        this.id_reponce = id_reponce;
        this.id_reclamation = id_reclamation;
        this.reponse = reponse;
        this.status = status;
        this.temps = temps;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id_reponce=" + id_reponce + ", id_reclamation=" + id_reclamation + ", reponse=" + reponse + ", status=" + status + ", temps=" + temps + '}';
    }

    public void setId_reponce(int id_reponce) {
        this.id_reponce = id_reponce;
    }

    public void setId_reclamation(Reclamation id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    public int getId_reponce() {
        return id_reponce;
    }

    public Reclamation getId_reclamation() {
        return id_reclamation;
    }

    public String getReponse() {
        return reponse;
    }

    public String getStatus() {
        return status;
    }

    public String getTemps() {
        return temps;
    }
    
    
    
    
}
