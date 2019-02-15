package com.rear_admirals.york_pirates;

import java.util.ArrayList;

public class College {

	private final String name;
	private ArrayList<College> ally;
    private boolean bossDead;

    public College(String name) {
        this.name = name;
        this.ally = new ArrayList<>();
        this.ally.add(this);
        this.bossDead = false;
    }

    public String getName() { return name; }

    public ArrayList<College> getAlly() { return ally; }
    public void addAlly(College newAlly){
        ally.add(newAlly);
    }

    public boolean isBossDead() { return bossDead; }
    public void setBossDead(boolean bossDead) {
        this.bossDead = bossDead;
    }

	public static College Derwent = new College("Derwent");
    public static College Vanbrugh = new College("Vanbrugh");
    public static College James = new College("James");

    //[NEW ASSESSMENT 3] Two new colleges added
    public static College Goodricke = new College("Goodricke");
    public static College Langwith = new College("Langwith");
    public static College Sea = new College ("Sea");
}
