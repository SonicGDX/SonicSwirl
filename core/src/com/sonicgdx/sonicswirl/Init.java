package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Init extends Game {
    public SpriteBatch batch;
	public BitmapFont font;
	public SonicGDX gameScreen;

	private MenuScreen menuScreen;

	public void create() {
		menuScreen = new MenuScreen(this);
		batch = new SpriteBatch(); // sprite batch provides multiple sprites to draw to the GPU to improve openGl performance https://gamedev.stackexchange.com/questions/32910/what-is-the-technical-defInition-of-sprite-batching
        // Can be easily shared between screens
		font = new BitmapFont(); // Default font = Arial
		this.setScreen(menuScreen);
		batch.disableBlending();
	}

	public void render() {
        super.render(); // This is required when extending the game class if you want to implement another screen e.g. menu
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
        gameScreen.dispose();
		menuScreen.dispose();
	}

}
