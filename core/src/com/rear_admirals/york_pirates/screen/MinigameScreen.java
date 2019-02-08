package com.rear_admirals.york_pirates.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.rear_admirals.york_pirates.MinigameGeese;
import com.rear_admirals.york_pirates.PirateGame;
import com.rear_admirals.york_pirates.Player;
import com.rear_admirals.york_pirates.base.BaseScreen;

import java.util.Random;

public class MinigameScreen extends BaseScreen {
    private Player player;

    private Array<MinigameGeese> geeseList;
    private Boolean minigamerun = false;
    private Boolean inProgress = false;
    private Table goldTable;
    private Label goldAmount;
    private Label goldString;
    private Stage stage;

    public MinigameScreen(PirateGame main){
        super(main);

        this.player = main.getPlayer();
        stage = new Stage(new ScreenViewport());

        //FOR TESTING ONLY
        player.setGold(200);
        //FOR TESTING ONLY



        geeseList = new Array<MinigameGeese>(5);





    }

    @Override
    public void show(){
        stage.clear();
        Gdx.input.setInputProcessor(stage);
        goldTable = new Table();
        goldTable.setFillParent(true);
        stage.addActor(goldTable);

        goldAmount = new Label("You have:" + String.valueOf(player.getGold()) +"G", pirateGame.getSkin(),"title");
        goldTable.add(goldAmount);
        final TextButton textButton = new TextButton("Pay 100G to play?", pirateGame.getSkin());
        textButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                minigamerun = true;
                player.setGold(player.getGold()-100);
                Gdx.app.log(String.valueOf(player.getGold()), "gold");

            }
        });
        goldTable.row();
        goldTable.add(textButton);

        //This breaks it for some reason..
//        goldTable.setY(600);
//        goldTable.setX(600);

        Random random = new Random();
        for (int y = 0; y<5; y=y+1){
            this.geeseList.add(new MinigameGeese(random.nextInt(10)+1, 30, (y+1)*110));
            Gdx.app.log(geeseList.get(y).toString(), "words");
        }
    }

    @Override
    public void update(float delta) {


    }
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(minigamerun && !inProgress){
            minigameSetup();
        }
        if(minigamerun && inProgress){
            minigame();
        }

        stage.draw();
        stage.act();

    }
    public void minigameSetup(){
        stage.clear();
            inProgress = true;
            for (int x = 0; x<5; x=x+1){
                stage.addActor(geeseList.get(x).getImage());

            }

    }

    public void minigame(){

        for (int x = 0; x<5; x=x+1){
            //Gdx.app.log(String.valueOf(geeseList.get(x).xPos), "xpos before");
            geeseList.get(x).xPos=geeseList.get(x).xPos + geeseList.get(x).speed;
            geeseList.get(x).getImage().setX(geeseList.get(x).xPos);
            if(geeseList.get(x).xPos>viewwidth/1.2){
                Gdx.app.log(geeseList.get(x).toString(), "won");
                minigamerun = false;
            }
            //Gdx.app.log(String.valueOf(geeseList.get(x).xPos), "xpos after");

        }
    }
    @Override
    public void dispose(){

    }

    @Override
    public void resize(int width, int height){
        stage.getViewport().update(width, height, true);
    }
}
