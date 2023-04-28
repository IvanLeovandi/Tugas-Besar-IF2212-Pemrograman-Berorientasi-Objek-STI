package com.simplicity.Furniture;

public class Toilet extends Furniture {
    boolean isUsed;

    public Toilet(){
        super("Toilet");
    }

    public boolean getIsUsed(){
        return isUsed;
    }

    public void setIsUsed(boolean isUsed){
        this.isUsed = isUsed;
    }
}
