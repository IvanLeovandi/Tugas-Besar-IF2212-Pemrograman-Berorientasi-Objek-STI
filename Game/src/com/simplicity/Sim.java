package com.simplicity;

import java.util.*;

import com.simplicity.Actions.Action;
import com.simplicity.Actions.Work;
import com.simplicity.Foods.CookedFood.CookedFood;
import com.simplicity.Foods.Ingredients.Ingredient;
import com.simplicity.Interfaces.Edible;
import com.simplicity.Interfaces.Placeable;
import com.simplicity.Interfaces.Purchasable;

public class Sim {
    private String name;
    private String job;
    private int balance;
    private Inventory inventory;
    private int hunger;
    private int mood;
    private int health;
    private int status;
    private Action currentAction;

    private void act(Action action) {
        this.currentAction = action;
    };

    public void work(int durMilli) {
        act(new Work());
    }

    public void workout(int durMilli) {

    }

    public void sleep(int durMilli) {

    }

    public void eat(Edible food) {

    }

    public CookedFood cook(List<Ingredient> Ingredients) {
        return new CookedFood();
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

    public void place(Placeable o) {

    }

    public void viewTime() {

    }
}
