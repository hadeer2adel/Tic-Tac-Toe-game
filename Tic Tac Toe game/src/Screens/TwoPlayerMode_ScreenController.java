/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screens;
import javafx.scene.control.Dialog;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
 import javafx.scene.control.ChoiceDialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class TwoPlayerMode_ScreenController{
     
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private  Button btnLetPlay;
    @FXML
    private TextField txtField1;
    @FXML
    private  TextField txtField2;
    public static String player1Name;
    public static String player2Name;
     
    
    public void switchToGame(ActionEvent event) throws IOException{//Game Screen
        player1Name = txtField1.getText();
        player2Name = txtField2.getText();
        
        if((player1Name.equals(""))||(player2Name.equals(""))){
            Alert alert = new Alert(Alert.AlertType.NONE,"Attention",ButtonType.OK); 
            alert.setTitle("Attention");
            alert.setContentText("Please Enter Your Name !!");
            alert.showAndWait();
        }
        else if(player1Name.equals(player2Name)){
            Alert alert = new Alert(Alert.AlertType.NONE,"Attention",ButtonType.OK); 
            alert.setTitle("Attention");
            alert.setContentText("Please change Second Name !!");
            alert.showAndWait();
        }
        else{
            Game_ScreenController.setPlayers(player1Name, player2Name);
            root = FXMLLoader.load(getClass().getResource("/Screens/Game_Screen.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    
    public void switchToHome(ActionEvent event) throws IOException{//Home Screen
        root = FXMLLoader.load(getClass().getResource("/Screens/Home_Screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    
    

       
}
