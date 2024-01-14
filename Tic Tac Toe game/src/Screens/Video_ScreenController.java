/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screens;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
 * @author Dell
 */
public class Video_ScreenController implements Initializable{
    //impement

    private Stage stage;
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
    private static String lblMode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerName.setText(lblPlayer);
        String path = "" ;
        switch (lblVideo) {
            case "win":
                path = new File("src/Media/win.mp4").getAbsolutePath();
                break;
            case "losser":
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
    public static void setData(String player, String video, String mode) {
        lblPlayer = player;
        lblVideo = video;
        lblMode = mode;
    }
    

    public void switchToHome(ActionEvent event) throws IOException {//Home Screen
        mediaPlayer.stop();
        root = FXMLLoader.load(getClass().getResource("/Screens/Home_Screen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToGameAgain(ActionEvent event) throws IOException {//Game Screen
        mediaPlayer.stop();
        if(lblMode.equalsIgnoreCase("twoPlayer")){
            root = FXMLLoader.load(getClass().getResource("/Screens/Game_Screen.fxml"));
        }
        else if(lblMode.equalsIgnoreCase("pc")){
            root = FXMLLoader.load(getClass().getResource("/Screens/Pc_Game.fxml"));
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
      

    }
      
}
