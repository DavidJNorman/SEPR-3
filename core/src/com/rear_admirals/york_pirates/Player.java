package com.rear_admirals.york_pirates;

import com.rear_admirals.york_pirates.screen.combat.attacks.*;
import com.rear_admirals.york_pirates.screen.combat.attacks.GrapeShot;

import java.util.ArrayList;
import java.util.List;

import static com.rear_admirals.york_pirates.College.*;
import static com.rear_admirals.york_pirates.ShipType.*;

public class Player {
    private Ship playerShip;
    private int gold;
    private int points;
    public static List<Attack> attacks = new ArrayList<Attack>();
    private int attackBuffTurns;
    private int accuracyBuffTurns;
    public boolean isAttackBuffed;
    public boolean isAccuracyBuffed;
    private BuffConstant constant = new BuffConstant();

    public Player() {
	    this.playerShip = new Ship(Brig, "Your Ship", Derwent);
        this.gold = 100;
        this.points = 0;
        attacks.add(Ram.attackRam);
        attacks.add(GrapeShot.attackSwivel);
        attacks.add(Attack.attackBoard);
    }

    public Player(Ship ship) {
        this.playerShip = ship;
        this.gold = 0;
        this.points = 0;

        attacks.add(Ram.attackRam);
        attacks.add(Attack.attackSwivel);
        attacks.add(Attack.attackBoard);
    }

    public Ship getPlayerShip() { return this.playerShip; }

    public int getPoints() { return points; }

	public int getGold() { return gold; }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setGold(int gold) { this.gold = gold; }

	public boolean payGold(int amount){
        if (amount > gold){
            return false;
        }
        else{
            addGold(-amount);
            return true;
        }
    }

    public void addPoints(int amount) { points += amount; }

    public void addGold(int amount) { gold = gold + amount; }

    public int getAttackBuffTurns() {
        return attackBuffTurns;
    }

    public int getAccuracyBuffTurns() {
        return accuracyBuffTurns;
    }

    public void setAttackBuffTurns(int turns){
        this.attackBuffTurns = turns;
    }

    public void setAccuracyBuffTurns(int accuracyBuffTurns) {
        this.accuracyBuffTurns = accuracyBuffTurns;
    }

    public void setBuff(String category){
        switch(category){
           case "attack":
               this.playerShip.setAttack(constant.ATTACK_BUFF+playerShip.getAttack());
               this.isAttackBuffed = true;
               break;

           case "accuracy":
               this.playerShip.setAccuracy(constant.ACCURACY_BUFF + playerShip.getAccuracy());
               this.isAccuracyBuffed = true;
               break;
        }

    }
    public void removeBuff(String category){
        switch(category){
            case "attack":
                this.playerShip.setAttack(playerShip.getAttack() - constant.ATTACK_BUFF);
                this.isAttackBuffed = false;
                break;

            case "accuracy":
                this.playerShip.setAccuracy(playerShip.getAccuracy() - constant.ACCURACY_BUFF);
                this.isAccuracyBuffed = false;
                break;
        }

    }
}