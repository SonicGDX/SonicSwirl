package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

public class Entity {
    int xPos, yPos;

    Sprite sprite;

    Entity(Texture image, int width, int height) {
        sprite = new Sprite(image,width,height);

    }


    public boolean checkTile(float xPos, float yPos, TileMap tm) //TODO improve naming and add comment explanation
    {
        int xPosition = (int) xPos; int yPosition = (int) yPos;

        //TODO max tile no limit
        int tileX = xPosition % 128 / 16;
        int chunkX = xPosition / 128;

        int tileY = yPosition % 128 / 16;
        int chunkY = yPosition / 128;

        int grid = xPosition % 16;

        int tempTileY, tempChunkY;

        //if (tileY == 0) {
        //	Gdx.app.log("TileY","= 0");
        //}

        //Gdx.app.log("gridValue", String.valueOf(tm.map[chunkX][chunkY][tileX][tileY].height[grid]));

        if (tm.getHeight(chunkX,chunkY,tileX,tileY,grid) == 16)
        {
            // sensor regression, checks one tile above with downwards facing sensors in an attempt to find surface if the height of the array is full
            if (tileY < 7)
            {
                tempChunkY = chunkY;
                tempTileY = tileY + 1;
            }
            else
            {
                tempChunkY = chunkY + 1;
                tempTileY = 0;
            }

            if (tm.getHeight(chunkX,tempChunkY,tileX,tempTileY,grid) > 0) //TODO outline conditions in comment
            {
                chunkY = tempChunkY;
                tileY = tempTileY;
                //Gdx.app.debug("regression","true");
            }
            //else Gdx.app.debug("regression","false");
        }

        else if (tm.getHeight(chunkX,chunkY,tileX,tileY,grid) == 0)
        {
            // sensor extension, checks one tile below with downwards facing sensors in an attempt to find surface
            if (tileY == 0)
            {
                chunkY--;
                tileY = 7;
            }
            else
            {
                tileY--;
            }
            //Gdx.app.debug("extension","true");
        }


        // Classes are reference types so modifying a value would affect all the tiles that are the same.

        return true;
    }

    /*
    @Deprecated
    public int regression(int chunkX, int chunkY, int tileX, int tileY, int grid, TileMap tm)
    {

        //TODO recursive? Check nearby tiles

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

        //CHECK height depending on conditions
        if (height == 0) {
            return 0;
        }
        else if (height < 16) {
            return 1;
        }
        else {
            return MathUtils.clamp(1 + regression(chunkX, chunkY, tileX, tileY, grid, tm),0,2);
        }


    }
    */


}
