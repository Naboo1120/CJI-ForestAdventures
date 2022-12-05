package wiu.cji.cs492.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;


public class Food extends Collectables{
    public String food;
    public Food(float width, float height, Body body, String type) {
        super(width, height, body, type);
        this.fixture.setUserData(this);
        this.fixture.setSensor(true);
        body.setGravityScale(0);
        food = type;



    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Food", "Player has collided with food");
        //this.body = null; // might have to remove body from world???
        this.fixture.setSensor(true);

        this.fixture.setUserData(null);
        body.setGravityScale(-10);




        // this.fixture = null;
        this.collected = false;
        this.touched = true;

    }
    public void onHeadHits(Vector2 v) {
        Gdx.app.log("Food", "Player has collided with food");
        //this.body = null; // might have to remove body from world???
        this.fixture.setSensor(true);

        this.fixture.setUserData(null);
        body.setGravityScale(-100);
///NOTE TO JOY REMAP LEVEL 1.8



       // this.fixture = null;
        this.collected = false;
        this.touched = true;

    }
}
