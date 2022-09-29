package wiu.cji.cs492.coreGame;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import wiu.cji.cs492.helper.Constants;
import wiu.cji.cs492.helper.Hud;
import wiu.cji.cs492.helper.TileMapHelper;


public class GameScreen implements Screen {

    final ForestAdventures game;

    private World world;

    //Box2d usage
    private Box2DDebugRenderer box2DDebugRenderer;

    //For object rendering
    private SpriteBatch spriteBatch;

    //camera and view port
    private OrthographicCamera gamecam;
    private Viewport gamePort;

    //Tiled map variables
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private TileMapHelper tileMapHelper;

    //For the Hud class
    private Hud hud;

    public GameScreen(final ForestAdventures game){

        this.game = game;
        this.spriteBatch = new SpriteBatch();
        this.world = new World(new Vector2(0,0),false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        this.tileMapHelper = new TileMapHelper(this);
        //Calls to helper class
        this.orthogonalTiledMapRenderer = tileMapHelper.setUpMap();

        //Hud class call
        hud = new Hud(game.batch);


        //Camera
        gamecam = new OrthographicCamera();
        gamecam.setToOrtho(false,Constants.DEVICE_WIDTH ,Constants.DEVICE_HEIGHT );



    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        this.update();
        ScreenUtils.clear(0,0,0.2f,0);
        orthogonalTiledMapRenderer.render();

        //Renders the objects
        spriteBatch.begin();

        box2DDebugRenderer.render(world, gamecam.combined.scl(Constants.PPM));

        spriteBatch.end();

        //Renders the Hud
        game.batch.setProjectionMatrix(Hud.stage.getCamera().combined);
        hud.stage.draw();



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

    public void gamecamUpdate(){
        //gamecam.position.set(new Vector3());
        gamecam.update();
    }

    public void update(){
        //Updates the world at 60fps
        world.step(1/60f, 6, 2);
        //updates the camera to follow player
        gamecamUpdate();
        //This allows the camera to be combined with projection and view
        spriteBatch.setProjectionMatrix(gamecam.combined);


        //Renders the map to the game camera
        orthogonalTiledMapRenderer.setView(gamecam);
        //add if statement for inputs
        if(Gdx.input.justTouched()){
            game.setScreen(new MainMenuScreen((ForestAdventures)game));
            dispose();
        }
   }

    public World getWorld() {
        return world;
    }
}
