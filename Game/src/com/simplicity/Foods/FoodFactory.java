package com.simplicity.Foods;

import java.util.*;

import com.simplicity.Exceptions.DuplicateFoodException;
import com.simplicity.Exceptions.MissingFoodTypeException;

public class FoodFactory {
    private static FoodFactory instance = new FoodFactory();
    private Map<String, Integer> foodMenu = new HashMap<>();
    private Map<String, Integer> ingredientMenu = new HashMap<>();
    private Map<String, Set<String>> cookableMenu = new HashMap<>();

    static {
        try {
            instance.addIngredient("BEEF", 12, 15);
            instance.addIngredient("CARROT", 3, 2);
            instance.addIngredient("CHIKEN", 10, 8);
            instance.addIngredient("MILK", 5, 5);
            instance.addIngredient("PEANUT", 5, 5);
            instance.addIngredient("POTATO", 3, 4);
            instance.addIngredient("RICE", 5, 5);
            instance.addIngredient("SPINACH", 5, 5);
            instance.addCookedFood("CHICKEN RICE", 16, new HashSet<String>(Arrays.asList("")));
            instance.addCookedFood("CURRY RICE", 30, new HashSet<String>(Arrays.asList("")));
            instance.addCookedFood("PEANUT MILK", 5, new HashSet<String>(Arrays.asList("")));
            instance.addCookedFood("STEAK", 22, new HashSet<String>(Arrays.asList("")));
            instance.addCookedFood("STIR FRY", 5, new HashSet<String>(Arrays.asList("")));
        } catch (DuplicateFoodException e) {
        }
    }

    public static FoodFactory getInstance() {
        return instance;
    }

    public Food createFood(String type) throws MissingFoodTypeException {
        String typeC = type.toUpperCase();

        if (ingredientMenu.containsKey(typeC)) {
            return new Ingredient(typeC, foodMenu.get(typeC), ingredientMenu.get(typeC));
        } else if (foodMenu.containsKey(typeC)) {
            return new Food(typeC, foodMenu.get(typeC));
        } else {
            throw new MissingFoodTypeException();
        }
    }

    public void addFood(String type, int satietyPoint) throws DuplicateFoodException {
        String typeC = type.toLowerCase();

        if (foodMenu.containsKey(typeC)) {
            throw new DuplicateFoodException(type);
        } else {
            foodMenu.put(typeC, satietyPoint);
        }
    }

    public void addCookedFood(String type, int satietyPoint, Set<String> ingredients) throws DuplicateFoodException {
        String typeC = type.toLowerCase();

        if (foodMenu.containsKey(typeC)) {
            throw new DuplicateFoodException(type);
        } else {
            cookableMenu.put(typeC, ingredients);
        }
    }

    public void addIngredient(String type, int satietyPoint, int price) throws DuplicateFoodException {
        String typeC = type.toLowerCase();

        if (foodMenu.containsKey(typeC)) {
            throw new DuplicateFoodException(type);
        } else {
            foodMenu.put(typeC, satietyPoint);
            ingredientMenu.put(typeC, price);
        }
    }

    public Set<String> getIngredientsOfFood(String foodType) {
        String TypeC = foodType.toLowerCase();

        return cookableMenu.get(TypeC);
    }

    public boolean removeFood(String type) {
        String typeC = type.toLowerCase();

        if (foodMenu.containsKey(typeC)) {
            if (ingredientMenu.containsKey(typeC)) {
                ingredientMenu.remove(type);
            }

            foodMenu.remove(type);
            return true;
        } else {
            return false;
        }
    }

    public void printCookableMenu(){
        for (Map.Entry<String,Set<String>> entry : cookableMenu.entrySet()) {
            System.out.println("Key = " + entry.getKey() +
                                ", Value = " + (entry.getValue()));
        }
    }
}