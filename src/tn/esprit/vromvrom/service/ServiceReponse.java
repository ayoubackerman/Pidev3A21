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
import tn.esprit.vromvrom.Model.Reponse;
import tn.esprit.vromvrom.Model.Role;
import tn.esprit.vromvrom.Model.User;

/**
 *
 * @author ASUS
 */
public class ServiceReponse implements IServiceReponse{
      private Connection cnx;

    public ServiceReponse(){
        cnx = Database.getInstance().getCnx();
    }

    @Override
    public void ajouter(Reponse t) throws SQLException {
        Statement ste =  cnx.createStatement();
        
         String requeteInsert = "INSERT INTO `reponse`( `id_reclamation`) VALUES "
                 + "( '" + t.getId_reclamation()+ "');";
        ste.executeUpdate(requeteInsert);
    }
    
    
//    @Override
//    public void rf(Reponse t) throws SQLException {
//       Statement ste =  cnx.createStatement();
//        
//         String requeteInsert = "INSERT INTO `reclamation`( `id_user`, `reclamation`, `resolution`, `typre_reclamation`) VALUES ( '" + t.getId_user()+"' , '" + t.getReclamation()+ "' , '" + t.getResolution()+ "' , '" + t.getType_reclamation()+ "');";
//        ste.executeUpdate(requeteInsert);    
//    }

    @Override
    public boolean delete(Reponse t) throws SQLException {
       Statement ste =  cnx.createStatement();
 if (search(t)==true){
         ste = cnx.createStatement();
         String requeteDelete ="DELETE FROM reponse WHERE id_reclamation="+ t.getId_reclamation();
         ste.executeUpdate(requeteDelete);}
         else{
           System.out.println("L'utulisateur n'existe pas");
        }
         return true;    }

   

    @Override
    public boolean update(Reponse t) throws SQLException {
         try {
            Statement ste =  cnx.createStatement();
            String requete = "UPDATE `reponse` SET reponse=? "
                    + "WHERE id_reclamation= ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(4, t.getReponse());
            pst.executeUpdate();
            System.out.println("Votre reclamation est bien modifie !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return true;
    }

    @Override
    public boolean search(Reponse t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reponse> readAll() throws SQLException {
        ServiceReponse r = new ServiceReponse();
   List<Reponse> arr=new ArrayList<>();
    Statement ste= cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from reponse");
     while (rs.next()) {                
               int id_reponse=rs.getInt(1);
               int id_reclamation=rs.getInt(2);
               int id_user=rs.getInt(3);
               String reponse=rs.getString(4);
               String temps=rs.getString(5);
               
               Reponse p =new Reponse(id_reponse,id_reclamation,id_user,reponse);
     arr.add(p);
     }
    return arr;
    }

    public Reponse SelectReponse(int id) {
         Reponse r = new Reponse();
        
        ServiceReponse ro =new ServiceReponse();
        String req = "SELECT * FROM reponse where id_reclamation='"+id+"'";
        
        try {
            PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery(req);
            
            while(rs.next()){           
                 
                  r = new Reponse(rs.getInt("id_reponse"),rs.getInt("id_reclamation"), rs.getInt("id_user"),rs.getString("reponse"),rs.getString("temps"));

            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReponse .class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

   

    
}
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package tn.esprit.vromvrom.service;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import tn.esprit.vromvrom.Database.Database;
//import tn.esprit.vromvrom.Model.Reponse;
//import tn.esprit.vromvrom.Model.Role;
//import tn.esprit.vromvrom.Model.User;
//
///**
// *
// * @author ASUS
// */
//public class ServiceReponse implements IServiceReponse{
//      private Connection cnx;
//
//    public ServiceReponse(){
//        cnx = Database.getInstance().getCnx();
//    }
//
//    @Override
//    public void ajouter(Reponse t) throws SQLException {
//        Statement ste =  cnx.createStatement();
//        
////         String requeteInsert = "INSERT INTO `reclamation`( `id_user`, `reclamation`, `resolution`, `typre_reclamation`) VALUES "
////                 + "( '" + t.getId_user()+"', '" + t.getReclamation()+ "',, '" + t.getResolution()+ "',, '" + t.getType_reclamation()+ "');";
////        ste.executeUpdate(requeteInsert);
//    }
//
//    @Override
//    public boolean delete(Reponse t) throws SQLException {
//       Statement ste =  cnx.createStatement();
// if (search(t)==true){
//         ste = cnx.createStatement();
//         String requeteDelete ="DELETE FROM reclamation WHERE id_reclamation="+ t.getId_reclamation();
//         ste.executeUpdate(requeteDelete);}
//         else{
//           System.out.println("L'utulisateur n'existe pas");
//        }
//         return true;    }
//
//   
//
//    @Override
//    public boolean update(Reponse t) throws SQLException {
//         try {
//            Statement ste =  cnx.createStatement();
//            String requete = "UPDATE `reponse` SET reponse=? "
//                    + "WHERE id_reponse= ?";
//            PreparedStatement pst = cnx.prepareStatement(requete);
//            pst.setString(1, t.getReponse());
//            
//          
//            pst.executeUpdate();
//            System.out.println("Votre reclamation est bien modifie !");
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//         return true;
//    }
//
//    @Override
//    public boolean search(Reponse t) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public List<Reponse> readAll() throws SQLException {
//        ServiceReponse r = new ServiceReponse();
//   List<Reponse> arr=new ArrayList<>();
//    Statement ste= cnx.createStatement();
//    ResultSet rs=ste.executeQuery("select * from reponse");
//     while (rs.next()) {                
//               
//               String reponse=rs.getString(4);
//               String temps=rs.getString(5);
//               
//               Reponse p =new Reponse(reponse,temps);
//     arr.add(p);
//     }
//    return arr;
//    }
//
//    public Reponse SelectReponse(int id) {
//         Reponse r = new Reponse();
//        
//        ServiceReponse ro =new ServiceReponse();
//        String req = "SELECT * FROM reponse where id_reponse ="+id;
//        
//        try {
//            PreparedStatement ps = cnx.prepareStatement(req);
//
//        ResultSet rs = ps.executeQuery(req);
//            
//            while(rs.next()){           
//                 
//               r = new Reponse(rs.getInt("id_reclamation"),rs.getInt("id_reclamation"), rs.getString("reponse"),rs.getString("temps"));
//
//            }
//            
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceReponse .class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return r;
//    }
//
//    
//}
