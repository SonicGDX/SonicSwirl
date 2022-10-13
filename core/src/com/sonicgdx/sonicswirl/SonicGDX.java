package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;

public class SonicGDX extends ApplicationAdapter {
	SpriteBatch batch;
	Sprite sprite1;
	Texture img;
	OrthographicCamera camera;

	int x = 1; //https://colourtann.github.io/HelloLibgdx/
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

		/*if (250-y >= 125)
		{
			y += 10;
		}*/

		/*if (x<=1280){
			x+=1;
			//	System.out.println(x);
		}*/

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera. - https://libgdx.com/wiki/start/a-simple-game
		batch.setProjectionMatrix(camera.combined);

		batch.begin();

		sprite1.draw(batch);
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
