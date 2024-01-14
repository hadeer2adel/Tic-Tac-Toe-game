
package Screens;

import static Screens.TwoPlayerMode_ScreenController.player1Name;
import static Screens.TwoPlayerMode_ScreenController.player2Name;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Dell
 */
public class SingleMode_ScreenController {

     private Stage stage;
     private Scene scene;
     private Parent root;
     
    public void switchToPcGame(ActionEvent event) {try {
        Pc_GameController.setPlayers("Player", "PC");
        root = FXMLLoader.load(getClass().getResource("/Screens/Pc_Game.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
         } catch (IOException ex) {
             ex.printStackTrace();
         }
    }
    
    public void switchTohome(ActionEvent event) throws IOException{//home Screen
        root = FXMLLoader.load(getClass().getResource("/Screens/Home_Screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
  
    
}
