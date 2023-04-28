package com.simplicity.Furniture;

public class GasStove extends Furniture {
    boolean isUsed;

    public GasStove(){
        super("Gas Stove");
    }

    public boolean getIsUsed(){
        return isUsed;
    }

    public void setIsUsed(boolean isUsed){
        this.isUsed = isUsed;
    }
}
