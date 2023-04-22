package com.simplicity.Foods;

import java.util.*;

import com.simplicity.Exceptions.DuplicateFoodException;
import com.simplicity.Exceptions.MissingFoodTypeException;

public class FoodFactory {
    private static FoodFactory instance = new FoodFactory();
    private Map<String, Integer> foodMenu = new HashMap<>();
    private Map<String, Integer> ingredientMenu = new HashMap<>();

    static {
        try {
            instance.addIngredient("sapi", 12, 15);
            instance.addIngredient("wortel", 3, 2);
            instance.addIngredient("ayam", 10, 8);
            instance.addIngredient("susu", 5, 5);
            instance.addIngredient("kacang", 5, 5);
            instance.addIngredient("kentang", 3, 4);
            instance.addIngredient("nasi", 5, 5);
            instance.addIngredient("bayam", 5, 5);
            instance.addFood("nasi ayam", 16);
            instance.addFood("nasi kari", 30);
            instance.addFood("susu kacang", 5);
            instance.addFood("bistik", 22);
            instance.addFood("tumis sayur", 5);
        } catch (DuplicateFoodException e) {}
    }

    public static FoodFactory getInstance() {
        return instance;
    }

    public Food createFood(String type) throws MissingFoodTypeException {
        type = type.toLowerCase();

        if (foodMenu.containsKey(type)) {
            return new Food(type, foodMenu.get(type));
        } else {
            throw new MissingFoodTypeException();
        }
    }

    public Ingredient createIngredient(String type) throws MissingFoodTypeException {
        type = type.toLowerCase();

        if (ingredientMenu.containsKey(type.toLowerCase())) {
            return new Ingredient(type, foodMenu.get(type), ingredientMenu.get(type));
        } else {
            throw new MissingFoodTypeException("Tidak ada Ingredient dengan nama tersebut.");
        }
    }

    public void addFood(String type, int satietyPoint) throws DuplicateFoodException {
        type = type.toLowerCase();

        if (foodMenu.containsKey(type)) {
            throw new DuplicateFoodException(type);
        } else {
            foodMenu.put(type, satietyPoint);
        }
    }

    public void addIngredient(String type, int satietyPoint, int price) throws DuplicateFoodException {
        type = type.toLowerCase();

        if (foodMenu.containsKey(type)) {
            throw new DuplicateFoodException(type);
        } else {
            foodMenu.put(type, satietyPoint);
            ingredientMenu.put(type, price);
        }
    }

    public boolean removeFood(String type) {
        type = type.toLowerCase();

        if (foodMenu.containsKey(type)) {
            if (ingredientMenu.containsKey(type)) {
                ingredientMenu.remove(type);
            }

            foodMenu.remove(type);
            return true;
        } else {
            return false;
        }
    }
}
