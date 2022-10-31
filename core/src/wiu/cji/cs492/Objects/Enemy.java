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
    public Boolean collided;

    public Enemy(float width, float height, Body body, int flipTime){
        super(width, height, body);
        this.speed = 5f; // speed will be half of player
        flip = flipTime; //flip time can be changed depending on length of platform
        collided = false;

    }
    public void handleInput(){      //in case of input
        if(Gdx.input.isTouched()){

        }
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

    public void onhit(){
        collided = true;
        // game.dispose();
        Gdx.app.log("DeathWall", "found the players fall damage lol ");
    }

    @Override
    public void render(SpriteBatch spriteBatch) {


    }
}
