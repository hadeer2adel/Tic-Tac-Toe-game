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
public class GameDetails {
    private int playerId_1, playerId_2; 
    private String playerName_1, playerName_2; 
    private int playerScore_1, playerScore_2; 

    public GameDetails(String playerName_1, String playerName_2) {
        this.playerName_1 = playerName_1;
        this.playerName_2 = playerName_2;
        this.playerScore_1 = 0;
        this.playerScore_2 = 0;
    }
    
    public GameDetails(int id1, String playerName_1, int id2, String playerName_2) {
        this.playerName_1 = playerName_1;
        this.playerName_2 = playerName_2;
        this.playerScore_1 = 0;
        this.playerScore_2 = 0;
        playerId_1 = id1;
        playerId_2 = id2;
    }

    public String getPlayerName_1() {
        return playerName_1;
    }

    public String getPlayerName_2() {
        return playerName_2;
    }

    public int getPlayerScore_1() {
        return playerScore_1;
    }

    public int getPlayerScore_2() {
        return playerScore_2;
    }

    public void updatePlayerScore_1() {
        this.playerScore_1 += 1;
    }

    public void updatePlayerScore_2() {
        this.playerScore_2 += 1;
    }

    public int getPlayerId_1() {
        return playerId_1;
    }

    public int getPlayerId_2() {
        return playerId_2;
    }
    

}
