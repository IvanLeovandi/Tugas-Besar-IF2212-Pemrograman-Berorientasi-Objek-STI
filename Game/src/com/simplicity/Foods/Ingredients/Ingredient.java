package com.simplicity.Foods.Ingredients;

import com.simplicity.Foods.Food;
import com.simplicity.Interfaces.Purchasable;

public abstract class Ingredient extends Food implements Purchasable {
    private int price;

    public Ingredient(String name, int price, int satietyPoint) {
        super(name, satietyPoint);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
