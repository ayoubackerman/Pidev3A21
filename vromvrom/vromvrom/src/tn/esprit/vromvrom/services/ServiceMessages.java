package tn.esprit.vromvrom.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.vromvrom.entities.Conversation;
import tn.esprit.vromvrom.entities.Messages;
import tn.esprit.vromvrom.entities.user;
import tn.esprit.vromvrom.utils.MaConnexion;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class ServiceMessages implements IService<Messages>{

    private Connection cnx;

    public ServiceMessages(){
        cnx = MaConnexion.getInstance().getCnx();
    }


    @Override
    public void createOne(Messages messages) throws SQLException {
        String req = "INSERT INTO messages (conversation_id, expediteur,destinataire,texte) " +
                " VALUES (?, ?, ?, ?)";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, messages.getConversation().getId());
        st.setString(2, messages.getExpediteur());
        st.setString(3, messages.getDestinataire());
        st.setString(4, messages.getTexte());
        st.executeUpdate();
        System.out.println("Message ajouté !");
    }

    @Override
    public void updateOne(Messages messages) throws SQLException {

    }

    @Override
    public void deletOne(Messages messages) throws SQLException {
        String req = "DELETE FROM messages WHERE heure_envoi = ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setTimestamp(1, Timestamp.valueOf(messages.getHeureEnvoi()));
        int rowsDeleted = st.executeUpdate();
        System.out.println(rowsDeleted + " lignes ont été supprimées.");
    }

    @Override
    public List<Messages> selectAll() throws SQLException {
        ObservableList<Messages> temp = FXCollections.observableArrayList();

        String req = "SELECT *" +
                "FROM Messages" +
                " JOIN conversation ON Messages.conversation_id = conversation.id"+
                " JOIN user ur ON conversation.utilisateur1 = ur.id_user"+
                " JOIN user ur2 ON conversation.utilisateur2 = ur2.id_user";


        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            Messages p = new Messages();


            p.setId(rs.getInt(1));

            user user1 = new user(rs.getInt(12),rs.getInt(13),rs.getString(14),rs.getString(15),rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19));
            user user2 = new user(rs.getInt(20),rs.getInt(21),rs.getString(22),rs.getString(23),rs.getString(24),rs.getString(25),rs.getString(26),rs.getString(27));
            Conversation conversation = new Conversation(rs.getInt(7),user1,user2,rs.getDate(10),rs.getDate(11));
            p.setConversation(conversation);


            p.setExpediteur(rs.getString(3));
            p.setDestinataire(rs.getString(4));
            p.setTexte(rs.getString(5));
            p.setHeureEnvoi(rs.getObject(6, LocalDateTime.class));


            temp.add(p);

        }


        return temp;
    }
}
