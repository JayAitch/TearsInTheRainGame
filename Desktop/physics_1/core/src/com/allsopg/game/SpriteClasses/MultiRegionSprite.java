package com.allsopg.game.SpriteClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jordan Harrison on 31/03/2018.
 */

public abstract class MultiRegionSprite extends AnimatedSprite{
    private Array<TextureAtlas.AtlasRegion> regions;
    private Map<Integer,Array<TextureAtlas.AtlasRegion>> animationRegions;
    public  float frameTimer;

    public MultiRegionSprite(String atlas, Texture t, Vector2 pos, int[] regionLengths, int width, int height){
        super(t, pos, width, height);
        createAnimArrays(atlas, regionLengths);
    }
    @Override
    public void update(float stateTime) {
        frameTimer += Gdx.graphics.getDeltaTime(); // useing frame timer to allow this value to be reset
        super.update(frameTimer);
    }

// generate multiple animation regions based on the array passed into the constructor
    private void createAnimArrays(String atlasString, int[]regionLengths){
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(atlasString));
        regions = new Array<TextureAtlas.AtlasRegion>(atlas.getRegions());
        regions.sort(new RegionComparator());
        animationRegions = new HashMap<Integer, Array<TextureAtlas.AtlasRegion>>();

        for(int i = 0; i < regionLengths.length; i++) { // for the lenth of array passede in
            Array<TextureAtlas.AtlasRegion> tempRegion = new Array<TextureAtlas.AtlasRegion>();
            for(int i2 = 0; i2 < regionLengths[i]; i2++) { // for value in each array element
                tempRegion.add(regions.pop()); // pop frames off and add to temperary region array

            }
            animationRegions.put(i,tempRegion); // add contents of temp array to hashmap
        }
        animationInit(animationRegions.get(0), Animation.PlayMode.LOOP); // assign animation in the first element
    }


    //stub for changing animation
    protected void changeAnimation(){
        animationInit(animationRegions.get(1), Animation.PlayMode.NORMAL);
    }
}
