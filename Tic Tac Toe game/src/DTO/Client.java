/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import Screens.Invitation_Screen1Controller;
import Screens.Login_ScreenController;
import Screens.Response_ScreenController;
import Screens.Signup_ScreenController;
import Screens.WaitMessage_ScreenController;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
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

public class Client implements Runnable{

    private Thread thread;
    private Socket server;
    private int port = 5005;
    private DataInputStream ear;
    private DataOutputStream mouth;
    private BlockingQueue<JsonObject> messages;
    private boolean connectedServer;
    private boolean opSuccess;

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
    public void run(){
        System.out.println("DTO.Client.run()");
        while(connectedServer){
            try {
                String serverResponse = ear.readUTF();
                JsonReader jsonReader = Json.createReader(new StringReader(serverResponse));
                JsonObject responseJson = jsonReader.readObject();
                
                if(responseJson.containsKey("response"))
                    handleResponse(responseJson);
                else if(responseJson.containsKey("request"))
                    handleRequest(responseJson);
                
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void stop(){
        try {
            thread.stop();
            server.close();
            server = null;
            connectedServer = false;
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
                case "invite":
                    sendInvitationHandeler(responseJson);
                    break;
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
            default:
                System.out.println("Unknown response type: " + requestJson);
                break;
        }
    }
    
    public void Login(){
        System.out.println("DTO.Client.Login()");
        try {
            opSuccess = false;
            String email = Login_ScreenController.getEmail();
            String password = Login_ScreenController.getPassword();
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
                FileWriter writer = new FileWriter("playerData.json");
                writer.write(responseJson.toString());
                writer.close();
                opSuccess = true;
            } 
        }   catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SignUp() {
        System.out.println("DTO.Client.SignUp()");
        try {
            opSuccess = false;
            String email = Signup_ScreenController.getEmail();
            String name = Signup_ScreenController.getName();
            String password = Signup_ScreenController.getPassword();
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
   
    public void sendInvitation() {
        System.out.println("DTO.Client.sendInvitation()");
        opSuccess = false;
        try {
            int id1 = Invitation_Screen1Controller.getPlayer1ID();
            int id2 = Invitation_Screen1Controller.getPlayer2ID();
            String name1 = Invitation_Screen1Controller.getPlayer1Name();
            String name2 = Invitation_Screen1Controller.getPlayer2Name();
            
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
            Platform.runLater(()->{
                WaitMessage_ScreenController controller = new WaitMessage_ScreenController();
                controller.openGameScreen();
            });
        } 
        else {
            Platform.runLater(()->{
                WaitMessage_ScreenController controller = new WaitMessage_ScreenController();
                controller.openInvitationScreen();
            });
        }
    }
    
    public void receiveInvitation(JsonObject requestJson) {
        System.out.println("DTO.Client.receiveInvitation()");
        
        Platform.runLater(() -> {
            Response_ScreenController.setPlayer(requestJson.getString("name1"));
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
    
}