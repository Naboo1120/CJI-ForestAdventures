package wiu.cji.cs492.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;

import wiu.cji.cs492.coreGame.helper.Hud;

public class Food extends Collectables{

    public Food(float width, float height, Body body, String type) {
        super(width, height, body, type);
        fixture.setUserData(this);

    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Food", "Player has collided with food");
        this.body = null;
        this.collected = false;

    }
}
