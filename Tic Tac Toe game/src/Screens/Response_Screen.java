package Screens;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Response_Screen extends StackPane {

    protected final AnchorPane anchorPane;
    protected final ImageView backgroundImage;
    protected final Rectangle rectangle;
    protected final Text CompleteMessage;
    protected final Label nameOfPlayer;
    protected final ImageView imageSmile;
    protected final Button btn_ofCourse;
    protected final Button btn_antherTime;

    public Response_Screen() {

        anchorPane = new AnchorPane();
        backgroundImage = new ImageView();
        rectangle = new Rectangle();
        CompleteMessage = new Text();
        nameOfPlayer = new Label();
        imageSmile = new ImageView();
        btn_ofCourse = new Button();
        btn_antherTime = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        getStylesheets().add("/Screens/Response_Screen.css");

        anchorPane.setPrefHeight(200.0);
        anchorPane.setPrefWidth(200.0);

        backgroundImage.setFitHeight(400.0);
        backgroundImage.setFitWidth(607.0);
        backgroundImage.setLayoutX(-3.0);
        backgroundImage.setPickOnBounds(true);
        backgroundImage.setImage(new Image(getClass().getResource("../Images/Rectangle%2012.png").toExternalForm()));

        rectangle.setArcHeight(5.0);
        rectangle.setArcWidth(5.0);
        rectangle.setHeight(223.0);
        rectangle.setLayoutX(96.0);
        rectangle.setLayoutY(96.0);
        rectangle.setStroke(javafx.scene.paint.Color.BLACK);
        rectangle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        rectangle.setStrokeWidth(0.0);
        rectangle.getStyleClass().add("linearBg");
        rectangle.setWidth(410.0);

        CompleteMessage.setFill(javafx.scene.paint.Color.WHITE);
        CompleteMessage.setLayoutX(198.0);
        CompleteMessage.setLayoutY(145.0);
        CompleteMessage.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        CompleteMessage.setStrokeWidth(0.0);
        CompleteMessage.setText(" is exesting to play with you !");
        CompleteMessage.setFont(new Font("System Italic", 20.0));

        nameOfPlayer.setLayoutX(156.0);
        nameOfPlayer.setLayoutY(122.0);
        nameOfPlayer.setText("sami");
        nameOfPlayer.setTextFill(javafx.scene.paint.Color.WHITE);
        nameOfPlayer.setFont(new Font("System Italic", 20.0));

        imageSmile.setFitHeight(85.0);
        imageSmile.setFitWidth(158.0);
        imageSmile.setLayoutX(251.0);
        imageSmile.setLayoutY(165.0);
        imageSmile.setPickOnBounds(true);
        imageSmile.setPreserveRatio(true);
        imageSmile.setImage(new Image(getClass().getResource("../Images/Ellipse%206.png").toExternalForm()));

        btn_ofCourse.setLayoutX(156.0);
        btn_ofCourse.setLayoutY(262.0);
        btn_ofCourse.setMnemonicParsing(false);
        btn_ofCourse.setPrefHeight(45.0);
        btn_ofCourse.setPrefWidth(120.0);
        btn_ofCourse.getStyleClass().add("btn");
        btn_ofCourse.getStylesheets().add("/Screens/Response_Screen.css");
        btn_ofCourse.setText("of Course! ");
        btn_ofCourse.setFont(new Font("System Bold Italic", 17.0));

        btn_antherTime.setLayoutX(339.0);
        btn_antherTime.setLayoutY(262.0);
        btn_antherTime.setMnemonicParsing(false);
        btn_antherTime.setPrefHeight(45.0);
        btn_antherTime.setPrefWidth(120.0);
        btn_antherTime.getStyleClass().add("btn");
        btn_antherTime.setText("anther time");
        btn_antherTime.setFont(new Font("System Bold Italic", 17.0));

        anchorPane.getChildren().add(backgroundImage);
        anchorPane.getChildren().add(rectangle);
        anchorPane.getChildren().add(CompleteMessage);
        anchorPane.getChildren().add(nameOfPlayer);
        anchorPane.getChildren().add(imageSmile);
        anchorPane.getChildren().add(btn_ofCourse);
        anchorPane.getChildren().add(btn_antherTime);
        getChildren().add(anchorPane);

    }
}
