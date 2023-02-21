/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.Voiture;

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
        String requeteInsert = "INSERT INTO `Voiture` (`id_voiture`,`id_user`, `modele`,`marque`,`matricule`,`image`) VALUES (NULL, '" + t.getId_user()+"' , '" + t.getModele()+"' ,'" + t.getMarque()+"' ,'" + t.getMatricule()+"' ,'" + t.getImg()+"');";
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
            pre.setInt(1,t.getId_user());
            pre.setString(1,t.getModele());
            pre.setString(2,t.getMarque());
            pre.setInt(2,t.getMatricule());
            pre.setString(3,t.getImg());
            pre.setInt(3,t.getId());
            pre.executeUpdate();
            return true;
        }
        else{
           System.out.println("L'utulisateur n'existe pas");
           return true;
        } 
    }

    @Override
    public boolean search(Voiture t) throws SQLException {
        Statement ste= cnx.createStatement();
        ResultSet rs=ste.executeQuery("select * from voiture");
        boolean ok=false; 
        while (rs.next()&&(ok==false)) {         
        if (rs.getInt(1)== t.getId())
            ok=true;
        }
     return ok;    
    }

    @Override
    public List<Voiture> readAll() throws SQLException {
        List<Voiture> temp = new ArrayList<>();

        String req = "SELECT * FROM `voiture`";

        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            Voiture v = new Voiture();

            v.setId(rs.getInt(1));
            v.setId_user(rs.getInt(2));
            v.setModele(rs.getString(1));
            v.setMarque(rs.getString(2));
            v.setMatricule(rs.getInt(3));
            v.setImg(rs.getString(3));

            temp.add(v);

        }


        return temp;
    }
    
}
