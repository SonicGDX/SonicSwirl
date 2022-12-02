package com.sonicgdx.sonicswirl;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application; //Lwjgl is a library that exposes cross-platform libraries like OpenGL
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.sonicgdx.sonicswirl.SonicGDX;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(1280, 720); // Default is 640 by 480 which has a 4:3 aspect ratio
      	config.useVsync(true);
		config.setForegroundFPS(60); // 60 is the minimum frame-rate almost all devices will be able to keep up with.
		// Physics would have to be tuned for higher frame-rates as the render function is called every frame.
		config.setTitle("Sonic Swirl");
		new Lwjgl3Application(new SonicGDX(), config);


	}
}
