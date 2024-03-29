
package Screens;

import Mainpkg.Main;
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
import javafx.stage.Stage;


public class Home_ScreenController {
    private Stage stage = Mainpkg.Main.getAppStage();
    private Scene scene;
    private Parent root;
    
    public void switchToScene1(ActionEvent event) throws IOException{//home screen
        root = FXMLLoader.load(getClass().getResource("/Screens/Home_Screen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Screens/SingleMode_Screen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchTo2playerScreen(ActionEvent event) throws IOException{//switch to 2player screen
        root = FXMLLoader.load(getClass().getResource("/Screens/TwoPlayerMode_Screen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToLogIn(ActionEvent event) throws IOException{//switch to Log in screen
        root = FXMLLoader.load(getClass().getResource("/Screens/Login_Screen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     
}