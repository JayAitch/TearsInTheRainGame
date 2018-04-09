package com.allsopg.game.spawners;

import com.allsopg.game.bodies.CarPlatform;
import com.allsopg.game.utility.Constants;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;

import java.sql.Time;
import java.util.Random;

import static com.allsopg.game.utility.Constants.CAR_SIZE;
import static com.allsopg.game.utility.Constants.MOB_CAR_ATLAS_PATH;
import static com.allsopg.game.utility.Constants.CAR_PLATFORM_REGION_LENGTHS;
import static com.allsopg.game.utility.Constants.VIRTUAL_WIDTH;


/**
 * Created by Jordan Harrison on 09/04/2018.
 */

public class MobCarSpawner {
    protected Array<CarPlatform> spawnedPlatforms;
    private SpriteBatch batch;
    private Vector2 position;
    private Random random;

    public MobCarSpawner(SpriteBatch spriteBatch) {
        batch = spriteBatch;
        spawnedPlatforms = new Array<CarPlatform>();
        random = new Random();
        tickMethod();
    }

    public void SpawnCars(int amount) {
        for(int i =0; i < amount; i++) {
            position = new Vector2(0,random.nextInt(10));
            CarPlatform tempCar = new CarPlatform(MOB_CAR_ATLAS_PATH, CAR_SIZE, position, CAR_PLATFORM_REGION_LENGTHS);
            tempCar.moveLeft();
            spawnedPlatforms.add(tempCar);


        }

    }
    public void tickMethod(){
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                checkPositions();
            }
        },5,5);
    }

    public void checkPositions(){
        for(int i = 0; i < spawnedPlatforms.size; i++){
            CarPlatform tempMob = spawnedPlatforms.get(i);
            if(tempMob.getPos().x > 2){
                tempMob.dispose();
                spawnedPlatforms.removeIndex(i);
            }
        }
    }

    public void update(float deltaTime) {
        if (spawnedPlatforms != null) {
            for(int i = 0; i < spawnedPlatforms.size; i++){
                spawnedPlatforms.get(i).update(deltaTime);
            }
        }
    }

    public void draw(){
        if (spawnedPlatforms != null) {
            for(int i = 0; i < spawnedPlatforms.size; i++){
                spawnedPlatforms.get(i).draw(batch);
            }
        }
    }
}