package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.graphics.Texture;

public class Player extends Entity {
    float lSensorX, rSensorX, middleY;


    Texture img;
    Player(Texture image, int width, int height) {
        super(image, width, height);

    }

}
