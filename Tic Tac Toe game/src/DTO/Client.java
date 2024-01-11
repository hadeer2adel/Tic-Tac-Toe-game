/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author win 10
 */
public class Client {

    Socket socket;
    String messages;
    Thread thread;
    PrintStream printStream;
    DataInputStream dataInputStream;
    int localPortNum;
    String messageSentToServer, email, password;

    //static Vector<ClientHandler> clientVector = new Vector<ClientHandler>();
    public Client() {
        try {
            socket = new Socket(InetAddress.getLoopbackAddress(), 5005);
            dataInputStream = new DataInputStream(socket.getInputStream());
            printStream = new PrintStream(socket.getOutputStream());
            String msg = dataInputStream.readLine();
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        String msg;
                        try {
                            msg = dataInputStream.readLine();
                        } catch (IOException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }.start();

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*finally {
            try {
                socket.close();
                dataInputStream.close();
                printStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }*/

    }

}
