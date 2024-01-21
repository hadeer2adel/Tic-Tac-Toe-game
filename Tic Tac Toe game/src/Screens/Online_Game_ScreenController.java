/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screens;

import DTO.Client;
import DTO.ConnectedClient;
import DTO.GameDetails;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.json.JsonObject;

/**
 * FXML Controller class
 *
 * @author win 10
 */
public class Online_Game_ScreenController implements Initializable {
    
    private Stage stage = Mainpkg.Main.getAppStage();
    private Scene scene;
    private Parent root;
    
    @FXML
    private StackPane stackPane1;
    @FXML
    private AnchorPane anchorPane1;
    @FXML
    private ImageView imag1;
    @FXML
    private ImageView imag2;
    @FXML
    private ImageView imag3;
    @FXML
    private Button btn_back;
    @FXML
    private Button btn_record;
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
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    
    private Client client;
    private static GameDetails game;
    private static int btnsClicked;
    private static Image xImage, oImage;
    private static boolean isGameEnd = false;
    private static Button[] gameBtn;
    private static String[] gameboard = {"", "", "",
                                  "", "", "",
                                  "", "", "",};
    

    public static void setGame(int id1, String name1, int id2, String name2 ) {
        game = new GameDetails(id1, name1, id2, name2);
    }
    
    private Button[] assignBtn(){
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
        client = ConnectedClient.getClient();
        
        label1.setText(game.getPlayerName_1());
        label3.setText(game.getPlayerName_2());
        label2.setText("" + game.getPlayerScore_1());
        label4.setText("" + game.getPlayerScore_2());
        
        xImage = new Image("/Images/X (7).png");
        oImage = new Image("/Images/o.png");
        gameBtn = assignBtn();
        
        btnsClicked = 0;
        isGameEnd = false;
        if(client.getId() == game.getPlayerId_2()){
            System.out.println("Screens.Online_Game_ScreenController.initialize()-------------------11");
            sentMovePrepare(true);
        }
    }   
    
    public void clickBtn1() {
        int n = 0;
        if (gameboard[n].equals("")) {
            DrawXorO(n);
        }
    }
    
    public void clickBtn2() {
        int n = 1;
        if (gameboard[n].equals("")) {
            DrawXorO(n);
        }
    }
    
    public void clickBtn3(){
        int n = 2;
        if (gameboard[n].equals("")) {
            DrawXorO(n);
        }
    }
    
    public void clickBtn4(){
        int n = 3;
        if (gameboard[n].equals("")) {
            DrawXorO(n);
        }
    }
    
    public void clickBtn5(){
        int n = 4;
        if (gameboard[n].equals("")) {
            DrawXorO(n);
        }
    }
    
    public void clickBtn6(){
        int n = 5;
        if (gameboard[n].equals("")) {
            DrawXorO(n);
        }
    }
    
    public void clickBtn7(){
        int n = 6;
        if (gameboard[n].equals("")) {
            DrawXorO(n);
        }
    }
    
    public void clickBtn8(){
        int n = 7;
        if (gameboard[n].equals("")) {
            DrawXorO(n);
        }
    }
    
    public void clickBtn9(){
        int n = 8;
        if (gameboard[n].equals("")) {
            DrawXorO(n);
        }
    }
   
  
    public void onlyDraw(int move , String ch, boolean end) {
        isGameEnd = end;
        sentMovePrepare(false);
        System.out.println("Screens.Online_Game_ScreenController.onlyDraw()------------------tt");
        btnsClicked++;
        
        if(ch.equals("x"))
            gameboard[move] = "x";
        else 
           gameboard[move] = "o";
        
        showImage(gameboard[move], gameBtn[move]);
        
        if (!isGameEnd && btnsClicked == 9) {
            isGameEnd = true;
            Online_Video_ScreenController.setData("It's Draw", "draw");
            try {
                switchToVideoScreen();
            } catch (IOException ex) {
                Logger.getLogger(Game_ScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(isGameEnd){
            Online_Video_ScreenController.setData("You Lose", "loss");
            try {
                switchToVideoScreen();
            } catch (IOException ex) {
                Logger.getLogger(Game_ScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void sentMovePrepare(boolean disable) {
        for(Button btn :gameBtn)
            btn.setDisable(disable);
    }
    
    @FXML
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
    
    private void drawLine(int n1, int n2, int n3) {
        gameBtn[n1].setStyle("-fx-background-color:linear-gradient(to bottom, navy, lightskyblue);"
                + "-fx-effect: dropshadow(three-pass-box, darkblue, 10, 0, 0, 0);");
        gameBtn[n2].setStyle("-fx-background-color:linear-gradient(to bottom, darkblue, lightskyblue);"
                + "-fx-effect: dropshadow(three-pass-box, darkblue, 10, 0, 0, 0);");
        gameBtn[n3].setStyle("-fx-background-color:linear-gradient(to bottom, midnightblue, lightskyblue);"
                + "-fx-effect: dropshadow(three-pass-box, darkblue, 10, 0, 0, 0);");
    }
    
    private String isWin() {
        System.out.println("Screens.Online_Game_ScreenController.isWin()");
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
     @FXML
    private void DrawXorO(int n) {
        btnsClicked++;
        if (!isGameEnd) {
            if (game.getPlayerId_1() == client.getId()) {
                gameboard[n] = "x";
                showImage(gameboard[n], gameBtn[n]);
                sentMovePrepare(true);
                checkWinner();
                client.sendMove(game.getPlayerId_1(), game.getPlayerId_2(), n, gameboard[n], isGameEnd);
            } else {
                gameboard[n] = "o";
                showImage(gameboard[n], gameBtn[n]);
                sentMovePrepare(true);
                checkWinner();
                client.sendMove(game.getPlayerId_2(), game.getPlayerId_1(), n, gameboard[n], isGameEnd);
            }
        }
        if (btnsClicked == 9) {
            isGameEnd = true;
        }
    }

    public void checkWinner() {

        if (btnsClicked > 4) {
            String Winner = isWin();
            if (Winner.equals("x")) {
                game.updatePlayerScore_1();
                isGameEnd = true;
                Online_Video_ScreenController.setData("You Win", "win");
                try {
                    switchToVideoScreen();
                } catch (IOException ex) {
                    Logger.getLogger(Game_ScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (Winner.equals("o")) {
                game.updatePlayerScore_2();
                isGameEnd = true;
                Online_Video_ScreenController.setData("You Win", "win");
                try {
                    switchToVideoScreen();
                } catch (IOException ex) {
                    Logger.getLogger(Game_ScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if ((!isGameEnd) && btnsClicked == 9) {
            Online_Video_ScreenController.setData("It's Draw", "draw");
            try {
                switchToVideoScreen();
            } catch (IOException ex) {
                Logger.getLogger(Game_ScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void switchToVideoScreen() throws IOException {

        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(e -> {
            try {
                root = FXMLLoader.load(getClass().getResource("/Screens/Online_Video_Screen.fxml"));
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
        });
        delay.play();
    }
   
    public static void setJson (JsonObject jo){
        Response_ScreenController.jsonObject = jo;
    }
}
