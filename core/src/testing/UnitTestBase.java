package testing;

import com.rear_admirals.york_pirates.Ship;
import com.rear_admirals.york_pirates.ShipType;
import org.junit.jupiter.api.Test;

import static com.rear_admirals.york_pirates.College.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTestBase { //TODO Use this as a test suite to run other tests

    @Test
    public void testInit(){
        //PirateGame myPirGame = new PirateGame();
        Ship PlayerShip = InitPlayerShip();
        assertEquals("Derwent", PlayerShip.getName(), "Enemy not loaded in correctly");
        Ship SeaMonster = InitSeaMonster();
        assertEquals("Vanbrugh", SeaMonster.getName(), "Enemy not loaded in correctly");
    }

    static Ship InitPlayerShip() {
        ShipType playerShip = ShipType.getDefaultCollege();
        Ship myShip = new Ship(playerShip, Derwent);
        System.out.println("Player College name is: " + myShip.getName() + "\n");
        return myShip;
    }

    static Ship InitSeaMonster() {
        ShipType mySeaMonster = ShipType.getEnemy();
        Ship myShip = new Ship(mySeaMonster, Sea);
        System.out.println("Enemy College name is: " + myShip.getName() + "\n");
        return myShip;
    }
}
