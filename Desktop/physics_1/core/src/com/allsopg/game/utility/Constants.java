package com.allsopg.game.utility;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by gerard on 09/11/2016.
 * Updated 17/02/18
 */

public class Constants {
    //Screen Size
    public static final float VIRTUAL_WIDTH = Gdx.graphics.getWidth();
    public static final float VIRTUAL_HEIGHT = Gdx.graphics.getHeight();
    //World to screen scale
    public static final float TILE_SIZE   = 32;
    public static final float UNITSCALE = 1.0f / TILE_SIZE;
    //Animation Speed
    public static final float FRAME_DURATION = 1.0f / 10.0f;
    public static final float TIME_STEP=1/60f;
    public static final int LEVEL_TIME = 30;

    public static final int VELOCITY_ITERATIONS = 6;
    public static final int POSITION_ITERATIONS = 2;

    public static final String BACKGROUND = "tileData/assignment_two.tmx";
    public static final String PHYSICS_MATERIALS_PATH = "tileData/physicsData.json";

    public static final float DENSITY=.5f;
    public static final float FRICTION=.5f;
    public static final float RESTITUTION=.5f;
    //impulse strength
    public static final float FORCE_X=30f;
    public static final float FORCE_Y=30f;
    //Speed
    public static final float MAX_VELOCITY = 1f;
    public static final float MAX_HEIGHT = 18;
    //player body
    public static int PLAYER_WIDTH= 5;
    public static int PLAYER_HEIGHT=  2;
    public static float PLAYER_OFFSET_Y=0.8f;
    public static float PLAYER_OFFSET_X= 2.5f;
    //player graphics
    public static final String PLAYER_ATLAS_PATH = "gfx/Playercar/playerCar.atlas";
    public static final int[] PLAYER_CAR_REGION_LENGTHS = {4,9};
    public static final Texture MEDIUM = new Texture(Gdx.files.internal("gfx/mediumSize.png"));
    public static final Texture SMALL = new Texture(Gdx.files.internal("gfx/smallSize.png"));
    public static final Texture TINY = new Texture(Gdx.files.internal("gfx/tinySize.png"));
    public static final Texture CAR_SIZE = new Texture(Gdx.files.internal("gfx/carSize.png"));
    //player start position
    public static final Vector2 START_POSITION = new Vector2(10,10);


    //car platform
    public static final int[] CAR_PLATFORM_REGION_LENGTHS = {4,13};
    public static final String MOB_CAR_ATLAS_PATH = "gfx/MobCar/mob_car.atlas";
    public static int CAR_PLATFORM_WIDTH = 5;
    public static int CAR_PLATFORM_HEIGHT = 2;
    public static float CAR_PLATFORM_OFFSET_Y = 1f;
    public static float CAR_PLATFORM_OFFSET_X = 3f;
    public static final Vector2 PLATFORM_POSITION = new Vector2(0,8);
}
