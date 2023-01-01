package com.sonicgdx.sonicswirl;

public class FloorSensor implements Sensor{

    private boolean active;
    private float xPos, yPos;

    private Tile tile;
    private float distance;

    public FloorSensor(float xPos, float yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public void check() {

    }

    public void setPosition(float x, float y) {
        xPos = x; yPos = y;
    }
}
