package wiu.cji.cs492.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import wiu.cji.cs492.coreGame.helper.Hud;

public class Food extends Collectables{
    Vector2 startPosition;

    public Food(float width, float height, Body body, String type) {
        super(width, height, body, type);
        fixture.setUserData(this);
        body.setGravityScale(0);




    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Food", "Player has collided with food");
        //this.body = null; // might have to remove body from world???
        fixture.setSensor(true);

        fixture.setUserData(null);
        body.setGravityScale(-10);



       // this.fixture = null;
        this.collected = false;
        this.touched = true;

    }
}
