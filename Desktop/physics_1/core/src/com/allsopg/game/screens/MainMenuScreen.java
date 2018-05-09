package com.allsopg.game.screens;

import com.allsopg.game.Sound.SoundPlayer;
import com.allsopg.game.TBWGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;

import static com.allsopg.game.utility.Constants.BACKGROUND;
import static com.allsopg.game.utility.Constants.BACKGROUND_TRACK_PATH;
import static com.allsopg.game.utility.Constants.INTRO_SCREEN_PATH;
import static com.allsopg.game.utility.Constants.VIRTUAL_HEIGHT;
import static com.allsopg.game.utility.Constants.VIRTUAL_WIDTH;


/**
 * Created by gerard on 16/02/2018.
 */

public class MainMenuScreen extends ScreenAdapter {
    private TBWGame game;
    private Texture introScreenT;

    public MainMenuScreen(TBWGame aGame) {
            this.game = aGame;
        }

    @Override
    public void show() {
        introScreenT = new Texture(Gdx.files.internal(INTRO_SCREEN_PATH));
        game.getAssetManager().load(BACKGROUND, TiledMap.class);
        game.getAssetManager().finishLoading();
     //   game.getAssetManager().isLoaded(BACKGROUND_TRACK_PATH);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.camera.update();
        game.batch.begin();
        game.batch.draw(introScreenT, 0, 0, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            SoundPlayer.playBackgroundTrack();
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
