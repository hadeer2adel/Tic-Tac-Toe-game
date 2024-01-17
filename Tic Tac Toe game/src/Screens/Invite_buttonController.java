package Screens;

import DTO.Client;
import DTO.ConnectedClient;
import DTO.UserData;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Invite_buttonController {
    
    private Stage stage = Mainpkg.Main.getAppStage();;
    private Scene scene;
    private Parent root;
    private Client client;

    @FXML
    private ImageView playerImg;
    @FXML
    private Label name;
    @FXML
    private HBox cardHBox;
    @FXML
    private Button btn_invite;

    public void setData(UserData p) {
        name.setText(p.getName());
        btn_invite.setId(""+p.getId());
    }

    public void invitation(ActionEvent event) throws IOException{
        openWaitScreen();
        Client client = ConnectedClient.getClient();
        if(client.isServerConnected()){
            client.sendInvitation(Integer.parseInt(btn_invite.getId()));
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Server");
            alert.setHeaderText(null);
            alert.setContentText("Server is OFF now");
            alert.showAndWait();
        }
    } 

    public void openWaitScreen() {
        System.out.println("Screens.Invitation_Screen1Controller.openWaitScreen()");
        try {
            root = FXMLLoader.load(getClass().getResource("/Screens/WaitMessage_Screen.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Invitation_Screen1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 

       

        
        
    }
 

