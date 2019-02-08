package com.rear_admirals.york_pirates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MinigameGoose {
    public int speed;
    private Texture gooseTexture;
    private Image gooseImage;
    public int xPos;
    public int yPos;
    public String name;

    public MinigameGoose(int speed, int xPos, int yPos, String name) {
        this.name = name;
        this.speed = speed;
        this.xPos = xPos;
        this.yPos = yPos;
        gooseTexture = new Texture(Gdx.files.internal("PH_GooseMini.png"));
        gooseImage = new Image(gooseTexture);
        gooseImage.setBounds(xPos, yPos, 150, 150);
    }

    public String toString() {
        return String.valueOf(speed);
    }

    public Image getImage() {
        return this.gooseImage;
    }
}