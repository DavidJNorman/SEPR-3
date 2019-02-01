package com.rear_admirals.york_pirates.screen;

import com.badlogic.gdx.graphics.Texture;

public class MinigameGeese {
    private int speed;
    private Texture texture;

    public MinigameGeese(int speed){
        this.speed = speed;
    }
public String toString(){
        return String.valueOf(speed);
}
}
