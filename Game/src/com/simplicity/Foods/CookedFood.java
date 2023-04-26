package com.simplicity.Foods;

import com.simplicity.Interfaces.Edible;

public enum CookedFood implements Edible{
    CHICKENRICE("Nasi Ayam", 16),
    CURRYRICE("Nasi Kari", 30),
    PEANUTMILK("Susu Kacang", 5),
    STIRFRY("Tumis Sayur", 5),
    STEAK("Bistik", 22);

    private final String type = "CookedFood";
    private final String name;
    private final int satietyPoint;

    CookedFood(String name, int satietyPoint){
        this.name=name;
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

    // public boolean isEnoughIngredient(){
    //     return true;
    // }//ngecek ingredient 
}
