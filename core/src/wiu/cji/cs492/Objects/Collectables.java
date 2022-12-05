package wiu.cji.cs492.Objects;

import static wiu.cji.cs492.coreGame.helper.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;


// can be changed to allow other the collections rn its written for food
public abstract class Collectables extends GameEntity {
    protected Fixture fixture;
    protected Boolean collected;
    protected Texture collTexture;
    protected Sprite collSprite;
    protected Boolean touched;
    public Collectables(float width, float height, Body body, String type) {
        super(width, height, body);
        collected= false;
        touched = false;
        FixtureDef fdef  = new FixtureDef();
        PolygonShape shape = new PolygonShape();



        shape.setAsBox(.3f,  .3f);
        fdef.shape = shape;
        if (type =="Carrot"){ // id the type is carrot set this texture
            collTexture = new Texture("MapAssets/Props/Carrot.png");
            collSprite = new Sprite(collTexture);
            collSprite.setPosition(0,0);
        }
        else if(type == "Acorn")
        {
            collTexture = new Texture("MapAssets/Props/Acorn.png");
            collSprite = new Sprite(collTexture);
            collSprite.setPosition(0,0);
        }
        else if(type == "Trash")
        {
            collTexture = new Texture("MapAssets/Props/Garbage.png");
            collSprite = new Sprite(collTexture);
            collSprite.setPosition(0,0);
        }
        fdef.isSensor = true;

       // body.createFixture(fdef).setUserData("food");

        fixture = body.createFixture(fdef);


    }
    public abstract void onHeadHit();

    @Override
    public void update() {

    }

    public void draw(SpriteBatch spriteBatch) {
        //Draws the sprite on the body for the collectables
        if(!collected) {
            spriteBatch.draw(collSprite, body.getPosition().x * PPM - collSprite.getWidth() / 2, body.getPosition().y * PPM - collSprite.getHeight() / 2);
        }
    }

    public Boolean getCollected() {
        return collected;
    }
public Boolean getTouched(){return  touched;}
    public void setCollected(Boolean collected) {
        Gdx.app.log("Collectables", "Size of collection is " + width  + " with a added postion of " + (body.getPosition().x) / PPM);

        this.collected = collected;
    }

    public Texture getTexture(){
        return this.texture;
    }
}
