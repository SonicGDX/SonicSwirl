package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Entity {
    float lSensorX, rSensorX, middleY;
    public static Sprite playerSprite;

    Texture img;
    Player() {

        img = new Texture("1x1-000000ff.png");
        playerSprite = new Sprite(img,20,40);

    }

}
