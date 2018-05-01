package com.allsopg.game;

import com.allsopg.game.Sound.SoundPlayer;
import com.allsopg.game.screens.EndScreen;
import com.allsopg.game.screens.MainMenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.allsopg.game.utility.Constants.VIRTUAL_HEIGHT;
import static com.allsopg.game.utility.Constants.VIRTUAL_WIDTH;

/**
 * Created by gerard on 12/02/2017.
 */

public class TBWGame extends Game {
    private AssetManager assetManager;
    public OrthographicCamera camera;
    public SoundPlayer sPlayer;
    public Viewport viewport;
    public SpriteBatch batch;
    public BitmapFont font;

    @Override
    public void create() {
        sPlayer = new SoundPlayer();
        assetManager = new AssetManager();
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new
                InternalFileHandleResolver()));
        camera = new OrthographicCamera();
        viewport = new FitViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);
        batch = new SpriteBatch();
        //Using LibGDX's default Arial font.
        font = new BitmapFont();
        setScreen(new MainMenuScreen(this));
    }
    public void endGame(){
        setScreen(new EndScreen());
    }
    public AssetManager getAssetManager() {
        return assetManager;
    }
}
