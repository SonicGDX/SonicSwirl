package com.sonicgdx.sonicswirl;

/**
 * This class is instantiated by Entity sensor methods - e.g. downSensorCheck() - to return multiple values to the movement methods.
 */
public class ReturnTile {
    public final Tile tile;
    public final float distance;

    /**
     * @param returnTile the tile type found by the sensor
     * @param returnDistance the distance between the sensor and the surface
     */
    public ReturnTile(Tile returnTile, float returnDistance) {
        this.tile = returnTile;
        this.distance = returnDistance;
    }

}
