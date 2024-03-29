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
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;

/**
 * FXML Controller class
 *
 * @author win 10
 */
public class PlayerInfo_ScreenController implements  Initializable  {
     private Stage stage = Mainpkg.Main.getAppStage();
     private Scene scene;
     private Parent root;
     
    @FXML
    private Label labelName;
    @FXML
    private Label labelNumOfScore; 

     
     public void switchToInvetation(ActionEvent event) throws IOException{//Invitation_Screen
        root = FXMLLoader.load(getClass().getResource("/Screens/Invitation_Screen1.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } 
    public void switchToRecords(ActionEvent event) throws IOException {//Invitation_Screen
        Client client = ConnectedClient.getClient();
        if (client.isServerConnected()) {
            client.getAllRecords();
            root = FXMLLoader.load(getClass().getResource("/Screens/Records_Screen.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Server");
            alert.setHeaderText(null);
            alert.setContentText("Server is OFF now");
            alert.showAndWait();
        }

    }

    
    public void initialize(URL location, ResourceBundle resources) {
        FileInputStream fis = null;
        try {

            File f = new File("Files/playerData"+ConnectedClient.getClient().getId()+".json");
            fis = new FileInputStream(f);
            JsonReader reader = Json.createReader(fis);
            JsonStructure jsonSt = reader.read();
            JsonObject obj = (JsonObject) jsonSt;

            labelName.setText(obj.getString("name"));
            labelNumOfScore.setText(""+obj.getInt("score"));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    
    }
     
    

    
        
    
}
