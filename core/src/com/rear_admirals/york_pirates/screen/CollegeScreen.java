package com.rear_admirals.york_pirates.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.rear_admirals.york_pirates.BuffConstant;
import com.rear_admirals.york_pirates.College;
import com.rear_admirals.york_pirates.PirateGame;
import com.rear_admirals.york_pirates.Player;
import com.rear_admirals.york_pirates.base.BaseScreen;

public class CollegeScreen extends BaseScreen {
    private Player player;
    private Label pointsLabel;
    private Label goldLabel;
    private int toHeal;
    private int attackBuffTurn;
    private int accuracyBuffTurn;
    private BuffConstant constant = new BuffConstant();

    public CollegeScreen(PirateGame main, College college){
        super(main);
        this.player = main.getPlayer();

        Table uiTable = new Table();

        Label pointsTextLabel = new Label("Points: ", main.getSkin());
        pointsLabel = new Label(Integer.toString(main.getPlayer().getPoints()), main.getSkin());
        pointsLabel.setAlignment(Align.left);

        Label goldTextLabel = new Label("Gold:", main.getSkin());
        goldLabel = new Label(Integer.toString(main.getPlayer().getGold()), main.getSkin());
        goldLabel.setAlignment(Align.left);

        uiTable.add(pointsTextLabel);
        uiTable.add(pointsLabel).width(pointsTextLabel.getWidth());
        uiTable.row();
        uiTable.add(goldTextLabel).fill();
        uiTable.add(goldLabel).fill();

        uiTable.align(Align.topRight);
        uiTable.setFillParent(true);

        uiStage.addActor(uiTable);

        Table optionsTable = new Table();
        optionsTable.setFillParent(true);
        Label title = new Label("Welcome to " + college.getName(), main.getSkin(), "title");
        final Label message = new Label("", main.getSkin());
        optionsTable.add(title);
        optionsTable.row();


        toHeal = player.getPlayerShip().getHealthMax() - player.getPlayerShip().getHealth();
        final TextButton heal = new TextButton("Repair Ship for "+ Integer.toString(toHeal/10) +" gold", main.getSkin());

        if (toHeal == 0) { heal.setText("Your ship is already fully repaired!"); }

        heal.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (toHeal==0){
                    heal.setText("Your ship is already fully repaired!");
                }
                else {
                    if (player.payGold(toHeal / 10)) {
                        System.out.println("charged");
                        player.getPlayerShip().setHealth(player.getPlayerShip().getHealthMax());
                        message.setText("Successful repair");
                    } else {
                        message.setText("You don't have the funds to repair your ship");
                    }
                }
            }
        });

        final TextButton attackBuff = new TextButton("Add 10 attack for 2 battles" + Integer.toString(constant.ATTACK_BUFF_PRICE)+ "gold",main.getSkin());
        attackBuffTurn = player.getAttackBuffTurns();
        if(attackBuffTurn > 0){attackBuff.setText("Already have this buff");}
        attackBuff.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (player.isAttackBuffed) {
                    attackBuff.setText("Already have this buff");
                } else {
                    if(player.payGold(constant.ATTACK_BUFF_PRICE) && !player.isAttackBuffed){
                        System.out.println("charged");
                        player.setAttackBuffTurns(constant.ARRACK_BUFF_TURNS);
                        player.setBuff(constant.ATTACK_BUFF_TAG);
                        System.out.println("player attack buff turns:" + (player.getAttackBuffTurns()));
                        System.out.println("player attack" + player.getPlayerShip().getAttack());


                    }else{
                        message.setText("You don't have enough money");
                    }
                }
            }
        });

        final TextButton accuracyButton = new TextButton("Add 3 accuracy for 2 battles" + Integer.toString(constant.ACCURACY_BUFF_PRICE) + "gold", main.getSkin());
        accuracyBuffTurn = player.getAccuracyBuffTurns();
        if(accuracyBuffTurn > 0){accuracyButton.setText("Already have this buff");}
        accuracyButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(player.isAccuracyBuffed){
                    accuracyButton.setText("Already have this buff");
                }else{
                    if(player.payGold(constant.ACCURACY_BUFF_PRICE) && !player.isAccuracyBuffed){
                        System.out.println("charged");
                        player.setBuff(constant.ACCURACY_BUFF_TAG);
                        player.setAccuracyBuffTurns(constant.ACCURACY_BUFF_TURN);
                        System.out.println("player accuracy buff turns:" + (player.getAccuracyBuffTurns()));
                        System.out.println("player accuracy" + player.getPlayerShip().getAccuracy());

                    }else{
                        message.setText("You don't have enough money");
                    }
                }
            }
        });





        optionsTable.add(heal);


        optionsTable.row();
        optionsTable.add(message);
        optionsTable.row();
        optionsTable.add(attackBuff);
        optionsTable.row();
        optionsTable.add(accuracyButton);


        mainStage.addActor(optionsTable);
        Gdx.input.setInputProcessor(mainStage);
    }

    @Override
    public void update(float delta){
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            System.out.println("ESCAPE");
            pirateGame.setScreen(pirateGame.getSailingScene());
            dispose();
        }

        toHeal = player.getPlayerShip().getHealthMax() - player.getPlayerShip().getHealth();
        goldLabel.setText(Integer.toString(pirateGame.getPlayer().getGold()));
        pointsLabel.setText(Integer.toString(pirateGame.getPlayer().getPoints()));
    }
}
