/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screens;

import DTO.GameDetails;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class Game_ScreenController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private static GameDetails game;

    private int btnsClicked;
    private boolean X_or_O, isGameEnd = false;

    @FXML
    private Label label1, label3;
    @FXML
    private Label label2, label4;
    private Image xImage, oImage;
    private Button[] gameBtn;
    private String[] gameboard = {"", "", "",
        "", "", "",
        "", "", "",};

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        label1.setText(game.getPlayerName_1());
        label3.setText(game.getPlayerName_2());
        label2.setText("" + game.getPlayerScore_1());
        label4.setText("" + game.getPlayerScore_2());

        xImage = new Image("/Images/X (7).png");
        oImage = new Image("/Images/o.png");

        btnsClicked = 0;
        X_or_O = true;
        isGameEnd = false;
        gameBtn = new Button[9];
    }

    public void clickBtn1(ActionEvent event) {
        int n = 0;
        if (gameboard[n].equals("")) {
            gameBtn[n] = (Button) event.getSource();
            DrawXorO(n, event);
        }
    }

    public void clickBtn2(ActionEvent event){
        int n = 1;
        if (gameboard[n].equals("")) {
            gameBtn[n] = (Button) event.getSource();
            DrawXorO(n, event);
        }
    }

    public void clickBtn3(ActionEvent event){
        int n = 2;
        if (gameboard[n].equals("")) {
            gameBtn[n] = (Button) event.getSource();
            DrawXorO(n, event);
        }
    }

    public void clickBtn4(ActionEvent event){
        int n = 3;
        if (gameboard[n].equals("")) {
            gameBtn[n] = (Button) event.getSource();
            DrawXorO(n, event);
        }
    }

    public void clickBtn5(ActionEvent event){
        int n = 4;
        if (gameboard[n].equals("")) {
            gameBtn[n] = (Button) event.getSource();
            DrawXorO(n, event);
        }
    }

    public void clickBtn6(ActionEvent event) {
        int n = 5;
        if (gameboard[n].equals("")) {
            gameBtn[n] = (Button) event.getSource();
            DrawXorO(n, event);
        }
    }

    public void clickBtn7(ActionEvent event){
        int n = 6;
        if (gameboard[n].equals("")) {
            gameBtn[n] = (Button) event.getSource();
            DrawXorO(n, event);
        }
    }

    public void clickBtn8(ActionEvent event){
        int n = 7;
        if (gameboard[n].equals("")) {
            gameBtn[n] = (Button) event.getSource();
            DrawXorO(n, event);
        }
    }

    public void clickBtn9(ActionEvent event){
        int n = 8;
        if (gameboard[n].equals("")) {
            gameBtn[n] = (Button) event.getSource();
            DrawXorO(n, event);
        }
    }

    private void DrawXorO(int n, ActionEvent event){
        btnsClicked++;
        if (!isGameEnd) {
            if (X_or_O) {
                gameboard[n] = "x";
                ImageView xImageView = new ImageView(xImage);
                xImageView.setFitHeight(gameBtn[n].getHeight() - 10);
                xImageView.setFitWidth(gameBtn[n].getWidth() - 20);
                gameBtn[n].setGraphic(xImageView);
                X_or_O = false;
            } else {
                gameboard[n] = "o";
                ImageView oImageView = new ImageView(oImage);
                oImageView.setFitHeight(gameBtn[n].getHeight() - 10);
                oImageView.setFitWidth(gameBtn[n].getWidth() - 20);
                gameBtn[n].setGraphic(oImageView);
                X_or_O = true;
            }
            if (btnsClicked > 4) {
                String Winner = isWin();
                if (Winner.equals("x")) {
                    game.updatePlayerScore_1();
                    isGameEnd = true;
                    Video_ScreenController.setData("The Winner is "+game.getPlayerName_1(), "win");
                    try {
                        switchToVideoScreen(event);
                    } catch (IOException ex) {
                        Logger.getLogger(Game_ScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (Winner.equals("o")) {
                    game.updatePlayerScore_2();
                    isGameEnd = true;
                    Video_ScreenController.setData("The Winner is "+game.getPlayerName_2(), "win");
                    try {
                        switchToVideoScreen(event);
                    } catch (IOException ex) {
                        Logger.getLogger(Game_ScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if ((!isGameEnd) && btnsClicked == 9) {
                isGameEnd = true;
                Video_ScreenController.setData("It's Draw", "draw");
                try {
                    switchToVideoScreen(event);
                } catch (IOException ex) {
                    Logger.getLogger(Game_ScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private String isWin() {
        String win = "";

        if ((!gameboard[0].equals("")) && gameboard[0].equals(gameboard[4]) && gameboard[0].equals(gameboard[8])) //check diagonal 1
        {
            win = gameboard[0];
            drawLine(0, 4, 8);
        } 
        else if ((!gameboard[2].equals("")) && gameboard[2].equals(gameboard[4]) && gameboard[2].equals(gameboard[6])) //check diagonal 2
        {
            win = gameboard[2];
            drawLine(2, 4, 6);
        } 
        else // check row or column
        {
            for (int i = 0, j = 0; i < 9 || j < 3; i += 3, j += 1) {
                if ((!gameboard[i].equals("")) && gameboard[i].equals(gameboard[i + 1]) && gameboard[i].equals(gameboard[i + 2])) //check row
                {
                    win = gameboard[i];
                    drawLine(i, i + 1, i + 2);
                    break;
                }
                else if ((!gameboard[j].equals("")) && gameboard[j].equals(gameboard[j + 3]) && gameboard[j].equals(gameboard[j + 6])) //check column
                {
                    win = gameboard[j];
                    drawLine(j, j + 3, j + 6);
                    break;
                }
            }
        }
        return win;
    }

    private void drawLine(int n1, int n2, int n3) {
        gameBtn[n1].setStyle("-fx-background-color:linear-gradient(to bottom, navy, lightskyblue);"
                + "-fx-effect: dropshadow(three-pass-box, darkblue, 10, 0, 0, 0);");
        gameBtn[n2].setStyle("-fx-background-color:linear-gradient(to bottom, darkblue, lightskyblue);"
                + "-fx-effect: dropshadow(three-pass-box, darkblue, 10, 0, 0, 0);");
        gameBtn[n3].setStyle("-fx-background-color:linear-gradient(to bottom, midnightblue, lightskyblue);"
                + "-fx-effect: dropshadow(three-pass-box, darkblue, 10, 0, 0, 0);");
    }

    public static void setPlayers(String player1, String player2) {
        game = new GameDetails(player1, player2);
    }

    public void switchTo2playermode(ActionEvent event) throws IOException {//Game Screen
        root = FXMLLoader.load(getClass().getResource("/Screens/TwoPlayerMode_Screen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToVideoScreen(ActionEvent event) throws IOException {
        
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(e -> {
            try {
                root = FXMLLoader.load(getClass().getResource("/Screens/Video_Screen.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
        });
        delay.play();
    }
}