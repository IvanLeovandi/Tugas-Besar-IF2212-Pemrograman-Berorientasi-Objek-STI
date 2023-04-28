package com.simplicity.Furniture;

public class ElectricStove extends Furniture {
    boolean isUsed;

    public ElectricStove(){
        super("Electric Stove");
    }

    public boolean getIsUsed(){
        return isUsed;
    }

    public void setIsUsed(boolean isUsed){
        this.isUsed = isUsed;
    }
}
