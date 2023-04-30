package com.simplicity.Foods.Ingredients;

import com.simplicity.GameObject;
import com.simplicity.Interfaces.Edible;
import com.simplicity.Interfaces.Purchasable;


public class Ingredient extends GameObject implements Purchasable, Edible{

    public MyIngredient myIngredient;
    private final static String type = "Ingredient";

    public enum MyIngredient {
        RICE("Rice", 5, 5),
        POTATO("Potato", 3, 4),
        CHICKEN("Chicken", 10, 8),
        BEEF("Beef", 12, 15),
        CARROT("Carrot", 3, 2),
        SPINACH("Spinach", 3, 2),
        PEANUT("Peanut", 2, 2),
        MILK("Milk", 2, 1);

        private final String type = "Ingredient";
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

        // public void setPrice(int price){
        //     this.price = price;
        // }

        // public boolean getCanBePurchased(){
        //     return canBePurchased;
        // }
        
        // public void setCanBePurchased(boolean canBePurchased){
        //     this.canBePurchased=canBePurchased;
        // }
    }

    public Ingredient(String name) {
        super(type);
        switch(name){
            case "Rice":
                this.myIngredient = MyIngredient.RICE;
                break;
            case "Potato":
                this.myIngredient = MyIngredient.POTATO;
                break;
            case "Chicken":
                this.myIngredient = MyIngredient.CHICKEN;
                break;
            case "Beef":
                this.myIngredient = MyIngredient.BEEF;
                break;
            case "Carrot":
                this.myIngredient = MyIngredient.CARROT;
                break;
            case "Spinach":
                this.myIngredient = MyIngredient.SPINACH;
                break;
            case "Peanut":
                this.myIngredient = MyIngredient.PEANUT;
                break;
            case "Milk":
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
