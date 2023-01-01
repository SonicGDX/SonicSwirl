package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

/**
 * The base class all objects extend from, including the Player.
 */
public abstract class Entity {
    float xPos, yPos;
    float lSensorX, rSensorX, centreY, topY;

    //TODO reconsider usage of local variables as well as sprite.getx/y
    Sprite sprite;
    Entity(Texture image, int width, int height) {
        sprite = new Sprite(image,width,height);
    }

    public void floorSensors()
    {
        calculateSensorPositions();

        ReturnTile leftSensorTile = downSensorCheck(xPos, yPos);
        ReturnTile rightSensorTile = downSensorCheck(xPos + sprite.getWidth(), yPos);
        //Gdx.app.debug("Right Ground Sensor distance", String.valueOf(rightSensorTile.returnDistance));

        if (leftSensorTile.getDistance() > rightSensorTile.getDistance()) {
            if (-14 < leftSensorTile.getDistance() && leftSensorTile.getDistance() < 14)
            {
                yPos += leftSensorTile.getDistance();
            }
        }
        else if (-14 < rightSensorTile.getDistance() && rightSensorTile.getDistance() < 14) {
            yPos += rightSensorTile.getDistance();
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

    public void calculateSensorPositions()
    {
        lSensorX = xPos;
        rSensorX = xPos + (sprite.getWidth() - 1); // xPos + (srcWidth - 1) - using srcWidth places it one pixel right of the square
        centreY = yPos + ((sprite.getHeight() - 1) / 2);
        topY = yPos + (sprite.getHeight() - 1);
    }

    /**Attempts to find the nearest top of the surface relative to the sensor's position.
     * If no surface is found, the method will check one tile downwards for a non-empty height (and therefore a non-empty Tile).
     * Conversely, if a tile that is full in that position (has a height of 16) is found, the method will check one tile upwards for a possible top of the surface.
     * @param xPosition the x-axis of the sensor that is being checked - this can be either the leftmost part of the object's sprite or the rightmost part; however if the sprite is rotated 90, 180, or 270 degrees the sensor's position will be adjusted accordingly.
     * @param yPosition the y-axis position of the sensor that is being checked - this will be the bottom of the Entity's sprite; however if the sprite is rotated 90, 180, or 270 degrees the sensor's position will be adjusted accordingly.
     * @return The Tile type of the nearest floor that has been located as well as the distance on the y-axis between the sensor and that Tile.
     * @see ReturnTile
     */
    public ReturnTile downSensorCheck(float xPosition, float yPosition) //TODO improve naming and add comment explanation
    {
        if (xPosition < 0 || yPosition < 0) return new ReturnTile(TileMap.getEmpty(),-16);
        //TODO prevent catch block in getTile() from being used.

        int tileX = Math.floorMod(MathUtils.round(xPosition), 128) / 16;
        int chunkX = MathUtils.round(xPosition) / 128;

        int tileY = Math.floorMod(MathUtils.round(yPosition), 128) / 16;
        int chunkY = MathUtils.round(yPosition) / 128;

        int block = Math.floorMod(MathUtils.round(xPosition),16); //Different behaviour for negative numbers compared to using %. For
        // example, -129 % 16 would return -1 which would cause an ArrayIndexOutOfBoundsException. Math.floorMod() would return a positive index in these cases.

        float distance = 0;
        byte height;

        int tempTileY, tempChunkY;

        height = TileMap.getTile(chunkX,chunkY,tileX,tileY).getHeight(block);

        distance = ((chunkY * 128) + (tileY * 16) + height) - yPosition;

        if (height == 16)
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

            height = TileMap.getTile(chunkX,tempChunkY,tileX,tempTileY).getHeight(block);
            if (height > 0) //TODO outline conditions in comment
            {
                chunkY = tempChunkY;
                tileY = tempTileY;

                distance += height;
            }
        }

        else if (height == 0)
        {
            // sensor extension, checks one tile below with downwards facing sensors in an attempt to find surface
            if (tileY == 0)
            {
                chunkY--;
                tileY = 7;
            }
            else tileY--;

            height = TileMap.getTile(chunkX,chunkY,tileX,tileY).getHeight(block);

            if (height == 0) distance -= 16;
            else distance -= (16-height);
        }
        return new ReturnTile(TileMap.getTile(chunkX,chunkY,tileX,tileY),distance);
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
