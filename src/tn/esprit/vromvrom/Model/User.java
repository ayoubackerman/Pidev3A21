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
public class User {
    private int id;
    private Role role;
    private String nom;
    private String prenom;
    private String mail;


    private String nomd;
    private String mdp;
    private String statut;

    public User() {
    }
    
    public User(int id) {
        this.id = id;
    }

    public User(int id, Role role, String nom, String prenom, String mail, String nomd, String mdp, String status) {
        this.id = id;
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.nomd = nomd;
        this.mdp = mdp;
        this.statut = status;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", role=" + role + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", nomd=" + nomd + ", mdp=" + mdp + '}';
    }

    public int getId() {
        return id;
    }
    public String getStatut() {
        return statut;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public void setStatus(String statut) {
        this.statut = statut;
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
        return nomd;
    }

    public void setNomd(String nomd) {
        this.nomd = nomd;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public User(Role role, String nom, String prenom, String mail, String nomd, String mdp, String statut) {
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.nomd = nomd;
        this.mdp = mdp;
        this.statut = statut;
    }

   

   
}
