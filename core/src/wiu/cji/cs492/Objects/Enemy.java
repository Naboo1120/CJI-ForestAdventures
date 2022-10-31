package wiu.cji.cs492.Objects;

import static wiu.cji.cs492.coreGame.helper.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import wiu.cji.cs492.coreGame.GameScreen;

public class Enemy extends GameEntity {

    int flip=60;  //flip time is multiplied by 60
    public Enemy(float width, float height, Body body, int flipTime){
        super(width, height, body);
        this.speed = 5f;
        flip = flipTime;


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
        //walks one direction flipping every second infinitely
        if(flip<=0) {
            body.setLinearVelocity(speed*-1, 0);
            flip += 60;
        }
        else
            flip-=1;

//        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {


    }
}
