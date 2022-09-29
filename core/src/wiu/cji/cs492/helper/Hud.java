package wiu.cji.cs492.helper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Hud {
    public static Stage stage;
    private Viewport viewport;

    private Integer foodCount;

    //Buttons for movement and Interaction
    //Texture

    Label foodLabel;

    public Hud(SpriteBatch spriteBatch){
        foodCount = 0;


        viewport = new FitViewport(Constants.DEVICE_WIDTH, Constants.DEVICE_HEIGHT, new OrthographicCamera());

        stage = new Stage(viewport, spriteBatch);

        Table table = new Table();
        //Will set the table to the center of the screen
        table.top();
        //Will allow it to fill the entire screen
        table.setFillParent(true);

        //Creation of the label "%03d" means the length of the label
        foodLabel = new Label(String.format("%03d", foodCount), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        //Adds the labels to the screen with padding to the top by 10 pixels
        table.add(foodLabel).expandX().padTop(10);

        stage.addActor(table);

    }


}
