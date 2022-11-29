package wiu.cji.cs492.coreGame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import wiu.cji.cs492.coreGame.helper.Prefs;


public class ForestAdventures extends Game{

	//Sprite Batch for maps, items and player
	public SpriteBatch batch;
	public static BitmapFont font;

	public Prefs prefs;

	@Override
	public void create () {

		prefs = new Prefs();

		batch = new SpriteBatch();
		font = new BitmapFont();

		this.setScreen(new MainMenuScreen(this));

	}

	@Override
	public void render () {
		super.render();

	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
