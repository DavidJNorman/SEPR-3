package com.rear_admirals.york_pirates.screen;

import com.badlogic.gdx.graphics.Texture;

public class MinigameGeese {
    private int speed;
    private Texture texture;
    private int xPos;
    private int yPos;

    public MinigameGeese(int speed, int xPos, int yPos){
        this.speed = speed;
        this.xPos = xPos;
        this.yPos = yPos;
    }
public String toString(){
        return String.valueOf(speed);
}
}
