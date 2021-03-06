package com.allsopg.game.utility;

/**
 * Created by gerard on 23/04/2017.
 */

public class GameData {
    private float time;
    private int score;
    private String playerName;
    private static GameData INSTANCE;

    private GameData(){} // private constructor preventing dublicates

    public static GameData getInstance(){ // singleton pattern instatiating object
        if(INSTANCE==null){return new GameData();}
        return INSTANCE;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    // test this value
    public int getScore() {
        return score;
    }

    public void setScore(int points) {
        score = points;
    }

    public void resetGameData(){
        time=0;
        score=0;
        playerName="";
    }
}
