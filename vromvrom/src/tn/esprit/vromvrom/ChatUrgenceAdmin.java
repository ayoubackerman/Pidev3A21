package tn.esprit.vromvrom;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.vromvrom.Model.Conversation;
import tn.esprit.vromvrom.Model.User;
import tn.esprit.vromvrom.service.ServiceConversation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ChatUrgenceAdmin implements Initializable {

    @FXML
    TableView<Conversation> table;

    @FXML
    TableColumn <Conversation,String> user;

    @FXML
    TableColumn<Conversation, Date> heure;

    @FXML
    TextField id_conv;

    @FXML
    Button btn;

    @FXML
    AnchorPane pane1;



    @FXML
    public void LoadConversation2(ActionEvent event) throws IOException{


        if (!id_conv.getText().isEmpty()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatUrgence.fxml"));
            Parent fxml = loader.load();
            pane1.getChildren().removeAll();
            pane1.getChildren().setAll(fxml);

            ChatUrgenceController controller = loader.getController();
            controller.TheConversationId(Integer.parseInt(id_conv.getText()));
            controller.afficherMessage();


/*
        Parent fxml = FXMLLoader.load(getClass(). getResource("ChatUrgence.fxml"));
        pane1.getChildren().removeAll();
        pane1.getChildren().setAll(fxml);

        ChatUrgenceController controller =


        controller.TheConversationId(Integer.parseInt(id_conv.getText()));

        controller.afficherMessage();

 */
        }
    }






    public void goToChat(javafx.event.ActionEvent event){
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatUrgence.fxml"));
            Parent root =(Parent) loader.load();
            ChatUrgenceController controller = loader.getController();


            controller.TheConversationId(Integer.parseInt(id_conv.getText()));

            controller.afficherMessage();


            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        }catch (Exception e){
            System.out.println("erreur lors du passage vers le chat");
            e.printStackTrace();
        }
    }







    public void table() throws SQLException {
        user.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUtilisateur1().getNomd()));
        heure.setCellValueFactory(new PropertyValueFactory <>("heureDebut"));

        ServiceConversation serviceConversation = new ServiceConversation();
        List<Conversation> list = serviceConversation.selectAll();
        List<Conversation> filteredList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getHeureFin()==null) {
                filteredList.add(list.get(i));
            }
        }
        //System.out.println(filteredList);
        table.setItems(FXCollections.observableArrayList(filteredList));
    }



    public void onEdit() throws SQLException {


        if (table.getSelectionModel().getSelectedItem() != null) {
            Conversation v = table.getSelectionModel().getSelectedItem();
            //System.out.println(v.getUtilisateur1().getNomd());
            ServiceConversation serviceConversation = new ServiceConversation();
            List<Conversation> list = serviceConversation.selectAll();
            for (int i=0;i<list.size();i++){
                if (list.get(i).getUtilisateur1().getNomd().equals(v.getUtilisateur1().getNomd()) && list.get(i).getHeureFin()==null){
                    id_conv.setText(String.valueOf(list.get(i).getId()));
                    break;
                }
            }
        }
    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            table();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        table.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 0) {
                try {
                    onEdit();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

    }
}
