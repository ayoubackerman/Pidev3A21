/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.Role;

/**
 *
 * @author MediaCenter Zaghouan
 */
public class ServiceRole implements IServiceRole<Role> {
      private Connection cnx;

    public ServiceRole(){
        cnx = Database.getInstance().getCnx();
    }

    @Override
    public void ajouter(Role t) throws SQLException {
     Statement ste =  cnx.createStatement();
        
         String requeteInsert = "INSERT INTO `role` (id, `role`) VALUES (NULL, '" + t.getRole()+"');";
        ste.executeUpdate(requeteInsert);    }

    @Override
    public boolean delete(Role t) throws SQLException {
         Statement ste =  cnx.createStatement();
 if (search(t)==true){
         ste = cnx.createStatement();
         String requeteDelete ="DELETE FROM user WHERE id="+ t.getId();
         ste.executeUpdate(requeteDelete);}
         else{
           System.out.println("L'utulisateur n'existe pas");
        }
         return true;    }

    @Override
    public boolean update(Role t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean search(Role t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Role> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
