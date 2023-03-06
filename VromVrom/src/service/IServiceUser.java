/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author MediaCenter Zaghouan
 */
public interface IServiceUser<User> {
    
      void ajouter(User t) throws SQLException;
    boolean delete(User t) throws SQLException;
    boolean update(User t) throws SQLException;
    public boolean search(User t) throws SQLException;
    public ObservableList<User> readAll() throws SQLException;
    public User login(String mail , String mdpasse,String numdu) throws SQLException;
    
}
