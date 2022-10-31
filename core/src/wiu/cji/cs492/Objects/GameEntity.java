package wiu.cji.cs492.Objects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import wiu.cji.cs492.coreGame.GameScreen;
import wiu.cji.cs492.coreGame.helper.Constants;

public abstract class GameEntity extends Sprite {
    protected float x, y,height, width, speed, velocityX, velocityY;
    protected static Body body;
    protected Texture texture;
    protected TextureRegion bunnySit;


    public GameEntity(float width, float height, Body body, GameScreen gameScreen){
        super(gameScreen.getAtlas().findRegion("bunnyRight"));
        bunnySit = new TextureRegion(getTexture(), 0,0,16,16);
        setBounds(0,0, 16/ Constants.PPM, 16/Constants.PPM);
        setRegion(bunnySit);

        this.body = body;
        this.x = body.getPosition().x;
        this.y = body.getPosition().y;
        this.height = height;
        this.width = width;
        this.velocityX = 0;
        this.velocityY = 0;
        this.speed = 0;

    }

    public void update(float delta){
        setPosition(x-getWidth()/2, y/getHeight()/2);
    }
    public void render(SpriteBatch spriteBatch){

    }
    public Body getBody(){return body;}
}
