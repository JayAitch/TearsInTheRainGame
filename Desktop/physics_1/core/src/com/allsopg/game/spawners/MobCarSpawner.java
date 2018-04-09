package com.allsopg.game.spawners;

import com.allsopg.game.bodies.CarPlatform;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.allsopg.game.utility.Constants.CAR_SIZE;
import static com.allsopg.game.utility.Constants.MOB_CAR_ATLAS_PATH;
import static com.allsopg.game.utility.Constants.PLATFORM_POSITION;
import static com.allsopg.game.utility.Constants.CAR_PLATFORM_REGION_LENGTHS;

import static com.allsopg.game.utility.Constants.START_POSITION;

/**
 * Created by Jordan Harrison on 09/04/2018.
 */

public class MobCarSpawner {
    private CarPlatform[] spawnedPlatforms;
    private CarPlatform carPlat;
    private CarPlatform carPlat2;
    private SpriteBatch batch;

    public MobCarSpawner(SpriteBatch spriteBatch) {
        batch = spriteBatch;
        carPlat = new CarPlatform(MOB_CAR_ATLAS_PATH, CAR_SIZE, START_POSITION, CAR_PLATFORM_REGION_LENGTHS);
        carPlat2 = new CarPlatform(MOB_CAR_ATLAS_PATH, CAR_SIZE, PLATFORM_POSITION, CAR_PLATFORM_REGION_LENGTHS);
        TestArray();
    }

    public void TestArray() {
        spawnedPlatforms = new CarPlatform[2];
        spawnedPlatforms[0] = carPlat;
        spawnedPlatforms[1] = carPlat2;
        carPlat.moveLeft();
        carPlat2.moveLeft();
    }

    public void update(float deltaTime) {
        for(CarPlatform carP : spawnedPlatforms){
            carP.update(deltaTime);
        }

    }

    public void draw(){
        for(CarPlatform carP : spawnedPlatforms){
            carP.draw(batch);
        }
    }
}