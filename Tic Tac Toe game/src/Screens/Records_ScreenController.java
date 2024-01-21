package Screens;

import DTO.ConnectedClient;
import DTO.Records;
import DTO.UserData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Records_ScreenController implements Initializable {

    private Stage stage = Mainpkg.Main.getAppStage();
    private Scene scene;
    private Parent root;
    @FXML
    private ListView<Records> onlineRecordsListview;
    private ObservableList<Records> onlineRecords;

    public void switchToProfile(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Screens/PlayerInfo_Screen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateUI() {
        try {
            JsonReader reader = Json.createReader(new FileInputStream(new File("Files/allRecords" + ConnectedClient.getClient().getId() + ".json")));
            JsonObject jsonObject = reader.readObject();
            JsonArray jsonOnlineRecords = jsonObject.getJsonArray("records");
            int size = jsonOnlineRecords.size();
            onlineRecords = FXCollections.observableArrayList();

            for (int i = 0; i < size; i++) {
                JsonObject jsonRecord = jsonOnlineRecords.getJsonObject(i);
                Records Record = new Records(jsonRecord.getInt("id"), jsonRecord.getString("name"), jsonRecord.getString("steps"), 0);
                onlineRecords.add(Record);
            }

            onlineRecordsListview.setItems(onlineRecords);
            onlineRecordsListview.setCellFactory(param -> new Records_ScreenController.YourListCell());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Records_ScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public class YourListCell extends ListCell<Records> {

        @Override
        protected void updateItem(Records item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("/Screens/Record_Card.fxml"));
                try {
                    HBox hbox = loader.load();

                    Record_CardController controller = loader.getController();
                    controller.setData(item);
                    setGraphic(hbox);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateUI();
    }

}
