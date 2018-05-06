package com.allsopg.game.Sound;

import com.allsopg.game.utility.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.IntMap;

/**
 * Created by Jordan Harrison on 01/05/2018.
 */

public class SoundPlayer {
    private static Music backgroundMusic;
    private static IntMap<Sound> soundKeys;

    public enum SoundEnum{
        EXPLODESND, CRASHSND
    }
    //generate intmap and sounds from internal files
    public SoundPlayer(){
        soundKeys = new IntMap<Sound>();
        Sound explodeSnd = Gdx.audio.newSound(Gdx.files.internal(Constants.EXPLOSION_SOUND_PATH));
        Sound crashSnd = Gdx.audio.newSound(Gdx.files.internal(Constants.CRASH_SOUND_PATH));
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(Constants.BACKGROUND_TRACK_PATH));
        soundKeys.put(1,crashSnd);
        soundKeys.put(2,explodeSnd);
    }

    public static void playBackgroundTrack(){
        backgroundMusic.play();
    }

    public void waitForBGLoad(){
    }
    public static void stopBackgroundTrack(){
        backgroundMusic.stop();
    }


    // using statics to aid calling from other objects don't do this irl
    // populate and play sound based on passed enum
    public static boolean playSound(SoundEnum soundEnum){
        //state machine to chose sound to play
        //enum driven for clarity when calling
        Sound sound;
        switch (soundEnum){
            case EXPLODESND:
                sound = soundKeys.get(2);
                break;
            case CRASHSND:
                sound = soundKeys.get(1);
                break;
            default:
                System.out.println("Sound enum not recognised");
                sound = null;
                break;
        }
        // pre enum method was driven using index param passed in Sound sound = soundKeys.get(keyCode);
        //check to see if sound has been stored
        if(sound != null){
            sound.play();
            return true;
        }
        return false;
    }
}
