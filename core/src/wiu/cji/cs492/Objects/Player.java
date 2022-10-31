package wiu.cji.cs492.Objects;

import static wiu.cji.cs492.coreGame.helper.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import wiu.cji.cs492.coreGame.GameScreen;

public class Player extends GameEntity {
    protected Vector2 startLocation;
    public Player(float width, float height, Body body){
        super(width, height, body);
        this.speed = 10f;
        startLocation = body.getPosition();
        //this may be added to the create entity class
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(22/PPM);

        fdef.shape = shape;
        body.createFixture(fdef);

        //fdef.filter.categoryBits =

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2/PPM, 10/PPM),new Vector2(2/PPM, 5/PPM));
        fdef.shape = head;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("head");

        shape.setRadius(22/PPM);

        fdef.shape = shape;


        //fdef.filter.categoryBits =

        EdgeShape feet = new EdgeShape();
        feet.set(new Vector2(-4/PPM, 10/PPM),new Vector2(2/PPM, 5/PPM));
        fdef.shape = feet;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("playerBody");


// can use edgeShape to define certain body parts

    }
    public void handleInput(){
        velocityX = 0;
        if(Gdx.input.isTouched()){

            if(Gdx.input.getX() > Gdx.graphics.getWidth() / 2){
                velocityX = 1;
            }
            if(Gdx.input.getX() < Gdx.graphics.getWidth() / 2){
                velocityX = -1;
            }
            if(Gdx.input.getY() < Gdx.graphics.getHeight() / 2){
                if (velocityY <= 0){ // needs to check for collision
                    float force = body.getMass() *10;
                    body.setLinearVelocity(body.getLinearVelocity().x, 0); // might need a debounce
                    body.applyLinearImpulse(new Vector2(0, force), body.getPosition(), true);
                }
            }
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
        update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {


    }
}
