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
import tn.esprit.vromvrom.Model.urgence;

/**
 *
 * @author MediaCenter Zaghouan
 */
public class ServiceUrgence implements IServiceUrgence{
    
      private Connection cnx;

    public ServiceUrgence(){
        cnx = Database.getInstance().getCnx();
    }

    @Override
    public void ajouter(urgence t) throws SQLException {
   
       Statement ste =  cnx.createStatement();
        
         String requeteInsert = "INSERT INTO `urgence` (id_urgence, `id_trajet`,`id_voiture`,`localisation`,`description`,`statuts`,`temps`) VALUES (NULL, '" + t.getId_trajet()+"' , '" + t.getId_voiture()+"' ,'" + t.getLocalisation()+"' ,'" + t.getDescription()+"' ,'" + t.getStatus()+"' ,'" + t.getTemps()+"' );";
        ste.executeUpdate(requeteInsert);   
    }

    @Override
    public boolean delete(urgence t) throws SQLException {
               Statement ste =  cnx.createStatement();

        if (search(t)==true){
         ste = cnx.createStatement();
         String requeteDelete ="DELETE FROM urgence WHERE id="+ t.getId_urgence();
         ste.executeUpdate(requeteDelete);}
         else{
           System.out.println("L'utulisateur n'existe pas");
        }
         return true;
    }

    @Override
    public boolean update(urgence t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean search(urgence t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<urgence> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
