package wiu.cji.cs492.Objects;

import static wiu.cji.cs492.coreGame.helper.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Finish extends GameEntity{
    protected Fixture fixture;
    public Boolean collided;
    public Finish(float width, float height, Body body) {
        super(width, height, body);
        this.collided = false;
        FixtureDef fdef  = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width /PPM , height/PPM );
        fdef.shape = shape;
        fdef.isSensor=true;
        body.setGravityScale(0);


        body.createFixture(fdef).setUserData(this);

        this.fixture = body.createFixture(fdef);
    }

    @Override
    public void update() {

    }
    public void onhit( ){
        this.collided = true;
        // game.dispose();

        // update game log

        Gdx.app.log("DeathWall", "found the players fall damage lol ");
    }
}
