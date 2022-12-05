package wiu.cji.cs492.coreGame.helper;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.lang.reflect.Array;


public class Prefs {

    private Preferences gameSaveData;


    public Prefs(){
        gameSaveData = Gdx.app.getPreferences("Game Save");
        gameSaveData.getInteger("level1",0);
        gameSaveData.getInteger("level2",0);
        gameSaveData.getInteger("level3",0);
        gameSaveData.getInteger("level4",0);
        gameSaveData.getInteger("level5",0);
        gameSaveData.getInteger("level6",0);
        gameSaveData.getInteger("level7",0);
        gameSaveData.getInteger("level8",0);
        gameSaveData.getInteger("level8",0);
        gameSaveData.getInteger("level8",0);
        gameSaveData.getInteger("level9",0);




    }

    public void increaseLevel(String Level){

        switch (Level) {
            case "MapAssets/Map1.1":
                gameSaveData.putInteger("level1", 1);
                gameSaveData.flush();
                break;
            case "MapAssets/Map1.2":
                gameSaveData.putInteger("level2", 1);
                gameSaveData.flush();
                break;
            case "MapAssets/Map1.3":
                gameSaveData.putInteger("level3", 1);
                gameSaveData.flush();
                break;
            case "MapAssets/Map1.4":
                gameSaveData.putInteger("level4", 1);
                gameSaveData.flush();
                break;
            case "MapAssets/Map1.5":
                gameSaveData.putInteger("level5", 1);
                gameSaveData.flush();
                break;
            case "MapAssets/Map1.6":
                gameSaveData.putInteger("level6", 1);
                gameSaveData.flush();
                break;
            case "MapAssets/Map1.7":
                gameSaveData.putInteger("level7", 1);
                gameSaveData.flush();
                break;
            case "MapAssets/Map1.8":
                gameSaveData.putInteger("level8", 1);
                gameSaveData.flush();
                break;
            case "MapAssets/Map1.9":
                gameSaveData.putInteger("level9", 1);
                gameSaveData.flush();
                break;
            default:
                Gdx.app.log("Save file Error", "Level not found");
                break;
        }


    }

    public Preferences getGameSaveData() {
        return gameSaveData;
    }
}
