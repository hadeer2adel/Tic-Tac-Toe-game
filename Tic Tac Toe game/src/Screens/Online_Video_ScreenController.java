/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screens;

import DTO.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonWriter;

/**
 * FXML Controller class
 *
 * @author Ahmed KH
 */
public class Online_Video_ScreenController implements Initializable {
    private Stage stage = Mainpkg.Main.getAppStage();
    private Scene scene;
    private Parent root;
    private Client client;
    private static GameDetails game;
    private static int replay;
    private static JsonObject jsonObject;
    
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
    
    public static void setReplay(int r){
        replay = r;
    }
    
    public static void setJson (JsonObject jo){
        jsonObject = jo;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        replay = 0;
        client = ConnectedClient.getClient();
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
    
    private void updateScore(){
        FileInputStream fis = null;
        FileWriter writer = null;
        int myId = ConnectedClient.getClient().getId();
        try {
            File f = new File("Files/playerData"+myId+".json");
            fis = new FileInputStream(f);
            JsonReader reader = Json.createReader(fis);
            JsonObject obj = reader.readObject();
            
            int score = obj.getInt("score");
            if(myId == game.getPlayerId_1())
                score += game.getPlayerScore_1();
            else
                score += game.getPlayerScore_2();
            
            JsonObject updatedObj = Json.createObjectBuilder()
                    .add("response", "login")
                    .add("status", "success")
                    .add("id", obj.getInt("id"))
                    .add("name", obj.getString("name"))
                    .add("score", score)
                    .build();

            writer = new FileWriter(f);
            writer.write(updatedObj.toString());
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Online_Video_ScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Online_Video_ScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Online_Video_ScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void setData(String player, String video, GameDetails g) {
        lblPlayer = player;
        lblVideo = video;
        game = g;
    }
    
    public void palyAgain(ActionEvent event) throws IOException {//Home Screen
        mediaPlayer.stop();
        if(client.isServerConnected()){
            checkReplay(1);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Server");
            alert.setHeaderText(null);
            alert.setContentText("Server is OFF now");
            alert.showAndWait();
        }

    }

    public void newGame(ActionEvent event) throws IOException {//Game Screen
        mediaPlayer.stop();
        if(client.isServerConnected()){
            checkReplay(2);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Server");
            alert.setHeaderText(null);
            alert.setContentText("Server is OFF now");
            alert.showAndWait();
        }

    }
    
    private void checkReplay(int r){
        if(replay == 0){
            System.out.println("-------------------------checkReplay-----0");
            client.playAgain(r, game);
        }
        else if(replay == 1 && r == 1){
            System.out.println("-------------------------checkReplay-----1");
            client.receivePlayAgainHandeler(jsonObject, true);
            openGameScreen();
        }
        else if(replay == 2 || r == 2){
            System.out.println("-------------------------checkReplay-----2");
            client.receivePlayAgainHandeler(jsonObject, false);
            openInvitationScreen();
        }
    }
    
    public void openInvitationScreen() {
        try {
            updateScore();
            root = FXMLLoader.load(getClass().getResource("/Screens/Invitation_Screen1.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Invitation_Screen1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void openGameScreen() {
        try {
            root = FXMLLoader.load(getClass().getResource("/Screens/Online_Game_Screen.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Invitation_Screen1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
