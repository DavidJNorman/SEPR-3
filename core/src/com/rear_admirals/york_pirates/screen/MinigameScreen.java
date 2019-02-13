package com.rear_admirals.york_pirates.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.rear_admirals.york_pirates.MiniGame;
import com.rear_admirals.york_pirates.PirateGame;
import com.rear_admirals.york_pirates.Player;
import com.rear_admirals.york_pirates.base.BaseScreen;

import javax.xml.soap.Text;
import java.util.Random;

public class MinigameScreen extends BaseScreen {
    private Player player;
    Texture MinigameBack;
    private MiniGame miniGame;
    private Boolean minigamerun = false;
    private Boolean inProgress = false;
    private Table goldTable;
    private Label goldAmount;
    private Label goldString;
    private Stage stage;
    private int betOn=6;
    private int lastWon = 6;
    private SpriteBatch batch;

    public MinigameScreen(PirateGame main){
        super(main);
        miniGame = new MiniGame();
        this.player = main.getPlayer();
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show(){
        stage.clear();
        Gdx.input.setInputProcessor(stage);
        MinigameBack = new Texture(Gdx.files.internal("MinigameBackground.jpg"));
        goldTable = new Table();
        goldTable.setFillParent(true);
        stage.addActor(goldTable);
        batch = new SpriteBatch();

        goldAmount = new Label("You have:" + String.valueOf(player.getGold()) +"G", pirateGame.getSkin(),"title");
        goldTable.add(goldAmount);


        final TextButton textButton = new TextButton("Pay 100G to play?", pirateGame.getSkin());
        textButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(player.getGold()>= 100) {
                    player.setGold(player.getGold() - 100);
                    bet();
                }else{
                    textButton.setText("You don't have enough gold to play");
                }
            }
        });
        goldTable.row();
        goldTable.add(textButton);


        final TextButton quitButton = new TextButton("Quit", pirateGame.getSkin());
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pirateGame.setScreen(pirateGame.getSailingScene());
            }
        });
        goldTable.row();
        goldTable.add(quitButton);


        for (int y = 0; y<5; y=y+1){
            Gdx.app.log(miniGame.Geese.get(y).toString(), "words");
        }
    }


    public void bet(){
        stage.clear();
         Table betTable = new Table();
         betTable.setFillParent(true);
         TextButton firstBet = new TextButton("Bet 100G on Goose 1", pirateGame.getSkin());
         firstBet.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                minigamerun = true;
                betOn = 4;
            }
        });
        TextButton secondBet = new TextButton("Bet 100G on Goose 2", pirateGame.getSkin());
        secondBet.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                minigamerun = true;
                betOn = 3;
            }
        });
        TextButton thirdBet = new TextButton("Bet 100G on Goose 3", pirateGame.getSkin());
        thirdBet.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                minigamerun = true;
                betOn = 2;
            }
        });
        TextButton fourthBet = new TextButton("Bet 100G on Goose 4", pirateGame.getSkin());
         fourthBet.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                minigamerun = true;
                betOn = 1;
            }
        });
        TextButton fifthBet = new TextButton("Bet 100G on Goose 5", pirateGame.getSkin());
         fifthBet.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                minigamerun = true;
                betOn = 0;
            }
        });
         betTable.add(firstBet);
         betTable.add(secondBet);
         betTable.add(thirdBet);
         betTable.add(fourthBet);
         betTable.add(fifthBet);
         stage.addActor(betTable);

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
            batch.begin();
            batch.draw(MinigameBack,0,0);
            batch.end();
            minigame();
        }


        stage.draw();
        stage.act();

    }

    public void minigameSetup(){
        stage.clear();
            inProgress = true;
            for (int x = 0; x<5; x=x+1){
                stage.addActor(miniGame.Geese.get(x).getImage());

            }

    }

    public void minigame(){

        for (int x = 0; x<5; x=x+1){
            //Gdx.app.log(String.valueOf(geeseList.get(x).xPos), "xpos before");
            miniGame.Geese.get(x).xPos += miniGame.Geese.get(x).speed;
            miniGame.Geese.get(x).getImage().setX(miniGame.Geese.get(x).xPos);
            if(miniGame.Geese.get(x).xPos>viewwidth/1.2){
                Gdx.app.log(String.valueOf(miniGame.Geese.get(x).id), "won");
                minigamerun = false;
                lastWon = miniGame.Geese.get(x).id;
                if (lastWon==betOn){
                    player.setGold(player.getGold()+200);
                    Gdx.app.log(String.valueOf(player.getGold()), "post win gold");
                }
                pirateGame.setScreen(new MinigameScreen(pirateGame));
            }
        }
    }
    @Override
    public void dispose(){
        stage.dispose();
    }

    @Override
    public void resize(int width, int height){
        stage.getViewport().update(width, height, true);
    }
}
