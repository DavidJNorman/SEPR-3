package testing;

import com.rear_admirals.york_pirates.PirateGame;
import com.rear_admirals.york_pirates.Player;
import com.rear_admirals.york_pirates.screen.MinigameScreen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinigameTest{

    @Test
    public void MinigameWinTest(){

        PirateGame myPirGame = new PirateGame();
        myPirGame.setPlayer(new Player());
        MinigameScreen myMinigame = new MinigameScreen(myPirGame);
        int BetOn = myMinigame.betOn;
        System.out.println(myPirGame.getPlayer().getGold());
        myMinigame.minigameTesting();
        int Winner = myMinigame.lastWon;
        if(Winner == BetOn){
            assertEquals(300, myPirGame.getPlayer().getGold());
        } else{
            assertEquals(100, myPirGame.getPlayer().getGold());
        }
        System.out.println(myPirGame.getPlayer().getGold());
    }
}
