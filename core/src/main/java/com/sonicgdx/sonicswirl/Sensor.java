package com.sonicgdx.sonicswirl;

public abstract class Sensor {
    protected boolean isActive;
    protected float xPosition, yPosition;

    protected Tile tile;
    protected float distance;

    public Sensor(float xPos, float yPos) {
        this.xPosition = xPos;
        this.yPosition = yPos;
    }

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
    public void setActive(boolean active) {
        isActive = active;
    }

}
