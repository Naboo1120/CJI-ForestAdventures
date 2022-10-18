package wiu.cji.cs492.coreGame.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Hud {
    public static Stage stage;
    private Viewport viewport;

    private Integer foodCount;

    public Integer movement;

    //Buttons for movement and Interaction
    TextureAtlas atlas;
    Skin skin;
    BitmapFont bitmapFont;
    Table table;

    static TextButton rightButton;
    static TextButton leftButton;
    static TextButton jumpButton;

    Label foodLabel;

    public Hud(SpriteBatch spriteBatch){
        foodCount = 0;


        viewport = new FitViewport(Constants.DEVICE_WIDTH, Constants.DEVICE_HEIGHT, new OrthographicCamera());

        stage = new Stage(viewport, spriteBatch);

        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas("Main Menu Assets/Buttons/Skin/uiskin.atlas");
        skin = new Skin(atlas);
        bitmapFont = new BitmapFont(Gdx.files.internal("Main Menu Assets/Buttons/Skin/default.fnt"));

        table = new Table();
        //Will set the table to the center of the screen
        table.top();
        //Will allow it to fill the entire screen
        table.setFillParent(true);

        TextButton.TextButtonStyle rightButtonStyle = new TextButton.TextButtonStyle();
        rightButtonStyle.up = skin.getDrawable("tree-plus");
        rightButtonStyle.pressedOffsetX = 1;
        rightButtonStyle.pressedOffsetY = -1;
        rightButtonStyle.font = bitmapFont;
        TextButton.TextButtonStyle leftButtonStyle = new TextButton.TextButtonStyle();
        leftButtonStyle.up = skin.getDrawable("tree-minus");
        leftButtonStyle.pressedOffsetX = 1;
        leftButtonStyle.pressedOffsetY = -1;
        leftButtonStyle.font = bitmapFont;
        TextButton.TextButtonStyle jumpButtonStyle = new TextButton.TextButtonStyle();
        jumpButtonStyle.up = skin.getDrawable("default-round");
        jumpButtonStyle.font = bitmapFont;

        rightButton = new TextButton("", rightButtonStyle);
        rightButton.setPosition(0, 0);
        leftButton = new TextButton("", leftButtonStyle);
        leftButton.setPosition(0, 0);
        jumpButton = new TextButton("JUMP", jumpButtonStyle);
        jumpButton.setPosition(0,0);



        //Creation of the label "%03d" means the length of the label
        foodLabel = new Label(String.format("%03d", foodCount), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        //Adds the labels to the screen with padding to the top by 10 pixels
        table.add(foodLabel).expandX().padTop(10);
        table.row();
        table.add(leftButton).expandY().left();
        table.add(rightButton).expandY().right();
        table.add(jumpButton).center();

        stage.addActor(table);

    }

    public static int movement(){
        if(leftButton.isPressed()){
            return -1;
        }
        if (rightButton.isPressed()){
            return 1;
        }
        if(jumpButton.isTouchFocusListener()){
            return 2;
        }

        return 0;
    }


}
