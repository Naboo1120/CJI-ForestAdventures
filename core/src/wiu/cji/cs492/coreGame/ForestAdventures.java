package wiu.cji.cs492.coreGame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class ForestAdventures extends Game {

	//Sprite Batch for maps, items and player
	public SpriteBatch batch;
	public static BitmapFont font;
	protected String levelName;
	public Music music;


	@Override
	public void create() {

		batch = new SpriteBatch();
		font = new BitmapFont();
		music = Gdx.audio.newMusic(Gdx.files.internal("Music/MainGameMusic.mp3"));
		music.setVolume(.5f);
		music.setLooping(true);
		music.play();



		this.setScreen(new MainMenuScreen(this));

	}

	@Override
	public void render() {
		super.render();

	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	public String getLevel(){
		return levelName;
}
	public void  setLevel(String levelName){
			this.levelName = levelName;
	}
}
