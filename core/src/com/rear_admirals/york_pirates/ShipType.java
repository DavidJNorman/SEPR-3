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
		//this.texture = new Texture("ship4.png"); //TODO: Uncomment, Commented out for testing purposes.
	} // There is currently no way to give ships a custom texture. Do we need this?

	public String getName() { return name; }

	int getAttack() { return attack; }

	int getDefence() { return defence; }

	int getAccuracy() { return accuracy; }

	//public Texture getTexture() { return texture; } //[NEW Assessment 3] Commented out as it is never used



	// Static Ship Types go here
//	public static ShipType Sloop = new ShipType("Sloop", 4, 4, 7, 80);
	public static ShipType Brig = new ShipType("Brig", "Brig" ,5, 5, 5);
//	public static ShipType Galleon = new ShipType("Galleon", 6, 6, 3, 120);


//ASSESSMENT 3 CONTENT:
	//[NEW Assessment 3] These are the 5 colleges and the Lake Monster which are static, used only for passing into battle correctly,
		//the SubType is used to declare what type of enemy, in this case Boss or monster, we face
		//The stats are passed in through the ShipType constructor, these have changeable statistics, health relying on defence * 20
	public static ShipType DerwentCollege = new ShipType("Derwent", "Boss", 1, 1, 1);
	public static ShipType VanbrughCollege = new ShipType("Vanbrugh", "Boss", 5, 10, 5);
	public static ShipType JamesCollege = new ShipType("James", "Boss", 6, 12, 5);
	public static ShipType LangwithCollege = new ShipType("Langwith", "Boss", 7, 18, 5);
	public static ShipType GoodrickeCollege = new ShipType("Goodricke", "Boss", 6, 15, 5);
	public static ShipType SeaMonster = new ShipType("Monster", "Monster",6,10,5);
	//

	//[NEW Assessment 3] This getter was added so we could have a switching function within combat screen and use name to switch, we have SubType for the text that appears when you enter battle.
	public String getSubType() { return SubType; }


	//[NEW Assessment 3] This is a newly added method for the sole purpose of testing.
	public static ShipType getDefaultCollege(){ return Brig; }

	//[NEW Assessment 3] This is a newly added method for the sole purpose of testing.
	public static ShipType getEnemy(){ return SeaMonster; }
	public static ShipType getEnemyShip(){ return VanbrughCollege; }
}
