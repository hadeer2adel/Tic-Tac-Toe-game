
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


public class Records_ScreenController{

     private Stage stage = Mainpkg.Main.getAppStage();
     private Scene scene;
     private Parent root;
    
    public void switchToProfile(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/Screens/PlayerInfo_Screen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
