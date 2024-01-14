/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import Screens.Invitation_Screen1Controller;
import Screens.Login_ScreenController;
import Screens.Signup_ScreenController;
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
    private boolean connected;
    private BlockingQueue<String> messageQueue;
    boolean reciverResponse = false ;
    public IntegerProperty senderIDIntProperty = new SimpleIntegerProperty(0);
    int senderID;
    public static UserData user;
    boolean mybool;

    public boolean isConnected() {
        return connected;
    }
    
    public Client() {
        try {
            connected = false;
            server = new Socket("localhost", port);
            ear = new DataInputStream(server.getInputStream());
            mouth = new DataOutputStream(server.getOutputStream());
            messages = new LinkedBlockingQueue<JsonObject>();
            connected = true;
            thread = new Thread(this);
            thread.start();

        } catch (IOException ex) {
            server = null;
            connected = false;
        }
    }
    
    @Override
    public void run(){
        while(connected){
            try {
                String serverResponse = ear.readUTF();
                JsonReader jsonReader = Json.createReader(new StringReader(serverResponse));
                JsonObject responseJson = jsonReader.readObject();
                handleResponse(responseJson);
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
            connected = false;
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void handleResponse(JsonObject responseJson) {
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
                    messages.put(responseJson);
                    break;
                default:
                    System.out.println("Unknown response type: " + responseType);
                    break;
            }
            String requestType = responseJson.getString("request");
            switch (requestType) {
                case "invite":
                    messages.put(responseJson);
                    break;
                default:
                    System.out.println("Unknown response type: " + responseType);
                    break;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Login(){
        try {
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
                Login_ScreenController.SuccessLogin();
            } 
        }   catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SignUp() {
        try {
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
                Signup_ScreenController.SuccessSignUp();
            } 
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
   
    public void sendInvitation() {
        try {
            int id1 = Invitation_Screen1Controller.getPlayer1ID();
            int id2 = Invitation_Screen1Controller.getPlayer2ID();
            
            JsonObject requestJson = Json.createObjectBuilder()
                    .add("request", "invite")
                    .add("Player1", id1)
                    .add("Player2", id2)
                    .build();
            mouth.writeUTF(requestJson.toString());
            mouth.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public boolean receiveInvitation() {
        try {
            JsonObject responseJson = messages.take();
            
            
            JsonReader jsonReader = Json.createReader(new StringReader(requestMessage));
            JsonObject responseJsonObject = jsonReader.readObject();
            String sender = responseJsonObject.getString("id1");
            String receiver = responseJsonObject.getString("id2");
            System.out.println(sender + " sent a request to " + receiver);
            //senderID = sender;
            Platform.runLater(()
                    -> senderIDIntProperty.set(senderID));
            return true;

        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void replyToInviteRequest(String reply) {
        try {
            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("func", "replyToInvite")
                    .add("senderUserid", senderID)
                    .add("receiverUserid", this.user.getId())
                    .add("reply", reply)
                    .build();

            Writer writer = new StringWriter();
            Json.createWriter(writer).write(jsonObject);
            String jsonString = writer.toString();

            mouth.writeUTF(jsonString);
            mybool = false;
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}