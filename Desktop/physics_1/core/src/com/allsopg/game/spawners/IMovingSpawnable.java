package com.allsopg.game.spawners;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Jordan Harrison on 23/04/2018.
 */

public interface IMovingSpawnable {
    public void update(float stateTime);
    public void dispose();
    public void moveSpawnable(float xVelocity);
    public void draw(SpriteBatch batch);
    public Vector2 getPosition();

}
