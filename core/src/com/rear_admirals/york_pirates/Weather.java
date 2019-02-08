package com.rear_admirals.york_pirates;

import java.util.Random;

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




    private void normal(float delta){
        effectTimer += delta;
        if (effectTimer > 5){
            System.out.println("normal");
            System.out.println(main.getPlayer().getPlayerShip().getHealth());
            effectTimer -= 5 ;
        }
    }

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

    public void ifUpdate(float delta){

        Random random = new Random();
        if(!weatherUpdateNeeded){
            weatherUpdateTimer += delta;
            if(weatherUpdateTimer > 10){
                weatherUpdateNeeded = true;

                weatherUpdateTimer -= 10;
            }
        }

        if(weatherUpdateNeeded){
            weatherState = 0 + random.nextInt(2);

            System.out.println(weatherState);
            weatherUpdateNeeded = false;
        }

        if(weatherState == 0){
            normal(delta);
        }else if(weatherState == 1){
            storming(delta);
        }

    }

    public void updateWeatherRegion(float delta){
        Random random= new Random();
        if(!weatherRegionUpdateNeeded){
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
