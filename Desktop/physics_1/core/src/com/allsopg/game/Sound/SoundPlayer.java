package com.allsopg.game.Sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Jordan Harrison on 01/05/2018.
 */

public class SoundPlayer {
    Sound backgroundMusic;
    public SoundPlayer(){
        backgroundMusic = Gdx.audio.newSound(Gdx.files.internal("sfx/TearsTheme.ogg"));
        backgroundMusic.play();
    }
}
