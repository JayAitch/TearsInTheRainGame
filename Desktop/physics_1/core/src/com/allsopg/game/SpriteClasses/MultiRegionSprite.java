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

    public MultiRegionSprite(String atlas, Texture t, Vector2 pos, int[] regionLengths, int width, int height){
        super(t, pos, width, height);
        createAnimArrays(atlas, regionLengths);
    }
    @Override
    public void update(float stateTime) {
        super.update(stateTime);
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
