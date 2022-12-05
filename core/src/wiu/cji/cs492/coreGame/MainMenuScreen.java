package wiu.cji.cs492.coreGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class MainMenuScreen implements Screen{
    final ForestAdventures game;
    private Stage stage;
    private Table table;
    private Texture Background;
    private Skin skin;
    private TextureAtlas atlas;
    private TextButton playButton;
    private TextButton settingsButton;
    private TextButton exitButton;
    private BitmapFont bitmapFont;



    public MainMenuScreen(final ForestAdventures game){

        this.game = game;

    }

    @Override
    public void show() {

        //These assets should be controlled by a manager class, but we can adapt to that later.
        //Sets the stage view to fit the device based on the constraints
        stage = new Stage(new FitViewport(800, 400));
        //Alows for input events on the stage
        Gdx.input.setInputProcessor(stage);
        //Adding image file to a texture object
        Background = new Texture("Main Menu Assets/forest.png");
        //Atlas lets there be images and text to an button object
        atlas = new TextureAtlas("Main Menu Assets/Buttons/Skin/uiskin.atlas");
        //Will let you apply the skin to the object
        skin = new Skin(atlas);
        //Used for the font in the buttons
        bitmapFont = new BitmapFont(Gdx.files.internal("Main Menu Assets/Buttons/Skin/default.fnt"));
        //Used to format the location of the buttons
        table = new Table(skin);
        table.setBounds(0, 0, 800, 400);

        //Creation of the buttons an its properties
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("default-round");
        textButtonStyle.down = skin.getDrawable("default-round-down");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = bitmapFont;


        //Button object created with above properties
        playButton = new TextButton("PLAY", textButtonStyle);
        playButton.pad(20);

        settingsButton = new TextButton("SETTINGS", textButtonStyle);
        settingsButton.pad(20);

        exitButton = new TextButton("Exit", textButtonStyle);
        exitButton.pad(20);


        //Adding the button to the table and table to the stage
        table.add(playButton);
        table.add(settingsButton).left().pad(20);
        table.add(exitButton).pad(20);
        stage.addActor(table);

    }

    @Override
    public void render(float delta) {

        //Clears the Screen before rendering
        ScreenUtils.clear(0,0,0.2f,0);

        update(delta);
        //Draws the background before the buttons
        stage.getBatch().begin();
        stage.getBatch().draw(Background, 0,0,800,400);
        stage.getBatch().end();

        //Draws the actors or buttons
        stage.draw();



    }

    //Used to separate logic from rendering
    public void update(float delta){
        //calls any act method to the actors on stage
        stage.act(delta);
        //Will chnage screens when the button is pressed
        if(playButton.isTouchFocusListener()){
            game.setScreen(new LevelListScreen((ForestAdventures)game));
            dispose();
        }

        if(settingsButton.isTouchFocusListener()){
            game.setScreen(new Settings((ForestAdventures)game));
            dispose();
        }

        if(exitButton.isTouchFocusListener()){
            System.exit(1);
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        //Will set the camera when stage is called, use true when using a HUD
        stage.getViewport().update(width, height, false);


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
        skin.dispose();
        atlas.dispose();
        stage.dispose();
    }

}
