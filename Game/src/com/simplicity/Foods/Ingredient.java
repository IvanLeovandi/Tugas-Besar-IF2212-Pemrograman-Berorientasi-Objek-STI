package com.simplicity.Foods;

import com.simplicity.Interfaces.Edible;
import com.simplicity.Interfaces.Purchasable;

public enum Ingredient implements Purchasable, Edible {
    RICE("Rice", 5, 5),
    POTATO("Potato", 3, 4),
    CHICKEN("Chicken", 10, 8),
    BEEF("Beef", 12, 15),
    CARROT("Carrot", 3, 2),
    SPINACH("Spinach", 3, 2),
    PEANUT("Peanut", 2, 2),
    MILK("Milk", 2, 1);

    private final String type = "Ingredient";
    private final String name;
    private int price;
    private final int satietyPoint;
    private boolean canBePurchased = false;

    Ingredient(String name, int price, int satietyPoint) {
        this.name=name;
        this.price = price;
        this.satietyPoint = satietyPoint;
    }

    public String getType(){
        return type;
    }
    public String getName(){
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getSatietyPoint(){
        return satietyPoint;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public boolean getCanBePurchased(){
        return canBePurchased;
    }
    
    public void setCanBePurchased(boolean canBePurchased){
        this.canBePurchased=canBePurchased;
    }
}
