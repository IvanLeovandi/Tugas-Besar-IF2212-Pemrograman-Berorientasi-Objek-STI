package com.simplicity.Furniture;

public class KingBed extends Furniture {
    boolean isUsed;

    public KingBed(){
        super("King Bed");
    }

    public boolean getIsUsed(){
        return isUsed;
    }

    public void setIsUsed(boolean isUsed){
        this.isUsed = isUsed;
    }
}
