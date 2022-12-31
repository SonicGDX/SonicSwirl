package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Player extends Entity {
    private boolean fSensors,cSensors,wSensors; //when grounded, fsensors are active. TODO
    private boolean debugMode = false;
    private final float ACCELERATION = 168.75F, SLOPE_FACTOR = 7.5F; private final int DEBUG_SPEED = 90, DECELERATION = 1800, MAX_SPEED = 360, JUMP_FORCE = 390;
    // An FPS of 60 was used to obtain the adjusted values
    // Original: ACCELERATION = 0.046875F, DECELERATION = 0.5F, DEBUG_SPEED = 1.5F, MAX_SPEED = 6, SLOPE_FACTOR = 0.125;
    // Original values were designed to occur 60 times every second so by multiplying it by 60 you get the amount of pixels moved per second.
    private float speedX = 0, speedY = 0, groundSpeed = 0, groundAngle = 0;
    private Texture img;
    Player(Texture image, int width, int height) {
        super(image, width, height);
        xPos = 600; yPos = 200; // Player starts at (600,200);
    }

    //TODO Tommy Ettinger's digital extension could be used for faster operations on GWT
    public void move(float delta)
    {
        //TODO Would be better to implement an InputProcessor. This makes more sense as an interrupt rather than constant polling.
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q))
        {
            debugMode = !debugMode;
            groundSpeed = 0;
            groundAngle = 0;
            //Gdx.app.debug("debugMode",String.valueOf(debugMode));
            //TODO acceleration in debug mode
        }
        if (debugMode) {
            if (Gdx.input.isKeyPressed(Input.Keys.D)) xPos += (DEBUG_SPEED * delta);
            if (Gdx.input.isKeyPressed(Input.Keys.A)) xPos -= (DEBUG_SPEED * delta);
            if (Gdx.input.isKeyPressed(Input.Keys.W)) yPos += (DEBUG_SPEED * delta);
            if (Gdx.input.isKeyPressed(Input.Keys.S)) yPos -= (DEBUG_SPEED * delta);
            //Gdx.app.debug("deltaTime",String.valueOf(delta));
        }
        else {

            //TODO perhaps add a check if the player is stationary before calculating collision
            floorSensors();
            //TODO possibly extract more parts into methods

            //TODO Right now, right movement is prioritised if both directions are pressed at the same time. Consider cancelling them out.

            if (groundSpeed != 0) groundSpeed -= delta * SLOPE_FACTOR * MathUtils.sinDeg(groundAngle); //TODO this only happens when the player is not in ceiling mode.

            if (Gdx.input.isKeyPressed(Input.Keys.D) || (Gdx.input.isKeyPressed(Input.Keys.RIGHT))) // if moving right
            {
                if (groundSpeed < 0) groundSpeed += (DECELERATION * delta); // Deceleration acts in the opposite direction to the one in which the player is currently moving.
                else if (groundSpeed < MAX_SPEED) groundSpeed += (ACCELERATION * delta); //Takes 128 frames to accelerate from 0 to 6 - exactly 2 seconds
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.A) || (Gdx.input.isKeyPressed(Input.Keys.LEFT))) // if moving left
            {
                if (groundSpeed > 0) groundSpeed -= (DECELERATION * delta);
                else if (groundSpeed > -MAX_SPEED) groundSpeed -= ACCELERATION * delta;
            }
            else groundSpeed -= Math.min(Math.abs(groundSpeed), ACCELERATION * delta) * Math.signum(groundSpeed); // friction if not pressing any directions
            // Decelerates until the absolute value of groundSpeed is lower than the ACCELERATION value (which doubles as the friction value) and then stops

            speedX = groundSpeed * MathUtils.cosDeg(groundAngle);
            speedY = groundSpeed * MathUtils.sinDeg(groundAngle);

            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) jump(delta);

            xPos += speedX * delta;
            yPos += speedY * delta;



            //TODO ground angle and sin/cos with Gdx MathUtils

        }

        enforceBoundaries();

        calculateSensorPositions();

        sprite.setPosition(xPos, yPos);
        sprite.setRotation(groundAngle);

    }

    public void jump(float delta) {
        speedX -= delta * JUMP_FORCE * MathUtils.sinDeg(groundAngle);
        speedY -= delta * JUMP_FORCE * MathUtils.cosDeg(groundAngle);

    }

    /**
     *Collides with the nearest floor within a certain limit by adjusting the player's yPos appropriately.
     *The positive limit is always 14, but the negative limit only becomes more lenient as the player's speed increases.
     *Limits of -16<=x<=16 are not used as those distances are likely too far away from the player to matter.
     *Uses angle for rotation and speed of player only and for player slope physics.
     *Applies unique calculation to find minimum value, from Sonic 2 depending on player's speed
     */
    @Override
    public void floorSensors()
    {
        calculateSensorPositions();

        SensorReturn leftSensorTile = downSensorCheck(lSensorX, yPos);
        SensorReturn rightSensorTile = downSensorCheck(rSensorX, yPos);

        if (leftSensorTile.returnDistance > rightSensorTile.returnDistance) {
            if (Math.max(-Math.abs(speedX) - 4,-14) < leftSensorTile.returnDistance && leftSensorTile.returnDistance < 14)
            {
                yPos += leftSensorTile.returnDistance;
                groundAngle = leftSensorTile.returnTile.angle;
            }
        }
        else if (Math.max(-Math.abs(speedX) - 4, -14) < rightSensorTile.returnDistance && rightSensorTile.returnDistance < 14)
        {
            yPos += rightSensorTile.returnDistance;
            groundAngle = rightSensorTile.returnTile.angle; //TODO possibly apply this to enemies?
        }
    }

}
