package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

/**
 * The base class all objects extend from, including the Player.
 */
public abstract class Entity {
    protected float xPos, yPos;
    protected float leftEdgeX, rightEdgeX, bottomEdgeY, topEdgeY;

    //TODO reconsider usage of local variables as well as sprite.getx/y
    Sprite sprite;
    Entity() {
        sprite = new Sprite();
    }

    public void enforceBoundaries()
    {
        // "Invisible walls" - prevent objects from going beyond borders to simplify calculations. TODO stop collision errors when going outside index bounds
        //xPos = Math.min(xPos,1280);
        //yPos = Math.min(yPos,720);
        xPos = Math.max(xPos,0);
        //yPos = Math.max(yPos,0);
    }

    public void calculateSensorPositions(float widthRadius, float heightRadius)
    {
        topEdgeY = yPos + heightRadius;
        bottomEdgeY = yPos - heightRadius;
        leftEdgeX = xPos - widthRadius;
        rightEdgeX = xPos + widthRadius;
    }
    public float snapToNearest (float angle, float snapTo) {
        return MathUtils.round(angle/snapTo) * snapTo;
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
