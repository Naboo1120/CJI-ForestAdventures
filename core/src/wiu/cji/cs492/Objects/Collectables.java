package wiu.cji.cs492.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;


// can be changed to allow other the collections rn its written for food
public class Collectables extends GameEntity {

    public Collectables(float width, float height, Body body, String type) {
        super(width, height, body);
        if (type =="Carrot"){
            this.texture = new Texture(Gdx.files.internal("MapAssets/Props/Rock.png"));
        }
    }

    @Override
    public void update() {

    }
    public Texture getTexture(){
        return this.texture;
    }
}
