package wiu.cji.cs492.Objects;

import static wiu.cji.cs492.coreGame.helper.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class DeathWall extends GameEntity{
    protected Fixture fixture;
    public DeathWall(float width, float height, Body body) {
        super(width, height, body);

        FixtureDef fdef  = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width /2/PPM + body.getPosition().x, height/2/PPM +body.getPosition().y );
        fdef.shape = shape;

        body.createFixture(fdef).setUserData("DeathWall");

        fixture = body.createFixture(fdef);
    }

    @Override
    public void update() {

    }
    public void onhit(Player plr){
        plr.resetFall();
        Gdx.app.log("DeathWall", "found the players fall damage lol ");
    }
}
