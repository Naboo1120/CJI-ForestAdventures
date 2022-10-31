package wiu.cji.cs492.Objects;

import static wiu.cji.cs492.coreGame.helper.Constants.PPM;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import wiu.cji.cs492.coreGame.helper.Hud;

public class Player extends GameEntity {
    protected Vector2 startLocation;
    protected Body bod;

    public Player(float width, float height, Body body){
        super(width, height, body);
        this.speed = 10f;
        startLocation = body.getPosition();
        //this may be added to the create entity class
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(13/PPM);
        //fdef.filter.categoryBits =


        //fdef.filter.categoryBits =

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2/PPM, 10/PPM),new Vector2(2/PPM, 5/PPM));
        fdef.shape = head;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("head");


// can use edgeShape to define certain body parts

    }
    public void handleInput(){
        //From Hud class it gets either 1 or -1 for left and right, and 2 for jump otherwise it stays 0

        if(Hud.movement() == 1){
            velocityX = 1;
        }
        else if(Hud.movement() == -1){
            velocityX = -1;
        }
        else if(Hud.movement() == 2){
            if(body.getLinearVelocity().y == 0) {
                float force = body.getMass() * 15;
                body.setLinearVelocity(body.getLinearVelocity().x, 0); // might need a debounce
                body.applyLinearImpulse(new Vector2(0, force), body.getPosition(), true);
            }
        }
        else{
            velocityX = 0;
        }
        body.setLinearVelocity(velocityX * speed, body.getLinearVelocity().y <25 ? body.getLinearVelocity().y :25);
    }


    @Override
    public void update() {

        x = body.getPosition().x;
        y = body.getPosition().y;
        //Check the users key
        handleInput();


    }

    public void resetFall(){
        x = body.getPosition().x - 20/PPM;
        y = startLocation.y;
        //update();
    }


    @Override
    public void render(SpriteBatch spriteBatch) {


    }
}
