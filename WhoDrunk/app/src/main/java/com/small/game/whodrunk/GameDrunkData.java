package com.small.game.whodrunk;

import java.util.ArrayList;

/**
 * Created by smallstrong on 16/6/6.
 */
public class GameDrunkData {

    private static GameDrunkData gameDrunkData;

    private GameDrunkData() {

    }

    public static GameDrunkData getSingleton() {
        if (gameDrunkData == null) {                         //Single Checked
            synchronized (GameDrunkData.class) {
                if (gameDrunkData == null) {                 //Double Checked
                    gameDrunkData = new GameDrunkData();
                }
            }
        }
        return gameDrunkData;
    }


    ArrayList<String> gameDrunkList = new ArrayList<>();

    public ArrayList<String> getGameDrunkList() {
        return gameDrunkList;
    }

    public void setGameDrunkList(ArrayList<String> gameDrunkList) {
        this.gameDrunkList = gameDrunkList;
    }

}
