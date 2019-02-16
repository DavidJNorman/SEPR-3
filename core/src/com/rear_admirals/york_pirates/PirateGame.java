package com.rear_admirals.york_pirates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.rear_admirals.york_pirates.screen.MainMenu;
import com.rear_admirals.york_pirates.screen.SailingScreen;
import com.rear_admirals.york_pirates.screen.WinScreen;

public class PirateGame extends Game {
    private SpriteBatch batch;
    private BitmapFont font;
    private Skin skin;
    private Player player;
	private SailingScreen sailingScene;
	public static Department Chemistry;
	public static Department Physics;
	public static Department ComputerScience;
	private WinScreen winScreen;


	public void create(){
		Gdx.graphics.setTitle("York Pirates!");
		this.skin = new Skin(Gdx.files.internal("flat-earth-ui.json"));
		batch = new SpriteBatch();
        //Use LibGDX's default Arial font.
        font = new BitmapFont();
        player = new Player();
		Chemistry = new Department("Chemistry", "Attack", this);
		Physics = new Department("Physics", "Defence", this);
		ComputerScience = new Department("ComputerScience", "Accuracy", this); //[NEW ASSESSMENT 3] The computer Science department is new to this class
		this.sailingScene = new SailingScreen(this);
		this.winScreen = new WinScreen(this);//[NEW ASSESSMENT 3] Win screen is new to this class
        setScreen(new MainMenu(this));
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	public Skin getSkin() { return this.skin; }

	public void setSkin(Skin skin) { this.skin = skin; }

	public Player getPlayer() { return this.player; }

	public void setPlayer(Player myPlayer) { this.player = myPlayer; }

	public SailingScreen getSailingScene() { return this.sailingScene; }

	public WinScreen getWinScreen() {return this.winScreen;} //[NEW ASSESSMENT 3] Win screen is new to this class
}
