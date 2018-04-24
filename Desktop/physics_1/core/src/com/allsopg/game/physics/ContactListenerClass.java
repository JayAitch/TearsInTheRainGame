package com.allsopg.game.physics;

import com.allsopg.game.bodies.CarPlatform;
import com.allsopg.game.bodies.PlayerCharacter;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Listens for contact between fixtures and resolves collisions
 * Class is instantiated inside world manager
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

    // resolve collision between fixtures and calls there reactions
    private void resolveCollision(Fixture fixtureA, Fixture fixtureB){
        if(fixtureA.getBody().getType() == BodyDef.BodyType.DynamicBody && fixtureB.getBody().getType() == BodyDef.BodyType.KinematicBody) { // if fa is dynamic and fix b is kinamatic
            try {
                ((CarPlatform) fixtureB.getBody().getUserData()).reaction(); // try cast b -> mob car
            } catch (Exception e) {
                System.out.println("not a car");
            }
            try {
                ((PlayerCharacter) fixtureA.getBody().getUserData()).reaction(); // try cast a -> player character
            } catch (Exception e) {
                System.out.println("not a car");
            }
        }
        if(fixtureB.getBody().getType() == BodyDef.BodyType.DynamicBody && fixtureA.getBody().getType() == BodyDef.BodyType.KinematicBody) {
            try {
                ((CarPlatform) fixtureA.getBody().getUserData()).reaction(); // try cast a -> mob car
            } catch (Exception e) {
                System.out.println("not a car");
            }
            try {
                ((PlayerCharacter) fixtureB.getBody().getUserData()).reaction(); // try cast b -> player character
            } catch (Exception e) {
                System.out.println("not a car");
            }
        }
    }
}
