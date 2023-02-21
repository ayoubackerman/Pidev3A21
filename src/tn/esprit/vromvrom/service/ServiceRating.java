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
import tn.esprit.vromvrom.Model.Rating;

/**
 *
 * @author MediaCenter Zaghouan
 */
public class ServiceRating implements IServiceRating<Rating> {
      private Connection cnx;

    public ServiceRating(){
        cnx = Database.getInstance().getCnx();
    }

    @Override
    public void ajouter(Rating t) throws SQLException {
     Statement ste =  cnx.createStatement();
        
         String requeteInsert = "INSERT INTO `role` (id, `role`) VALUES (NULL, '" + t.getId()+"');";
        ste.executeUpdate(requeteInsert);    }

    @Override
    public boolean delete(Rating t) throws SQLException {
         Statement ste =  cnx.createStatement();
         if (search(t)==true){
            ste = cnx.createStatement();
            String requeteDelete ="DELETE FROM user WHERE id="+ t.getId();
            ste.executeUpdate(requeteDelete);
         }
         else{
           System.out.println("L'utulisateur n'existe pas");
        }
         return true;    
    }

    @Override
    public boolean update(Rating t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean search(Rating t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rating> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
