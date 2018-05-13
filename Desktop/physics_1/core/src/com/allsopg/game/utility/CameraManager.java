package com.allsopg.game.utility;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import static com.allsopg.game.utility.Constants.UNITSCALE;
import static com.allsopg.game.utility.Constants.VIRTUAL_HEIGHT;
import static com.allsopg.game.utility.Constants.VIRTUAL_WIDTH;

/**
 * Created by gerard on 14/03/2017.
 * updated 12/03/18
 */

public class CameraManager {
    private Vector2 position;
    private Sprite target;
    private OrthographicCamera camera;
    private TiledMap tiledMap;
    float levelWidth;
    float levelHeight;

    public CameraManager(OrthographicCamera camera, TiledMap tiledMap) {
        this.camera = camera;
        this.tiledMap = tiledMap;
        TiledMapTileLayer tiledMapTileLayer = (TiledMapTileLayer)
                this.tiledMap.getLayers().get(0); // populate tile map variable with bottom layer
        levelWidth = tiledMapTileLayer.getWidth(); // get level size variables
        levelHeight = tiledMapTileLayer.getHeight();
        position = new Vector2();// camera positionn

    }
    //update camera based on player position
    public void update () {
        if (!hasTarget()) return;
        if (cameraTrackX()||cameraTrackY()) { // if cameratracks return false
            position.x = target.getX() + target.getOriginX(); // adjust potsitions
            position.y = target.getY() + target.getOriginY();
            camera.position.set(position.x, position.y, 0);
            camera.update();
        }
}

    public void setTarget (Sprite target) { this.target = target; }
    public boolean hasTarget () { return target != null; }

    private boolean cameraTrackX() { // if camera X position is sufficiently different from player X
        if ((target.getX() > (VIRTUAL_WIDTH * UNITSCALE) / 2f) &&
                (target.getX() < (levelWidth - (VIRTUAL_WIDTH * UNITSCALE )/2))) {
            return true;
        }
        return false;
    }

    private boolean cameraTrackY() { // if camera Y position is sufficiently different from player Y
        if ((target.getY() > (VIRTUAL_HEIGHT * UNITSCALE) / 2f) &&
                (target.getY() < (levelHeight - (VIRTUAL_HEIGHT * UNITSCALE )/2))) {
            return true;
        }
        return false;
    }
}
