package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Entity {
    int xPos, yPos;

    Sprite sprite;

    Entity(Texture image, int width, int height) {
        sprite = new Sprite(image,width,height);

    }

}
