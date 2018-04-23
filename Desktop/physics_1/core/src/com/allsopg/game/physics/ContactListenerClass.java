package com.allsopg.game.physics;

import com.allsopg.game.bodies.CarPlatform;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Created by Jordan Harrison on 23/04/2018.
 */

public class ContactListenerClass implements com.badlogic.gdx.physics.box2d.ContactListener {

    // when any objects contact this method is fired
    public void beginContact(Contact contact){
        Fixture fixture1 = contact.getFixtureA();
        Fixture fixture2 = contact.getFixtureB();
       // if(fixture1.getBody().getType() == BodyDef.BodyType.KinematicBody && fixture2.getBody().getType() == BodyDef.BodyType.KinematicBody) {
            try {
                ((CarPlatform) fixture1.getBody().getUserData()).reaction();
            } catch (Exception e) {
                System.out.println("not a car");
            }
            try {
                ((CarPlatform) fixture2.getBody().getUserData()).reaction();
            } catch (Exception e) {
            System.out.println("not a car");
            }

        System.out.println(fixture1.getBody().getType()+" has hit "+ fixture2.getBody().getType());
    }
    public void endContact(Contact contact){

    }
    public void preSolve(Contact contact, Manifold manifold){

    }
    public void postSolve(Contact contact, ContactImpulse contactImpulse){

    }
}
