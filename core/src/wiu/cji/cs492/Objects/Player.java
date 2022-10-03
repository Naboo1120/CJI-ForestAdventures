package wiu.cji.cs492.Objects;

import static wiu.cji.cs492.coreGame.helper.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

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
            }else{
                velocityX = -1;

            }
        }
        body.setLinearVelocity(velocityX * speed, body.getLinearVelocity().y <25 ? body.getLinearVelocity().y :25);
    }
    @Override
    public void update() {
        x =body.getPosition().x*PPM;
        y =body.getPosition().y*PPM;
        //Check the users key
        handleInput();
    }
    @Override
    public void render(SpriteBatch spriteBatch) {


    }
}
