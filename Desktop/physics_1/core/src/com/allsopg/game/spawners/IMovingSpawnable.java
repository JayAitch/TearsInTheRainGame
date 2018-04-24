package com.allsopg.game.spawners;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * This interface has been made to simplifying use of classes with mobspawner the methods specfied here are needed for the MobSpawner to function
 * Created by Jordan Harrison on 23/04/2018.
 */

public interface IMovingSpawnable {
     void update(float stateTime);
     void dispose();
     void moveSpawnable(float xVelocity);
     void draw(SpriteBatch batch);
     Vector2 getPosition();

}
