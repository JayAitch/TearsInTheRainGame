package com.allsopg.game.SpriteClasses;

import com.allsopg.game.utility.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Comparator;

import static com.allsopg.game.utility.Constants.CAR_PLATFORM_HEIGHT;
import static com.allsopg.game.utility.Constants.CAR_PLATFORM_WIDTH;
import static com.allsopg.game.utility.Constants.FRAME_DURATION;
import static com.allsopg.game.utility.Constants.PLAYER_HEIGHT;
import static com.allsopg.game.utility.Constants.PLAYER_WIDTH;

/**
 * Created by gerard on 09/11/2016.
 * updated 02/03/18
 */

public abstract class AnimatedSprite extends Sprite {
    protected Animation animation;
    protected Animation.PlayMode playmode;
    private TextureAtlas atlas;

    /**
     * Generates an animation in Normal playmode with sizing done through, t, height and width
     * Animation generated and initiated, consististing of every frame in atlas
     * @param t initial sizing texture
     * @param pos Start Postion of object
     * @param width Width modifier
     * @param height Hieght modifier
     */
    public AnimatedSprite(String atlasString, Texture t, Vector2 pos, int width, int height){
        super(t,width, height);
        this.setX(pos.x);
        this.setY(pos.y);
        playmode = Animation.PlayMode.LOOP;
        initAtlas(atlasString);
    }

    /**
     * Generates an animation in with sizing done through, t, height and width
     * does not generate an animation by default this needs to be done in extended class
     * @param t initial sizing texture
     * @param pos Start Postion of object
     * @param width Width modifier
     * @param height Hieght modifier
     */
    public AnimatedSprite(Texture t, Vector2 pos, int width, int height){
        super(t, width, height);
        this.setX(pos.x);
        this.setY(pos.y);
        playmode = Animation.PlayMode.LOOP;
    }

    public void update(float animationTime){
        this.setRegion((TextureRegion) animation.getKeyFrame(animationTime));
    }


    /**
     * Generates an animation that consist of the entire atlas
     * @param atlasString
     */
    private void initAtlas(String atlasString){
        atlas = new TextureAtlas(Gdx.files.internal(atlasString));
        //load animations
        Array<TextureAtlas.AtlasRegion> regions = new
                Array<TextureAtlas.AtlasRegion>(atlas.getRegions());
        regions.sort(new RegionComparator());
        animation = new Animation(FRAME_DURATION,regions, Animation.PlayMode.LOOP);
    }

    /**
     * animation init for use with second constructor
     * Should be called via the extended class after generating animation regions
     * @param regions
     * @param loopType
     */
    protected void animationInit(Array<TextureAtlas.AtlasRegion> regions, Animation.PlayMode loopType) {
        regions.sort(new RegionComparator());
        animation = new Animation(Constants.FRAME_DURATION, regions, loopType);
    }


    protected static class RegionComparator implements Comparator<TextureAtlas.AtlasRegion> {
        @Override
        public int compare(TextureAtlas.AtlasRegion region1, TextureAtlas.AtlasRegion region2) {
            return region1.name.compareTo(region2.name);
        }
    }
    public void dispose(){}

}