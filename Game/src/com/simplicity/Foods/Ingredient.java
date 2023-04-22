package com.simplicity.Foods;

import com.simplicity.Interfaces.Purchasable;

public class Ingredient extends Food implements Purchasable {
    private int price;
    private boolean canBePurchased = true;

    public Ingredient(String type, int price, int satietyPoint) {
        super(type, satietyPoint);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean getCanBePurchased() {
        return canBePurchased;
    }

    public void setCanBePurchased(boolean canBePurchased) {
        this.canBePurchased = canBePurchased;
    }
}
