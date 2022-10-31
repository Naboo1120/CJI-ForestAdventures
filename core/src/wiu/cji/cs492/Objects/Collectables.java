package wiu.cji.cs492.Objects;

import static wiu.cji.cs492.coreGame.helper.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;


// can be changed to allow other the collections rn its written for food
public abstract class Collectables extends GameEntity {
    protected Fixture fixture;
    protected Boolean collected;
    public Collectables(float width, float height, Body body, String type) {
        super(width, height, body);
        collected = false;
        FixtureDef fdef  = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width /2/PPM + body.getPosition().x, height/2/PPM +body.getPosition().y );
        fdef.shape = shape;

        body.createFixture(fdef).setUserData("food");

        fixture = body.createFixture(fdef);
        if (type =="Carrot"){ // need to remove later
            this.texture = new Texture(Gdx.files.internal("MapAssets/Props/Rock.png"));
        }

    }
    public abstract void onHeadHit();

    @Override
    public void update() {

    }

    public Boolean getCollected() {
        return collected;
    }

    public void setCollected(Boolean collected) {
        this.collected = collected;
    }

    public Texture getTexture(){
        return this.texture;
    }
}
