package com.allsopg.game.utility;

import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 * Created by gerard on 24/02/2018.
 */

public interface IWorldObject {
     void buildBody();
     FixtureDef getFixtureDef(float density, float friction, float restitution);
     void reaction();
}
