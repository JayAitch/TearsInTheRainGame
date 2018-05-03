package com.allsopg.game.SpriteClasses;

import com.allsopg.game.spawners.IMovingSpawnable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Jordan Harrison on 03/05/2018.
 */

public class FlameChimney extends AnimatedSprite{
    public FlameChimney(String atlasString, Texture t, Vector2 pos, int width, int height){
        super(atlasString, t, pos, width, height);
    }
    public void draw(SpriteBatch batch){
        super.draw(batch);
    }

    @Override
    public void update(float stateTime) {
        super.update(stateTime);
    }

}
