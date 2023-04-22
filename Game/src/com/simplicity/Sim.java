package com.simplicity;

import com.simplicity.Interfaces.Edible;
import com.simplicity.Interfaces.Purchasable;
import com.simplicity.Interfaces.WorldObject;

public class Sim {
    private String name;
    private String job;
    private int balance;
    private int hunger;
    private int mood;
    private int health;
    private int status;

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public int getBalance() {
        return balance;
    }

    public int getHunger() {
        return hunger;
    }

    public int getMood() {
        return mood;
    }

    public int getHealth() {
        return health;
    }

    public int getStatus() {
        return status;
    }

    public void work(int durMilli) {
    }

    public void workout(int durMilli) {

    }

    public void sleep(int durMilli) {

    }

    public void eat(Edible food) {

    }

    public void visit() {

    }

    public void defecate() {

    }

    public void upgradeHouse(House house) {

    }

    public void buy(Purchasable item) {

    }

    public void move() {

    }

    public void openInventory() {

    }

    public void place(WorldObject o) {

    }

    public void viewTime() {

    }
}
