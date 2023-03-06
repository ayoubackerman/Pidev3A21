/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import database.Database;
import model.Role;
import model.User;
import model.Voiture;

/**
 *
 * @author mehdi
 */
public class ServiceVoiture implements IServiceVoiture{
 private Connection cnx;

    public ServiceVoiture(){
        cnx = Database.getInstance().getCnx();
    }

    @Override
    public void ajouter(Voiture t) throws SQLException {
        Statement ste =  cnx.createStatement();
        String requeteInsert = "INSERT INTO `Voiture` (`id_voiture`,`id_user`, `modele`,`marque`,`matricule`,`image`) VALUES (NULL, '" + t.getUser().getId_user()+"' , '" + t.getModele()+"' ,'" + t.getMarque()+"' ,'" + t.getMatricule()+"' ,'" + t.getImg()+"');";
        ste.executeUpdate(requeteInsert);   
    }

    @Override
    public boolean delete(Voiture t) throws SQLException {
        if (search(t) == true){
            Statement ste = cnx.createStatement();
            String requeteDelete ="DELETE FROM voiture WHERE id_voiture="+ t.getId()+"";
            ste.executeUpdate(requeteDelete);}
        else{
           System.out.println("L'utulisateur n'existe pas");
        }
         return true;
    }

    @Override
    public boolean update(Voiture t) throws SQLException {
        if (search(t)==true){
            PreparedStatement pre=cnx.prepareStatement("UPDATE `voiture` SET id_user = ?, modele = ?, marque = ?, matricule = ?, image = ? WHERE `id_voiture`=? ;");
            pre.setInt(1,t.getUser().getId_user());
            pre.setString(2,t.getModele());
            pre.setString(3,t.getMarque());
            pre.setInt(4,t.getMatricule());
            pre.setString(5,t.getImg());
            pre.setInt(6,t.getId());
            pre.executeUpdate();
            return true;
        }
        else{
           System.out.println("L'ABRARS n'existe pas");
           return true;
        } 
    }

    @Override
    public boolean search(Voiture t) throws SQLException {
        PreparedStatement pre= cnx.prepareStatement("select * from voiture where `id_voiture` = ?");
        pre.setInt(1, t.getId());
        return pre.executeQuery().first();   
    }

    @Override
    public ObservableList<Voiture> readAll() throws SQLException {
        ObservableList<Voiture> temp = FXCollections.observableArrayList();

 
        PreparedStatement ps = cnx.prepareStatement("SELECT voiture.id_voiture, voiture.modele, voiture.marque, voiture.matricule, voiture.image, user.id_user  as id_user, "
                                                    + "user.nom, user.prenom, user.mail, user.Nomd, user.mdp, user.statuts, role.id_role as id_role, role  FROM voiture "
                                                    + "join user on voiture.id_user = user.id_user "
                                                    + "join role on user.id_role = role.id_role");
        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            Voiture v = new Voiture();
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
            v.setUser(user);
            v.setId(rs.getInt("id_voiture"));
            v.setModele(rs.getString("modele"));
            v.setMarque(rs.getString("marque"));
            v.setMatricule(rs.getInt("matricule"));
            v.setImg(rs.getString("image"));
            

            temp.add(v);

        }


        return temp;
    }
    
}
