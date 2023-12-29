package Screens;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public abstract class Signup_Screen extends StackPane {

    protected final AnchorPane ancorpn_AnchorPane;
    protected final ImageView imgvew_ImageView;
    protected final TextField field_Name;
    protected final TextField field_Email;
    protected final PasswordField field_Password;
    protected final PasswordField field_ConfirmPassword;
    protected final Button btn_SignUp;
    protected final ImageView imgviw_TicTacToe;
    protected final Button btn_Back;

    public Signup_Screen() {

        ancorpn_AnchorPane = new AnchorPane();
        imgvew_ImageView = new ImageView();
        field_Name = new TextField();
        field_Email = new TextField();
        field_Password = new PasswordField();
        field_ConfirmPassword = new PasswordField();
        btn_SignUp = new Button();
        imgviw_TicTacToe = new ImageView();
        btn_Back = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        ancorpn_AnchorPane.setPrefHeight(200.0);
        ancorpn_AnchorPane.setPrefWidth(200.0);

        imgvew_ImageView.setFitHeight(401.0);
        imgvew_ImageView.setFitWidth(600.0);
        imgvew_ImageView.setPickOnBounds(true);
        imgvew_ImageView.setImage(new Image(getClass().getResource("../Images/Rectangle%2012.png").toExternalForm()));

        field_Name.setLayoutX(215.0);
        field_Name.setLayoutY(159.0);
        field_Name.setPrefHeight(33.0);
        field_Name.setPrefWidth(213.0);
        field_Name.setPromptText("  Name");
        field_Name.setStyle("-fx-background-color: rgba(61, 122, 214, 1); -fx-background-radius: 20px; -fx-border-color: rgba(255, 255, 255, 1); -fx-border-radius: 20px;");
        field_Name.setFont(new Font("System Italic", 15.0));

        field_Email.setLayoutX(213.0);
        field_Email.setLayoutY(201.0);
        field_Email.setPrefHeight(33.0);
        field_Email.setPrefWidth(213.0);
        field_Email.setPromptText("  e-mail");
        field_Email.setStyle("-fx-background-color: rgba(61, 122, 214, 1); -fx-background-radius: 20px; -fx-border-radius: 20px; -fx-border-color: rgba(255, 255, 255, 1);");
        field_Email.setFont(new Font("System Italic", 15.0));

        field_Password.setLayoutX(213.0);
        field_Password.setLayoutY(242.0);
        field_Password.setPrefHeight(33.0);
        field_Password.setPrefWidth(213.0);
        field_Password.setPromptText("  password");
        field_Password.setStyle("-fx-background-color: rgba(61, 122, 214, 1); -fx-border-color: rgba(255, 255, 255, 1); -fx-background-radius: 20px; -fx-border-radius: 20px;");
        field_Password.setFont(new Font("System Italic", 15.0));

        field_ConfirmPassword.setLayoutX(213.0);
        field_ConfirmPassword.setLayoutY(282.0);
        field_ConfirmPassword.setPrefHeight(33.0);
        field_ConfirmPassword.setPrefWidth(213.0);
        field_ConfirmPassword.setPromptText("  confirm password");
        field_ConfirmPassword.setStyle("-fx-background-color: rgba(61, 122, 214, 1); -fx-border-color: rgba(255, 255, 255, 1); -fx-border-radius: 20px; -fx-background-radius: 20px;");
        field_ConfirmPassword.setFont(new Font("System Italic", 15.0));

        btn_SignUp.setLayoutX(213.0);
        btn_SignUp.setLayoutY(323.0);
        btn_SignUp.setMnemonicParsing(false);
        btn_SignUp.setPrefHeight(40.0);
        btn_SignUp.setPrefWidth(213.0);
        btn_SignUp.setStyle("-fx-background-color: rgba(252, 208, 21, 1); -fx-border-color: rgba(255, 255, 255, 1); -fx-background-radius: 20px; -fx-border-radius: 20px;");
        btn_SignUp.setText("Sign Up");
        btn_SignUp.setFont(new Font("System Bold Italic", 17.0));

        imgviw_TicTacToe.setFitHeight(136.0);
        imgviw_TicTacToe.setFitWidth(290.0);
        imgviw_TicTacToe.setLayoutX(164.0);
        imgviw_TicTacToe.setLayoutY(21.0);
        imgviw_TicTacToe.setPickOnBounds(true);
        imgviw_TicTacToe.setPreserveRatio(true);
        imgviw_TicTacToe.setImage(new Image(getClass().getResource("../Images/img2-removebg-preview%20(1).png").toExternalForm()));

        btn_Back.setLayoutX(67.0);
        btn_Back.setLayoutY(343.0);
        btn_Back.setMnemonicParsing(false);
        btn_Back.setStyle("-fx-background-color: rgba(252, 208, 21, 1); -fx-background-radius: 20px; -fx-border-color: rgba(255, 255, 255, 1); -fx-border-radius: 20px;");
        btn_Back.setText("Back");
        btn_Back.setFont(new Font("System Bold Italic", 17.0));

        ancorpn_AnchorPane.getChildren().add(imgvew_ImageView);
        ancorpn_AnchorPane.getChildren().add(field_Name);
        ancorpn_AnchorPane.getChildren().add(field_Email);
        ancorpn_AnchorPane.getChildren().add(field_Password);
        ancorpn_AnchorPane.getChildren().add(field_ConfirmPassword);
        ancorpn_AnchorPane.getChildren().add(btn_SignUp);
        ancorpn_AnchorPane.getChildren().add(imgviw_TicTacToe);
        ancorpn_AnchorPane.getChildren().add(btn_Back);
        getChildren().add(ancorpn_AnchorPane);

    }
}
