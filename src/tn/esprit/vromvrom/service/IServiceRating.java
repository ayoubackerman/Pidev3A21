/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.service;

import java.sql.SQLException;
import java.util.List;


public interface IServiceRating<Rating> {
     void ajouter(Rating t) throws SQLException;
    boolean delete(Rating t) throws SQLException;
    boolean update(Rating t) throws SQLException;
    public boolean search(Rating t) throws SQLException;
    public List<Rating> readAll() throws SQLException;
    
}
