package com.allsopg.game.spawners;

import com.allsopg.game.bodies.CarPlatform;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;

import java.util.Random;

import static com.allsopg.game.utility.Constants.CAR_SIZE;
import static com.allsopg.game.utility.Constants.CAR_SPEED_MOD;
import static com.allsopg.game.utility.Constants.CAR_SPEED_RANGE;
import static com.allsopg.game.utility.Constants.MOB_CAR_ATLAS_PATH;
import static com.allsopg.game.utility.Constants.CAR_PLATFORM_REGION_LENGTHS;
import static com.allsopg.game.utility.Constants.SPAWNER_SCAN_TICK;


/**
 *
 * Created by Jordan Harrison on 09/04/2018.
 */
public class MobCarSpawner {
    protected Array<IMovingSpawnable> spawnedPlatforms;
    private SpriteBatch batch;
    private Vector2 position;
    private Random random;

    // generates an array for sprites and initiates tick method
    public MobCarSpawner(SpriteBatch spriteBatch) {
        batch = spriteBatch;
        spawnedPlatforms = new Array<IMovingSpawnable>();
        random = new Random();
        tickMethod();
    }

    //spawn cars based on the int passed in position is currently stubbed
    public void SpawnCars(int amount) {
        for(int i =0; i < amount; i++) {
            position = new Vector2(0,(random.nextInt(10)+5));
            CarPlatform tempCar = new CarPlatform(MOB_CAR_ATLAS_PATH, CAR_SIZE, position, CAR_PLATFORM_REGION_LENGTHS);
            tempCar.moveSpawnable(random.nextInt(CAR_SPEED_RANGE)+CAR_SPEED_MOD);
            spawnedPlatforms.add(tempCar);
        }
    }

    // event currently used to scan out of bounds enemy sprites
    public void tickMethod(){
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                checkPositions();
            }
        },5,SPAWNER_SCAN_TICK);
    }

    public void checkPositions(){
        for(int i = 0; i < spawnedPlatforms.size; i++){
            IMovingSpawnable tempMob = spawnedPlatforms.get(i);
            if(tempMob.getPosition().x > 100){   //if out of bounds
                System.out.println(tempMob.getPosition().x);
                // calls dispose on object
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