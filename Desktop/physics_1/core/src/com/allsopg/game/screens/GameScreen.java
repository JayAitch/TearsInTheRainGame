package com.allsopg.game.screens;

import com.allsopg.game.SpriteClasses.FlameChimney;
import com.allsopg.game.TBWGame;
import com.allsopg.game.bodies.PlayerCharacter;
import com.allsopg.game.physics.WorldManager;
import com.allsopg.game.spawners.MobSpawner;
import com.allsopg.game.utility.CameraManager;
import com.allsopg.game.utility.Constants;
import com.allsopg.game.utility.GameData;
import com.allsopg.game.utility.HUD;
import com.allsopg.game.utility.UniversalResource;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;

import static com.allsopg.game.utility.Constants.CAR_SIZE;
import static com.allsopg.game.utility.Constants.CAR_SPAWN_RATE;
import static com.allsopg.game.utility.Constants.FIRE_CHIMNEY_ONE_POS;
import static com.allsopg.game.utility.Constants.FIRE_CHIMNEY_PATH;
import static com.allsopg.game.utility.Constants.FIRE_CHIMNEY_TWO_POS;
import static com.allsopg.game.utility.Constants.PLAYER_ATLAS_PATH;
import static com.allsopg.game.utility.Constants.PLAYER_CAR_REGION_LENGTHS;
import static com.allsopg.game.utility.Constants.SMALL;
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
    private FlameChimney flameChimney;
    private FlameChimney flameChimney2;

    // populate reference to self in constructor
    public GameScreen(TBWGame tbwGame){this.game = tbwGame;}

    // size game based on virtual sizes
    @Override
    public void resize(int width, int height) {
        game.camera.setToOrtho(false, VIRTUAL_WIDTH * UNITSCALE, VIRTUAL_HEIGHT * UNITSCALE);
        game.batch.setProjectionMatrix(game.camera.combined);
    }

    // show method is called when screen is shown
    @Override
    public void show() {
        super.show();
        // create flame chimneys
        flameChimney = new FlameChimney(FIRE_CHIMNEY_PATH,SMALL,FIRE_CHIMNEY_ONE_POS,2,4);
        flameChimney2 = new FlameChimney(FIRE_CHIMNEY_PATH,SMALL,FIRE_CHIMNEY_TWO_POS,2,4);
        tiledMap = game.getAssetManager().get(Constants.BACKGROUND);
        orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(this.tiledMap,UNITSCALE);
        orthogonalTiledMapRenderer.setView(game.camera);
        if(!WorldManager.isInitialised()){WorldManager.initialise(game,tiledMap);}
        //player
        playerCar = new PlayerCharacter(PLAYER_ATLAS_PATH,CAR_SIZE,START_POSITION,PLAYER_CAR_REGION_LENGTHS, game);
        // create mob spawner and initiate spawning method
        mobSpawner = new MobSpawner(game.batch);
        spawnMobs();
        // camera and hud settup
        cameraManager = new CameraManager(game.camera,tiledMap);
        cameraManager.setTarget(playerCar);
        gameHUD = new HUD(game.batch, playerCar, game);

    }

    // Spawns cars and adds to score every wave of cars
    public void spawnMobs(){
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                mobSpawner.SpawnCars(1);
                HUD.addScore(10);
            }
        },1,CAR_SPAWN_RATE);

          }
    // render all game objects
    // rendering of mob cars is passed to mob spawner
    @Override
    public void render(float delta) {
        frameDelta += delta;
        flameChimney.update(frameDelta);
        flameChimney2.update(frameDelta);
        playerCar.update(frameDelta);
        mobSpawner.update(frameDelta);
        gameHUD.update(delta);
        game.batch.setProjectionMatrix(game.camera.combined);
        UniversalResource.getInstance().tweenManager.update(delta);
        clearScreen();
        draw();
        WorldManager.getInstance().doPhysicsStep(delta);
    }

    // draw all game objects, called post render, drawing cars passed to mob spawner
    private void draw() {
       orthogonalTiledMapRenderer.setView(game.camera);
       orthogonalTiledMapRenderer.render();
        cameraManager.update();
        game.batch.begin();
        flameChimney2.draw(game.batch);
        flameChimney.draw(game.batch);
        playerCar.draw(game.batch);
        mobSpawner.draw();
        game.batch.end();
        gameHUD.stage.draw();
        WorldManager.getInstance().debugRender();
    }
    // clear screen to black
    private void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g,
                Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

}
