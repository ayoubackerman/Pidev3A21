/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.Trajet;
import database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Role;
import model.User;
import model.Trajet;


/**
 *
 * @author USER
 */

public class ServiceTrajet implements IService<Trajet> {
    private Connection cnx;
    private Object sp;

    public ServiceTrajet(){
        cnx = Database.getInstance().getCnx();
    }

    @Override
    public void createOne(Trajet t) throws SQLException {
//      String req =   "INSERT INTO trajet(ville_depart, ville_darrive, prix, nbr_place,id_user,id_trajet,Date, mode_paiement)" + " VALUES (?, ?, ?, ?,  ?,?,?, ?)";
////        //System.out.println(req);
//lic void createOne(Urgence urgence) throws SQLException {
       String req = "INSERT INTO trajet (ville_depart, ville_darrive, prix, nbr_place, id_user, id_trajet, Date, mode_paiement) " +
             "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
PreparedStatement st = cnx.prepareStatement(req);

st.setString(1, t.getVille_depart());
st.setString(2, t.getVille_darrive());
st.setFloat(3, t.getPrix());
st.setInt(4, t.getNbr_place());
st.setInt(5, t.getUser().getId_user());
st.setInt(6, t.getId_trajet());
st.setString(7, t.getDate());
st.setString(8, t.getMode_paiement());

int nb = st.executeUpdate();
if (nb > 0) {
    System.out.println("Trajet ajouté !");
} else {
    System.out.println("Erreur lors de l'ajout du trajet.");
}
    }
   @Override
    public void updateOne(Trajet t) throws SQLException {
      

  
        String req =  " UPDATE trajet SET ville_depart=?,ville_darrive=?,prix=?,nbr_place=?,Date=?,mode_paiement=? WHERE id_trajet='"+ t.getId_trajet() +"' ; ";
     
            PreparedStatement st = cnx.prepareStatement(req);

            st.setString(1, t.getVille_depart());
            st.setString(2, t.getVille_darrive());
                st.setFloat(3, t.getPrix());
                 st.setFloat(4, t.getNbr_place());
//                st.setInt(5,t.getUser().getId_user());
                st.setString(5, t.getDate());
                st.setString(6, t.getMode_paiement());
                
           
            st.executeUpdate();
//            System.out.println("participants number of event " + planning.getNomActivite() + " is updated successfully");
    }

//    public void deleteOne1(int id_trajet) throws SQLException {
//    String req = "DELETE FROM trajet WHERE id_trajet = ?";
//    PreparedStatement st = cnx.prepareStatement(req);
//    st.setInt(1, id_trajet);
//    st.executeUpdate();
//    System.out.println("trajet supprimé");
//}
    
   public void deleteOne1(int idt) throws SQLException {
    String req = "DELETE FROM trajet WHERE id_trajet = ?";
    PreparedStatement st = cnx.prepareStatement(req);
    st.setInt(1, idt);
    st.executeUpdate();
    System.out.println("trajet supprimé");
}
   
    @Override
    public void deletOne(Trajet trajet) throws SQLException {
 
try {
            String req = "DELETE FROM `trajet` WHERE id_trajet = '" +trajet.getId_trajet();
            PreparedStatement ste = cnx.prepareStatement(req);
            ste.setInt(1, trajet.getId_trajet());
            ste.executeUpdate();
            System.out.println("trajet supprimé");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceTrajet.class.getName()).log(Level.SEVERE, null, ex);
        }    }

         
        public void delet(int id) throws SQLException {
 
try {
            String req = "DELETE FROM `trajet` WHERE id_trajet = '" + id + "' ;";
            PreparedStatement ste = cnx.prepareStatement(req);
            
            ste.executeUpdate();
            System.out.println("trajet supprimé");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceTrajet.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    
     
  

    @Override
    public ObservableList<Trajet> selectAll() throws SQLException {
        ObservableList<Trajet> temp = FXCollections.observableArrayList();

//        String req = "SELECT * FROM `trajet`";
 String req = "SELECT *" +
                "FROM trajet" +
                " JOIN user ON trajet.id_user = user.id_user where nbr_place>0"  ;
       PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){
  Trajet tr = new Trajet();

  
   
        tr.setVille_depart(rs.getString("ville_depart"));
           tr.setVille_darrive(rs.getString("ville_darrive"));
            tr.setPrix(rs.getInt("prix"));
           tr.setNbr_place(rs.getInt("nbr_place"));
            Role role = new Role();
            role.setId_role(rs.getInt(10));
            User u = new User(rs.getInt(9),role,rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15));
            System.out.println(u);
            tr.setUser(u);
             tr.setId_trajet(rs.getInt("id_trajet"));
            tr.setDate(rs.getString("Date"));
             tr. setMode_paiement(rs.getString("mode_paiement"));
  
  
//  
//  
//  
//  
//  
//  
//  
//  
//          
////           tr.setVille_depart(rs.getString("ville_depart"));
////           tr.setVille_darrive(rs.getString("ville_darrive"));
////            tr.setPrix(rs.getInt("prix"));
////           tr.setNbr_place(rs.getInt("nbr_place"));
////            tr.setNbr_place(rs.getInt("id_user"));
////             tr.setNbr_place(rs.getInt("id_trajet"));
////            tr.setDate(rs.getString("date"));
////             tr. setMode_paiement(rs.getString("mode_paiement"));
//           
//
            temp.add(tr);

        }


        return temp;
    }

  

   
}
    
        
        

    
    
        
        
        
        
    


    

