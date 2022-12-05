package wiu.cji.cs492.coreGame;

import static wiu.cji.cs492.coreGame.helper.Constants.AVAILABLE_LEVELS;

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


public class LevelListScreen implements Screen{
    final ForestAdventures game;
    private Stage stage;
    private Table table;
    private Texture Background;
    private Skin skin;
    private TextureAtlas atlas;
    private TextButton l1Button,l2Button, l3Button, l4Button, l5Button, l6Button,l7Button, l8Button, l9Button;
    private TextButton backButton;
    private BitmapFont bitmapFont;
    private String levelName;



    public LevelListScreen(final ForestAdventures game){
        levelName = "MapAssets/Map1.";
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

        l1Button = new TextButton("Level 1", textButtonStyle);
        l1Button.pad(9);
        l2Button = new TextButton("Level 2", textButtonStyle);
        l2Button.pad(9);
        l3Button = new TextButton("Level 3", textButtonStyle);
        l3Button.pad(9);
        l4Button = new TextButton("Level 4", textButtonStyle);
        l4Button.pad(9);
        l5Button = new TextButton("Level 5", textButtonStyle);
        l5Button.pad(9);
        l6Button = new TextButton("Level 6", textButtonStyle);
        l6Button.pad(9);
        l7Button = new TextButton("Level 7", textButtonStyle);
        l7Button.pad(9);
        l8Button = new TextButton("Level 8", textButtonStyle);
        l8Button.pad(9);
        l9Button = new TextButton("Level 9", textButtonStyle);
        l9Button.pad(9);


        //we will add more when levels are made

        backButton = new TextButton("Main Menu", textButtonStyle);
        backButton.pad(20);


        //Adding the button to the table and table to the stage
        table.add(l1Button).top();
        if(game.prefs.getGameSaveData().getInteger("level1") == 1){
            table.add(l2Button);
        }
        if(game.prefs.getGameSaveData().getInteger("level2") == 1){
            table.add(l3Button).bottom();
        }
        if(game.prefs.getGameSaveData().getInteger("level3") == 1){
            table.add(l4Button);
        }
        if(game.prefs.getGameSaveData().getInteger("level4") == 1){
            table.add(l5Button).top();
        }
        if(game.prefs.getGameSaveData().getInteger("level5") == 1){
            table.add(l6Button);
        }
        if(game.prefs.getGameSaveData().getInteger("level6") == 1){
            table.add(l7Button).bottom();
        }
        if(game.prefs.getGameSaveData().getInteger("level7") == 1){
            table.add(l8Button);
        }
        if(game.prefs.getGameSaveData().getInteger("level8") == 1){
            table.add(l9Button).top();
        }

        table.add(backButton).pad(20);
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
        table.setPosition(10,0);
        stage.getBatch().end();
        //Draws the actors or buttons
        stage.draw();



    }
    private void choseLevel(int s ){
        if(s<= AVAILABLE_LEVELS) { //replace with a level completed var.
            try {

                game.setScreen(new GameScreen((ForestAdventures) game, levelName + s));
                game.setLevel(levelName + s);
                dispose();
            } catch (Exception e) {
                Gdx.app.log("boop", "" + e.getLocalizedMessage());

            }
        }
    }

    //Used to separate logic from rendering
    public void update(float delta){
        //calls any act method to the actors on stage
        stage.act(delta);
        //Will chnage screens when the button is pressed

        if(l1Button.isTouchFocusListener()){
            choseLevel(1);
        }
        if(l2Button.isTouchFocusListener()){
            choseLevel(2);
        }
        if(l3Button.isTouchFocusListener()){
            choseLevel(3);
        }
        if(l4Button.isTouchFocusListener()){
            choseLevel(4);
        }
        if(l5Button.isTouchFocusListener()){
            choseLevel(5);
        }
        if(l6Button.isTouchFocusListener()){
            choseLevel(6);
        }
        if(l7Button.isTouchFocusListener()){
            choseLevel(7);
        }
        if(l8Button.isTouchFocusListener()){
            choseLevel(8);
        }
        if(l9Button.isTouchFocusListener()){
            choseLevel(9);
        }

        if(backButton.isTouchFocusListener()){
            game.setScreen(new MainMenuScreen((ForestAdventures)game));
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
