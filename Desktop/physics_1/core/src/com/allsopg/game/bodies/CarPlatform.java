package com.allsopg.game.bodies;

import com.allsopg.game.physics.WorldManager;
import com.allsopg.game.utility.IWorldObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

import static com.allsopg.game.utility.Constants.CAR_PLATFORM_OFFSET_X;
import static com.allsopg.game.utility.Constants.CAR_PLATFORM_OFFSET_Y;
import static com.allsopg.game.utility.Constants.DENSITY;
import static com.allsopg.game.utility.Constants.FRICTION;
import static com.allsopg.game.utility.Constants.RESTITUTION;

/**
 * Created by Jordan Harrison on 31/03/2018.
 */

public class CarPlatform extends AnimatedSprite implements IWorldObject{
    private Body platformBody;
    private Array<TextureAtlas.AtlasRegion> regions;
    private Map<Integer,Array<TextureAtlas.AtlasRegion>> animationRegions;

    public CarPlatform(String atlas, Texture t, Vector2 pos, int[] regionLengths){
        super(t, pos);
        createAnimArrays(atlas, regionLengths);
        buildBody();
    }


    public void buildBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(getX()+ CAR_PLATFORM_OFFSET_X,getY()+ CAR_PLATFORM_OFFSET_Y);
        platformBody = WorldManager.getInstance().getWorld().createBody(bodyDef);
        platformBody.setUserData(this);
        platformBody.setFixedRotation(true);
        platformBody.createFixture(getFixtureDef(DENSITY,FRICTION,RESTITUTION));
    }

    @Override
    public void update(float stateTime) {
        super.update(stateTime);
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

    @Override
    public void reaction() {

    }

    private void createAnimArrays(String atlasString, int[]regionLengths){
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(atlasString));
        regions = new Array<TextureAtlas.AtlasRegion>(atlas.getRegions());
        regions.sort(new RegionComparator());
        animationRegions = new HashMap<Integer, Array<TextureAtlas.AtlasRegion>>();

        for(int i = 0; i < regionLengths.length; i++) {
            Array<TextureAtlas.AtlasRegion> tempRegion = new Array<TextureAtlas.AtlasRegion>();
            for(int i2 = 0; i2 < regionLengths[i]; i2++) {
                tempRegion.add(regions.pop());

            }
            animationRegions.put(i,tempRegion);
        }
        animationInit(animationRegions.get(0), Animation.PlayMode.LOOP);
    }
    //stub for changing animation
    private void changeAnimation(){
        animationInit(animationRegions.get(1), Animation.PlayMode.NORMAL);
    }
}
