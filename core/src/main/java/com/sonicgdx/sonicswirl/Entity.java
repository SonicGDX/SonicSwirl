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

    public SensorReturn downSensorCheck(float xPosition, float yPosition) //TODO improve naming and add comment explanation
    {
        //TODO max tile no limit
        int tileX = (int) xPosition % 128 / 16;
        int chunkX = (int) xPosition / 128;

        int tileY = (int) yPosition % 128 / 16;
        int chunkY = (int) yPosition / 128;

        int grid = (int) xPosition % 16;

        int tempTileY, tempChunkY;

        float distance = 0;

        byte height;

        //if (tileY == 0) {
        //	Gdx.app.debug("TileY","= 0");
        //}

        //Gdx.app.debug("gridValue", String.valueOf(TileMap.map[chunkX][chunkY][tileX][tileY].height[grid]));

        height = TileMap.map[chunkX][chunkY][tileX][tileY].getHeight(grid);

        distance = ((chunkY * 128) + (tileY * 16) + height) - yPosition;

        if (height == 16)
        {
            //Gdx.app.debug("distance",String.valueOf(distance));

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

            height = TileMap.map[chunkX][tempChunkY][tileX][tempTileY].getHeight(grid);
            if (height > 0) //TODO outline conditions in comment
            {
                chunkY = tempChunkY;
                tileY = tempTileY;

                distance += height;
            }

            //if (distance == 32 || distance == -32) Gdx.app.debug("distance",String.valueOf(distance));
        }

        else if (height == 0)
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

            height = TileMap.map[chunkX][chunkY][tileX][tileY].getHeight(grid);

            if (height == 0)
            {
                distance -= 16;
            }
            else
            {
                distance -= (16-height);
            }
            //Gdx.app.debug("extension","true");

           //if (distance == 32) Gdx.app.debug("distance",String.valueOf(distance));

        }

        // Classes are reference types so modifying a value would affect all the tiles that are the same.

        return new SensorReturn(TileMap.map[chunkX][chunkY][tileX][tileY],distance);
    }

    /*
    @Deprecated
    public int regression(int chunkX, int chunkY, int tileX, int tileY, int grid)
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


        height = TileMap.map.getHeight(chunkX,chunkY,tileX,tileY,grid);

        //CHECK height depending on conditions
        if (height == 0) {
            return 0;
        }
        else if (height < 16) {
            return 1;
        }
        else {
            return MathUtils.clamp(1 + regression(chunkX, chunkY, tileX, tileY, grid),0,2);
        }


    }
    */


}
