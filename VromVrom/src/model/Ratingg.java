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
public class Ratingg {
    private int id;
    private User user;
    private double etoiles;
    private String commentaire;
    private User user2;

    public Ratingg() {
        id = 0;
        user = null;
        etoiles = 0;
        commentaire="";
    }

    public int getId() {
        return id;
    }
    
    public User getUser2(){
        return user2;
    }

    public User getUser() {
        return user;
    }

    public double getEtoiles() {
        return etoiles;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser2(User user){
        this.user2 = user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public void setEtoiles(double etoiles) {
        this.etoiles = etoiles;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Ratingg(User user, double etoiles, String commentaire, User user2) {
        this.user = user;
        this.etoiles = etoiles;
        this.commentaire = commentaire;
        this.user2 = user2;
    }

    @Override
    public String toString() {
        return "Rating{" + "id=" + id + ", id_user=" + user + ", etoiles=" + etoiles + ", commentaire=" + commentaire + '}';
    }
    
    
}
