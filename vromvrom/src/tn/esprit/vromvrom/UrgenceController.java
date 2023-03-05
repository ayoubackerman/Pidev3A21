package tn.esprit.vromvrom;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import tn.esprit.vromvrom.Model.Trajet;
import tn.esprit.vromvrom.Model.Urgence;
import tn.esprit.vromvrom.Model.Voiture_urgence;
import tn.esprit.vromvrom.service.ServiceUrgence;
import tn.esprit.vromvrom.Database.Database;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import tn.esprit.vromvrom.service.ServiceVoiture_urgence;


import javax.swing.*;

public class UrgenceController implements Initializable{

    @FXML
    private TableView<Urgence> m ;

    @FXML
    private TableColumn<Urgence, Integer> a;

    @FXML
    private TableColumn<Urgence, Integer> b;

    @FXML
    private TableColumn<Urgence, String> c;

    @FXML
    private TableColumn<Urgence, String> d ;

    @FXML
    private TableColumn<Urgence, String> e;

    @FXML
    private TableColumn<Urgence, String> f;




    @FXML
    private TextField id_trajet;
    @FXML
    private TextField id_voiture;
    @FXML
    private TextField localisation;
    @FXML
    private TextField description;
    @FXML
    private TextField status;
    @FXML
    private TextField date;
    @FXML
    private Button Add;

    ServiceUrgence SU = new ServiceUrgence();
    @FXML
    private Button Del;
    @FXML
    private Button Update;

    @FXML
    private ComboBox combo;
    @FXML
    private ComboBox combos;






    @FXML
    void Modifier(ActionEvent event) throws SQLException {

        try {


            int a = 0;

            ServiceVoiture_urgence serviceVoiture_urgence = new ServiceVoiture_urgence();
            List<Voiture_urgence> list = serviceVoiture_urgence.selectAll();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getMatricule().equals(combo.getValue())) {
                    a = list.get(i).getId_voiture();
                    break;
                }
            }


            int t = 0;
            try {
                t = Integer.parseInt(id_trajet.getText());
            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"veillez selectionner une urgence");
            }

            //int ivd = Integer.parseInt(id_voiture.getText());
            String l = localisation.getText();
            String d = description.getText();
            //String s = status.getText();
            String da = date.getText();

            Urgence ur = new Urgence(new Trajet(t), new Voiture_urgence(a), l, d, combos.getValue().toString(), da);

            if (t!=0){
                SU.updateOne(ur);
            }//else {
             //   JOptionPane.showMessageDialog(null,"veillez sasir le trajet");
            //}

            table();
/*
        String sql = "UPDATE urgence SET id_trajet = '"+ t +"', id_voiture = '"+ ivd +"', localisation = '"+ l +"', description = '"+ d +"', statuts = '"+ s +"' WHERE temps = '"+ da +"'" ;
        java.sql.Connection cnx;
        cnx = MaConnexion.getInstance().getCnx();
        try {
            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
            st.executeUpdate();
            table();
            JOptionPane.showMessageDialog(null,"Le fichier a été modifier");
        }catch(SQLException ex){
            ex.getMessage();
        }

 */


        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"veillez sasir tous vos données");
        }


    }






    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {


        try {

            String idf = date.getText();
            //int i=Integer.valueOf(idf);
            //SuppRole(idf);
            Urgence ur = new Urgence(idf);
            SU.deletOne(ur);
            table();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"veillez selectionner ce que vouz velez supprimer");
        }


       // JOptionPane.showMessageDialog(null,"Le fichier a été supprimer avec succés");


    }
/*
    public void SuppRole(String id){


        String sql ="DELETE FROM urgence WHERE temps='"+ id +"'";
        try {
            java.sql.Connection cnx;
            cnx = MaConnexion.getInstance().getCnx();
            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
            st.executeUpdate();
            table();
            JOptionPane.showMessageDialog(null,"Le fichier a été supprimer");
        }catch(SQLException ex){
            ex.getMessage();
        }


    }

 */









    public void onEdit() {

        java.sql.Connection cnx;
        cnx = Database.getInstance().getCnx();

        if (m.getSelectionModel().getSelectedItem() != null) {
            Urgence urgence = m.getSelectionModel().getSelectedItem();
            int i = urgence.getTrajet().getId_trajet();
            String n = String.valueOf(i);
            int j = urgence.getVoiture().getId_voiture();
            String m = String.valueOf(j);


            id_trajet.setText(n);
            id_voiture.setText(m);
            localisation.setText(urgence.getLocalisation());
            description.setText(urgence.getDescription());
            status.setText(urgence.getStatuts());
            date.setText(urgence.getTemps());



        }
    }







