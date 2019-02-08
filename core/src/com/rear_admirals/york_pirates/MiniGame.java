package com.rear_admirals.york_pirates;

import java.util.ArrayList;
import java.util.Random;

public class MiniGame {
    private ArrayList<Goose> Geese;
    public MiniGame() {
        Geese = new ArrayList<Goose>();
        for (int i = 0; i < 4; i++) {
            Geese.add(new Goose("Goose" + Integer.toString(i)));
        }
    }

    public class Goose {
        private int speed;
        private String name;
        Random rand;

        public Goose(String name) {
            this.name = name;
            speed = rand.nextInt(11) + 20;
        }

        public int getSpeed() {
            return this.speed;
        }
    }

    public String GetWinner() {
        String winner = Geese.get(0).name;
        for (int i = 1; i < 4; i++) {
            if (Geese.get(i).speed > Geese.get(i - 1).speed) {
                winner = Geese.get(i).name;
            }
        }
        return winner;
    }

    public int BetGold(int bet, String chosenGoose) {
        if (chosenGoose == GetWinner()) {
            return bet * 2;
        } else {
            return 0;
        }
    }
}
