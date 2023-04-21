package com.simplicity.Foods.CookedFood;

import com.simplicity.Foods.Food;

public abstract class CookedFood extends Food {
    public CookedFood(String type, int satietyPoint) {
        super(type, satietyPoint);
    }

    public boolean isEnoughIngredient(){
        return true;
    }//ngecek ingredient 
}
