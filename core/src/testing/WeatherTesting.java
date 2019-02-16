package testing;

import com.rear_admirals.york_pirates.PirateGame;
import com.rear_admirals.york_pirates.Player;
import com.rear_admirals.york_pirates.Ship;
import com.rear_admirals.york_pirates.Weather;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class WeatherTesting {

    @Test
    void StormingWeather(){
        PirateGame myPirGame = new PirateGame();
        myPirGame.setPlayer(new Player());
        Player myPlayer = myPirGame.getPlayer();
        Weather myWeather = new Weather(myPirGame);
        int pointsBefore = myPlayer.getPoints();
        int HealthBefore = myPlayer.getPlayerShip().getHealth();

        myWeather.storming(20);

        int pointsAfter = myPlayer.getPoints();
        int HealthAfter = myPlayer.getPlayerShip().getHealth();
        assertNotEquals(HealthBefore, HealthAfter, "Test health changes after storming.");
        assertNotEquals(pointsBefore, pointsAfter, "Test points increase after storming.");
    }

    @Test
    public void updateWeatherRegionTest(){
        PirateGame myPirGame = new PirateGame();
        myPirGame.setPlayer(new Player());
        Weather myWeather = new Weather(myPirGame);

        myWeather.updateWeatherRegion(20f);
        int regionBefore = myWeather.getRegion();
        myWeather.updateWeatherRegion(20f);
        int regionAfter = myWeather.getRegion();

        System.out.println(regionBefore);
        System.out.println(regionAfter);
        assertNotEquals(regionBefore, regionAfter, "Region has not changed");
    }

    @Test
    public void ifUpdateTest(){
        PirateGame myPirGame = new PirateGame();
        myPirGame.setPlayer(new Player());
        Weather myWeather = new Weather(myPirGame);

        myWeather.ifUpdate(20);
        int stateBefore = myWeather.getWeatherState();
        myWeather.ifUpdate(20);
        int stateAfter = myWeather.getWeatherState();
        //System.out.println(stateBefore);
        //System.out.println(stateAfter);
        assertNotEquals(stateBefore, stateAfter, "Weather State");
    }
}
