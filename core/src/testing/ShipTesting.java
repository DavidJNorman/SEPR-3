package testing;

import com.rear_admirals.york_pirates.Ship;
import com.rear_admirals.york_pirates.screen.combat.attacks.Attack;
import com.rear_admirals.york_pirates.screen.combat.attacks.Flee;
import org.junit.jupiter.api.Test;

import static com.rear_admirals.york_pirates.screen.combat.attacks.Attack.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShipTesting {

    //SHIP TEST
    @Test
    public void AttackTest() {
        Ship myPlayer = UnitTestBase.InitPlayerShip(); //Initialises a player's ship with Derwent shiptype like in the game.
        Ship myEnemy = UnitTestBase.InitSeaMonster(); //Initialises an enemy ship with Vanbrugh shiptype.
        myPlayer.setAccuracy(100);
        Attack TestPlayerAttack = new Attack();
        TestPlayerAttack.setAccPercent(100);
        TestPlayerAttack.doAttack(myPlayer, myEnemy);

        myEnemy.setAccuracy(100);
        TestPlayerAttack.doAttack(myEnemy, myPlayer);
        System.out.println("Max health of enemy: " + myEnemy.getHealthMax() + "\n" + "Current Health of enemy: " + myEnemy.getHealth());
        System.out.println("Max health of Player: " + myPlayer.getHealthMax() + "\n" + "Current Health of Player: " + myPlayer.getHealth());
        assertNotEquals(myEnemy.getHealthMax(), myEnemy.getHealth(), "Health should be different");
        assertNotEquals(myPlayer.getHealthMax(), myPlayer.getHealth(), "Health should be different");
    }


    @Test
    public void FleeTest() {
        Ship myPlayer = UnitTestBase.InitPlayerShip(); //Initialises a player's ship with Derwent shiptype like in the game.
        Ship myEnemy = UnitTestBase.InitSeaMonster(); //Initialises an enemy ship with Vanbrugh shiptype.
        Flee TestPlayerFlee = new Flee();
        boolean changeFlag = false;
        int counter = 0;
        while (!(changeFlag)) {
            if (counter > 100) {
                break;
            }
            int returned = TestPlayerFlee.doAttack(myPlayer, myEnemy);
            if (returned == 1) {
                changeFlag = true;
            }
            counter++;

        }
        assertEquals(true, changeFlag, "In 100 cycles, flee did not work.");
    }

    @Test
    public void BiteTest() {
        Template("Bite");
    }

    @Test
    public void TailWhipTest() {
        Template("TailWhip");
    }

    @Test
    public void SlamTest() {
        Template("Slam");
    }

    public Attack TestAttack(String name) {
        switch (name) { //TODO: Can be tested
            case "Bite":
                return attackBite;
            case "TailWhip":
                return attackTail;
            case "Slam":
                return attackSlam;
        }
        return new Attack();
    }

    public void Template(String name) {
        Ship myPlayer = UnitTestBase.InitPlayerShip(); //Initialises a player's ship with Derwent shiptype like in the game.
        Ship myEnemy = UnitTestBase.InitSeaMonster(); //Initialises an enemy ship with Vanbrugh shiptype.
        Attack TestTemplate = TestAttack(name);
        TestTemplate.setAccPercent(100);
        myPlayer.setAccuracy(100);
        TestTemplate.doAttack(myEnemy, myPlayer);
        assertNotEquals(myPlayer.getHealthMax(), myPlayer.getHealth(), "Test enemy health lowered");
    }
}

