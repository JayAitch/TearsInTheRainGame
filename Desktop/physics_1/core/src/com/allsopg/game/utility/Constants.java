package com.allsopg.game.utility;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
    public static final int LEVEL_TIME = 60;
    //Physics manager iterations
    public static final int VELOCITY_ITERATIONS = 6;
    public static final int POSITION_ITERATIONS = 2;
    // static textures
    public static final String INTRO_SCREEN_PATH = "gfx/TITRIntro.png";

    public static final String BACKGROUND = "tileData/assignment_two.tmx";
    public static final String PHYSICS_MATERIALS_PATH = "tileData/physicsData.json"; // physics tile data

    //Constants for bodies
    public static final float DENSITY=.5f;
    public static final float FRICTION=.5f;
    public static final float RESTITUTION=.5f;

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
    //player Stats
    //impulse strength
    public static final float BASE_X_FORCE =30f;
    public static final float BASE_FORCE_Y =30f;
    public static final int PLAYER_MAX_LIFE = 5;
    public static final float INVULNERABILITY_TIME = 0.5f;

    //car platform graphics
    public static final int[] CAR_PLATFORM_REGION_LENGTHS = {4,13};
    public static final String MOB_CAR_ATLAS_PATH = "gfx/MobCar/mob_car.atlas";
    public static int CAR_PLATFORM_WIDTH = 5;
    public static int CAR_PLATFORM_HEIGHT = 2;
    public static float CAR_PLATFORM_OFFSET_Y = 1f;
    public static float CAR_PLATFORM_OFFSET_X = 3f;
    public static final Vector2 PLATFORM_POSITION = new Vector2(0,8);

    //miscilanious sprites
    public static final String FIRE_CHIMNEY_PATH =  "gfx/Flame/FChimney.atlas";
    public static final Vector2 FIRE_CHIMNEY_ONE_POS = new Vector2(2,3);
    public static final Vector2 FIRE_CHIMNEY_TWO_POS = new Vector2(50,3);

    //spawn constants
    //randoms for speed Random: speed = random(range)+ mod
    public static final int CAR_SPEED_RANGE = 6;
    public static final int CAR_SPEED_MOD = 3;
    // spawn tick interval
    public static final float CAR_SPAWN_RATE = 2f;
    // tick used to scan for out of bound objects
    public static final float SPAWNER_SCAN_TICK = 0.25f;


    // sound and music
    public static final String BACKGROUND_TRACK_PATH = "sfx/TearsTheme.ogg";
    public static final String EXPLOSION_SOUND_PATH = "sfx/ExplosionSound.ogg";
    public static final String CRASH_SOUND_PATH = "sfx/CrashSound.ogg";
}
