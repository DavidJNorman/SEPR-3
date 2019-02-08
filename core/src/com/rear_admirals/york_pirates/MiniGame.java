package com.rear_admirals.york_pirates;

import java.util.ArrayList;
import java.util.Random;

public class MiniGame {
    public ArrayList<MinigameGoose> Geese;
    public MiniGame() {
        Random random = new Random();
        Geese = new ArrayList<MinigameGoose>();
        for (int i = 0; i < 5; i++) {
            Geese.add(new MinigameGoose(random.nextInt(10)+1, 30, (i+1)*110, "Goose" + Integer.toString(i)));
        }
    }

    public String GetWinner() {
        String winner = Geese.get(0).name;
        for (int i = 1; i < 5; i++) {
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
