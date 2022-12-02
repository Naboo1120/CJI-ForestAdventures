package wiu.cji.cs492.Objects;

import static wiu.cji.cs492.coreGame.helper.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.steer.behaviors.Jump;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import wiu.cji.cs492.coreGame.helper.Hud;

public class Player extends GameEntity {
    protected Vector2 startLocation;
    protected Texture tex;
    protected TextureRegion texRegion;
    protected Sprite frun1, frun2, brun1, brun2, sit, bsit;
    protected int frames = 30;
    protected boolean forward = true;
    private Sound JumpSound;
    public String playerType;


    public Player(float width, float height, Body body, String type){
        super(width, height, body);
        this.speed = 10f;
        startLocation = body.getPosition();
        //this may be added to the create entity class
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(13/PPM);
        playerType = type;
        //fdef.filter.categoryBits =

        JumpSound = Gdx.audio.newSound(Gdx.files.internal("SoundEffects/JumpSound.mp3"));

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2/PPM, 10/PPM),new Vector2(2/PPM, 5/PPM));
        fdef.shape = head;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("head");

        //Creation of the texture and sprite for the player
        if(type.equals("Bunny")) {
            tex = new Texture("PlayerAssets/bunnyAni.png");
            //        texRegion = new TextureRegion(tex,98,37,49,31);   //tester
            texRegion = new TextureRegion(tex, 147, 37, 47, 31);  //frun1
            frun1 = new Sprite(texRegion);
            frun1.setPosition(0, 0);
            texRegion = new TextureRegion(tex, 98, 37, 49, 31);     //frun2
            frun2 = new Sprite(texRegion);
            frun2.setPosition(0, 0);
            texRegion = new TextureRegion(tex, 0, 3, 47, 31);    //brun1
            brun1 = new Sprite(texRegion);
            brun1.setPosition(0, 0);
            texRegion = new TextureRegion(tex, 49, 3, 49, 31);      //brun2
            brun2 = new Sprite(texRegion);
            brun2.setPosition(0, 0);
            texRegion = new TextureRegion(tex, 43, 66, 47, 33);     //sit
            sit = new Sprite(texRegion);
            sit.setPosition(0, 0);
            texRegion = new TextureRegion(tex, 0, 67, 42, 31);     //bsit
            bsit = new Sprite(texRegion);
            bsit.setPosition(0, 0);
        }
        else if(type.equals("Squirrel")) {
            tex = new Texture("PlayerAssets/Squirrel.png");
            //        texRegion = new TextureRegion(tex,98,37,49,31);   //tester
            texRegion = new TextureRegion(tex, 147, 37, 47, 31);  //frun1
            frun1 = new Sprite(texRegion);
            frun1.setPosition(0, 0);
            texRegion = new TextureRegion(tex, 98, 37, 49, 31);     //frun2
            frun2 = new Sprite(texRegion);
            frun2.setPosition(0, 0);
            texRegion = new TextureRegion(tex, 0, 3, 47, 31);    //brun1
            brun1 = new Sprite(texRegion);
            brun1.setPosition(0, 0);
            texRegion = new TextureRegion(tex, 49, 3, 49, 31);      //brun2
            brun2 = new Sprite(texRegion);
            brun2.setPosition(0, 0);
            texRegion = new TextureRegion(tex, 43, 66, 47, 33);     //sit
            sit = new Sprite(texRegion);
            sit.setPosition(0, 0);
            texRegion = new TextureRegion(tex, 0, 67, 42, 31);     //bsit
            bsit = new Sprite(texRegion);
            bsit.setPosition(0, 0);
        }
        else if(type.equals("Raccoon")) {
            tex = new Texture("PlayerAssets/Raccoon.png");
            //        texRegion = new TextureRegion(tex,98,37,49,31);   //tester
            texRegion = new TextureRegion(tex, 147, 37, 47, 31);  //frun1
            frun1 = new Sprite(texRegion);
            frun1.setPosition(0, 0);
            texRegion = new TextureRegion(tex, 98, 37, 49, 31);     //frun2
            frun2 = new Sprite(texRegion);
            frun2.setPosition(0, 0);
            texRegion = new TextureRegion(tex, 0, 3, 47, 31);    //brun1
            brun1 = new Sprite(texRegion);
            brun1.setPosition(0, 0);
            texRegion = new TextureRegion(tex, 49, 3, 49, 31);      //brun2
            brun2 = new Sprite(texRegion);
            brun2.setPosition(0, 0);
            texRegion = new TextureRegion(tex, 43, 66, 47, 33);     //sit
            sit = new Sprite(texRegion);
            sit.setPosition(0, 0);
            texRegion = new TextureRegion(tex, 0, 67, 42, 31);     //bsit
            bsit = new Sprite(texRegion);
            bsit.setPosition(0, 0);
        }






// can use edgeShape to define certain body parts

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
            if(body.getLinearVelocity().y == 0) {
                float force = body.getMass() * 15;
                body.setLinearVelocity(body.getLinearVelocity().x, 0); // might need a debounce
                body.applyLinearImpulse(new Vector2(0, force), body.getPosition(), true);
                long id = JumpSound.play(1f);
                JumpSound.setLooping(id,false);
            }
        }
        else{
            velocityX = 0;
        }
        body.setLinearVelocity(velocityX * speed, body.getLinearVelocity().y <25 ? body.getLinearVelocity().y :25);
    }


    @Override
    public void update() {

        x = body.getPosition().x;
        y = body.getPosition().y;
        //Check the users key
        handleInput();




    }

    public void resetFall(){
        x = body.getPosition().x - 20/PPM;
        y = startLocation.y;
        update();
    }


    @Override
    public void render(SpriteBatch spriteBatch) {
        //Drawing of the player, called in the gameScreen class
        spriteBatch.begin();

        if(body.getLinearVelocity().x==0 && body.getLinearVelocity().y ==0 && forward) {
            spriteBatch.draw(sit, body.getPosition().x * PPM - sit.getWidth() / 2, body.getPosition().y * PPM - sit.getHeight() / 2);
            frames = 10;
        }
        else if(body.getLinearVelocity().x==0 && body.getLinearVelocity().y ==0 && !forward)
        {
            spriteBatch.draw(bsit, body.getPosition().x * PPM - sit.getWidth() / 2, body.getPosition().y * PPM - sit.getHeight() / 2);
            frames = 10;
        }
        else if(velocityX < 0) {
            if(frames < 15)
                spriteBatch.draw(brun1, body.getPosition().x * PPM - brun1.getWidth() / 2, body.getPosition().y * PPM - brun1.getHeight() / 2);
            else
                spriteBatch.draw(brun2, body.getPosition().x * PPM - brun2.getWidth() / 2, body.getPosition().y * PPM - brun2.getHeight() / 2);
            if(frames <= 0)
                frames +=30;
            else
                frames--;
            forward = false;
        }
        else {
            if(frames<15)
                spriteBatch.draw(frun1, body.getPosition().x * PPM - frun1.getWidth() / 2, body.getPosition().y * PPM - frun1.getHeight() / 2);
            else
                spriteBatch.draw(frun2, body.getPosition().x * PPM - frun2.getWidth() / 2, body.getPosition().y * PPM - frun2.getHeight() / 2);
            if(frames <= 0)
                frames +=30;
            else
                frames--;
            forward = true;
        }

        spriteBatch.end();

    }
}
