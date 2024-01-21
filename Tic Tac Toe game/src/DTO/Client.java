/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import Screens.Invitation_Screen1Controller;
import Screens.Invite_buttonController;
import Screens.Login_ScreenController;
import Screens.Online_Game_ScreenController;
import Screens.Online_Video_ScreenController;
import Screens.Response_ScreenController;
import Screens.Signup_ScreenController;
import Screens.WaitMessage_ScreenController;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;

public class Client implements Runnable {

    private Thread thread;
    private Socket server;
    private int port = 5005;
    private DataInputStream ear;
    private DataOutputStream mouth;
    private BlockingQueue<JsonObject> messages;
    private boolean connectedServer;
    private boolean opSuccess;
    private int id;

    public int getId() {
        return id;
    }
    
    public boolean isopSuccess() {
        return opSuccess;
    }

    public boolean isServerConnected() {
        return connectedServer;
    }

    public Client() {
        try {
            connectedServer = false;
            server = new Socket("localhost", port);
            ear = new DataInputStream(server.getInputStream());
            mouth = new DataOutputStream(server.getOutputStream());
            messages = new LinkedBlockingQueue<JsonObject>();
            connectedServer = true;
            thread = new Thread(this);
            thread.start();

        } catch (IOException ex) {
            server = null;
            connectedServer = false;
        }
    }