/*
    private void EnregistrerVersBase2() {


        java.sql.Connection cnx;
        cnx = MaConnexion.getInstance().getCnx();


        try {
            String sql = "INSERT INTO urgence (id_trajet, id_voiture, localisation, description, statuts) VALUES (?,?,?,?,?)";

            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);



            st.setString(1, id_trajet.getText());
            st.setString(2, id_voiture.getText());
            st.setString(3, localisation.getText());
            st.setString(4, description.getText());
            st.setString(5, status.getText());



            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error");
        }
    }

 */

    @FXML
    private void AddUrgence(ActionEvent event) throws SQLException {


        Voiture_urgence v = null;
        ServiceVoiture_urgence serviceVoiture_urgence = new ServiceVoiture_urgence();
        List<Voiture_urgence> list = serviceVoiture_urgence.selectAll();
        for (int i =0;i<list.size();i++){
            if (list.get(i).getMatricule().equals(combo.getValue())){
                int a = list.get(i).getId_voiture();
                v = new Voiture_urgence(a);
                break;
            }
        }






        Trajet t = new Trajet();
        try {
        t = new Trajet(Integer.parseInt(id_trajet.getText()));
        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null,"veillez sasir tous vos données");
        }
       // System.out.println(v);

        Urgence ur = new Urgence(t,v,localisation.getText(),description.getText(),combos.getValue().toString());


        if (t.getId_trajet()!=0){
            SU.createOne(ur);
        }else {
            JOptionPane.showMessageDialog(null,"veillez sasir le trajet");
        }



        table();

        id_trajet.clear();
        id_voiture.clear();
        description.clear();
        localisation.clear();
        status.clear();






    }

















    public void table() throws SQLException {


       // a.setCellValueFactory(new PropertyValueFactory <>("Trajet"));

        System.out.println(SU.selectAll());

        a.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTrajet().getId_trajet()).asObject());

        b.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getVoiture().getId_voiture()).asObject());
        c.setCellValueFactory(new PropertyValueFactory <>("localisation"));
        d.setCellValueFactory(new PropertyValueFactory <>("description"));
        e.setCellValueFactory(new PropertyValueFactory <>("statuts"));
        f.setCellValueFactory(new PropertyValueFactory <>("temps"));


        m.setItems(SU.selectAll());
        //m.setItems(RecupBase());
        //System.out.println(SU.selectAll());



    }

/*
    public static ObservableList<Urgence> RecupBase(){

        ObservableList<Urgence> list = FXCollections.observableArrayList();

        java.sql.Connection cnx;
        cnx = MaConnexion.getInstance().getCnx();
        String sql = "SELECT urgence.*, trajet.*\n" +
                "FROM urgence\n" +
                "JOIN trajet ON urgence.id_trajet = trajet.id_trajet;";
        try {

            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

            ResultSet R = st.executeQuery();



            while (R.next()){
                Urgence r =new Urgence();
               // r.setId_trajet(R.getInt(2));
                //r.setId_voiture(R.getInt(3));
                r.setLocalisation(R.getString(4));
                r.setDescription(R.getString(5));
                r.setStatuts(R.getString(6));
                r.setTemps(R.getString(7));
                //System.out.println(r);
                //System.out.println(r.getId_Trajet());

                System.out.println(R.getString(15));




                list.add(r);
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
        return list;
    }

 */

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ServiceVoiture_urgence serviceVoiture_urgence = new ServiceVoiture_urgence();
        try {
            List<Voiture_urgence> list3 = serviceVoiture_urgence.selectAll();
            ObservableList<String> list2 = FXCollections.observableArrayList();
            for (int i=0 ; i<list3.size();i++){
                if (list3.get(i).getStatuts()==0){
                    list2.add(list3.get(i).getMatricule());
                }
            }
            combo.setItems(list2);
            combo.setValue(list2.get(0));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }




        try {
            table();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        date.setVisible(false);

        m.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 0) {
                onEdit();

            }
        });


        try {
            table();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



        ObservableList<String> list = FXCollections.observableArrayList("en cour","terminer");
        combos.setItems(list);

        combos.setValue(list.get(0));


    }








}

