package wiu.cji.cs492.Objects;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class GameEntity{
    protected float x, y,height, width, speed, velocityX, velocityY;
    protected Body body;


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

    public abstract void update();
    public void render(SpriteBatch spriteBatch){

    }
    public Body getBody(){return body;}
}
