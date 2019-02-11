package com.rear_admirals.york_pirates.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rear_admirals.york_pirates.PirateGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new PirateGame(), config);
		config.foregroundFPS = 60;
		config.fullscreen = false;
		config.width = 1920;
		config.height = 1080;
	}
}
