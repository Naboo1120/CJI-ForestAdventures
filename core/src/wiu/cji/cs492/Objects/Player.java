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

public class Player extends GameEntity {


    public Player(float width, float height, Body body){
        super(width, height, body);
        this.speed = 10f;


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
                float force = body.getMass() *10;
                body.setLinearVelocity(body.getLinearVelocity().x, 0); // might need a debounce
                body.applyLinearImpulse(new Vector2(0, force), body.getPosition(), true);
            }
        }
        body.setLinearVelocity(velocityX * speed, body.getLinearVelocity().y <25 ? body.getLinearVelocity().y :25);
    }
    @Override
    public void update() {
        x =body.getPosition().x;
        y =body.getPosition().y;
        //Check the users key
        handleInput();


    private void checkUserInput(){
        velocityX = 0;
        if()
    }

    @Override
    public void render(SpriteBatch spriteBatch) {


    }
}
