package wiu.cji.cs492.coreGame.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

import wiu.cji.cs492.Objects.Collectables;

public class WorldContactListener implements ContactListener {


    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        Gdx.app.log("Begin Contact", ""+fixA.getUserData());
        if (fixA.getUserData() == "head" || fixB.getUserData() =="head"){
            Fixture head = fixA.getUserData() == "head" ? fixA:fixB;
            Fixture object = head == fixA ? fixB:fixA;
            if (object.getUserData() != null && Collectables.class.isAssignableFrom(object.getUserData().getClass())) {
                ((Collectables) object.getUserData()).onHeadHit();

            }
        }

        int contactDef = fixA.getFilterData().categoryBits| fixB.getFilterData().categoryBits;

       // switch (contactDef){
           // case
        //}
    }

    @Override
    public void endContact(Contact contact) {
        Gdx.app.log("End Contact", "");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
