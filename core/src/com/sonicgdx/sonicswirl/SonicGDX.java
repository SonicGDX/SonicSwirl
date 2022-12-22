package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

//import com.badlogic.gdx.maps.tiled.TiledMap;
//import java.util.Arrays;
//import java.awt.*;
//import com.badlogic.gdx.math.Rectangle;

public class SonicGDX implements Screen {

    final Init Init; TileMap tm;
    ShapeRenderer dr; Texture img; Texture playerImg; FPSLogger frameLog;
    float speedX = 0, speedY = 0,
            groundSpeed = 0,
            x = 600, y = 200; // Player starts at (600,200);
    final int DEBUG_SPEED = 90, DECELERATION = 1800, MAX_SPEED = 360, ACCELERATION = 168;
    // A deltaTime of 0.016666 was approximated to obtain the adjusted values TODO use more accurate values
    // Original: ACCELERATION = 0.046875F, DECELERATION = 0.5F, DEBUG_SPEED = 1.5F, MAX_SPEED = 6;

    //TODO change usage of local variables x and y
    OrthographicCamera camera; Viewport viewport; Vector2 cameraOffset = Vector2.Zero;
    boolean debugMode = false;
    boolean fSensors,cSensors,wSensors; //when grounded, fsensors are active. TODO
    int vpHeight, vpWidth;

    Player player;

    public SonicGDX(final Init Init) {

        //Have to declare it outside, so it is a global variable?

        this.Init = Init;

        //TODO implement class with reference to https://gamedev.stackexchange.com/a/133593

        //Gdx.app.debug("debugMode",String.valueOf(tile[1][3][15]));

        vpWidth = Gdx.app.getGraphics().getWidth(); vpHeight = Gdx.app.getGraphics().getHeight();
        //TODO possibly reduce viewport resolution to reduce pixels being missing at lower resolutions

        camera = new OrthographicCamera(); // 3D camera which projects into 2D.
        viewport = new FitViewport(vpWidth,vpHeight,camera);

        // stretch viewport //TODO Update comments
        camera.setToOrtho(false); // Even if the device has a scaled resolution, the in game view will still be 1280x720
        // So for example, one screen won't be in the bottom left corner in 1080p
        // but would take up the entire view

        tm = new TileMap();
        dr = new ShapeRenderer();
        img = new Texture(Gdx.files.internal("1x1-ffffffff.png")); playerImg = new Texture(Gdx.files.internal("1x1-000000ff.png"));
        player = new Player(playerImg,20,40);

        player.sprite.setPosition(x,y);

        cameraOffset.x = camera.position.x - player.sprite.getX();
        cameraOffset.y = camera.position.y - player.sprite.getY();

        //frameLog = new FPSLogger();

    }

