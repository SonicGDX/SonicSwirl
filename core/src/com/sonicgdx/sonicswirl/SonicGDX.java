package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class SonicGDX extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () { // equivalent to start in unity
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () { // equivalent to update in unity
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		batch.draw(img, 250, 250, 1, 1, 50, 50);
		//batch.draw(img, 0, 0, 1,1,1000,100);
		/*for (int i=0; i< 5; i++){
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
