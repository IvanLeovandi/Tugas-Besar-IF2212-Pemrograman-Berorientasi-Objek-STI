package com.simplicity.Foods.Ingredients;

import com.simplicity.Foods.Food;
import com.simplicity.Interfaces.Purchasable;

public abstract class Ingredient extends Food implements Purchasable {
    private String name;
    private int price;

    public Ingredient(String type, int price, int satietyPoint) {
        super(type, satietyPoint);
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
