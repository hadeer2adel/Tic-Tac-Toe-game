package Screens;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public abstract class Login_Screen extends StackPane {

    protected final AnchorPane anchorPane;
    protected final ImageView imageView;
    protected final TextField field_Email;
    protected final PasswordField field_Password;
    protected final ImageView imageView0;
    protected final Button btn_Login;
    protected final Button btn_Back;
    protected final Label label_Create_New_Acc;
    protected final Label label_Signup;

    public Login_Screen() {

        anchorPane = new AnchorPane();
        imageView = new ImageView();
        field_Email = new TextField();
        field_Password = new PasswordField();
        imageView0 = new ImageView();
        btn_Login = new Button();
        btn_Back = new Button();
        label_Create_New_Acc = new Label();
        label_Signup = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        anchorPane.setPrefHeight(200.0);
        anchorPane.setPrefWidth(200.0);

        imageView.setFitHeight(400.0);
        imageView.setFitWidth(601.0);
        imageView.setPickOnBounds(true);
        imageView.setImage(new Image(getClass().getResource("../Images/Rectangle%2012.png").toExternalForm()));

        field_Email.setLayoutX(203.0);
        field_Email.setLayoutY(172.0);
        field_Email.setPrefHeight(41.0);
        field_Email.setPrefWidth(218.0);
        field_Email.setPromptText("  e-mail");
        field_Email.setStyle("-fx-background-color: rgba(61, 122, 214, 1); -fx-background-radius: 20px; -fx-border-color: rgba(255, 255, 255, 1); -fx-border-radius: 20px;");
        field_Email.setFont(new Font("System Italic", 18.0));

        field_Password.setLayoutX(203.0);
        field_Password.setLayoutY(237.0);
        field_Password.setPrefHeight(41.0);
        field_Password.setPrefWidth(218.0);
        field_Password.setPromptText("  password");
        field_Password.setStyle("-fx-background-color: rgba(61, 122, 214, 1); -fx-background-radius: 20px; -fx-border-color: rgba(255, 255, 255, 1); -fx-border-radius: 20px;");
        field_Password.setFont(new Font("System Italic", 18.0));

        imageView0.setFitHeight(141.0);
        imageView0.setFitWidth(345.0);
        imageView0.setLayoutX(148.0);
        imageView0.setLayoutY(24.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("../Images/img2-removebg-preview%20(1).png").toExternalForm()));

        btn_Login.setLayoutX(203.0);
        btn_Login.setLayoutY(294.0);
        btn_Login.setMnemonicParsing(false);
        btn_Login.setPrefHeight(41.0);
        btn_Login.setPrefWidth(218.0);
        btn_Login.setStyle("-fx-background-color: rgba(252, 208, 21, 1); -fx-background-radius: 20px; -fx-border-color: rgba(255, 255, 255, 1); -fx-border-radius: 20px;");
        btn_Login.setText("Log in");
        btn_Login.setFont(new Font("System Bold Italic", 18.0));

        btn_Back.setLayoutX(62.0);
        btn_Back.setLayoutY(350.0);
        btn_Back.setMnemonicParsing(false);
        btn_Back.setStyle("-fx-background-color: rgba(252, 208, 21, 1); -fx-background-radius: 20px; -fx-border-color: rgba(255, 255, 255, 1); -fx-border-radius: 20px;");
        btn_Back.setText("Back");
        btn_Back.setFont(new Font("System Bold Italic", 15.0));

        label_Create_New_Acc.setLayoutX(199.0);
        label_Create_New_Acc.setLayoutY(350.0);
        label_Create_New_Acc.setPrefHeight(17.0);
        label_Create_New_Acc.setPrefWidth(190.0);
        label_Create_New_Acc.setText("Create new account?");
        label_Create_New_Acc.setTextFill(javafx.scene.paint.Color.WHITE);
        label_Create_New_Acc.setFont(new Font("System Italic", 18.0));

        label_Signup.setLayoutX(367.0);
        label_Signup.setLayoutY(351.0);
        label_Signup.setText("Sign Up");
        label_Signup.setUnderline(true);
        label_Signup.setFont(new Font("System Italic", 17.0));

        anchorPane.getChildren().add(imageView);
        anchorPane.getChildren().add(field_Email);
        anchorPane.getChildren().add(field_Password);
        anchorPane.getChildren().add(imageView0);
        anchorPane.getChildren().add(btn_Login);
        anchorPane.getChildren().add(btn_Back);
        anchorPane.getChildren().add(label_Create_New_Acc);
        anchorPane.getChildren().add(label_Signup);
        getChildren().add(anchorPane);

    }
}
