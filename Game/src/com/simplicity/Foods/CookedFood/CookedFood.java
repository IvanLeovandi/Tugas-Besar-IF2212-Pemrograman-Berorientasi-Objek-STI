package com.simplicity.Foods.CookedFood;

import java.util.*;
import com.simplicity.Foods.Ingredients.Ingredient;
import com.simplicity.GameObject;
import com.simplicity.Interfaces.Edible;

public class CookedFood extends GameObject implements Edible {

    private MyCookedFood myCookedFood;
    private final static String type = "COOKEDFOOD";
    private static Map<CookedFood, List<Ingredient>> cookedFoodList = new HashMap<>();

    public enum MyCookedFood {
        
        CHICKENRICE("CHICKEN RICE", Arrays.asList(new Ingredient("Rice"), new Ingredient("Chicken")), 16),
        CURRYRICE("CURRY RICE", Arrays.asList(new Ingredient("Rice"), new Ingredient("Potato"), new Ingredient("Carrot"), new Ingredient("Beef")), 30),
        PEANUTMILK("PEANUT MILK", Arrays.asList(new Ingredient("Milk"), new Ingredient("Peanut")), 5),
        STIRFRY("STIR FRY", Arrays.asList(new Ingredient("Carrot"), new Ingredient("Spinach")), 5),
        STEAK("STEAK", Arrays.asList(new Ingredient("Potato"), new Ingredient("Beef")), 22);

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
            case "CHICKEN RICE":
                this.myCookedFood = MyCookedFood.CHICKENRICE;
                break;
            case "CURRY RICE":
                this.myCookedFood = MyCookedFood.CURRYRICE;
                break;
            case "PEANUT MILK":
                this.myCookedFood = MyCookedFood.PEANUTMILK;
                break;
            case "STIR FRY":
                this.myCookedFood = MyCookedFood.STIRFRY;
                break;
            case "STEAK":
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

    private void addListCookedFood(){
        cookedFoodList.put(new CookedFood("CHICKEN RICE"), Arrays.asList(new Ingredient("Rice"), new Ingredient("Chicken")));
        cookedFoodList.put(new CookedFood("CURRY RICE"), Arrays.asList(new Ingredient("Rice"), new Ingredient("Potato"), new Ingredient("Carrot"), new Ingredient("Beef")));
        cookedFoodList.put(new CookedFood("PEANUT MILK"), Arrays.asList(new Ingredient("Milk"), new Ingredient("Peanut")));
        cookedFoodList.put(new CookedFood("STIR FRY"), Arrays.asList(new Ingredient("Carrot"), new Ingredient("Spinach")));
        cookedFoodList.put(new CookedFood("STEAK"), Arrays.asList(new Ingredient("Potato"), new Ingredient("Beef")));
    }

    public List<Ingredient> getIngredients(){
        return myCookedFood.ingredients;
    } 

    public static Map<CookedFood, List<Ingredient>> getCookedFoodList(){
        return cookedFoodList;
    }
    
    public static void printListCookedFood() {
        for (Map.Entry<CookedFood,List<Ingredient>> entry : cookedFoodList.entrySet()) {
            System.out.println("Key = " + entry.getKey().getName() +
                             ", Value = " + ((Ingredient)entry.getValue()));
        }
    }
}