
package Screens;

import Mainpkg.Main;
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
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Dell
 */
public class SingleMode_ScreenController{

     private Stage stage;
     private Scene scene;
     private Parent root;
     
    public void switchToPcGame(ActionEvent event) throws IOException{//pc Game Screen
        root = FXMLLoader.load(getClass().getResource("/Screens/Pc_Game.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchTohome(ActionEvent event) throws IOException{//home Screen
        root = FXMLLoader.load(getClass().getResource("/Screens/Home_Screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
  
    
}
