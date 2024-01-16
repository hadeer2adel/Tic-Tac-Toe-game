/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screens;

import DTO.*;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class Login_ScreenController {

    private Stage stage = Mainpkg.Main.getAppStage();
    private Scene scene;
    private Parent root;
    
    private static String email, password;
    private static boolean successLogin = false;
    private Client client;

    @FXML
    private PasswordField field_Password;
    @FXML
    private TextField field_Email;
    @FXML
    private Button btn_Login;
    
    private boolean validation(String email, String password) {
        boolean valid = false;
        if (email.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Email is empity");
            alert.showAndWait();
        }
        else if (password.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Password is empity");
            alert.showAndWait();
        }
        else
            valid = true;
        return valid;
    }

    public static void SuccessLogin() {
        successLogin = true;
    }
    public static String getEmail() {
        return email;
    }
    public static String getPassword() {
        return password;
    }

    public void switchToInviation(ActionEvent event) throws IOException, InterruptedException{//inviation Screen
        email = field_Email.getText();
        password = field_Password.getText();
        if(validation(email, password)){
            client = new Client();
            ConnectedClient.setClient(client);
            if(client.isServerConnected()){
                client.Login();
                if (client.isopSuccess()) {
                    root = FXMLLoader.load(getClass().getResource("/Screens/Invitation_Screen1.fxml"));
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } 
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid email or password");
                    alert.showAndWait();
                }
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
    
    public void switchToHome(ActionEvent event) throws IOException{//home Screen
        root = FXMLLoader.load(getClass().getResource("/Screens/Home_Screen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
  
    @FXML
    private void switchSignUp(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Screens/Signup_Screen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
