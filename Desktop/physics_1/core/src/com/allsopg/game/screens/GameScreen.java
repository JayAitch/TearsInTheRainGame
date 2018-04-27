package com.allsopg.game.screens;

import com.allsopg.game.TBWGame;
import com.allsopg.game.bodies.PlayerCharacter;
import com.allsopg.game.physics.WorldManager;
import com.allsopg.game.spawners.MobSpawner;
import com.allsopg.game.utility.CameraManager;
import com.allsopg.game.utility.Constants;
import com.allsopg.game.utility.HUD;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Timer;

import static com.allsopg.game.utility.Constants.CAR_SIZE;
import static com.allsopg.game.utility.Constants.PLAYER_ATLAS_PATH;
import static com.allsopg.game.utility.Constants.PLAYER_CAR_REGION_LENGTHS;
import static com.allsopg.game.utility.Constants.START_POSITION;
import static com.allsopg.game.utility.Constants.UNITSCALE;
import static com.allsopg.game.utility.Constants.VIRTUAL_HEIGHT;
import static com.allsopg.game.utility.Constants.VIRTUAL_WIDTH;

/**
 * Created by gerard on 12/02/2017.
 */

public class GameScreen extends ScreenAdapter {
    private TBWGame game;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private PlayerCharacter playerCar;
    private MobSpawner mobSpawner;
    private HUD gameHUD;
    private CameraManager cameraManager;
    private float frameDelta = 0;

    public GameScreen(TBWGame tbwGame){this.game = tbwGame;}

    @Override
    public void resize(int width, int height) {
        game.camera.setToOrtho(false, VIRTUAL_WIDTH * UNITSCALE, VIRTUAL_HEIGHT * UNITSCALE);
        game.batch.setProjectionMatrix(game.camera.combined);
    }

    @Override
    public void show() {
        super.show();
        tiledMap = game.getAssetManager().get(Constants.BACKGROUND);
        orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(this.tiledMap,UNITSCALE);
        orthogonalTiledMapRenderer.setView(game.camera);
        if(!WorldManager.isInitialised()){WorldManager.initialise(game,tiledMap);}
        //player
        playerCar = new PlayerCharacter(PLAYER_ATLAS_PATH,CAR_SIZE,START_POSITION,PLAYER_CAR_REGION_LENGTHS, game);
        mobSpawner = new MobSpawner(game.batch);
        spawnMobs();
        cameraManager = new CameraManager(game.camera,tiledMap);
        cameraManager.setTarget(playerCar);
        gameHUD = new HUD(game.batch, playerCar,game);
    }

    // stubbed call to spawn cars
    public void spawnMobs(){
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                mobSpawner.SpawnCars(1);
            }
        },1,5);
          }

    @Override
    public void render(float delta) {
        frameDelta += delta;
        playerCar.update(frameDelta);
        mobSpawner.update(frameDelta);
        gameHUD.update(delta);
        game.batch.setProjectionMatrix(game.camera.combined);
        clearScreen();
        draw();
        WorldManager.getInstance().doPhysicsStep(delta);
    }

    private void draw() {
       orthogonalTiledMapRenderer.setView(game.camera);
       orthogonalTiledMapRenderer.render();
        cameraManager.update();
        game.batch.begin();
        playerCar.draw(game.batch);
        mobSpawner.draw();
        game.batch.end();
        gameHUD.stage.draw();
        WorldManager.getInstance().debugRender();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g,
                Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}