package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application; //Lwjgl is a library that exposes cross platform libraries like OpenGL
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.sonicgdx.sonicswirl.SonicGDX;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Sonic Swirl");
		new Lwjgl3Application(new SonicGDX(), config);


	}
}
