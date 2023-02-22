/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.Role;
import tn.esprit.vromvrom.Model.User;
import java.sql.*;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MediaCenter Zaghouan
 */
public class ServiceUser implements IServiceUser<User> {
     
    private Connection cnx;

    public ServiceUser(){
        cnx = Database.getInstance().getCnx();
    }

    @Override
    public void ajouter(User t) throws SQLException {
        
        Statement ste =  cnx.createStatement();
        
        String requeteInsert = "INSERT INTO `user` (id_user, id_role,`nom`, prenom,`mail`,`nomd`,`mdp`,`statuts`) VALUES (NULL, '" + t.getRole().getId()+"', '" + t.getNom() + "', '" + t.getPrenom() + "', '" + t.getMail()+"', '" + t.getNomd()+"', '" + t.getMdp()+"', '" + t.getStatut()+"' );";
        ste.executeUpdate(requeteInsert);
        System.out.println("Utilisateur ajouté");
    }
    

    @Override
    public boolean delete(User t) throws SQLException {
           if (search(t)==true){
         Statement ste =(Statement) cnx.createStatement();
         String requeteDelete ="DELETE FROM user WHERE id="+ t.getId();
         ste.executeUpdate(requeteDelete);}
         else{
            System.out.println("L'utulisateur n'existe pas");
        }
         return true;
    }

    @Override
    public boolean update(User t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean search(User t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<User> readAll() throws SQLException {
    ServiceRole r = new ServiceRole();
    ObservableList<User> arr = FXCollections.observableArrayList();
    Statement ste= cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from user");
     while (rs.next()) {                
               int id_user=rs.getInt(1);
               Role role = new Role();
               role.setId(rs.getInt(2));
               String nom=rs.getString(3);
               String prenom=rs.getString(4);
               String mail=rs.getString(5);
               String nomd=rs.getString(6);
               String mdp=rs.getString(7);
               String status = rs.getString(8);
               
               User p=new User(id_user,role,nom,prenom,mail,nomd,mdp,status);
     arr.add(p);
     }
    return arr;
    }

    @Override
    public User login(String email, String mdpasse, String numdu) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        
//         
//      User p=new User();
//      Role r=new Role();
//        String req=("select * from user WHERE mail=? or Nomd=? and mdp=? ");
//        PreparedStatement pre = cnx.prepareStatement(req);
//         pre.setString(1, email);
//         pre.setString(2, numdu);
//         pre.setString(3, mdpasse);
//        ResultSet rs = pre.executeQuery();
//        while (rs.next()) {
//            int id = rs.getInt(1);
//            Role role = rs.get(2);
//            String nom = rs.getString(3);
//            String prenom = rs.getString(4);
//            String mail = rs.getString(5);
//            String nomd = rs.getString(6);
//            String mdp = rs.getString(7);
//            
//            p = new User(id, role, nom, prenom, mail, nomd, mdp);
//            User.connecte=new User(id, role, nom, prenom, mail, nomd, mdp);
//        }
//        return p;
    }

    
}
