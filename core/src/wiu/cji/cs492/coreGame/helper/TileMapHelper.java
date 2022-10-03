package wiu.cji.cs492.coreGame.helper;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import com.badlogic.gdx.physics.box2d.Shape;
import wiu.cji.cs492.Objects.Player;
import wiu.cji.cs492.coreGame.GameScreen;

import static wiu.cji.cs492.coreGame.helper.Constants.PPM;

public class TileMapHelper {
    private TiledMap tiledMap;
    private GameScreen gameScreen;

    public TileMapHelper(GameScreen gameScreen){
        this.gameScreen = gameScreen;
    }

    //Method to render the map and objects
    public OrthogonalTiledMapRenderer setUpMap(){
        //grabs the objects and map files
        tiledMap = new TmxMapLoader().load("MapAssets/Map0.1.tmx");
        //THIS WAS THE WORST PART
        parseMapObjects(tiledMap.getLayers().get("Ground Object").getObjects());
        parseMapObjects(tiledMap.getLayers().get("Player").getObjects());
        //returns to the game screen
        return  new OrthogonalTiledMapRenderer(tiledMap);
    }

    public void parseMapObjects(MapObjects mapObjects){
        //Iteration through map objects
        for(MapObject mapObject : mapObjects){

            if(mapObject instanceof PolygonMapObject){
                createStaticBody((PolygonMapObject) mapObject);
            }

            //Rectangle Case
            if(mapObject instanceof RectangleMapObject){
                RectangleMapObject rectangleObject = (RectangleMapObject) mapObject;
                Rectangle rectangle = rectangleObject.getRectangle();
                String tempName = mapObject.getName();
                if (tempName != null && tempName.equals("player")){
                    Body body = BodyHelperService.createBody(
                            rectangle.getX() + rectangle.getWidth()/2,
                            rectangle.getY()+rectangle.getHeight()/2,
                            rectangle.getWidth(),
                            rectangle.getHeight(),
                            false,
                            gameScreen.getWorld()
                    );
                    gameScreen.setPlayer(new Player(rectangle.width, rectangle.height, body));
                }

            }
        }
    }


    private BodyDef getBodyDef(float x, float y)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);

        return bodyDef;
    }

    private void createStaticBody(PolygonMapObject polygonMapObject){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = gameScreen.getWorld().createBody(bodyDef);
        Shape shape = createPolygonShape(polygonMapObject);
        body.createFixture(shape, 1000);
        shape.dispose();
    }

    private Shape createPolygonShape(PolygonMapObject polygonMapObject) {
        float[] vertices = polygonMapObject.getPolygon().getTransformedVertices();
        Vector2[] worldVeritces = new Vector2[vertices.length/2];
        for (int i = 0; i< vertices.length/2 ; i++){
            Vector2 current = new Vector2(vertices[i*2]/PPM, vertices[i*2 +1]/PPM); // need to devide to match the world "scaling"
            worldVeritces[i] = current;
        }

        PolygonShape shape = new PolygonShape();
        shape.set(worldVeritces);
        return shape;
    }


}