package wiu.cji.cs492.Objects;

import static wiu.cji.cs492.coreGame.helper.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.steer.behaviors.MatchVelocity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import wiu.cji.cs492.coreGame.helper.Hud;

public class Player extends GameEntity {

    public Player(float width, float height, Body body){
        super(width, height, body);
        this.speed = 10f;


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
            float force = body.getMass() *15;
            body.setLinearVelocity(body.getLinearVelocity().x, 0); // might need a debounce
            body.applyLinearImpulse(new Vector2(0, force), body.getPosition(), true);
        }
        else{
            velocityX = 0;

        }
        body.setLinearVelocity(velocityX * speed, body.getLinearVelocity().y <25 ? body.getLinearVelocity().y :25);
    }

    public static float getPlayerLinerVelocity() {
        return body.getLinearVelocity().y;
    }
    @Override
    public void update() {
        x =body.getPosition().x;
        y =body.getPosition().y;

        //Check the users key
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {


    }
}
