package wiu.cji.cs492.Objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import wiu.cji.cs492.coreGame.GameScreen;
import wiu.cji.cs492.coreGame.helper.Constants;
import wiu.cji.cs492.coreGame.helper.Hud;

public class Player extends GameEntity {

    protected TextureRegion bunnySit;


    @Override
    public Body getBody() {
        return super.getBody();
    }



    public Player(float width, float height, Body body, GameScreen gameScreen){
        super(width, height, body, gameScreen);
        this.speed = 10f;

        gameScreen.getAtlas().findRegion("BunnyLeft");
        bunnySit = new TextureRegion(getTexture(),0,0,47,31);
        setBounds(0, 0, 16/Constants.PPM, 16/Constants.PPM);
        setRegion(bunnySit);

    }
    public void handleInput(){
        //From Hud class it gets either 1 or -1 for left and right, and 2 for jump otherwise it stays 0

        if(Hud.movement() == 1){
            velocityX = 1;
        }
        else if(Hud.movement() == -1){
            velocityX = -1;
        }
        else if(Hud.movement() == 2){
            float force = body.getMass() *15;
            body.setLinearVelocity(body.getLinearVelocity().x, 0); // might need a debounce
            body.applyLinearImpulse(new Vector2(0, force), body.getPosition(), true);
        }
        else{
            velocityX = 0;
        }
        body.setLinearVelocity(velocityX * speed, body.getLinearVelocity().y <25 ? body.getLinearVelocity().y :25);
    }

    public static float getPlayerLinerVelocity() {
        return body.getLinearVelocity().y;
    }
    @Override
    public void update(float delta) {

        x = body.getPosition().x;
        y = body.getPosition().y;

        setPosition(body.getPosition().x-getWidth()/2,body.getPosition().y-getHeight());

        //Check the users key
        handleInput();
        Gdx.app.log("Player Update", "Its Updating");

    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

}
