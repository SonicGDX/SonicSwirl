package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.math.MathUtils;

public class FloorSensor implements Sensor{

    private boolean isActive;
    private float xPosition, yPosition;

    private Tile tile;
    private float distance;

    public FloorSensor(float xPos, float yPos) {
        this.xPosition = xPos;
        this.yPosition = yPos;
    }

    /**Attempts to find the nearest top of the surface relative to the sensor's position.
     * If no surface is found, the method will check one tile downwards for a non-empty height (and therefore a non-empty Tile).
     * Conversely, if a tile that is full in that position (has a height of 16) is found, the method will check one tile upwards for a possible top of the surface.
     * The tile attribute is set to the tile type of the nearest floor that has been located
     * The distance attribute is set to the distance on the y-axis between the sensor and that Tile.
     */
    @Override
    public void process() {
        if (xPosition < 0 || yPosition < 0) {
            tile = TileMap.getEmpty(); distance = -50;
        }
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
        tile = TileMap.getEmpty(); distance = -50;
    }

    public void setPosition(float x, float y) {
        xPosition = x; yPosition = y;
    }
    public float getDistance() {
        return distance;
    }
    public Tile getTile() {
        return tile;
    }
    public boolean getActive() {
        return isActive;
    }

}