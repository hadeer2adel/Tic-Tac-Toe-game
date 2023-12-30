package Screens;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class Frist_Screen extends StackPane {

    protected final AnchorPane anchorPane;
    protected final ImageView backgroundImage;
    protected final Button btn_play;

    public Frist_Screen() {

        anchorPane = new AnchorPane();
        backgroundImage = new ImageView();
        btn_play = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        getStylesheets().add("/Screens/Frist_Screen.css");

        anchorPane.setPrefHeight(200.0);
        anchorPane.setPrefWidth(200.0);

        backgroundImage.setFitHeight(400.0);
        backgroundImage.setFitWidth(600.0);
        backgroundImage.setPickOnBounds(true);
        backgroundImage.setImage(new Image(getClass().getResource("../Images/img2%202.png").toExternalForm()));

        btn_play.setLayoutX(236.0);
        btn_play.setLayoutY(347.0);
        btn_play.setMnemonicParsing(false);
        btn_play.setPrefHeight(39.0);
        btn_play.setPrefWidth(129.0);
        btn_play.getStyleClass().add("btn_play");
        btn_play.setText("let's play");
        btn_play.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        btn_play.setFont(new Font("System Bold Italic", 18.0));

        anchorPane.getChildren().add(backgroundImage);
        anchorPane.getChildren().add(btn_play);
        getChildren().add(anchorPane);

    }
}
