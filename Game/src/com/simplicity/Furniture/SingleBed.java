package com.simplicity.Furniture;

public class SingleBed extends Furniture {
    boolean isUsed;

    public SingleBed(){
        super("Single Bed");
    }

    public boolean getIsUsed(){
        return isUsed;
    }

    public void setIsUsed(boolean isUsed){
        this.isUsed = isUsed;
    }
}
