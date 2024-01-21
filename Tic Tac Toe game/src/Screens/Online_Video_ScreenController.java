/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screens;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed KH
 */
public class Online_Video_ScreenController implements Initializable {
    private Stage stage = Mainpkg.Main.getAppStage();
    private Scene scene;
    private Parent root;
    
    @FXML
    private MediaView mediaView;
    @FXML
    private Media media;
    private MediaPlayer mediaPlayer;
    @FXML
    private Label playerName;
    
    private static String lblPlayer;
    private static String lblVideo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        playerName.setText(lblPlayer);
        String path = "" ;
        switch (lblVideo) {
            case "win":
                path = new File("src/Media/win.mp4").getAbsolutePath();
                break;
            case "loss":
                path = new File("src/Media/losser.mp4").getAbsolutePath();
                break;
            case "draw":
                 path = new File("src/Media/draw.mp4").getAbsolutePath();
                break;
        }
        media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play(); 
    }    
    
    public static void setData(String player, String video) {
        lblPlayer = player;
        lblVideo = video;
    }
}
