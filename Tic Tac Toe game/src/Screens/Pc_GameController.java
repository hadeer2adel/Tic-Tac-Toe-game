package Screens;

import DTO.GameDetails;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;
import javafx.util.Duration;

public class Pc_GameController implements Initializable {
    private Stage stage = Mainpkg.Main.getAppStage();
     private Scene scene;
     private Parent root;

    private static GameDetails game;

    private int btnsClicked;
    private boolean X_or_O, isGameEnd = false;

    private static String level;

    public static void setLevel(String s) {
        level = s;
    }

    @FXML
    private Label label1, label3;
    @FXML
    private Label label2, label4;
    @FXML
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    private Image xImage, oImage;
    private Button[] gameBtn;
    private String[] gameboard = {"", "", "", "", "", "", "", "", ""};
    private ActionEvent event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label1.setText(game.getPlayerName_1());
        label3.setText(game.getPlayerName_2());
        label2.setText("" + game.getPlayerScore_1());
        label4.setText("" + game.getPlayerScore_2());

        xImage = new Image(getClass().getResourceAsStream("/Images/X (7).png"));
        oImage = new Image(getClass().getResourceAsStream("/Images/o.png"));

        btnsClicked = 0;
        X_or_O = true;
        isGameEnd = false;
        gameBtn = new Button[]{btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};
    }

    public void clickBtn1(ActionEvent event) {
        handlePlayerMove(0, event);
        System.out.println("------------------------------------");
    }

    public void clickBtn2(ActionEvent event) {
        handlePlayerMove(1, event);
    }

    public void clickBtn3(ActionEvent event) {
        handlePlayerMove(2, event);
    }

    public void clickBtn4(ActionEvent event) {
        handlePlayerMove(3, event);
    }

    public void clickBtn5(ActionEvent event) {
        handlePlayerMove(4, event);
    }

    public void clickBtn6(ActionEvent event) {
        handlePlayerMove(5, event);
    }

    public void clickBtn7(ActionEvent event) {
        handlePlayerMove(6, event);
    }

    public void clickBtn8(ActionEvent event) {
        handlePlayerMove(7, event);
    }

    public void clickBtn9(ActionEvent event) {
        handlePlayerMove(8, event);
    }

    public void handlePlayerMove(int buttonIndex, ActionEvent event) {
        if (buttonIndex >= 0 && buttonIndex < gameBtn.length && gameboard[buttonIndex].equals("")) {
            gameBtn[buttonIndex] = (Button) event.getSource();
            drawXorO(buttonIndex, event);

            switch (level) {
                case "easy":
                    System.out.println("------------------------easy");
                    computerEasyMove(event);
                    break;
                case "hard":
                    System.out.println("------------------------hard");
                    computerHardMove(event);
                    break;
            }

        }
    }

    public void computerEasyMove(ActionEvent event) {
        if (!isGameEnd && btnsClicked < 9) {
            int randomNum;
            do {
                randomNum = getRandomNum();
            } while (!gameboard[randomNum].equals(""));
            gameboard[randomNum] = "o";
            drawXorO(randomNum, event);

        }
    }

    /* public void computerHardMove(ActionEvent event) {
        if (!isGameEnd && btnsClicked < 9) {
            int bestMove;
            do {
                bestMove = getBestMove();
            } while (!gameboard[bestMove].equals(""));
            gameboard[bestMove] = "o";
            drawXorO(bestMove, event);

        }
        System.out.println("------------------------cpmmove");
    }

    private int getBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;

        for (int i = 0; i < 9; i++) {
            if (gameboard[i].equals("")) {
                gameboard[i] = "o";
                int score = minimax(0, false);
                gameboard[i] = "";

                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }
        return bestMove;

    }

    private int minimax(int depth, boolean isMaximizing) {

        String winner = isWin();
        if ("x".equals(winner)) {
            return -10 + depth; // Human wins
        } else if ("o".equals(winner)) {
            return 10 - depth; // PC wins
        } else if (btnsClicked == 9) {
            return 0; // Draw
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (gameboard[i].equals("")) {
                    gameboard[i] = "o";
                    int score = minimax(depth + 1, false);
                    gameboard[i] = "";
                    bestScore = Math.max(score, bestScore);
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (gameboard[i].equals("")) {
                    gameboard[i] = "x";
                    int score = minimax(depth + 1, true);
                    gameboard[i] = "";
                    bestScore = Math.min(score, bestScore);
                }
            }
            return bestScore;
        }
    }
     */
    public void computerHardMove(ActionEvent event) {
        if (!isGameEnd && btnsClicked < 9) {
            int bestMove = getBestMove();

            gameboard[bestMove] = "o";
            drawXorO(bestMove, event);
        }
    }

    private int getBestMove() {
        int[] result = minimax(0, true);
        return result[1];
    }

    private int[] minimax(int depth, boolean isMaximizing) {
        int[] bestMove = new int[]{isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE, -1};

        for (int i = 0; i < 9; i++) {
            if (gameboard[i].equals("")) {
                gameboard[i] = isMaximizing ? "o" : "x";

                String win = checkWin();

                if (win.equals("o")) {
                    gameboard[i] = "";
                    return new int[]{10 - depth, i};
                } else if (win.equals("x")) {
                    gameboard[i] = "";
                    return new int[]{-10 + depth, i};
                } else if (btnsClicked == 9) {
                    gameboard[i] = "";
                    return new int[]{0, i};
                }

                int[] score = minimax(depth + 1, !isMaximizing);

                gameboard[i] = "";

                if ((isMaximizing && score[0] > bestMove[0]) || (!isMaximizing && score[0] < bestMove[0])) {
                    bestMove[0] = score[0];
                    bestMove[1] = i;
                }
            }
        }

        return bestMove;
    }

    private void drawXorO(int n, ActionEvent event) {
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
                    Video_ScreenController.setData("You Win", "win", "pc");
                    try {
                        switchToVideoScreen(event);
                    } catch (IOException ex) {
                        Logger.getLogger(Pc_GameController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (Winner.equals("o")) {
                    try {
                        game.updatePlayerScore_2();
                        isGameEnd = true;
                        Video_ScreenController.setData("You Loss", "losser", "pc");
                        switchToVideoScreen(event);
                    } catch (IOException ex) {
                        Logger.getLogger(Pc_GameController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
            if ((!isGameEnd) && btnsClicked == 9) {
                isGameEnd = true;
                Video_ScreenController.setData("It's Draw", "draw", "pc");
                try {
                    switchToVideoScreen(event);
                } catch (IOException ex) {
                    Logger.getLogger(Pc_GameController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private int getRandomNum() {
        Random random = new Random();
        return random.nextInt(9);
    }

    private String isWin() {
        String win = "";

        if ((!gameboard[0].equals("")) && gameboard[0].equals(gameboard[4]) && gameboard[0].equals(gameboard[8])) {
            win = gameboard[0]; // Diagonal 1
           drawLine(0, 4, 8);
        } else if ((!gameboard[2].equals("")) && gameboard[2].equals(gameboard[4]) && gameboard[2].equals(gameboard[6])) {
            win = gameboard[2]; // Diagonal 2
            drawLine(2, 4, 6);
        } else {
            for (int i = 0, j = 0; i < 9 || j < 3; i += 3, j += 1) {
                if ((!gameboard[i].equals("")) && gameboard[i].equals(gameboard[i + 1]) && gameboard[i].equals(gameboard[i + 2])) {
                    win = gameboard[i]; // Row
                   drawLine(i, i + 1, i + 2);
                    break;
                } else if ((!gameboard[j].equals("")) && gameboard[j].equals(gameboard[j + 3]) && gameboard[j].equals(gameboard[j + 6])) {
                    win = gameboard[j]; // Column
                   drawLine(j, j + 3, j + 6);
                    break;
                }
            }
        }
        return win;
    }
        private String checkWin() {
        String win = "";

        if ((!gameboard[0].equals("")) && gameboard[0].equals(gameboard[4]) && gameboard[0].equals(gameboard[8])) {
            win = gameboard[0]; // Diagonal 1
          
        } else if ((!gameboard[2].equals("")) && gameboard[2].equals(gameboard[4]) && gameboard[2].equals(gameboard[6])) {
            win = gameboard[2]; // Diagonal 2
           
        } else {
            for (int i = 0, j = 0; i < 9 || j < 3; i += 3, j += 1) {
                if ((!gameboard[i].equals("")) && gameboard[i].equals(gameboard[i + 1]) && gameboard[i].equals(gameboard[i + 2])) {
                    win = gameboard[i]; // Row
                    break;
                } else if ((!gameboard[j].equals("")) && gameboard[j].equals(gameboard[j + 3]) && gameboard[j].equals(gameboard[j + 6])) {
                    win = gameboard[j]; // Column
                  
                    break;
                }
            }
        }
        return win;
    }

    private void drawLine(int n1, int n2, int n3) {
        gameBtn[n1].setStyle("-fx-background-color: linear-gradient(to bottom, navy, lightskyblue);"
                + "-fx-effect: dropshadow(three-pass-box, darkblue, 10, 0, 0, 0);");
        gameBtn[n2].setStyle("-fx-background-color: linear-gradient(to bottom, darkblue, lightskyblue);"
                + "-fx-effect: dropshadow(three-pass-box, darkblue, 10, 0, 0, 0);");
        gameBtn[n3].setStyle("-fx-background-color: linear-gradient(to bottom, midnightblue, lightskyblue);"
                + "-fx-effect: dropshadow(three-pass-box, darkblue, 10, 0, 0, 0);");
    }

    public static void setPlayers(String player1, String player2) {
        game = new GameDetails(player1, player2);
    }

    public void switchToVideoScreen(ActionEvent event) throws IOException {

        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(e -> {
            try {
                root = FXMLLoader.load(getClass().getResource("/Screens/Video_Screen.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
        });
        delay.play();
    }

    public void switchToSingleMode(ActionEvent event) throws IOException{//single mode Screen
        root = FXMLLoader.load(getClass().getResource("/Screens/SingleMode_Screen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
