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
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import wiu.cji.cs492.Objects.Collectables;
import wiu.cji.cs492.Objects.GameEntity;
import wiu.cji.cs492.Objects.Player;
import wiu.cji.cs492.coreGame.helper.Hud;
import wiu.cji.cs492.coreGame.helper.TileMapHelper;
import wiu.cji.cs492.coreGame.helper.WorldContactListener;


public class GameScreen implements Screen {

    final ForestAdventures game;

    private World world;

    private Hud hud;

    private TextureAtlas atlas;

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
    private Array<Collectables> collect = new Array<Collectables>();


    public GameScreen(final ForestAdventures game){
        atlas = new TextureAtlas("PlayerAssets/bunny.pack");

        this.game = game;
        hud = new Hud(game.batch);
        this.spriteBatch = new SpriteBatch();
        this.world = new World(new Vector2(0,-25.3f),false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        this.tileMapHelper = new TileMapHelper(this);
        //Calls to helper class
        this.orthogonalTiledMapRenderer = tileMapHelper.setUpMap(); //Can we use this to pass levels?

        //Creates Player
        //player = new Player(5f,5f, player.getBody(),this);

        //Camera
        gamecam = new OrthographicCamera();
        viewport = new ExtendViewport(250, 225, gamecam);
        world.setContactListener(new WorldContactListener());

    }


    @Override
    public void show() {


    }

    public void update(float delta){
        //Updates the world at 60fps
        world.step(1/60f, 6, 2);
        player.update(delta);
        //updates the camera to follow player
        gamecamUpdate();
        //Renders the map to the game camera
        orthogonalTiledMapRenderer.setView(gamecam);


    }

    @Override
    public void render(float delta) {
        this.update(delta);
        ScreenUtils.clear(0,0,0.2f,0);
        orthogonalTiledMapRenderer.render();
        box2DDebugRenderer.render(world, gamecam.combined.scl(PPM));
        spriteBatch.setProjectionMatrix(gamecam.combined.scl(PPM));

        spriteBatch.begin();

        player.draw(spriteBatch);

        for (Collectables c : collect){
            Body body = c.getBody();

            spriteBatch.draw(c.getTexture(), body.getPosition().x, body.getPosition().y);


        }
        spriteBatch.end();
        spriteBatch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();


        //Renders the objects





    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);

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
        box2DDebugRenderer.dispose();


    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    public void gamecamUpdate(){
        Vector3 position = gamecam.position;
        position.x = Math.round(player.getBody().getPosition().x *PPM *10)/10f;
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
    public void addCollectables(Collectables collectables){
        collect.add(collectables);
        Gdx.app.log("collectables", "Collectable created");
    }
    public void removeCollectable(Collectables collectables){
        //???

    }
}
