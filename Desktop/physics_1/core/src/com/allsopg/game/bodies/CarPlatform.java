package com.allsopg.game.bodies;

import com.allsopg.game.physics.WorldManager;
import com.allsopg.game.utility.IWorldObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import static com.allsopg.game.utility.Constants.CAR_PLATFORM_HEIGHT;
import static com.allsopg.game.utility.Constants.CAR_PLATFORM_OFFSET_X;
import static com.allsopg.game.utility.Constants.CAR_PLATFORM_OFFSET_Y;
import static com.allsopg.game.utility.Constants.CAR_PLATFORM_WIDTH;
import static com.allsopg.game.utility.Constants.DENSITY;
import static com.allsopg.game.utility.Constants.FRICTION;
import static com.allsopg.game.utility.Constants.RESTITUTION;

/**
 * Created by Jordan Harrison on 09/04/2018.
 */

public class CarPlatform extends com.allsopg.game.SpriteClasses.MultiRegionSprite implements IWorldObject {

    private Body platformBody;

    public CarPlatform(String atlas, Texture t, Vector2 pos, int[] regionLengths){
        super(atlas, t, pos, regionLengths, CAR_PLATFORM_WIDTH, CAR_PLATFORM_HEIGHT);
        buildBody();
    }

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


    public void buildBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(getX()+ CAR_PLATFORM_OFFSET_X,getY()+ CAR_PLATFORM_OFFSET_Y);
        platformBody = WorldManager.getInstance().getWorld().createBody(bodyDef);
        platformBody.setUserData(this);
        platformBody.setFixedRotation(true);
        platformBody.createFixture(getFixtureDef(DENSITY,FRICTION,RESTITUTION));
    }

    public void moveLeft(){
        Vector2 vel = platformBody.getLinearVelocity();
        Vector2 pos = platformBody.getPosition();
        platformBody.setLinearVelocity(3,0);

    }
    @Override
    public void update(float stateTime) {
        super.update(stateTime);
        this.setPosition(platformBody.getPosition().x-CAR_PLATFORM_OFFSET_X,platformBody.getPosition().y-CAR_PLATFORM_OFFSET_Y);
    }
    @Override
    public void reaction() {

    }

    public Vector2 getPos(){
        return platformBody.getPosition();
    }

    public void dispose(){
        WorldManager.getInstance().getWorld().destroyBody(platformBody);
        platformBody.setUserData(null);
        platformBody = null;

    }
}
