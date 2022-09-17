package wiu.cji.cs492.helper;

import com.badlogic.gdx.physics.box2d.*;

import static wiu.cji.cs492.helper.Constants.PPM;

public class BodyHelperService {
    public static Body createBody(float x, float y, float width, float height, boolean isStatic, World world){
        //Creation of new body definition for the player
        BodyDef bodyDef = new BodyDef();
        //The type of body
        bodyDef.type = isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
        //Sets position of body
        bodyDef.position.set(x / PPM, y / PPM);
        //Defines the rotation if it will change or be static
        bodyDef.fixedRotation = true;

        //Constructor of body
        Body body = world.createBody(bodyDef);

        //Creates the shape of the body set as a box
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2/PPM, height/2/PPM);

        //Will create shapes the world can collide with
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }
}
