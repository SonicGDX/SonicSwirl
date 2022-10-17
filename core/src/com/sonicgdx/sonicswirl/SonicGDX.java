package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.Game;
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
	OrthographicCamera camera;

	float speed = 0;

	int x = 100; //https://colourtann.github.io/HelloLibgdx/
	int y = 10;

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
		sprite1 = new Sprite(img,50,50);
		sprite1.setPosition(100,100);

	}

	@Override
	public void render () { // equivalent to update in unity

		ScreenUtils.clear(0, 0, 0, 1); // clears the screen and sets the background to a certain colour

		camera.update(); // recompute matrix for orthographical projection - this is necessary if
		// it needs to move.

		// capital F can be used to cast from double to float (e.g. 50.55F)

		//if (250-y >= 125) y += 10;

		if (speed <= 6) speed += 0.02;
		//speed += 0.046875;

		if (x<=1280){
			x+=speed;
			sprite1.setPosition(x,100);
		}

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera. - https://libgdx.com/wiki/start/a-simple-game
		batch.setProjectionMatrix(camera.combined);

		batch.begin();

		sprite1.draw(batch);

		System.out.println(sprite1.getBoundingRectangle()); // use for collision detection - to set, it is sprite.SetBounds()

		//batch.draw(img, x, 250 - y , 1, 1); // draw the square texture
		batch.draw(img,0,0,1240,100); //draw the rectangle

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
