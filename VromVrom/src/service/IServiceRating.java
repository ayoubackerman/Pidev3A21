/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;


public interface IServiceRating<Rating> {
     void ajouter(Rating t) throws SQLException;
    boolean delete(Rating t) throws SQLException;
    boolean update(Rating t) throws SQLException;
    public boolean search(Rating t) throws SQLException;
    public ObservableList<Rating> readAll() throws SQLException;
    
}
