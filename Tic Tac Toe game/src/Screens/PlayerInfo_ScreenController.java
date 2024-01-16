/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screens;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author win 10
 */
public class PlayerInfo_ScreenController  {
     private Stage stage = Mainpkg.Main.getAppStage();
     private Scene scene;
     private Parent root;
     
     public void switchToInvetation(ActionEvent event) throws IOException{//Invitation_Screen
        root = FXMLLoader.load(getClass().getResource("/Screens/Invitation_Screen1.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } 
     public void switchToRecords(ActionEvent event) throws IOException{//Invitation_Screen
        root = FXMLLoader.load(getClass().getResource("/Screens/Records_Screen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     
    

    
        
    
}
