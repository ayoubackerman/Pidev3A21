/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.service;

import java.sql.Connection;
import java.sql.SQLException;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Reclamation t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Reclamation t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean search(Reclamation t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reclamation> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
