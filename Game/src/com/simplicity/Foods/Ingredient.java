package com.simplicity.Foods;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().isAssignableFrom(o.getClass())) {
            return false;
        }
        return getType() == ((Ingredient) o).getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), Ingredient.class);
    }
}