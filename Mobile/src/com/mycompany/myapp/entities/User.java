/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Wael
 */
public class User {
    private int idUser;
    private String nom;
    private String prenom;
    private String mail;
    private String nomd;
    private String mdp;
    private String statuts;
    private String num;

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNomd(String nomd) {
        this.nomd = nomd;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setStatuts(String statuts) {
        this.statuts = statuts;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getIdUser() {
        return idUser;
    }

    public User(int idUser, String nom, String prenom, String mail, String nomd, String mdp, String statuts, String num) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.nomd = nomd;
        this.mdp = mdp;
        this.statuts = statuts;
        this.num = num;
    }

    public User() {
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getNomd() {
        return nomd;
    }

    public String getMdp() {
        return mdp;
    }

    public String getStatuts() {
        return statuts;
    }

    public String getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", nomd=" + nomd + ", mdp=" + mdp + ", statuts=" + statuts + ", num=" + num + '}';
    }
    
}
