package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.w3c.dom.Text;

public class MenuScreen implements Screen {
    
    final Init Init;
    final int SCREEN_WIDTH,SCREEN_HEIGHT;
    private final ScreenViewport menuViewport;
    Skin buttonSkin; TextureAtlas atlas; //TextButton button;

    public MenuScreen(final Init Init){
        Gdx.app.setLogLevel(3); //TODO reduce logging level for release builds
        this.Init = Init;

        //TODO change viewport size
        SCREEN_WIDTH = Gdx.graphics.getWidth(); SCREEN_HEIGHT = Gdx.graphics.getHeight();

        menuViewport = new ScreenViewport();

        //TODO replace placeholder assets and finish UI, maybe with VisUI
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

        menuViewport.apply();
		Init.batch.begin();
		Init.font.draw(Init.batch, "Menu PlaceHolder", SCREEN_WIDTH / 2F - 65, SCREEN_HEIGHT / 2F);
		Init.font.draw(Init.batch, "Press to begin", SCREEN_WIDTH / 2F - 65, SCREEN_HEIGHT / 2F - 100);
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
        menuViewport.update(width,height,true);
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
