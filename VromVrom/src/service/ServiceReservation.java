/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.Reservation;
import database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Role;
import model.Trajet;
import model.User;

/**
 *
 * @author user
 */
public class ServiceReservation implements IService<Reservation>{
  private final Connection cnx;
    private Object spR;

    public ServiceReservation(){
        cnx = Database.getInstance().getCnx();
    }
    @Override
    public void createOne(Reservation r) throws SQLException {
       String req = "INSERT INTO reservation (id_reservation, id_trajet, id_user) " +
                " VALUES (?, ?, ?)";
        //System.out.println(req);
        PreparedStatement st = cnx.prepareStatement(req);
         st.setInt(1, r.getId_reservation());
        st.setInt(2, r.getUser().getId_user());
        st.setInt(3, r.getTrajet().getId_trajet());
      


        st.executeUpdate();
        System.out.println("Urgence ajouté !");
    }
    @Override
    public void updateOne(Reservation R) throws SQLException {
        
        String req = "UPDATE reservation SET id_reservation = ?, id_trajet = ?, id_user = ? WHERE id_reservation = ?";

        System.out.println(req);

        PreparedStatement st = cnx.prepareStatement(req);
       
        st.setInt(1,R.getId_reservation());
         st.setInt(2,R.getTrajet().getId_trajet());
        st.setInt(3,R.getUser().getId_user());
  int rowsUpdated = st.executeUpdate();
        System.out.println(rowsUpdated + " lignes ont été mises à jour.");
    }
    

    @Override
    public void deletOne(Reservation reservation) throws SQLException {
        try {
            String req = "DELETE FROM reservation WHERE reservation.`id_reservation` = ?";
            PreparedStatement stR = cnx.prepareStatement(req);
            stR.setInt(1, reservation.getId_reservation());
               stR.executeUpdate();
            System.out.println("reservation supprimé");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceTrajet.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

 
   

    /**
     *
     * @return
     * @throws SQLException
     */
   
  @Override
    public ObservableList<Reservation> selectAll() throws SQLException {
        ObservableList<Reservation> temp = FXCollections.observableArrayList();

        String req = "SELECT *" +
                "FROM reservation" +
                " JOIN trajet ON reservation.id_trajet = trajet.id_trajet" +
                " JOIN user ON reservation.id_user = user.id_user";
        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){
  Reservation re = new Reservation();
        re.setId_reservation(rs.getInt("id_reservation"));
        User u = new User();
        u.setId_user(rs.getInt("id_user"));
         
 Trajet t = new Trajet(rs.getString(4), rs.getString(5), rs.getFloat(6), rs.getInt(7), u, rs.getInt(9), rs.getString(10), rs.getString(11));
            System.out.println(t);
            re.setTrajet(t);
            Role role = new Role();
            role.setId_role(rs.getInt(13));
            User us = new User(rs.getInt(12),role,rs.getString(14),rs.getString(15),rs.getString(16),rs.getString(17),rs.getString(18));
            re.setUser(us);
            re.setUser(us);
          temp.add(re);

            }
     return temp;
    
    
}
}

    

    
      

