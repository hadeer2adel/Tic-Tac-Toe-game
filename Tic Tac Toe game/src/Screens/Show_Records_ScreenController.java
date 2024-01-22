package Screens;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ahmed KH
 */
public class Show_Records_ScreenController implements Initializable {
    
    private Stage stage = Mainpkg.Main.getAppStage();
    private Scene scene;
    private Parent root;
    private static String record ;
    private String[] steps ;
    private static Image xImage, oImage;
    private static Button[] gameBtn;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;
    @FXML
    private Button back;
    /**
     * Initializes the controller class.
     */
    
    public static void setRecord(String record){
        Show_Records_ScreenController.record = record;
    }

    private Button[] assignBtn() {
        Button[] btns;
        btns = new Button[9];
        btns[0] = btn1;
        btns[1] = btn2;
        btns[2] = btn3;
        btns[3] = btn4;
        btns[4] = btn5;
        btns[5] = btn6;
        btns[6] = btn7;
        btns[7] = btn8;
        btns[8] = btn9;
        return btns;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        xImage = new Image("/Images/X (7).png");
        oImage = new Image("/Images/o.png");
        gameBtn = assignBtn();
        steps = record.split(",");
        
    } 
    
    private void showImage(String ch, Button btn){
       
        Image img;
        if(ch.equals("x"))
            img = xImage;
        else
            img = oImage;

        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(btn.getHeight() - 10);
        imageView.setFitWidth(btn.getWidth() - 20);
        btn.setGraphic(imageView);
  
    }
    
    public void switchToProfile() throws IOException{
        root = FXMLLoader.load(getClass().getResource("/Screens/PlayerInfo_Screen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
    public void startRecord() {
    Timeline timeline = new Timeline();
    
    for (String part : steps) {
        String[] values = part.split("&");

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1 * timeline.getKeyFrames().size()), event -> {
            showImage(values[0], gameBtn[Integer.parseInt(values[1])]);
        });

        timeline.getKeyFrames().add(keyFrame);
    }

    timeline.play();
}


    
  
}
