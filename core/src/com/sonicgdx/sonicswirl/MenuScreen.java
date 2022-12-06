package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;

public class MenuScreen implements Screen{
    
    final init init;

    OrthographicCamera camera;

    public MenuScreen(final init init){
        this.init = init;

        camera = new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    }
    
    @Override
    public void render(float delta)
    {
        ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		init.batch.setProjectionMatrix(camera.combined);

		init.batch.begin();
		init.font.draw(init.batch, "Menu PlaceHolder", 500, 200);
		init.font.draw(init.batch, "Press to begin", 500, 100);
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
