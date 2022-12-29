package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Entity {
    float xPos, yPos;
    float lSensorX, rSensorX, centreY, topY;

    //TODO reconsider usage of local variables as well as sprite.getx/y
    Sprite sprite;
    Entity(Texture image, int width, int height) {
        sprite = new Sprite(image,width,height);
    }

    public void floorSensors()
    {
        calculateSensors();

        SensorReturn leftSensorTile = downSensorCheck(xPos, yPos);
        SensorReturn rightSensorTile = downSensorCheck(xPos + sprite.getWidth(), yPos);
        //Gdx.app.debug("Right Ground Sensor distance", String.valueOf(rightSensorTile.returnDistance));

        if (leftSensorTile.returnDistance > rightSensorTile.returnDistance) {
            if (-14 < leftSensorTile.returnDistance && leftSensorTile.returnDistance < 14)
            {
                yPos += leftSensorTile.returnDistance;
            }
        }
        else if (-14 < rightSensorTile.returnDistance && rightSensorTile.returnDistance < 14) {
            yPos += rightSensorTile.returnDistance;
        }
    }

    public void enforceBoundaries()
    {
        // "Invisible walls" - prevent objects from going beyond borders to simplify calculations. TODO stop collision errors when going outside index bounds
        //xPos = Math.min(xPos,1280);
        //yPos = Math.min(yPos,720);
        xPos = Math.max(xPos,0);
        yPos = Math.max(yPos,0);
    }

    public void calculateSensors()
    {
        lSensorX = xPos;
        rSensorX = xPos + (sprite.getWidth() - 1); // xPos + (srcWidth - 1) - using srcWidth places it one pixel right of the square
        centreY = yPos + ((sprite.getHeight() - 1) / 2);
        topY = yPos + (sprite.getHeight() - 1);
    }

    public SensorReturn downSensorCheck(float xPosition, float yPosition) //TODO improve naming and add comment explanation
    {
        if (xPosition < 0 || yPosition < 0) return new SensorReturn(TileMap.getEmpty(),-16);

        //TODO prevent catch block in getTile() from being used.

        int tileX = Math.floorMod((int) xPosition, 128) / 16;
        int chunkX = (int) xPosition / 128;

        int tileY = Math.floorMod((int) yPosition, 128) / 16;
        int chunkY = (int) yPosition / 128;

        int grid = Math.floorMod((int) xPosition,16); //Different behaviour for negative numbers compared to using %. For
        // example, -129 % 16 would return -1 which would cause an ArrayIndexOutOfBoundsException. Math.floorMod would return a positive index in these cases.

        int tempTileY, tempChunkY;

        float distance = 0;

        byte height;

        //if (tileY == 0) {
        //	Gdx.app.debug("TileY","= 0");
        //}

        //Gdx.app.debug("gridValue", String.valueOf(TileMap.map[chunkX][chunkY][tileX][tileY].height[grid]));
        height = TileMap.getTile(chunkX,chunkY,tileX,tileY).getHeight(grid);

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

            height = TileMap.getTile(chunkX,tempChunkY,tileX,tempTileY).getHeight(grid);
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

            height = TileMap.getTile(chunkX,chunkY,tileX,tileY).getHeight(grid);

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

        return new SensorReturn(TileMap.getTile(chunkX,chunkY,tileX,tileY),distance);
    }

    /*
    @Deprecated
    public int regression(int chunkX, int chunkY, int tileX, int tileY, int grid)
    {
        //TODO possibly use for more accuracy?

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
