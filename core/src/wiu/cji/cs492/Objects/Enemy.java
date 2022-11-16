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

public class Enemy extends GameEntity {

    int flip, tflip;  //flip time is multiplied by 60
    public Boolean collided;
    public Texture enemyTex;
    public TextureRegion enemyTexReg;
    public Sprite enemySprite;

    public Enemy(float width, float height, Body body, int flipTime){
        super(width, height, body);
        this.speed = 3f; // speed will be one third of players speed
        this.velocityX = 1;
        flip = flipTime; //flip time can be changed depending on length of platform
        tflip = flip;
        collided = false;

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(30/PPM);
        //fdef.filter.categoryBits =


        //fdef.filter.categoryBits =

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2/PPM, 10/PPM),new Vector2(2/PPM, 5/PPM));
        fdef.shape = head;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData(this);

        enemyTex = new Texture("EnemyAssets/fox-NESW.png");
        enemyTexReg = new TextureRegion(enemyTex,0,106,45,22);
        enemySprite = new Sprite(enemyTexReg);
        enemySprite.setPosition(0,0);

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
        body.setLinearVelocity(velocityX * -speed, velocityY);
        if(tflip<=0) {
            this.velocityX = this.velocityX * -1;
            tflip += this.flip;
        }
        else
            tflip-=1;

//        handleInput();
    }

    public void onhit(){
        collided = true;
        // game.dispose();
        Gdx.app.log("Enemy", "found the player was attacked ");
    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(enemySprite,  body.getPosition().x * PPM - enemySprite.getWidth() / 2, body.getPosition().y * PPM - enemySprite.getHeight() / 4);
    }
}
