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
            Gdx.app.log("Regression",String.valueOf(regression(chunkX,chunkY,tileX,tileY,grid,tm)));

        }

        // Classes are reference types so modifying a value would affect all the tiles that are the same.

        return true;
    }

    public int regression(int chunkX, int chunkY, int tileX, int tileY, int grid, TileMap tm)
    {

        //TODO recursive? Check nearby tiles

        //TODO regression, check up by one extra tile.

        int tempCY = chunkY; int tempTY = tileY; int height;

        for (int i=0;i<=2;i++) {

            if (tileY < 7)
            {
                tempTY= tileY + 1;
            }
            else
            {

                tempCY = chunkY +=1;
                tempTY = tileY = 0;
            }

            height = tm.getHeight(chunkX,tempCY,tileX,tempTY,grid);

            if (height == 0)
            {
                // If the height of the tile above is empty, regression does not occur since it is likely that the original tile is the surface.
                return i;
            }
            else if (height < 16)
            {
                chunkY = tempCY; tileY = tempTY;

                Gdx.app.debug("grid",String.valueOf(grid));
                Gdx.app.debug("collision","sensor regression");
            }
        }
        return 2;

    }


}
