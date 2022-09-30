package wiu.cji.cs492.coreGame.helper;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import wiu.cji.cs492.Objects.Player;
import wiu.cji.cs492.coreGame.GameScreen;

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
        parseMapObjects(tiledMap.getLayers().get("Ground Layer").getObjects());
        parseMapObjects(tiledMap.getLayers().get("Player").getObjects());
        //returns to the game screen
        return  new OrthogonalTiledMapRenderer(tiledMap);
    }

    public void parseMapObjects(MapObjects mapObjects){
        //Iteration through map objects
        for(MapObject mapObject : mapObjects){
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
                    ); gameScreen.setPlayer(new Player(rectangle.width, rectangle.height, body));}
                else {
                    //Box2d uses center cords so we need to math
                    BodyDef bodyDef = getBodyDef(rectangle.getX() + rectangle.getWidth() / 2f, rectangle.getY() + rectangle.getHeight() / 2f);

                    Body body = gameScreen.getWorld().createBody(bodyDef);
                    PolygonShape polygonShape = new PolygonShape();
                    polygonShape.setAsBox(rectangle.getWidth() / 2f, rectangle.getHeight() / 2f);
                    body.createFixture(polygonShape, 0.0f);
                    polygonShape.dispose();
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

    /*private void createStaticBody(RectangleMapObject rectangleMapObject){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = gameScreen.getWorld().createBody(bodyDef);
        Shape shape = createRectangleShape(rectangleMapObject);
        body.createFixture(shape, 1000);
        shape.dispose();
    }*/


}
