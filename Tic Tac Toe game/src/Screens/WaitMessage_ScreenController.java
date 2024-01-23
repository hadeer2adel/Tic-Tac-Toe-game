/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screens;

import DTO.Client;
import DTO.ConnectedClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class WaitMessage_ScreenController{

    private Stage stage = Mainpkg.Main.getAppStage();;
    private Scene scene;
    private Parent root;
    
    private static int id;
    private static String name;
    
    public static void setData(int d, String n){
        id = d;
        name = n;
    }
    
    private void updateScore(JsonObject requestJson) {
        FileInputStream fis = null;
        FileWriter writer = null;
        int myId = ConnectedClient.getClient().getId();
        try {
            File f = new File("Files/playerData"+myId+".json");
            fis = new FileInputStream(f);
            JsonReader reader = Json.createReader(fis);
            JsonObject obj = reader.readObject();
            
            int score = obj.getInt("score");
            if(myId == requestJson.getInt("id1"))
                score += requestJson.getInt("score1");
            else
                score += requestJson.getInt("score2");
            
            JsonObject updatedObj = Json.createObjectBuilder()
                    .add("response", "login")
                    .add("status", "success")
                    .add("id", obj.getInt("id"))
                    .add("name", obj.getString("name"))
                    .add("score", score)
                    .build();

            writer = new FileWriter(f);
            writer.write(updatedObj.toString());
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Online_Video_ScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Online_Video_ScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Online_Video_ScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void openCancelScreen(JsonObject requestJson)  {
        try {
            updateScore(requestJson);
            root = FXMLLoader.load(getClass().getResource("/Screens/Invitation_Screen1.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Invitation_Screen1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void openInvitationScreen() {
        try {
            root = FXMLLoader.load(getClass().getResource("/Screens/Invitation_Screen1.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Invitation_Screen1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void openGameScreen(int id1, String name1, int id2, String name2) {
        try {
            Online_Game_ScreenController.setGame(id1, name1, id2, name2);
            
            root = FXMLLoader.load(getClass().getResource("/Screens/Online_Game_Screen.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Invitation_Screen1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void onCancel(ActionEvent event) throws IOException{
        Client client = ConnectedClient.getClient();
        if(client.isServerConnected()){
            client.sendCancel(id,name,0,0);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Server");
            alert.setHeaderText(null);
            alert.setContentText("Server is OFF now");
            alert.showAndWait();
        }
    } 
}
