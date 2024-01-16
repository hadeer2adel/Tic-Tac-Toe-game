/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screens;

import DTO.Client;
import DTO.ConnectedClient;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.json.JsonObject;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class Response_ScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private static String name1;
    private Client client;
    private static JsonObject jsonObject;
    private Stage stage = Mainpkg.Main.getAppStage();;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Label nameOfPlayer;
    
    public static void setPlayer(String name1){
        Response_ScreenController.name1 = name1;
    }
    
    public static void setJson (JsonObject jo){
        Response_ScreenController.jsonObject = jo;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Screens.Response_ScreenController.initialize()");
        client = ConnectedClient.getClient();
        nameOfPlayer.setText(name1);
    }  
    
    public void acceptInvitation(ActionEvent event) throws IOException, InterruptedException{//inviation Screen
        System.out.println("Screens.Response_ScreenController.acceptInvitation()");
        if(client.isServerConnected()){
            client.receiveInvitationHandeler(jsonObject, true);
            openGameScreen();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Server");
            alert.setHeaderText(null);
            alert.setContentText("Server is OFF now");
            alert.showAndWait();
        }
    }

    public void rejectInvitation(ActionEvent event) throws IOException, InterruptedException{//inviation Screen
        System.out.println("Screens.Response_ScreenController.rejectInvitation()");
        if(client.isServerConnected()){
            client.receiveInvitationHandeler(jsonObject, false);
            openInvitationScreen();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Server");
            alert.setHeaderText(null);
            alert.setContentText("Server is OFF now");
            alert.showAndWait();
        }
    }
    
    public void openInvitationScreen() {
        System.out.println("Screens.WaitMessage_ScreenController.openInvitationScreen()");
        try {
            root = FXMLLoader.load(getClass().getResource("/Screens/Invitation_Screen1.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Invitation_Screen1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void openGameScreen() {
        try {
            root = FXMLLoader.load(getClass().getResource("/Screens/Game_Screen.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Invitation_Screen1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
