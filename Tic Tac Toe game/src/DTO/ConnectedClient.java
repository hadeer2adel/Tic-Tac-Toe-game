/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Dell
 */
public class ConnectedClient {
    private static Client client ;

    public static Client getClient() {
        return client;
    }

    public static void setClient(Client c) {
        client = c;
    }
}
