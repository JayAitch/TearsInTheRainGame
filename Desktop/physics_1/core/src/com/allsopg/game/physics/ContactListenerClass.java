package com.allsopg.game.physics;

import com.allsopg.game.bodies.CarPlatform;
import com.allsopg.game.bodies.PlayerCharacter;
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

        resolveCollision(contact.getFixtureA(), contact.getFixtureB());
    }
    public void endContact(Contact contact){

    }
    public void preSolve(Contact contact, Manifold manifold){

    }
    public void postSolve(Contact contact, ContactImpulse contactImpulse){

    }
    private void resolveCollision(Fixture fixtureA, Fixture fixtureB){
        if(fixtureA.getBody().getType() == BodyDef.BodyType.DynamicBody && fixtureB.getBody().getType() == BodyDef.BodyType.KinematicBody) {
            try {
                ((CarPlatform) fixtureB.getBody().getUserData()).reaction();
            } catch (Exception e) {
                System.out.println("not a car");
            }
            try {
                ((PlayerCharacter) fixtureA.getBody().getUserData()).reaction();
            } catch (Exception e) {
                System.out.println("not a car");
            }
        }
        if(fixtureB.getBody().getType() == BodyDef.BodyType.DynamicBody && fixtureA.getBody().getType() == BodyDef.BodyType.KinematicBody) {
            try {
                ((CarPlatform) fixtureA.getBody().getUserData()).reaction();
            } catch (Exception e) {
                System.out.println("not a car");
            }
            try {
                ((PlayerCharacter) fixtureB.getBody().getUserData()).reaction();
            } catch (Exception e) {
                System.out.println("not a car");
            }
        }
    }
}