    @Override
    public void render(float delta) {

        //frameLog.log();

        ScreenUtils.clear(Color.DARK_GRAY); // clears the screen and sets the background to a certain colour

        //TODO Would be better to implement an InputProcessor. This makes more sense as an interrupt rather than constant polling.
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q))
        {
            debugMode = !debugMode;
            groundSpeed = 0;
            //Gdx.app.log("debugMode",String.valueOf(debugMode));
            //TODO ACCELERATION in debug mode
        }
        if (debugMode) {
            if (Gdx.input.isKeyPressed(Input.Keys.D)) x += (DEBUG_SPEED * delta);
            if (Gdx.input.isKeyPressed(Input.Keys.A)) x -= (DEBUG_SPEED * delta);
            if (Gdx.input.isKeyPressed(Input.Keys.W)) y += (DEBUG_SPEED * delta);
            if (Gdx.input.isKeyPressed(Input.Keys.S)) y -= (DEBUG_SPEED * delta);
            //Gdx.app.debug("delta",String.valueOf(delta));
        }
        else {
            //if (250-y >= 150) y += 10;
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                groundSpeed = Math.min(groundSpeed + (ACCELERATION * delta), MAX_SPEED);
                //Takes 128 frames to accelerate from 0 to 6 - exactly 2 seconds
            } else	groundSpeed = Math.max(groundSpeed - (DECELERATION * delta), 0);
            x += groundSpeed * delta;

            Gdx.app.debug("speed",String.valueOf(groundSpeed*delta*60)); //test

            //TODO ground angle and sin/cos with Gdx MathUtils

        }

        //TODO define constants

        //TODO check for jumps here

        // "Invisible walls" - prevent players from going beyond borders to simplify calculations.
        x = Math.min(x,1000);
        x = Math.max(x,0);
        y = Math.max(y,0);

        player.sprite.setPosition(x, y); camera.position.set(x + cameraOffset.x,y + cameraOffset.y,camera.position.z); camera.update(); // recompute matrix for orthographical projection so that the change is responded to in the view

        boolean nothing = player.checkTile(x,y,tm);

        player.lSensorX = x;
        player.rSensorX = x + (player.sprite.getWidth() - 1); // x pos + (srcWidth - 1) - using srcWidth places it one pixel right of the square
        player.middleY = y + (player.sprite.getHeight() / 2);


        //TODO Add collision logic

        dr.setProjectionMatrix(camera.combined);
        dr.begin(ShapeRenderer.ShapeType.Filled);
        for (int i=0;i<8;i++)
        {
            for (int j =0; j<1;j++)
            {
                drawChunkDR(i,j);
            }
        }
        dr.end();

        // tells the SpriteBatch to render in the coordinate system specified by the camera
        Init.batch.setProjectionMatrix(camera.combined);
        Init.batch.begin();
        player.sprite.draw(Init.batch);
        // DEBUG
        Init.batch.draw(img,player.lSensorX,y); Init.batch.draw(img,player.rSensorX,y); Init.batch.draw(img,player.lSensorX,player.middleY); Init.batch.draw(img,player.rSensorX,player.middleY);
        Init.batch.end();
    }

    @Override
    public void dispose () {
        img.dispose();
        playerImg.dispose();
        dr.dispose();
    }


    //TODO multithreading except for GWT?
    public void drawChunkBatch(int chunkX, int chunkY) {

        for (int blockX = 0; blockX < 8; blockX++)
        {
            for (int blockY = 0; blockY < 8; blockY++)
            {
                for (int grid = 0; grid < 16; grid++)
                {
                    if (tm.map[chunkX][chunkY][blockX][blockY].empty){
                        break;
                    }
                    Init.batch.draw(img, blockX*16+grid+(128*chunkX),blockY*16+(128*chunkY),1, tm.map[chunkX][chunkY][blockX][blockY].height[grid]);

					/*if ((int) x == (chunkX*128 + blockX*16+grid))
					{
						if (tm.map[chunkX][chunkY][blockX][blockY].solidity == 0);
					}*/

                    //TODO reversed search order for flipped tiles. e.g. Collections.reverse() or ArrayUtils.reverse(byte[] array)

                }
            }
        }
    }
    public void drawChunkDR(int chunkX, int chunkY) {

        for (int blockX = 0; blockX < 8; blockX++)
        {
            for (int blockY = 0; blockY < 8; blockY++)
            {
                if (tm.map[chunkX][chunkY][blockX][blockY].empty){
                    continue;
                }
                for (int grid = 0; grid < 16; grid++)
                {

                    if (grid==0) dr.setColor(new Color(0));
                    else dr.setColor(new Color(0.125F * blockY,0,grid,0));
                    dr.rect(blockX*16+grid+(128*chunkX),blockY*16+(128*chunkY),1,tm.map[chunkX][chunkY][blockX][blockY].height[grid]);

					/*if ((int) x == (chunkX*128 + blockX*16+grid))
					{
						if (tm.map[chunkX][chunkY][blockX][blockY].solidity == 0);
					}*/

                    //TODO reversed search order for flipped tiles. e.g. Collections.reverse() or ArrayUtils.reverse(byte[] array)

                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void show() {
        //TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        //TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        //TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        //TODO Auto-generated method stub

    }

}