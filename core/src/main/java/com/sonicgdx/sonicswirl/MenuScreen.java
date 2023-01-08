package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {

    Stage stage;
    final Init Init;
    final int SCREEN_WIDTH,SCREEN_HEIGHT;
    private final ScreenViewport menuViewport;
    Skin buttonSkin; TextButton button;

    public MenuScreen(final Init Init){
        Gdx.app.setLogLevel(3); //TODO reduce logging level for release builds
        this.Init = Init;

        //TODO change viewport size
        SCREEN_WIDTH = Gdx.graphics.getWidth(); SCREEN_HEIGHT = Gdx.graphics.getHeight();

        menuViewport = new ScreenViewport();

        stage = new Stage(menuViewport,Init.batch);

        Init.gameScreen = new GameScreen(Init);

        buttonSkin = new Skin(Gdx.files.internal("ui/uiskin.json")); //Constructor automatically finds and disposes atlas file as required.

        button = new TextButton("Begin", buttonSkin);
    }

    @Override
    public void render(float delta)
    {
        ScreenUtils.clear(0.1f, 0, 0.2f, 1);

        menuViewport.apply();
        //Init.batch.setProjectionMatrix(menuViewport.getCamera().combined);
		Init.batch.begin();
        button.draw(Init.batch,1); //TODO what is parent alpha?
		Init.font.draw(Init.batch, "Sonic Swirl", SCREEN_WIDTH / 2F - 65, SCREEN_HEIGHT / 2F);
		Init.font.draw(Init.batch, "Press to begin", SCREEN_WIDTH / 2F - 65, SCREEN_HEIGHT / 2F - 100);
		Init.batch.end();

		if (Gdx.input.isTouched()) {
            //Init.batch.disableBlending(); //Blending is responsible for translucency using the alpha value but decreases performance.
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
        stage.dispose();
    }


}
