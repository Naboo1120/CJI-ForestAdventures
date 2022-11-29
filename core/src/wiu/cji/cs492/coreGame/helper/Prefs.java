package wiu.cji.cs492.coreGame.helper;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;


public class Prefs {
    private Preferences gameSaveData;
    private int completedLevel;

    public Prefs(){
        gameSaveData = Gdx.app.getPreferences("Game Save");
        completedLevel=gameSaveData.getInteger("level",0);

    }

    public void increaseLevel(){
        completedLevel++;
        gameSaveData.putInteger("level", completedLevel);
        gameSaveData.flush();
    }
    public int getCompletedLevel(){
        return completedLevel;
    }
}
