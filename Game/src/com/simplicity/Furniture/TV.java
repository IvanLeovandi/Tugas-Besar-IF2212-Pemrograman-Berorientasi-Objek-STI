package com.simplicity.Furniture;

public class TV extends Furniture {
    boolean isUsed;

    public TV(){
        super("TV");
    }

    public boolean getIsUsed(){
        return isUsed;
    }

    public void setIsUsed(boolean isUsed){
        this.isUsed = isUsed;
    }
    
}
