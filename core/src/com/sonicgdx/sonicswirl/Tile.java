package com.sonicgdx.sonicswirl;

public class Tile {

    boolean isFlipped, isSolid, empty;
    byte[] height,width;
    float angle;
    Tile(byte[] heightArray, byte[] widthArray, float angle, boolean solid, boolean flipped){
        this.empty = false;
        this.height = heightArray;
        this.width = widthArray;
        this.angle = angle;
        this.isFlipped = flipped;
        this.isSolid = solid;

    }

    Tile()
    {
        this.empty = true;
        this.height = new byte[16];
        this.width = new byte[16];
        this.angle = 0;
    }

}
