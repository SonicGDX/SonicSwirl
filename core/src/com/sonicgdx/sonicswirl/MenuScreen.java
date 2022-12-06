package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;

public class MenuScreen implements Screen{
    
    final init init;

    final int sWidth,sHeight;

    OrthographicCamera camera;

    public MenuScreen(final init init){
        this.init = init;

        sWidth = Gdx.graphics.getWidth(); sHeight = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,sWidth,sHeight);
    }
    
    @Override
    public void render(float delta)
    {
        ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		init.batch.setProjectionMatrix(camera.combined);
		init.batch.begin();

		init.font.draw(init.batch, "Menu PlaceHolder", sWidth / 2F - 60, sHeight / 2F);
		init.font.draw(init.batch, "Press to begin", sWidth / 2F - 60, sHeight / 2F - 100);
		init.batch.end();

		if (Gdx.input.isTouched()) {
			init.setScreen(new SonicGDX(init));
			dispose();
		}

    }

    @Override
    public void pause()
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }


}
