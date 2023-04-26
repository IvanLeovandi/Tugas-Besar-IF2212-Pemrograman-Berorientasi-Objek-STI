package com.simplicity.Foods;

import com.simplicity.Interfaces.Edible;
import com.simplicity.Interfaces.Purchasable;

public enum Ingredient implements Purchasable, Edible {
    RICE("Nasi", 5, 5),
    POTATO("Kentang", 3, 4),
    CHICKEN("Ayam", 10, 8),
    BEEF("Sapi", 12, 15),
    CARROT("Wortel", 3, 2),
    SPINACH("Bayam", 3, 2),
    PEANUT("Kacang", 2, 2),
    MILK("Susu", 2, 1);

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
