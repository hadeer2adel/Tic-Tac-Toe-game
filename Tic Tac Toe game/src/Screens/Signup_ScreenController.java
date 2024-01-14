/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screens;

import DTO.Client;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.json.Json;
import javax.json.JsonObject;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class Signup_ScreenController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private PasswordField field_Password;
    @FXML
    private PasswordField field_ConfirmPassword;
    @FXML
    private TextField field_Name;
    @FXML
    private TextField field_Email;
    @FXML
    private Button btn_SignUp;
    private final String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
    private final String nameRegex = "^[a-zA-Z0-9_-]{3,16}$";
    private static boolean successSignUp = false;
    private static String email, password ,name;
    private Client client;
    
    public void switchTologin(ActionEvent event) throws IOException{//login Screen
        root = FXMLLoader.load(getClass().getResource("/Screens/Login_Screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
  
    private boolean validation()
    {   
        //validation Function check about name , email and password .
        boolean valid = false;
        if(field_Name.getText().isEmpty()||
           field_Email.getText().isEmpty()||
           field_Password.getText().isEmpty()||
           field_ConfirmPassword.getText().isEmpty() )
        {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Complete your data.");
            alert.showAndWait();
        }
        else if (field_Password.getText().length() < 8) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Password must be at least 8 characters");
            alert.showAndWait();
        }
        else if (! field_Password.getText().equals(field_ConfirmPassword.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Password and confirm password doesn't match.");
            alert.showAndWait();
        }
        else if (! field_Name.getText().matches(nameRegex)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid name format.");
            alert.showAndWait();
        }
        else if (! field_Email.getText().matches(emailRegex)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid email format.");
            alert.showAndWait();
        }
        else
        {
            valid = true;
        }
        
        return valid;  
    }
    
    public static void SuccessSignUp() {
        successSignUp = true;
    }
    public static String getEmail() {
        return email;
    }
    public static String getPassword() {
        return password;
    }
    public static String getName() {
        return name;
    }
    
    public void createNewAccount(ActionEvent event) throws IOException
    {
        email = field_Email.getText();
        password = field_Password.getText();
        name =field_Name.getText();
        if (validation())
        {
            client = new Client();
            if(client.isConnected())
            {
                client.SignUp();
                if (successSignUp) {
                    //login Page
                    root = FXMLLoader.load(getClass().getResource("/Screens/Login_Screen.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } 
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Try Again.");
                    alert.showAndWait();
                }
                client.stop();
            }
            
        }
          
    }
    
   
    
    
    
}
