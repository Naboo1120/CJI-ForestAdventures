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
    protected Sprite frun1, frun2, brun1, brun2;
    protected int frames = 30;
    protected boolean forward = true;
    protected String eType;

    public Enemy(float width, float height, Body body, String type, int flipTime){
        super(width, height, body);
        this.speed = 3f; // speed will be one third of players speed
        this.velocityX = 1;
        flip = flipTime; //flip time can be changed depending on length of platform
        tflip = flip;
        collided = false;
        eType = type;

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

        //load enemy skins
        if(type.equals("Fox")) {
            enemyTex = new Texture("EnemyAssets/fox-NESW.png");
            enemyTexReg = new TextureRegion(enemyTex,0,108,45,28);
            brun1 = new Sprite(enemyTexReg);
            brun1.setPosition(0,0);
            enemyTexReg = new TextureRegion(enemyTex,100,108,45,28);
            brun2 = new Sprite(enemyTexReg);
            brun2.setPosition(0,0);
            enemyTexReg = new TextureRegion(enemyTex,0,230,45,28);
            frun1 = new Sprite(enemyTexReg);
            frun1.setPosition(0,0);
            enemyTexReg = new TextureRegion(enemyTex,100,230,45,28);
            frun2 = new Sprite(enemyTexReg);
            frun2.setPosition(0,0);
        }
        else if(type.equals("Snake"))
        {
            enemyTex = new Texture("EnemyAssets/Snake.png");
            enemyTexReg = new TextureRegion(enemyTex,5,5,10,10);
            brun1 = new Sprite(enemyTexReg);
            brun1.setPosition(0,0);
            enemyTexReg = new TextureRegion(enemyTex,100,108,45,28);
            brun2 = new Sprite(enemyTexReg);
            brun2.setPosition(0,0);
            enemyTexReg = new TextureRegion(enemyTex,0,230,45,28);
            frun1 = new Sprite(enemyTexReg);
            frun1.setPosition(0,0);
            enemyTexReg = new TextureRegion(enemyTex,100,230,45,28);
            frun2 = new Sprite(enemyTexReg);
            frun2.setPosition(0,0);
        }
        else if(type.equals("Wolf"))
        {
            enemyTex = new Texture("EnemyAssets/Wolf.png");
            enemyTexReg = new TextureRegion(enemyTex,0,35,40,28);
            brun1 = new Sprite(enemyTexReg);
            brun1.setPosition(0,0);
            enemyTexReg = new TextureRegion(enemyTex,110,35,40,28);
            brun2 = new Sprite(enemyTexReg);
            brun2.setPosition(0,0);
            enemyTexReg = new TextureRegion(enemyTex,0,0,40,28);
            frun1 = new Sprite(enemyTexReg);
            frun1.setPosition(0,0);
            enemyTexReg = new TextureRegion(enemyTex,110,0,40,28);
            frun2 = new Sprite(enemyTexReg);
            frun2.setPosition(0,0);
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

    }

    public void onhit(){
        collided = true;
        // game.dispose();
        Gdx.app.log("Enemy", "found the player was attacked ");
    }

    public void draw(SpriteBatch spriteBatch) {
//        spriteBatch.draw(frun1,  body.getPosition().x * PPM - frun1.getWidth() / 2, body.getPosition().y * PPM - frun1.getHeight() / 4);


        if(velocityX < 0) {
            if(frames < 15)
                spriteBatch.draw(brun1, body.getPosition().x * PPM - brun1.getWidth() / 2, body.getPosition().y * PPM - brun1.getHeight() / 2);
            else
                spriteBatch.draw(brun2, body.getPosition().x * PPM - brun2.getWidth() / 2, body.getPosition().y * PPM - brun2.getHeight() / 2);
            if(frames <= 0)
                frames +=30;
            else
                frames--;
            forward = false;
        }
        else {
            if(frames<15)
                spriteBatch.draw(frun1, body.getPosition().x * PPM - frun1.getWidth() / 2, body.getPosition().y * PPM - frun1.getHeight() / 2);
            else
                spriteBatch.draw(frun2, body.getPosition().x * PPM - frun2.getWidth() / 2, body.getPosition().y * PPM - frun2.getHeight() / 2);
            if(frames <= 0)
                frames +=30;
            else
                frames--;
            forward = true;
        }
    }
}
