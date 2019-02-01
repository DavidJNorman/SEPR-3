package com.rear_admirals.york_pirates.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.rear_admirals.york_pirates.PirateGame;
import com.rear_admirals.york_pirates.Player;
import com.rear_admirals.york_pirates.base.BaseScreen;

import java.util.Random;

public class MinigameScreen extends BaseScreen {
    private Player player;
    private Stage stage;
    private Array<MinigameGeese> geeseList;


    public MinigameScreen(PirateGame main){
        super(main);
        geeseList = new Array<MinigameGeese>(5);
        this.player = main.getPlayer();
        Random random = new Random();
        stage = new Stage(new FitViewport(1920,1080));
        for (int x = 0; x<5; x=x+1){
            this.geeseList.add(new MinigameGeese(random.nextInt(10)+1));
            Gdx.app.log(geeseList.get(x).toString(), "words");
        }


    }


    @Override
    public void update(float delta) {


    }
    public void render(float delta){
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        stage.act();

    }
}
