package com.allsopg.game.bodies;

import com.allsopg.game.TBWGame;
import com.allsopg.game.physics.WorldManager;
import com.allsopg.game.utility.CurrentDirection;
import com.allsopg.game.utility.HUD;
import com.allsopg.game.utility.IWorldObject;
import com.allsopg.game.utility.TweenData;
import com.allsopg.game.utility.TweenDataAccessor;
import com.allsopg.game.utility.UniversalResource;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Timer;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import static com.allsopg.game.utility.Constants.DENSITY;
import static com.allsopg.game.utility.Constants.BASE_X_FORCE;
import static com.allsopg.game.utility.Constants.BASE_FORCE_Y;
import static com.allsopg.game.utility.Constants.FRICTION;
import static com.allsopg.game.utility.Constants.INVULNERABILITY_TIME;
import static com.allsopg.game.utility.Constants.MAX_HEIGHT;
import static com.allsopg.game.utility.Constants.MAX_VELOCITY;
import static com.allsopg.game.utility.Constants.PLAYER_HEIGHT;
import static com.allsopg.game.utility.Constants.PLAYER_MAX_LIFE;
import static com.allsopg.game.utility.Constants.PLAYER_OFFSET_X;
import static com.allsopg.game.utility.Constants.PLAYER_OFFSET_Y;
import static com.allsopg.game.utility.Constants.PLAYER_WIDTH;
import static com.allsopg.game.utility.Constants.RESTITUTION;

/**
 * Created by gja10 on 13/02/2017.
 * Updated 02/03/18
 */

public class PlayerCharacter extends com.allsopg.game.SpriteClasses.MultiRegionSprite implements IWorldObject {
    private TBWGame gameRef;
    private Body playerBody;
    private boolean facingRight = true;

    private float xSpeed;
    private float ySpeed;
    private int health;
    private boolean invulnerable;

    protected TweenData tweenData;
    protected TweenManager tweenManager;

    // generates 2 seperate animations by use of the multiregionsprite class as well as setting size and texture
    public PlayerCharacter(String atlas, Texture t, Vector2 pos, int[] regionLengths, TBWGame game) {
        super(atlas, t, pos ,regionLengths, PLAYER_WIDTH, PLAYER_HEIGHT);
        gameRef = game;
        initiatePlayerStats();
        buildBody();
        initTweenData();
    }
    // base stats set on initiation via constants to allow these values to change throughout the game.
    private void initiatePlayerStats(){
        xSpeed = BASE_X_FORCE;
        ySpeed = BASE_FORCE_Y;
        health = PLAYER_MAX_LIFE;
        invulnerable = false;
    }
    //initiasing tween data for use with tween manager
    protected void initTweenData() {
        tweenData = new TweenData();
        tweenData.setXY(this.getX(), this.getY());
        tweenData.setColour(this.getColor());
        tweenData.setScale(this.getScaleX());
        tweenManager = UniversalResource.getInstance().tweenManager; //tweenManager;
    }
    // build phyisics body and set user data
    @Override
    public void buildBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX(),getY());
        playerBody = WorldManager.getInstance().getWorld().createBody(bodyDef);
        playerBody.setUserData(this);
        playerBody.setFixedRotation(true);
        playerBody.createFixture(getFixtureDef(DENSITY,FRICTION,RESTITUTION));
    }
    // update method for animation and position
    @Override
    public void update(float stateTime) {
        super.update(stateTime);
        this.setPosition(playerBody.getPosition().x-PLAYER_OFFSET_X,playerBody.getPosition().y-PLAYER_OFFSET_Y);
        if(!facingRight){flip(true,false);}
    }
    // State machine to drive movement through buttons, altering velocity
    public void move(CurrentDirection direction){
        Vector2 vel = playerBody.getLinearVelocity();
        Vector2 pos = playerBody.getPosition();
        switch(direction){
            case LEFT:
                facingRight=false;
                playmode = Animation.PlayMode.LOOP;
                if (vel.x > -MAX_VELOCITY) {
                playerBody.applyLinearImpulse(-xSpeed, 0, pos.x, pos.y, true);
                }
                break;
            case RIGHT:
                facingRight=true;
                playmode = Animation.PlayMode.LOOP;
                if (vel.x < MAX_VELOCITY) {
                    playerBody.applyLinearImpulse(xSpeed, 0, pos.x, pos.y, true);
                }
                break;
            case UP:
                playmode = Animation.PlayMode.NORMAL;
                if (pos.y< MAX_HEIGHT && vel.y < MAX_VELOCITY) {
                    playerBody.applyLinearImpulse(0, ySpeed, pos.x, pos.y, true);
                }
                break;
            case STOP:
                if(vel.x > -8 & vel.x < 8)
                    playmode = Animation.PlayMode.NORMAL;
        }
        animation.setPlayMode(playmode);
    }

    // generate fixture definitions for use with physics body
    @Override
    public FixtureDef getFixtureDef(float density, float friction, float restitution) {
        //prepare for Fixture definition
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth()/2.7f, getHeight()/4.9f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution=restitution;
        return fixtureDef;
    }
    // deals damage or ends game depending on lives
    @Override
    public void reaction() {
        if(!invulnerable) { //if not invulnerable
            if ((health - 1) > 0) { // if resulting lives are above 0
                health--; // reduce health
                HUD.assignHealth(health); // ui call
                // crash sound
                invulnerable = true; // set to invulnerable
                Tween.to(tweenData, TweenDataAccessor.TYPE_COLOUR, INVULNERABILITY_TIME) // tween callback to switch invulnerable back
                        .setCallback(new TweenCallback() {
                            @Override
                            public void onEvent(int type, BaseTween<?> source) {
                                invulnerable = false;
                            }
                        })
                        .target(.15f,.15f,.15f,0f)
                        .start(tweenManager);
            } else { // if resulting lives means player is dead
                health--;
                frameTimer = 0;
                changeAnimation();
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        // explosion sound
                        gameRef.endGame();
                    }
                }, 2f); // end game after 2 seconds
            }
        }
    }

}
