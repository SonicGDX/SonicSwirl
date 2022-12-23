package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class Player extends Entity {
    float lSensorX, rSensorX, middleY;
    boolean fSensors,cSensors,wSensors; //when grounded, fsensors are active. TODO
    boolean debugMode = false;
    final float ACCELERATION = 168.75F; final int DEBUG_SPEED = 90, DECELERATION = 1800, MAX_SPEED = 360;
    // An FPS of 60 was used to obtain the adjusted values
    // Original: ACCELERATION = 0.046875F, DECELERATION = 0.5F, DEBUG_SPEED = 1.5F, MAX_SPEED = 6;
    // Original values were designed to occur 60 times every second so by multiplying it by 60 you get the amount of pixels moved per second.
    float speedX = 0, speedY = 0, groundSpeed = 0,
    Texture img;
    Player(Texture image, int width, int height) {
        super(image, width, height);
        xPos = 600; yPos = 200; // Player starts at (600,200);

    }

    public void move()
    {
        //TODO Would be better to implement an InputProcessor. This makes more sense as an interrupt rather than constant polling.
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q))
        {
            debugMode = !debugMode;
            groundSpeed = 0;
            //Gdx.app.log("debugMode",String.valueOf(debugMode));
            //TODO ACCELERATION in debug mode
        }
        //TODO move movement into player class
        if (debugMode) {
            if (Gdx.input.isKeyPressed(Input.Keys.D)) xPos += (DEBUG_SPEED * delta);
            if (Gdx.input.isKeyPressed(Input.Keys.A)) xPos -= (DEBUG_SPEED * delta);
            if (Gdx.input.isKeyPressed(Input.Keys.W)) yPos += (DEBUG_SPEED * delta);
            if (Gdx.input.isKeyPressed(Input.Keys.S)) yPos -= (DEBUG_SPEED * delta);
            //Gdx.app.debug("deltaTime",String.valueOf(delta));
        }
        else {
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

            x += groundSpeed * delta;

            //TODO ground angle and sin/cos with Gdx MathUtils

        }
    }

}
