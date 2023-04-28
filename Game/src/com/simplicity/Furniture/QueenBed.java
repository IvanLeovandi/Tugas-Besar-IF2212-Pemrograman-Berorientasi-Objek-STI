package com.simplicity.Furniture;

public class QueenBed extends Furniture {
    boolean isUsed;

    public QueenBed(){
        super("Queen Bed");
    }

    public boolean getIsUsed(){
        return isUsed;
    }

    public void setIsUsed(boolean isUsed){
        this.isUsed = isUsed;
    }
}
