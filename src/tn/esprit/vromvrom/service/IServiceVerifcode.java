/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.service;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author user
 */
public interface IServiceVerifcode<verifcode> {
    
       void ajouter(verifcode t) throws SQLException;
    boolean delete(verifcode t) throws SQLException;
    boolean update(verifcode t) throws SQLException;
    public boolean search(verifcode t) throws SQLException;
    
    public List<verifcode> readAll() throws SQLException;
    
}
