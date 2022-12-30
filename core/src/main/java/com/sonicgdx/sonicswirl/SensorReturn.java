package com.sonicgdx.sonicswirl;

/**
 * This class is instantiated by Entity sensor methods - e.g. downSensorCheck() - to return multiple values to the movement methods.
 */
public class SensorReturn {
    public final Tile returnTile;
    public final float returnDistance;

    /**
     * @param returnTile the tile type found by the sensor
     * @param returnDistance the distance between the sensor and the surface
     */
    public SensorReturn(Tile returnTile, float returnDistance) {
        this.returnTile = returnTile;
        this.returnDistance = returnDistance;
    }

}
