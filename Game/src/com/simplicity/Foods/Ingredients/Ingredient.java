package com.simplicity.Foods.Ingredients;

import com.simplicity.GameObject;
import com.simplicity.Interfaces.Edible;
import com.simplicity.Interfaces.Purchasable;


public class Ingredient extends GameObject implements Purchasable, Edible{

    public MyIngredient myIngredient;
    private final static String type = "INGREDIENT";

    public enum MyIngredient {
        RICE("RICE", 5, 5),
        POTATO("POTATO", 3, 4),
        CHICKEN("CHICKEN", 10, 8),
        BEEF("BEEF", 12, 15),
        CARROT("CARROT", 3, 2),
        SPINACH("SPINACH", 3, 2),
        PEANUT("PEANUT", 2, 2),
        MILK("MILK", 2, 1);

        private final String name;
        private final int price;
        private final int satietyPoint;
        // private boolean canBePurchased = false;

        MyIngredient(String name, int price, int satietyPoint) {
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
    }

    public Ingredient(String name) {
        super(type);
        switch(name){
            case "RICE":
                this.myIngredient = MyIngredient.RICE;
                break;
            case "POTATO":
                this.myIngredient = MyIngredient.POTATO;
                break;
            case "CHICKEN":
                this.myIngredient = MyIngredient.CHICKEN;
                break;
            case "BEEF":
                this.myIngredient = MyIngredient.BEEF;
                break;
            case "CARROT":
                this.myIngredient = MyIngredient.CARROT;
                break;
            case "SPINACH":
                this.myIngredient = MyIngredient.SPINACH;
                break;
            case "PEANUT":
                this.myIngredient = MyIngredient.PEANUT;
                break;
            case "MILK":
                this.myIngredient = MyIngredient.MILK;
                break;
            default:
                System.out.println("No Match");
        }
    }

    public MyIngredient getMyIngredient(){
        return myIngredient;
    }

    public String getType(){
        return type;
    }

    public String getName(){
        return myIngredient.getName();
    }

    public int getPrice(){
        return myIngredient.getPrice();
    }

    public int getSatietyPoint(){
        return myIngredient.getSatietyPoint();
    }
}
