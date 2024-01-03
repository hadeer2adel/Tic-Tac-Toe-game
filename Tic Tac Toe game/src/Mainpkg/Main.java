package Mainpkg;

//import Screens.TwoPlayerMode_Screen;
import Screens.Home_ScreenController;
import Screens.SingleMode_ScreenController;
import Screens.SingleMode_ScreenController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Screens/Frist_Screen.fxml"));
        Scene scene = new Scene(root,588,388);
          
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        stage.setScene(scene);
        
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> {
            try {
                Parent firstScreenRoot = FXMLLoader.load(getClass().getResource("/Screens/Home_Screen.fxml"));
                Scene firstScreenScene = new Scene(firstScreenRoot);
                stage.setScene(firstScreenScene);
            } catch (IOException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
        });

        stage.show();
        stage.setResizable(false);
        delay.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
   
}
