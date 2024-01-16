/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screens;

import DTO.Client;
import DTO.ConnectedClient;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sarah Sobhy
 */
public class Invitation_Screen1Controller {

    private Stage stage = Mainpkg.Main.getAppStage();;
    private Scene scene;
    private Parent root;
    private static String name1,name2;
    private static int id1,id2;
    
    public static int getPlayer1ID(){
        return id1;
    }
    public static int getPlayer2ID(){
        return id2;
    }
    public static String getPlayer1Name(){
        return name1;
    }
    public static String getPlayer2Name(){
        return name2;
    }
    
    public void invitation(ActionEvent event) throws IOException{//login Screen
        id1 = 3;
        id2 = 4;
        name1 = "hadeer";
        name2 = "sara";
        System.out.println("Screens.Invitation_Screen1Controller.invitation()");
        
        openWaitScreen();
        Client client = ConnectedClient.getClient();
        if(client.isServerConnected()){
            System.out.println("--------------------WaitMessage");
            client.sendInvitation();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Server");
            alert.setHeaderText(null);
            alert.setContentText("Server is OFF now");
            alert.showAndWait();
        }
    } 
    
    public void openResponeScreen() {
        System.out.println("Screens.Invitation_Screen1Controller.openResponeScreen()");
        try {
            root = FXMLLoader.load(getClass().getResource("/Screens/Response_Screen.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Invitation_Screen1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void openWaitScreen() {
        System.out.println("Screens.Invitation_Screen1Controller.openWaitScreen()");
        try {
            root = FXMLLoader.load(getClass().getResource("/Screens/WaitMessage_Screen.fxml"));
            WaitMessage_ScreenController controller = new WaitMessage_ScreenController();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Invitation_Screen1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void switchTologin(ActionEvent event) throws IOException{//login Screen
        root = FXMLLoader.load(getClass().getResource("/Screens/Login_Screen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToProfile(ActionEvent event) throws IOException{//PlayerInfo
        root = FXMLLoader.load(getClass().getResource("/Screens/PlayerInfo_Screen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
