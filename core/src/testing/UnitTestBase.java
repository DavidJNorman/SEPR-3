package testing;

import com.badlogic.gdx.Gdx;
import com.rear_admirals.york_pirates.PirateGame;
import com.rear_admirals.york_pirates.Ship;
import com.rear_admirals.york_pirates.ShipType;
import com.rear_admirals.york_pirates.screen.combat.CombatScreen;
import org.junit.jupiter.api.Test;

import static com.rear_admirals.york_pirates.College.Vanbrugh;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTestBase {

    @Test
    public void testInit(){
        PirateGame myPirGame = new PirateGame();
        ShipType vanShip = ShipType.getCollege();
        Ship myShip = new Ship(vanShip, Vanbrugh);
        System.out.println("Name is: " + myShip.getName() + "\n");
        System.out.println(myShip.getName());
        CombatScreen Comb = new CombatScreen(myPirGame, myShip);
        assertEquals("Vanbrugh", ShipType.VanbrughCollege.getName(), "Enemy not loaded in correctly");
    }







}
