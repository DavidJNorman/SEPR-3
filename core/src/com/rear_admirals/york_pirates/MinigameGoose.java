package com.rear_admirals.york_pirates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
//[NEW ASSESSMENT 3] All this code was written within assessment 3 for a minigame.
public class MinigameGoose {
    public int speed;
    private Texture gooseTexture;
    private Image gooseImage;
    public int xPos;
    public int yPos;
    public int id;

    public MinigameGoose(int speed, int xPos, int yPos, int id) {
        this.id = id;
        this.speed = speed;
        this.xPos = xPos;
        this.yPos = yPos;
        gooseTexture = new Texture(Gdx.files.internal("PH_GooseMini.png"));
        gooseImage = new Image(gooseTexture);
        gooseImage.setBounds(xPos, yPos, 320, 180);
    }

    public String toString() {
        return String.valueOf(speed);
    }

    public Image getImage() {
        return this.gooseImage;
    }
}