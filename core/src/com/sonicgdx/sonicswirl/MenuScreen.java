package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;
import org.w3c.dom.Text;

public class MenuScreen implements Screen{
    
    final Init Init;

    final int sWidth,sHeight;
    OrthographicCamera camera;

    Skin buttonSkin; TextureAtlas atlas; //TextButton button;

    public MenuScreen(final Init Init){
        Gdx.app.setLogLevel(3); //TODO reduce logging level for release builds
        this.Init = Init;

        //TODO change viewport size
        sWidth = Gdx.graphics.getWidth(); sHeight = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,sWidth,sHeight);

        //TODO replace placeholder assets and finish UI
        atlas = new TextureAtlas(Gdx.files.internal("button/uiskin.atlas"));
        buttonSkin = new Skin();
        buttonSkin.addRegions(atlas);

        Init.gameScreen = new SonicGDX(Init);

        //button = new TextButton("Begin", buttonSkin,buttonSkin);
    }
    
    @Override
    public void render(float delta)
    {
        ScreenUtils.clear(0.1f, 0, 0.2f, 1);

		camera.update();
		Init.batch.setProjectionMatrix(camera.combined);
		Init.batch.begin();

		Init.font.draw(Init.batch, "Menu PlaceHolder", sWidth / 2F - 60, sHeight / 2F);
		Init.font.draw(Init.batch, "Press to begin", sWidth / 2F - 60, sHeight / 2F - 100);
		Init.batch.end();

		if (Gdx.input.isTouched()) {
			Init.setScreen(Init.gameScreen);
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
        buttonSkin.dispose();
        atlas.dispose();
    }


}