    @Override
    public void run() {
        System.out.println("DTO.Client.run()");
        while (connectedServer) {
            try {
                String serverResponse = ear.readUTF();
                JsonReader jsonReader = Json.createReader(new StringReader(serverResponse));
                JsonObject responseJson = jsonReader.readObject();

                if (responseJson.containsKey("response")) {
                    handleResponse(responseJson);
                } else if (responseJson.containsKey("request")) {
                    handleRequest(responseJson);
                }

            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void stop() {
        try {
            id = -1;
            thread.stop();
            server.close();
            server = null;
            connectedServer = false;
            ConnectedClient.setClient(null);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleResponse(JsonObject responseJson) {
        System.out.println("DTO.Client.handleResponse()");
        try {
            String responseType = responseJson.getString("response");
            switch (responseType) {
                case "login":
                    messages.put(responseJson);
                    break;
                case "signup":
                    messages.put(responseJson);
                    break;
                case "availablePlayers":
                    messages.put(responseJson);
                    break;
                case "invite":
                    sendInvitationHandeler(responseJson);
                    break;
                case "logout":
                    messages.put(responseJson);
                    break;
                case "playAgain":
                    playAgainHandeler(responseJson);
                default:
                    System.out.println("Unknown response type: " + responseType);
                    break;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleRequest(JsonObject requestJson) {
        System.out.println("DTO.Client.handleRequest()");
        String requestType = requestJson.getString("request");
        switch (requestType) {
            case "invite":
                receiveInvitation(requestJson);
                break;
            case "move":
                receiveMove(requestJson);
                break;
            case "playAgain":
                receivePlayAgain(requestJson);
                break;
            default:
                System.out.println("Unknown response type: " + requestJson);
                break;
        }
    }

    public void Login(String email, String password) {
        try {
            opSuccess = false;
            JsonObject requestJson = Json.createObjectBuilder()
                    .add("request", "login")
                    .add("email", email)
                    .add("password", password)
                    .build();
            mouth.writeUTF(requestJson.toString());
            mouth.flush();

            JsonObject responseJson = messages.take();
            String status = responseJson.getString("status");

            if (status.equals("success")) {
                id = responseJson.getInt("id");
                FileWriter writer = new FileWriter("Files/playerData"+id+".json");
                writer.write(responseJson.toString());
                writer.close();
                opSuccess = true;
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SignUp(String email, String name, String password) {
        System.out.println("DTO.Client.SignUp()");
        try {
            opSuccess = false;
            JsonObject requestJson = Json.createObjectBuilder()
                    .add("request", "signup")
                    .add("name", name)
                    .add("email", email)
                    .add("password", password)
                    .build();
            mouth.writeUTF(requestJson.toString());
            mouth.flush();

            //Response
            JsonObject responseJson = messages.take();
            String status = responseJson.getString("status");
            if (status.equals("success")) {
                opSuccess = true;
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getAvailablePlayers() {
        try {
            opSuccess = false;
            JsonObject requestJson = Json.createObjectBuilder()
                    .add("request", "availablePlayers")
                    .add("id", id)
                    .build();
            mouth.writeUTF(requestJson.toString());
            mouth.flush();

            JsonObject responseJson = messages.take();
            String status = responseJson.getString("status");
            if (status.equals("success")) {
                opSuccess = true;
                FileWriter writer = new FileWriter("Files/availablePlayers"+id+".json");
                writer.write(responseJson.toString());
                writer.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendInvitation(int id2, String name2) {
        System.out.println("DTO.Client.sendInvitation()");
        opSuccess = false;
        try {
            JsonReader reader = Json.createReader(new FileInputStream(new File("Files/playerData"+id+".json")));
            JsonObject player1 = reader.readObject();
            int id1 = player1.getInt("id");
            String name1 = player1.getString("name");

            JsonObject requestJson = Json.createObjectBuilder()
                    .add("request", "invite")
                    .add("id1", id1)
                    .add("id2", id2)
                    .add("name1", name1)
                    .add("name2", name2)
                    .build();

            mouth.writeUTF(requestJson.toString());
            mouth.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendInvitationHandeler(JsonObject responseJson) {
        System.out.println("DTO.Client.sendInvitationHandeler()");
        opSuccess = responseJson.getBoolean("status");
        if (opSuccess) {
            Platform.runLater(() -> {
                WaitMessage_ScreenController controller = new WaitMessage_ScreenController();
                controller.openGameScreen(responseJson.getInt("id1"),
                                responseJson.getString("name1"),
                                responseJson.getInt("id2"),
                                responseJson.getString("name2"));
            });
        } else {
            Platform.runLater(() -> {
                WaitMessage_ScreenController controller = new WaitMessage_ScreenController();
                controller.openInvitationScreen();
            });
        }
    }

    public void receiveInvitation(JsonObject requestJson) {
        System.out.println("DTO.Client.receiveInvitation()");

        Platform.runLater(() -> {
            Response_ScreenController.setJson(requestJson);
            Invitation_Screen1Controller controller = new Invitation_Screen1Controller();
            controller.openResponeScreen();
        });
    }

    public void receiveInvitationHandeler(JsonObject requestJson, boolean accepted) {
        try {
            System.out.println("DTO.Client.receiveInvitationHandeler()");
            JsonObject responseJson = Json.createObjectBuilder()
                    .add("response", "invite")
                    .add("status", accepted)
                    .add("id1", requestJson.getInt("id1"))
                    .add("id2", requestJson.getInt("id2"))
                    .add("name1", requestJson.getString("name1"))
                    .add("name2", requestJson.getString("name2"))
                    .build();

            mouth.writeUTF(responseJson.toString());
            mouth.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Logout() {
        try {
            opSuccess = false;
            JsonObject requestJson = Json.createObjectBuilder()
                    .add("request", "logout")
                    .add("id", id)
                    .build();
            mouth.writeUTF(requestJson.toString());
            mouth.flush();

            JsonObject responseJson = messages.take();
            String status = responseJson.getString("status");

            if (status.equals("success")) {
                File file = new File("Files/playerData"+id+".json");
                file.delete();
                file = new File("Files/availablePlayers"+id+".json");
                file.delete();
                stop();
                opSuccess = true;
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendMove(int  player1Id ,int player2Id, int move , String ch, boolean isGameEnd){
        System.out.println("DTO.Client.sendMove()");
        opSuccess = false;
        try {
            JsonObject requestJson = Json.createObjectBuilder()
                    .add("request", "move")
                    .add("player1Id", player1Id)
                    .add("player2Id", player2Id)
                    .add("character", ch )
                    .add("move", move)
                    .add("isGameEnd", isGameEnd)
                    .build();
            mouth.writeUTF(requestJson.toString());
            mouth.flush();

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void receiveMove(JsonObject requestJson) {
        Platform.runLater(() -> 
        {
            Online_Game_ScreenController controller = new Online_Game_ScreenController();
            controller.onlyDraw(requestJson.getInt("move"), requestJson.getString("character"), requestJson.getBoolean("isGameEnd"));
        });
    }
    
    public void playAgain(int replay, GameDetails game){
        System.out.println("DTO.Client.playAgain()");
        opSuccess = false;
        try {
            JsonObject requestJson = Json.createObjectBuilder()
                    .add("request", "playAgain")
                    .add("replay", replay)
                    .add("myID", id)
                    .add("id1", game.getPlayerId_1())
                    .add("id2", game.getPlayerId_2())
                    .add("score1", game.getPlayerScore_1())
                    .add("score2", game.getPlayerScore_2())
                    .build();

            mouth.writeUTF(requestJson.toString());
            mouth.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void playAgainHandeler(JsonObject responseJson) {
        System.out.println("DTO.Client.playAgainHandeler()");
        opSuccess = responseJson.getBoolean("status");
        if (opSuccess) {
            Platform.runLater(() -> {
                Online_Video_ScreenController controller = new Online_Video_ScreenController();
                controller.openGameScreen();
            });
        } else {
            Platform.runLater(() -> {
                Online_Video_ScreenController controller = new Online_Video_ScreenController();
                controller.openInvitationScreen();
            });
        }
    }

    public void receivePlayAgain(JsonObject requestJson) {
        System.out.println("DTO.Client.receivePlayAgain()");
        int replay = requestJson.getInt("replay");
        Platform.runLater(() -> {
            Online_Video_ScreenController.setReplay(replay);
            Online_Video_ScreenController.setJson(requestJson);
        });
    }

    public void receivePlayAgainHandeler(JsonObject requestJson, boolean accepted) {
        System.out.println("DTO.Client.receivePlayAgainHandeler()");
        try {
            JsonObject responseJson = Json.createObjectBuilder()
                    .add("response", "playAgain")
                    .add("status", accepted)
                    .add("myID", id)
                    .add("id1", requestJson.getInt("id1"))
                    .add("id2", requestJson.getInt("id2"))
                    .add("score1", requestJson.getInt("score1"))
                    .add("score2", requestJson.getInt("score2"))
                    .build();

            mouth.writeUTF(responseJson.toString());
            mouth.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}
