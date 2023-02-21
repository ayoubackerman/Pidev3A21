package tn.esprit.vromvrom.services;

import tn.esprit.vromvrom.entities.Urgence;
import tn.esprit.vromvrom.utils.MaConnexion;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;




public class ServiceUrgence  implements IService<Urgence>{

    private Connection cnx;
    //private Object sp;

    public ServiceUrgence(){
        cnx = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void createOne(Urgence urgence) throws SQLException {
        String req = "INSERT INTO urgence (id_trajet, id_voiture, localisation, description, statuts, temps) " +
                " VALUES (?, ?, ?, ?, ?, ?)";
        System.out.println(req);
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, urgence.getId_Trajet());
        st.setInt(2, urgence.getId_voiture());
        st.setString(3, urgence.getLocalisation());
        st.setString(4, urgence.getDescription());
        st.setString(5, urgence.getStatus());
        st.setString(6, urgence.getTemps());


        st.executeUpdate();
        System.out.println("Urgence ajouté !");
    }

    @Override
    public void updateOne(Urgence urgence, int id) throws SQLException {
        String req = "UPDATE urgence SET id_trajet = ?, id_voiture = ?, localisation = ?, description = ?, statuts = ?, temps = ? WHERE id_urgence = ?";

        System.out.println(req);

        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1,urgence.getId_Trajet());
        st.setInt(2,urgence.getId_voiture());
        st.setString(3,urgence.getLocalisation());
        st.setString(4,urgence.getDescription());
        st.setString(5,urgence.getStatus());
        st.setString(6,urgence.getTemps());
        st.setInt(7,id);



        int rowsUpdated = st.executeUpdate();
        System.out.println(rowsUpdated + " lignes ont été mises à jour.");
    }

    @Override
    public void deletOne(Urgence urgence) throws SQLException {
        String req = "DELETE FROM urgence WHERE id_urgence = ?";

        System.out.println(req);
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1,urgence.getId_urgence());
        int rowsDeleted = st.executeUpdate();
        System.out.println(rowsDeleted + " lignes ont été supprimées.");


    }

    @Override
    public List<Urgence> selectAll() throws SQLException {
        List<Urgence> temp = new ArrayList<>();

        String req = "SELECT * FROM `urgence`";

        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            Urgence p = new Urgence();

            p.setId_urgence(rs.getInt(1));
            p.setId_trajet(rs.getInt(2));
            p.setId_voiture(rs.getInt(3));
            p.setLocalisation(rs.getString(4));
            p.setDescription(rs.getString(5));
            p.setStatus(rs.getString(6));
            p.setTemps(rs.getString(7));

            temp.add(p);

        }


        return temp;
    }
}
