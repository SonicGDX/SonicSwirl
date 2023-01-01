package com.sonicgdx.sonicswirl;

public abstract class Sensor {
    protected boolean isActive;
    protected float xPosition, yPosition;

    protected Tile tile;
    protected float distance;

    abstract void process();

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
