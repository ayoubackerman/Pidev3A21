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
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.Role;

/**
 *
 * @author MediaCenter Zaghouan
 */
public class ServiceRole implements IServiceRole<Role> {
      private Connection cnx;

    public ServiceRole(){
        cnx = Database.getInstance().getCnx();
    }

    @Override
    public void ajouter(Role t) throws SQLException {
     Statement ste =  cnx.createStatement();
        
         String requeteInsert = "INSERT INTO `role` (id_role, `role`) VALUES (NULL, '" + t.getRole()+"');";
        ste.executeUpdate(requeteInsert);    }

    @Override
    public boolean delete(Role t) throws SQLException {
         Statement ste =  cnx.createStatement();
 if (search(t)==true){
         ste = cnx.createStatement();
         String requeteDelete ="DELETE FROM user WHERE id_role="+ t.getId_role()+"";
         ste.executeUpdate(requeteDelete);}
         else{
           System.out.println("L'utulisateur n'existe pas");
        }
         return true;    }

    @Override
    public boolean update(Role t) throws SQLException {
 if (search(t)==true){
        PreparedStatement pre=cnx.prepareStatement("UPDATE `role` SET ID = ?, role = ? WHERE `id_role`=? ;");
        pre.setInt(1,t.getId_role());
        pre.setString(2,t.getRole());
       
        
        pre.executeUpdate();
        return true;}
          else{
           System.out.println("L'utulisateur n'existe pas");
           return true;
        }    }

    @Override
    public boolean search(Role t) throws SQLException {
  Statement ste= cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from role");
    boolean ok=false; 
    while (rs.next()&&(ok==false)) {         
         if (rs.getInt(1)==t.getId_role())
             ok=true;
     }
     return ok;    }

    @Override
    public List<Role> readAll() throws SQLException {
        List<Role> arr=new ArrayList<>();
        Statement ste = cnx.createStatement();
        ResultSet rs=ste.executeQuery("select * from role");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String role=rs.getString(2);
              
               
               Role r=new Role(id,role);
     arr.add(r);
     }
    return arr;
    }
     public Role SelectRole(int id){
        Role r = new Role();
        String req = "SELECT * FROM role where id_role ="+id+"";
        
        try {
            PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery(req);
            
            while(rs.next()){           
                 
                r = new Role(rs.getInt("id_role"), rs.getString("role"));

            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRole .class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    
}
