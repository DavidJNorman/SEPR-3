package com.rear_admirals.york_pirates;

import com.badlogic.gdx.graphics.Texture;

public class ShipType {
	private int attack;
	private int defence;
	private int accuracy;
	private String name;
	private String SubType;
	private Texture texture;

	public ShipType (String name, String SubType, int attack, int defence, int accuracy) {
		this.name = name;
		this.SubType = SubType;
		this.attack = attack;
		this.defence = defence;
		this.accuracy = accuracy;
		this.texture = new Texture("ship4.png"); //TESTING (without assets created)
	} // There is currently no way to give ships a custom texture. Do we need this?

	public String getName() { return name; }

	public String getSubType() { return SubType; }

	public int getAttack() { return attack; }

	public int getDefence() { return defence; }

	public int getAccuracy() { return accuracy; }

	public Texture getTexture() { return texture; }

	// Static Ship Types go here
//	public static ShipType Sloop = new ShipType("Sloop", 4, 4, 7, 80);
	public static ShipType Brig = new ShipType("Brig", "Brig" ,5, 5, 5);
//	public static ShipType Galleon = new ShipType("Galleon", 6, 6, 3, 120);

	public static ShipType DerwentCollege = new ShipType("Derwent", "Boss", 99, 1, 5);


	public static ShipType VanbrughCollege = new ShipType("Vanbrugh", "Boss", 8, 1, 5);
	public static ShipType JamesCollege = new ShipType("James", "Boss", 6, 1, 5);
	public static ShipType LangwithCollege = new ShipType("Langwith", "Boss", 10, 1, 5);
	public static ShipType GoodrickeCollege = new ShipType("Goodricke", "Boss", 12, 1, 5);

}
