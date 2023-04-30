package com.simplicity.Foods.CookedFood;

import java.util.*;
import com.simplicity.Foods.Ingredients.Ingredient;
import com.simplicity.GameObject;
import com.simplicity.Interfaces.Edible;

public class CookedFood extends GameObject implements Edible {

    private MyCookedFood myCookedFood;
    private final static String type = "CookedFood";

    public enum MyCookedFood {
        
        CHICKENRICE("Chicken Rice", Arrays.asList(new Ingredient("Rice"), new Ingredient("Chicken")), 16),
        CURRYRICE("Curry Rice", Arrays.asList(new Ingredient("Rice"), new Ingredient("Potato"), new Ingredient("Carrot"), new Ingredient("Beef")), 30),
        PEANUTMILK("Peanut Milk", Arrays.asList(new Ingredient("Milk"), new Ingredient("Peanut")), 5),
        STIRFRY("Stir Fry", Arrays.asList(new Ingredient("Carrot"), new Ingredient("Spinach")), 5),
        STEAK("Steak", Arrays.asList(new Ingredient("Potato"), new Ingredient("Beef")), 22);

        private final String type = "CookedFood";
        private final String name;
        private final List<Ingredient> ingredients;
        private final int satietyPoint;

        MyCookedFood(String name, List<Ingredient> ingredients, int satietyPoint){
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
    }

    public CookedFood(String name) {
        super(type);
        switch(name) {
            case "Chicken Rice":
                this.myCookedFood = MyCookedFood.CHICKENRICE;
                break;
            case "Curry Rice":
                this.myCookedFood = MyCookedFood.CURRYRICE;
                break;
            case "Peanut Milk":
                this.myCookedFood = MyCookedFood.PEANUTMILK;
                break;
            case "Stir Fry":
                this.myCookedFood = MyCookedFood.STIRFRY;
                break;
            case "Steak":
                this.myCookedFood = MyCookedFood.STEAK;
                break;
            default:
                System.out.println("No Match");
        }
    }

    public MyCookedFood getMyCookedFood(){
        return myCookedFood;
    }

    public String getType(){
        return type;
    }

    public String getName(){
        return myCookedFood.getName();
    }

    public int getSatietyPoint(){
        return myCookedFood.getSatietyPoint();
    }

    public List<Ingredient> getIngredients(){
        return myCookedFood.ingredients;
    } 

    
}