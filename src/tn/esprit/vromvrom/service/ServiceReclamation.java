/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.Reclamation;


/**
 *
 * @author MediaCenter Zaghouan
 */
public class ServiceReclamation implements IServiceReclamation<Reclamation>{
    
     private Connection cnx;

    public ServiceReclamation(){
        cnx = Database.getInstance().getCnx();
    }

    @Override
    public void ajouter(Reclamation t) throws SQLException {
       Statement ste =  cnx.createStatement();
        
         String requeteInsert = "INSERT INTO `reclamation`( `id_user`, `reclamation`, `resolution`, `typre_reclamation`) VALUES ( '" + t.getId_user()+"' , '" + t.getReclamation()+ "' , '" + t.getResolution()+ "' , '" + t.getType_reclamation()+ "');";
        ste.executeUpdate(requeteInsert);    
    }

    @Override
    public boolean delete(Reclamation t) throws SQLException {
        Statement ste =  cnx.createStatement();
 if (search(t)==true){
         ste = cnx.createStatement();
         String requeteDelete ="DELETE FROM reclamation WHERE id_reclamation="+ t.getId_reclamation();
         ste.executeUpdate(requeteDelete);}
         else{
           System.out.println("L'utulisateur n'existe pas");
        }
         return true;    }

     @Override
    public boolean search(Reclamation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public boolean update(Reclamation t) throws SQLException {
         try {
            Statement ste =  cnx.createStatement();
            String requete = "UPDATE `reclamation` SET reclamation=? , resolution=?  , typre_reclamation=? "
                    + "WHERE id_reclamation= ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getReclamation());
            pst.setString(2, t.getResolution());
            pst.setString(3, t.getType_reclamation());
          
            pst.executeUpdate();
            System.out.println("Votre reclamation est bien modifie !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return true;
    }
    
    @Override
    public List<Reclamation> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
