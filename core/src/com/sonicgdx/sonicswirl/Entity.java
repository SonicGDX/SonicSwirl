package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Entity {
    int xPos, yPos;

    Sprite sprite;

    Entity(Texture image, int width, int height) {
        sprite = new Sprite(image,width,height);

    }


    public boolean checkTile(float xPos, float yPos, TileMap tm)
    {
        int xPosition = (int) xPos; int yPosition = (int) yPos;

        //TODO max tile no limit
        int tileX = xPosition % 128 / 16;
        int chunkX = xPosition / 128;

        int tileY = yPosition % 128 / 16;
        int chunkY = yPosition / 128;


        int grid = xPosition % 16;

        //if (tileY == 0) {
        //	Gdx.app.log("TileY","= 0");
        //}

        //Gdx.app.log("gridValue", String.valueOf(tm.map[chunkX][chunkY][tileX][tileY].height[grid]));

        if (tm.getHeight(chunkX,chunkY,tileX,tileY,grid) == 16)
        {
            Gdx.app.log("Regression",String.valueOf(regression(chunkX,chunkY,tileX,tileY,grid,tm,0)));

        }

        // Classes are reference types so modifying a value would affect all the tiles that are the same.

        return true;
    }

    public int regression(int chunkX, int chunkY, int tileX, int tileY, int grid, TileMap tm, int recursionCount) //TODO improve naming and add comment explanation
    {

        //TODO recursive? Check nearby tiles

        //TODO regression, check up by one extra tile.

        byte height;

        //INCREMENT Tile
        if (tileY < 7)
        {
            tileY = tileY + 1;
        }
        else
        {
            chunkY +=1;
            tileY = 0;
        }


        height = tm.getHeight(chunkX,chunkY,tileX,tileY,grid);

        //CHECK height depending on conditions TODO outline conditions in comment
        if (height == 0) {
            return recursionCount;
        }
        else if (height < 16)
        {
            return Math.min(2,recursionCount+1);
        }
        else{
            recursionCount = Math.min(2,recursionCount+1);
            return regression(chunkX, chunkY, tileX, tileY, grid, tm, recursionCount);
        }


    }


}
