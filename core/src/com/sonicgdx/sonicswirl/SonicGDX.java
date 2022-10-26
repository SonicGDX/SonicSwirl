package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.Game;
// input handling:
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
//import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
//import com.badlogic.gdx.Game; -- replaces ApplicationAdapter
public class SonicGDX extends Game {
	SpriteBatch batch;
	Sprite sprite1;
	Texture img;
	Texture img2;
	OrthographicCamera camera;
	// capital F can be used to cast from double to float (e.g. 50.55F)
	float speedX = 0, speedY = 0, groundSpeed = 0;
	float x = 25, y = 100;; //https://colourtann.github.io/HelloLibgdx/


	//https://info.sonicretro.org/SPG:Solid_Tiles#Sensors
	float leftFootSensor, rightFootSensor;

	static final float accel = 0.046875F, decel = 0.5F;


	// https://libgdx.com/wiki/start/a-simple-game

	@Override
	public void create () { // equivalent to start in unity

		// implement class with reference to https://gamedev.stackexchange.com/a/133593

		camera = new OrthographicCamera(); // 3D camera which projects into 2D.
   		camera.setToOrtho(false, 1280, 720); // Even if the device has a scaled resolution, the in game view will still be 1280x720
		// So for example, one screen won't be in the bottom left corner in 1080p
		// but would take up the entire view


		// https://web.archive.org/web/20200427232345/https://www.badlogicgames.com/wordpress/?p=1550

		batch = new SpriteBatch(); //sprite batch provides multiple sprites to draw to the GPU to improve openGl performance https://gamedev.stackexchange.com/questions/32910/what-is-the-technical-definition-of-sprite-batching
		img = new Texture("1x1-ffffffff.png");
		img2 = new Texture("1x1-000000ff.png");

		sprite1 = new Sprite(img,25,25);
		sprite1.setPosition(x,y);


	}

	@Override
	public void render () { // equivalent to update in unity

		ScreenUtils.clear(0, 0, 1, 1); // clears the screen and sets the background to a certain colour

		camera.update(); // recompute matrix for orthographical projection - this is necessary if it needs to move.

		//if (250-y >= 150) y += 10;
		if (Gdx.input.isKeyPressed(Input.Keys.D)){
			//ternary operator
			groundSpeed = (groundSpeed + accel <= 6) ? (groundSpeed + accel) : 6;
			//Takes 128 frames to accelerate from 0 to 6 - exactly 2 seconds

			if (x<=1280){
				x+=groundSpeed;
				if (x>1280) x = 20;
			}

		}
		else {
			groundSpeed = (groundSpeed - decel >= 0) ? (groundSpeed - decel) : 0;
			x+=groundSpeed;
		}
		sprite1.setPosition(x,y);
		if (x<1280) System.out.println("Ground Speed = " + groundSpeed + " & x = " + x);

		leftFootSensor = sprite1.getX();
		rightFootSensor = sprite1.getX() + (25-1); // x pos + (srcWidth - 1) - using srcWidth places it one pixel right of the square


		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera. - https://libgdx.com/wiki/start/a-simple-game
		batch.setProjectionMatrix(camera.combined);

		batch.begin();

		sprite1.draw(batch);

		//System.out.println(sprite1.getBoundingRectangle()); // use for collision detection - to set, it is sprite.SetBounds()

		//batch.draw(img, x, 250 - y , 1, 1); // draw the square texture
		batch.draw(img,0,0,1240,100); //draw the floor

		batch.draw(img2,leftFootSensor,y); // draw left foot sensor - DEBUG
		batch.draw(img2,rightFootSensor,y); // draw right foot sensor - DEBUG

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
