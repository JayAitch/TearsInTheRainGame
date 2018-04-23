package com.allsopg.game.spawners;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Jordan Harrison on 23/04/2018.
 */

public interface IMovingSpawnable {
     void update(float stateTime);
     void dispose();
     void moveSpawnable(float xVelocity);
     void draw(SpriteBatch batch);
     Vector2 getPosition();

}
