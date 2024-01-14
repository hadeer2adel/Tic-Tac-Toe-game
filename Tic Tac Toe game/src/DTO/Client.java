/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import Screens.Login_ScreenController;
import Screens.Signup_ScreenController;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
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
}