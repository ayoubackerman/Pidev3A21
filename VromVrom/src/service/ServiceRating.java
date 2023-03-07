/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import database.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Ratingg;
import model.Role;
import model.User;
import model.Voiture;

/**
 *
 * @author MediaCenter Zaghouan
 */
public class ServiceRating implements IServiceRating<Ratingg> {
      private Connection cnx;

    public ServiceRating(){
        cnx = Database.getInstance().getCnx();
    }

    @Override
    public void ajouter(Ratingg t) throws SQLException {
     Statement ste =  cnx.createStatement();
        String requeteInsert = "INSERT INTO `rating` (`id`,`id_user`, `etoiles`,`commentaire`,`id_user2`) VALUES (NULL, '" + t.getUser().getId_user()+"' , '" + t.getEtoiles()+"' ,'" + t.getCommentaire()+"', '" + t.getUser2().getId_user()+"');";
        ste.executeUpdate(requeteInsert);   
    }

    @Override
    public boolean delete(Ratingg t) throws SQLException {
        if (search(t) == true){
            Statement ste = cnx.createStatement();
            String requeteDelete ="DELETE FROM rating WHERE id="+ t.getId()+"";
            ste.executeUpdate(requeteDelete);}
        else{
           System.out.println("L'utulisateur n'existe pas");
        }
         return true; 
    }

    @Override
    public boolean update(Ratingg t) throws SQLException {
        if (search(t)==true){
            PreparedStatement pre=cnx.prepareStatement("UPDATE `rating` SET id_user = ?, etoiles = ?, commentaire = ? where id_user = ? and id_user2 = ?;");
            pre.setInt(1,t.getUser().getId_user());
            pre.setDouble(2,t.getEtoiles());
            pre.setString(3,t.getCommentaire());
            pre.setInt(4,t.getUser().getId_user());
            pre.setInt(5,t.getUser2().getId_user());

            pre.executeUpdate();
            return true;
        }
        else{
           System.out.println("L'utulisateur n'existe pas");
           return true;
        } 
    }

    @Override
    public boolean search(Ratingg t) throws SQLException {
        PreparedStatement pre = cnx.prepareStatement("select * from rating where `id` = ?");
        pre.setInt(1, t.getId());
        return pre.executeQuery().first(); 
    }
    

    public boolean search(int a, int b) throws SQLException {
        PreparedStatement pre = cnx.prepareStatement("select * from rating where `id_user` = ? and id_user2 = ?");
        pre.setInt(1, a);
        pre.setInt(2, b);
        return pre.executeQuery().first(); 
    }

    @Override
    public ObservableList<Ratingg> readAll() throws SQLException {
        ObservableList<Ratingg> temp = FXCollections.observableArrayList();

 
        PreparedStatement ps = cnx.prepareStatement("SELECT rating.id, rating.etoiles, rating.commentaire, user.id_user  as id_user, "
                                                    + "user.nom, user.prenom, user.mail, user.Nomd, user.mdp, user.statuts, role.id_role as id_role, role  FROM rating "
                                                    + "join user on rating.id_user = user.id_user "
                                                    + "join role on user.id_role = role.id_role");
        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            Ratingg r = new Ratingg();
            User user = new User();
            Role role = new Role();
            
            role.setId_role(rs.getInt("id_role"));
            role.setRole(rs.getString("role"));
            user.setId_role(role);
            user.setId_user(rs.getInt("id_user"));
            user.setMail(rs.getString("mail"));
            user.setMdp(rs.getString("mdp"));
            user.setNom(rs.getString("nom"));
            user.setNomd(rs.getString("Nomd"));
            user.setPrenom(rs.getString("prenom"));
            user.setStatus(rs.getString("statuts"));
            r.setUser(user);
            r.setId(rs.getInt("id"));
            r.setEtoiles(rs.getInt("etoiles"));
            r.setCommentaire(rs.getString("commentaire"));

            

            temp.add(r);

        }

        return temp;
    
    }
    

    public double calculateMoyenne(int id) throws SQLException {
        PreparedStatement ps = cnx.prepareStatement("select avg (etoiles) from rating where id_user = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getDouble(1);
        }
        return 0;    
    }
    
    public boolean check(int id, int id2) throws SQLException{
         PreparedStatement pre = cnx.prepareStatement("select * from rating where `id_user2` = ? and `id_user` = ?");
         pre.setInt(1, id);
         pre.setInt(2, id2);
         return pre.executeQuery().first();
    }
    
}
