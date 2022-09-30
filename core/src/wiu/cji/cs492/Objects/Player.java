package wiu.cji.cs492.Objects;

import static wiu.cji.cs492.coreGame.helper.Constants.PPM;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public class Player extends GameEntity {

    public Player(float width, float height, Body body){
        super(width, height, body);

    }
    @Override
    public void update() {
        x =body.getPosition().x*PPM;
        y =body.getPosition().y*PPM;
        //Check the users key

    }
    @Override
    public void render(SpriteBatch spriteBatch) {

    }
}
