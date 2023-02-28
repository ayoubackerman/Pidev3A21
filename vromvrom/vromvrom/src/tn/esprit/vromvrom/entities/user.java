/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.entities;

import java.util.Objects;

/**
 *
 * @author USER
 */
public class user {
    private int id_user,id_role;
    private String nom,prenom,mail,Nomd,mdp,statuts;

    public user() {
    }

    public user(int id_user) {
        this.id_user = id_user;
    }

    public user(int id_user, int id_role, String nom, String prenom, String mail, String nomd, String mdp, String statuts) {
        this.id_user = id_user;
        this.id_role = id_role;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        Nomd = nomd;
        this.mdp = mdp;
        this.statuts = statuts;
    }

    public user(int id_role, String nom, String prenom, String mail, String nomd, String mdp, String statuts) {
        this.id_role = id_role;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        Nomd = nomd;
        this.mdp = mdp;
        this.statuts = statuts;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNomd() {
        return Nomd;
    }

    public void setNomd(String nomd) {
        Nomd = nomd;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getStatuts() {
        return statuts;
    }

    public void setStatuts(String statuts) {
        this.statuts = statuts;
    }

    @Override
    public String toString() {
        return "user{" +
                "id_user=" + id_user +
                ", id_role=" + id_role +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mail='" + mail + '\'' +
                ", Nomd='" + Nomd + '\'' +
                ", mdp='" + mdp + '\'' +
                ", statuts='" + statuts + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        user user = (user) o;
        return id_user == user.id_user && id_role == user.id_role && Objects.equals(nom, user.nom) && Objects.equals(prenom, user.prenom) && Objects.equals(mail, user.mail) && Objects.equals(Nomd, user.Nomd) && Objects.equals(mdp, user.mdp) && Objects.equals(statuts, user.statuts);
    }

}