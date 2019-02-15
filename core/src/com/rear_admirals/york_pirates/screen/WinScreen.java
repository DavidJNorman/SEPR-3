package com.rear_admirals.york_pirates.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.rear_admirals.york_pirates.PirateGame;

//[NEW ASSESSMENT 3] All this code was written within assessment 3 for a Win screen
public class WinScreen implements Screen  {

    private PirateGame parent;
    private Stage stage;
    Texture end;
    SpriteBatch batch;

    public WinScreen(PirateGame mainGame){

        parent = mainGame;
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        stage.clear();
        Gdx.input.setInputProcessor(stage);
        end = new Texture(Gdx.files.internal("Win.png"));
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Draws
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        batch.begin();
        batch.draw(end,0,0);
        batch.end();
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}