package wiu.cji.cs492.coreGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import wiu.cji.cs492.helper.Constants;


public class MainMenuScreen implements Screen{
    final ForestAdventures_2 game;
    OrthographicCamera camera;


    //Each image needed with adjusted heights and widths
    Texture playButton;
    private static final int PLAYBUTTON_HEIGHT = 100;
    private static final int PLAYBUTTON_WIDTH = 200;
    Texture settingsButton;
    private static final int SETTINGSBUTTON_HEIGHT = 100;
    private static final int SETTINGBUTTON_WIDTH = 200;
    Texture exitButton;
    private static final int EXITBUTTON_HEIGHT = 100;
    private static final int EXITBUTTON_WIDTH = 200;

    Texture Background;
    private static final int BACKGROUND_HEIGHT = 400;
    private static final int BACKGROUND_WIDTH = 800;


    public MainMenuScreen(final ForestAdventures_2 game){

        this.game = game;

        //Setting Button Textures
        playButton = new Texture("Main Menu Assets/Pixel Buttons 2 (Rock Buttons)/Play_Unpressed.png");
        settingsButton = new Texture("Main Menu Assets/Pixel Buttons 2 (Rock Buttons)/Info_Pressed.png");
        exitButton = new Texture("Main Menu Assets/Pixel Buttons 2 (Rock Buttons)/Cross_Pressed.png");


        //Setting Background Texture
        Background = new Texture("Main Menu Assets/forest.png");

        //Creating a camera and setting the view with the games set W+H
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.DEVICE_WIDTH, Constants.DEVICE_HEIGHT);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        //Clears the Screen before rendering
        ScreenUtils.clear(0,0,0.2f,0);

        //Will allow camera to update with button interaction
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        //Draws the buttons on the screen
        game.batch.begin();
        game.batch.draw(Background, 0,0 , BACKGROUND_WIDTH,BACKGROUND_HEIGHT);
        game.batch.draw(playButton, 100, 100, PLAYBUTTON_WIDTH,PLAYBUTTON_HEIGHT);
        game.batch.draw(settingsButton, 250, 100, SETTINGBUTTON_WIDTH,SETTINGSBUTTON_HEIGHT);
        game.batch.draw(exitButton, 300, 20, EXITBUTTON_WIDTH,EXITBUTTON_HEIGHT);
        game.batch.end();

        //Simple check to see if the change in screens works
        if(Gdx.input.justTouched()){
            game.setScreen(new GameScreen((ForestAdventures_2)game));
            dispose();
        }


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
