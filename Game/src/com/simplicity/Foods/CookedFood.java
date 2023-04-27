package com.simplicity.Foods;

import java.util.*;

import com.simplicity.Interfaces.Edible;

public enum CookedFood implements Edible{
    
    CHICKENRICE("Chicken Rice", Arrays.asList(Ingredient.RICE, Ingredient.CHICKEN), 16),
    CURRYRICE("Curry Rice", Arrays.asList(Ingredient.RICE, Ingredient.POTATO, Ingredient.CARROT, Ingredient.BEEF), 30),
    PEANUTMILK("Peanut Milk", Arrays.asList(Ingredient.MILK, Ingredient.PEANUT), 5),
    STIRFRY("Stir Fry", Arrays.asList(Ingredient.CARROT, Ingredient.SPINACH), 5),
    STEAK("Steak", Arrays.asList(Ingredient.POTATO, Ingredient.BEEF), 22);

    private final String type = "CookedFood";
    private final String name;
    private final List<Ingredient> ingredients;
    private final int satietyPoint;

    CookedFood(String name, List<Ingredient> ingredients, int satietyPoint){
        this.name=name;
        this.ingredients = ingredients;
        this.satietyPoint=satietyPoint;
    }

    public String getType(){
        return type;
    }
    public String getName(){
        return name;
    }

    public int getSatietyPoint(){
        return satietyPoint;
    }

    public List<Ingredient> getIngredients(){
        return ingredients;
    }

    // public boolean isEnoughIngredient(){
    //     return true;
    // }//ngecek ingredient 
}
