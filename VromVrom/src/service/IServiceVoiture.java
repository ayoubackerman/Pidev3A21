/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.SQLException;
import java.util.List;
import model.Voiture;

/**
 *
 * @author mehdi
 */
public interface IServiceVoiture{
    void ajouter(Voiture t) throws SQLException;
    boolean delete(Voiture t) throws SQLException;
    boolean update(Voiture t) throws SQLException;
    public boolean search(Voiture t) throws SQLException;
    public List<Voiture> readAll() throws SQLException;
}
