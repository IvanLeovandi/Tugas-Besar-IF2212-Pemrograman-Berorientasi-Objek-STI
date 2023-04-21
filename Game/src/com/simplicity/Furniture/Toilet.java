package com.simplicity.Furniture;

public class Toilet extends Furniture {
    private boolean isUsed;

    public Toilet(){
        super("Toilet", new Point(1,1), 50);
        isUsed = false;
    }

    public boolean getIsUsed() {
        return isUsed;
    }

    // method defecate
}