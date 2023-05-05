package com.simplicity.Furniture;

public class Shower extends Furniture{
    boolean isUsed;

    public Shower(){
        super("Shower");
    }

    public boolean getIsUsed(){
        return isUsed;
    }

    public void setIsUsed(boolean isUsed){
        this.isUsed = isUsed;
    }
    
}
