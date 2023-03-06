/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.SQLException;
import java.util.List;
import model.urgence;

/**
 *
 * @author MediaCenter Zaghouan
 */
public interface IServiceUrgence {
    void ajouter(urgence t) throws SQLException;
    boolean delete(urgence t) throws SQLException;
    boolean update(urgence t) throws SQLException;
    public boolean search(urgence t) throws SQLException;
    public List<urgence> readAll() throws SQLException;
}
