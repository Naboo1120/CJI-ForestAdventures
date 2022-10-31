package wiu.cji.cs492.Objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import wiu.cji.cs492.coreGame.GameScreen;
import wiu.cji.cs492.coreGame.helper.Constants;

public abstract class GameEntity{
    protected float x, y,height, width, speed, velocityX, velocityY;
    protected static Body body;
    protected Texture texture;




    public GameEntity(float width, float height, Body body){


        this.body = body;
        this.x = body.getPosition().x;
        this.y = body.getPosition().y;
        this.height = height;
        this.width = width;
        this.velocityX = 0;
        this.velocityY = 0;
        this.speed = 0;


    }




    public void render(SpriteBatch spriteBatch){

    }
    public Body getBody(){return body;}

    public abstract void update();
}
