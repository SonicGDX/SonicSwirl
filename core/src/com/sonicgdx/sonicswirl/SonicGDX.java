package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;

public class SonicGDX extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Rectangle floor;
	// Rectangle used in gdx.math is different to the rectangle in java.awt.Rectangle

	// https://libgdx.com/wiki/start/a-simple-game
	@Override
	public void create () { // equivalent to start in unity
		batch = new SpriteBatch();
		img = new Texture("square.jpg");
		floor = new Rectangle();
		floor.x = 800 / 2 - 640 / 2;
		floor.y = 20;
		floor.width = 10;
		floor.height = 10;
	}

	@Override
	public void render () { // equivalent to update in unity
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		batch.draw(img, 250, 250, 1, 1, 50, 50); // draw the square texture
		batch.draw(img,floor.x,floor.y); //draw the rectangle

		/*batch.draw(img, 0, 0, 1,1,1000,100);
		for (int i=0; i< 5; i++){
			//sprite.setRotation(i*1.5);
			batch.draw(img, i*20, 200, 1,1,100,100);
		}
		*/
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
