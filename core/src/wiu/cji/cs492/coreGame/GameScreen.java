package wiu.cji.cs492.coreGame;


import static wiu.cji.cs492.coreGame.helper.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import wiu.cji.cs492.Objects.GameEntity;
import wiu.cji.cs492.Objects.Player;
import wiu.cji.cs492.coreGame.helper.Hud;
import wiu.cji.cs492.coreGame.helper.TileMapHelper;


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
    private Player player;


    public GameScreen(final ForestAdventures game){

        this.game = game;
        hud = new Hud(game.batch);
        this.spriteBatch = new SpriteBatch();
        this.world = new World(new Vector2(0,-25.3f),false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        this.tileMapHelper = new TileMapHelper(this);
        //Calls to helper class
        this.orthogonalTiledMapRenderer = tileMapHelper.setUpMap();

        //Creates Player


        //Camera
        gamecam = new OrthographicCamera();
        //gamecam.setToOrtho(false,Constants.DEVICE_WIDTH ,Constants.DEVICE_HEIGHT );
         viewport = new ExtendViewport(250, 225, gamecam);


    }


    @Override
    public void show() {


    }

    public void update(float delta){
        //Updates the world at 60fps
        world.step(1/60f, 6, 2);

        //updates the camera to follow player
        gamecamUpdate();
        //This allows the camera to be combined with projection and view
        spriteBatch.setProjectionMatrix(gamecam.combined);



        //Renders the map to the game camera
        orthogonalTiledMapRenderer.setView(gamecam);
        player.update();

    }

    @Override
    public void render(float delta) {
        this.update(delta);
        ScreenUtils.clear(0,0,0.2f,0);
        orthogonalTiledMapRenderer.render();

        hud.stage.draw();


        //Renders the objects
        spriteBatch.begin();



        spriteBatch.end();


        box2DDebugRenderer.render(world, gamecam.combined.scl(PPM));

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
    public void setPlayer(Player player){
        this.player = player;
    }
}
