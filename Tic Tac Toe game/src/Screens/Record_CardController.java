/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screens;

import DTO.Client;
import DTO.Records;
import DTO.UserData;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed KH
 */
public class Record_CardController implements Initializable {

    private Stage stage = Mainpkg.Main.getAppStage();
    private Scene scene;
    private Parent root;
    @FXML
    private Label label_record;
    @FXML
    private HBox cardHBox;
    @FXML
    private Button btn;

    public void setData(Records p) {
        label_record.setText(p.getName());
        btn.setId(p.getSteps());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    @FXML
    public void switchToShowRecord() throws IOException
    {
        System.out.println("switchToShowRecord to play video ");
        Show_Records_ScreenController.setRecord(btn.getId());
        Parent root = FXMLLoader.load(getClass().getResource("/Screens/Show_Records_Screen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
