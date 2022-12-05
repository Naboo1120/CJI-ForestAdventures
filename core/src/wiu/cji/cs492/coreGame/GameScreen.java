package wiu.cji.cs492.coreGame;


import static wiu.cji.cs492.coreGame.helper.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import wiu.cji.cs492.Objects.*;
import wiu.cji.cs492.coreGame.helper.Hud;
import wiu.cji.cs492.coreGame.helper.Prefs;
import wiu.cji.cs492.coreGame.helper.TileMapHelper;
import wiu.cji.cs492.coreGame.helper.WorldContactListener;


public class GameScreen implements Screen {

    final ForestAdventures game;

    private World world;

    private Hud hud;


    //Box2d usage
    private Box2DDebugRenderer box2DDebugRenderer;

    //For object rendering
    private SpriteBatch spriteBatch;

    //camera and view port
    private OrthographicCamera gamecam;
    private ExtendViewport viewport;


    //Tiled map variables
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private TileMapHelper tileMapHelper;
    private Array<Collectables> collect = new Array<Collectables>();
    private Array<DeathWall> dWalls = new Array<>();
    private Array<Enemy> enemys = new Array<>();
    private Array<Finish> finish = new Array<>();
    private Player player;

    private Music FaintSound;

    //Game safe file
    public Prefs prefs;

    public GameScreen(final ForestAdventures game, String levelRequested){

        this.game = game;
        hud = new Hud(game.batch);
        this.spriteBatch = new SpriteBatch();
        this.world = new World(new Vector2(0,-25.3f),false);
        //this.box2DDebugRenderer = new Box2DDebugRenderer();
        this.tileMapHelper = new TileMapHelper(this, levelRequested);
        //Calls to helper class
        this.orthogonalTiledMapRenderer = tileMapHelper.setUpMap(); //Can we use this to pass levels?

        FaintSound = Gdx.audio.newMusic(Gdx.files.internal("SoundEffects/FaintSound1.mp3"));

        //Camera
        gamecam = new OrthographicCamera();
        viewport = new ExtendViewport(250, 225, gamecam);
        world.setContactListener(new WorldContactListener(game));

        prefs = new Prefs();

    }


    @Override
    public void show() {


    }

    public void update(float delta){
        //Updates the world at 60fps
        world.step(1/60f, 6, 2);
        player.update();
        //updates the camera to follow player
        gamecamUpdate();
        //This allows the camera to be combined with projection and view
        spriteBatch.setProjectionMatrix(gamecam.combined);

        for (DeathWall d : dWalls){
            if (d.collided){
                FaintSound.play();
                FaintSound.setLooping(false);
                while (FaintSound.isPlaying())
                {
                    player.update();
                }
                game.setScreen(new GameOverScreen(game));

            }
        }
        for (Finish f : finish){
            if (f.collided){
                //On finish collision it will run the function to write on the save game file
                prefs.increaseLevel(game.getLevel());
                //Gdx.app.log("Level  Stats:", String.valueOf(prefs.getGameSaveData().getInteger("level2")));

                if(f.finishType.equals("NextLevel"))
                    game.setScreen(new LevelCompleteScreen(game));
                else
                    game.setScreen(new GameCompleteScreen(game));

            }
        }

        for (Enemy e : enemys){
            e.update();
            if (e.collided){

                FaintSound.play();
                FaintSound.setLooping(false);
                while (FaintSound.isPlaying())
                {

                }
                game.setScreen(new GameOverScreen(game));

            }
        }



        //Renders the map to the game camera
        orthogonalTiledMapRenderer.setView(gamecam);


    }

    @Override
    public void render(float delta) {
        this.update(delta);
        ScreenUtils.clear(0,0,0.2f,0);
        orthogonalTiledMapRenderer.render();
       // box2DDebugRenderer.render(world, gamecam.combined.scl(PPM));


        //Renders the objects
        player.render(spriteBatch);





        spriteBatch.begin();
        for (Collectables c : collect){
           // Body body = c.getBody();
            c.draw(spriteBatch);
            if(!c.getTouched() ) {
                //spriteBatch.draw(c.getTexture(), body.getPosition().x, body.getPosition().y);
            }else {
                if (! c.getCollected()){
                    hud.updateFood(1);
                    c.setCollected(true);
                    Gdx.app.log("food", "Food has been collected");
                }
            }

        }
        for(Enemy e : enemys){
            Body body = e.getBody();
            e.draw(spriteBatch);
        }
        spriteBatch.end();

        //for()
        //Sets the projection matrix to the huds stage
        spriteBatch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        //Sets the renderer to the world and uses the came screen combines camera with the scaling set. PPM
        //box2DDebugRenderer.render(world, gamecam.combined.scl(PPM));

    }




    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        spriteBatch.setProjectionMatrix(gamecam.combined);
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
        orthogonalTiledMapRenderer.dispose();
        world.dispose();
        //box2DDebugRenderer.dispose();


    }

    public void gamecamUpdate(){
        //gamecam.position.set(new Vector3());
        Vector3 position = gamecam.position;
        position.x = Math.round(player.getBody().getPosition().x *PPM *10)/10f;
        //float tempY = Math.round(player.getBody().getPosition().y  )/1f;
        //insert check for bottom of the screen
        position.y = 125;// + tempY;
        position.x = (position.x <=250)? 250 : position.x ;

        gamecam.position.set(position);

        gamecam.update();


    }




    public World getWorld() {
        return world;
    }
    public void addCollectables(Collectables collectables){
        collect.add(collectables);
        Gdx.app.log("collectables", "Collectable created");
    }
    public void addDeathWall(DeathWall d){
        dWalls.add(d);
    }
    public void addFinish(Finish f){finish.add(f);}
    public void addEnemy(Enemy e){enemys.add(e);}
    public void setPlayer(Player player){
        this.player = player;
    }
    public void removeCollectable(Collectables collectables){
        //???

    }
}
