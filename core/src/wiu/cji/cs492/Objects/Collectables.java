package wiu.cji.cs492.Objects;

import static wiu.cji.cs492.coreGame.helper.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
        shape.setAsBox(width/50/PPM + (body.getPosition().x)/PPM , height/50/PPM + (body.getPosition().y)/PPM );
        fdef.shape = shape;

       // body.createFixture(fdef).setUserData("food");

        fixture = body.createFixture(fdef);
        if (type =="Carrot"){ // need to remove later
            collTexture = new Texture("MapAssets/Props/Pretzel.png");
            collSprite = new Sprite(collTexture);
            collSprite.setPosition(0,0);
        }

    }
    public abstract void onHeadHit();

    @Override
    public void update() {

    }

    public void render(SpriteBatch spriteBatch) {

    }

    public Boolean getCollected() {
        return collected;
    }
public Boolean getTouched(){return  touched;}
    public void setCollected(Boolean collected) {
        this.collected = collected;
    }

    public Texture getTexture(){
        return this.texture;
    }
}
