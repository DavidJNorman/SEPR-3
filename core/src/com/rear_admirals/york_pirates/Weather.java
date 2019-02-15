package com.rear_admirals.york_pirates;

import java.util.Random;
//[NEW ASSESSMENT 3] All this code was written within assessment 3 for a weather system.
public class Weather {
    private PirateGame main;
    private boolean weatherUpdateNeeded;
    private boolean weatherRegionUpdateNeeded;
    private float weatherUpdateTimer = 0;
    private float effectTimer = 0;
    private float regionUPdateTimer = 0;
    private int weatherState;
    private int region;
    private static final String STORMING = "Storming";
    private static final String NORMAL = "Normal";

    public Weather(PirateGame main){
        this.main = main;
        this.weatherUpdateNeeded = true;
        this.weatherRegionUpdateNeeded = true;
    }

//handling the effect of normal weather on player(currently no effect, debug code used only)
    private void normal(float delta){
        effectTimer += delta;
        if (effectTimer > 5){
            System.out.println("normal");
            System.out.println(main.getPlayer().getPlayerShip().getHealth());
            effectTimer -= 5 ;
        }
    }

    //function handling the storming effect on player
    private void storming(float delta){
        effectTimer += delta;
        if(effectTimer > 1){
            main.getPlayer().getPlayerShip().damage(1);
            main.getPlayer().setPoints(main.getPlayer().getPoints()+2);
            System.out.println("storming");
            System.out.println(main.getPlayer().getPlayerShip().getHealth());
            effectTimer --;
        }
    }
    //update the weather within the affected region every 10s, selecting 2 different weather by using random number
    public void ifUpdate(float delta){

        Random random = new Random();
        if(!weatherUpdateNeeded){
            //update weather type counter start
            weatherUpdateTimer += delta;
            if(weatherUpdateTimer > 10){
                weatherUpdateNeeded = true;

                weatherUpdateTimer -= 10;
            }
        }

        if(weatherUpdateNeeded){
            weatherState = random.nextInt(2);

            System.out.println(weatherState);
            weatherUpdateNeeded = false;
        }

        if(weatherState == 0){
            normal(delta);
        }else if(weatherState == 1){
            storming(delta);
        }

    }
    //update the region(from 1 to 7) through random number every 30s
    public void updateWeatherRegion(float delta){
        Random random= new Random();
        if(!weatherRegionUpdateNeeded){
            //update region counter start
            regionUPdateTimer += delta;
            if(regionUPdateTimer > 30){
                weatherRegionUpdateNeeded = true;
                regionUPdateTimer -= 30;
            }
        }
        if(weatherRegionUpdateNeeded){

            region = 1 + random.nextInt(8);
            weatherRegionUpdateNeeded = false;
        }


    }
    //get method for ui display in game
    public int getRegion() {
        return region;
    }

    public String getWeather(){
        if(weatherState == 0){
            return NORMAL;
        }else if(weatherState == 1){
            return STORMING;
        }else return "unknown weather";

    }
}
