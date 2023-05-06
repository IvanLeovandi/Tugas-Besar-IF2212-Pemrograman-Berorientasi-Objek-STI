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

// public abstract class Food implements Edible {
// // private String type;
// private String name;
// private int satietyPoint;

// public Food(String name, int satietyPoint) {
// this.name = name;
// this.satietyPoint = satietyPoint;
// }

// public int getSatietyPoint() {
// return satietyPoint;
// }

// public String getName() {
// return name;
// }

// // public String getType() {
// // return type;
// // }

// @Override
// public boolean equals(Object o) {
// if (this == o) {
// return true;
// }
// if (o == null || getClass() != o.getClass()) {
// return false;
// }
// return getName().equals(((Food)o).getName());
// }
// }
