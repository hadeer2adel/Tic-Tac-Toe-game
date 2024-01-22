package Screens;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import DTO.Client;
import DTO.ConnectedClient;
import DTO.UserData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Invitation_Screen1Controller implements Initializable {

    private Stage stage = Mainpkg.Main.getAppStage();;
    private Scene scene;
    private Parent root;
    @FXML
    private Button refreshBtn;

    @FXML
    private ListView<UserData> onlinePlayersListview;
    private ObservableList<UserData>  onlinePlayers;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ImageView imageView = new ImageView(new Image("/Images/refresh.png"));
        imageView.setFitHeight(35);
        imageView.setFitWidth(30);
        refreshBtn.setGraphic(imageView);
        updateUI();
    }
    
    public void openResponeScreen() {
        System.out.println("Screens.Invitation_Screen1Controller.openResponeScreen()");
        try {
            root = FXMLLoader.load(getClass().getResource("/Screens/Response_Screen.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Invitation_Screen1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void switchToHome(ActionEvent event) throws IOException{
        Client client = ConnectedClient.getClient();
        if(client.isServerConnected()){
            client.Logout();
            if(client.isopSuccess()){
                root = FXMLLoader.load(getClass().getResource("/Screens/Home_Screen.fxml"));
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Logout");
                alert.setHeaderText(null);
                alert.setContentText("Sorry you can't logout");
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Server");
            alert.setHeaderText(null);
            alert.setContentText("Server is OFF now");
            alert.showAndWait();
        }
    }
    
    public void refresh(ActionEvent event) throws IOException{
        Client client = ConnectedClient.getClient();
        if(client.isServerConnected()){
            client.getAvailablePlayers();
            if(client.isopSuccess()){
                updateUI();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Refresh");
                alert.setHeaderText(null);
                alert.setContentText("Sorry can not refresh now");
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Server");
            alert.setHeaderText(null);
            alert.setContentText("Server is OFF now");
            alert.showAndWait();
        }
    }
    
    public void switchToProfile(ActionEvent event) throws IOException{//PlayerInfo
        root = FXMLLoader.load(getClass().getResource("/Screens/PlayerInfo_Screen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateUI() {
        try {
            JsonReader reader = Json.createReader(new FileInputStream(new File("Files/availablePlayers"+ConnectedClient.getClient().getId()+".json")));
            JsonObject jsonObject = reader.readObject();
            JsonArray jsonOnlinePlayers = jsonObject.getJsonArray("players");
            int size = jsonOnlinePlayers.size();
            onlinePlayers = FXCollections.observableArrayList();
            
            for(int i = 0; i < size; i++){
                JsonObject jsonPlayer = jsonOnlinePlayers.getJsonObject(i);
                UserData player = new UserData(jsonPlayer.getInt("id"), jsonPlayer.getString("name"), null, null, jsonPlayer.getInt("score"), true, true);
                onlinePlayers.add(player);
            }
            
            onlinePlayersListview.setItems(onlinePlayers);
            onlinePlayersListview.setCellFactory(param -> new YourListCell());
                
            } catch (FileNotFoundException ex) {
            Logger.getLogger(Invitation_Screen1Controller.class.getName()).log(Level.SEVERE, null, ex);            
        } 
    }

    public class YourListCell extends ListCell<UserData> {

        @Override
        protected void updateItem(UserData item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("/Screens/invite_button.fxml"));
                try {
                    HBox hbox = loader.load();

                    Invite_buttonController controller = loader.getController();
                    controller.setData(item);
                    setGraphic(hbox);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
