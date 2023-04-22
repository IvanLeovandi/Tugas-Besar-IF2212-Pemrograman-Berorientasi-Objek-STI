package com.simplicity.Foods;

import com.simplicity.GameObject;
import com.simplicity.Interfaces.Edible;

public class Food extends GameObject implements Edible {
    private int satietyPoint;

    public Food(String type, int satietyPoint) {
        super(type);
        this.satietyPoint = satietyPoint;
    }

    public int getSatietyPoint() {
        return satietyPoint;
    }

    public void setSatietyPoint(int satietyPoint) {
        this.satietyPoint = satietyPoint;
    }
}
