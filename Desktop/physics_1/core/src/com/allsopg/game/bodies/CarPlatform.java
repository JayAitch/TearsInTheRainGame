package com.allsopg.game.bodies;

import com.allsopg.game.TBWGame;
import com.allsopg.game.physics.WorldManager;
import com.allsopg.game.spawners.IMovingSpawnable;
import com.allsopg.game.utility.IWorldObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Timer;

import static com.allsopg.game.utility.Constants.CAR_PLATFORM_HEIGHT;
import static com.allsopg.game.utility.Constants.CAR_PLATFORM_OFFSET_X;
import static com.allsopg.game.utility.Constants.CAR_PLATFORM_OFFSET_Y;
import static com.allsopg.game.utility.Constants.CAR_PLATFORM_WIDTH;
import static com.allsopg.game.utility.Constants.DENSITY;
import static com.allsopg.game.utility.Constants.FRICTION;
import static com.allsopg.game.utility.Constants.RESTITUTION;

/**
 * implementation of multiregionsprite abstract this contains physics fixtures and calls super for
 * updating and drawing
 * Created by Jordan Harrison on 09/04/2018.
 */

public class CarPlatform extends com.allsopg.game.SpriteClasses.MultiRegionSprite implements IWorldObject, IMovingSpawnable {

    private Body platformBody;
    public boolean isDead;
    /**
     *
     * @param atlas atlas location for the animaiton
     * @param t initial sizing texture
     * @param pos start position of object
     * @param regionLengths array to designate amount and size of regions
     */
    public CarPlatform(String atlas, Texture t, Vector2 pos, int[] regionLengths){
        super(atlas, t, pos, regionLengths, CAR_PLATFORM_WIDTH, CAR_PLATFORM_HEIGHT);
        isDead = false;
        buildBody();
    }
    // Create fixture def
    public FixtureDef getFixtureDef(float density, float friction, float restitution) {
        //prepare for Fixture definition
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth()/2.7f, getHeight()/3.8f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;
        return fixtureDef;
    }

    // build body and set userdata
    public void buildBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(getX()+ CAR_PLATFORM_OFFSET_X,getY()+ CAR_PLATFORM_OFFSET_Y);
        platformBody = WorldManager.getInstance().getWorld().createBody(bodyDef);
        platformBody.setUserData(this);
        platformBody.setFixedRotation(true);
        platformBody.createFixture(getFixtureDef(DENSITY,FRICTION,RESTITUTION));
    }
    // move Spawnable called by spawn manager
    public void moveSpawnable(float xVelocity){
        platformBody.setLinearVelocity(xVelocity,0);
    }
    // update needed here to adjust position, super is called for animation, called from mobspawner
    @Override
    public void update(float stateTime) {
        super.update(stateTime);
        this.setPosition(platformBody.getPosition().x-CAR_PLATFORM_OFFSET_X,platformBody.getPosition().y-CAR_PLATFORM_OFFSET_Y);
    }
    // crash resolved by contactlistenerclass passed to world manager
    @Override
    public void reaction() {
        frameTimer = 0;
        changeAnimation();
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                dispose();
            }
        }, 0.5f);
}
    //called from mob spawner
    @Override
    public void draw(SpriteBatch batch){
        super.draw(batch);
    }
    // return position for out of bounds checks
    public Vector2 getPosition(){
        return platformBody.getPosition();
    }
    // boolean added as adding to the destroybody list a body that doesn't exist causes crashing
    // this method flags this body for deletion out of physics step
    public void dispose(){
        if(!isDead) {
            WorldManager.getInstance().getWorld().destroyBody(platformBody);
            isDead = true;
        }
    }

}
