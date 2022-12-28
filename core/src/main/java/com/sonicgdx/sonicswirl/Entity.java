package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Entity {
    float xPos, yPos;
    //TODO reconsider usage of local variables as well as sprite.getx/y
    Sprite sprite;
    Entity(Texture image, int width, int height) {
        sprite = new Sprite(image,width,height);

    }

    public SensorReturn downSensorCheck(int xPosition, int yPosition, Tile[][][][] tm) //TODO improve naming and add comment explanation
    {
        //TODO max tile no limit
        int tileX = xPosition % 128 / 16;
        int chunkX = xPosition / 128;

        int tileY = yPosition % 128 / 16;
        int chunkY = yPosition / 128;

        int grid = xPosition % 16;

        int tempTileY, tempChunkY;

        int distance = 0;

        byte height;

        //if (tileY == 0) {
        //	Gdx.app.log("TileY","= 0");
        //}

        //Gdx.app.log("gridValue", String.valueOf(tm.map[chunkX][chunkY][tileX][tileY].height[grid]));

        height = tm[chunkX][chunkY][tileX][tileY].getHeight(grid);

        if (height == 16)
        {
            distance = yPosition - (chunkY * 128 + (tileY+1) * 16);

            //Gdx.app.log("distance",String.valueOf(distance));

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

            height = tm[chunkX][tempChunkY][tileX][tempTileY].getHeight(grid);
            if (height > 0) //TODO outline conditions in comment
            {
                chunkY = tempChunkY;
                tileY = tempTileY;

                distance -= height;
            }

            if (distance == 32 || distance == -32) Gdx.app.log("distance",String.valueOf(distance));
        }

        else if (height == 0)
        {
            distance = yPosition - (chunkY * 128 + tileY * 16);

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

            height = tm[chunkX][chunkY][tileX][tileY].getHeight(grid);

            if (height == 0)
            {
                distance += 16;
            }
            else
            {
                distance += height;
            }
            //Gdx.app.debug("extension","true");

           if (distance == 32) Gdx.app.log("distance",String.valueOf(distance));

        }


        // Classes are reference types so modifying a value would affect all the tiles that are the same.

        return new SensorReturn(tm[chunkX][chunkY][tileX][tileY],distance);
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
