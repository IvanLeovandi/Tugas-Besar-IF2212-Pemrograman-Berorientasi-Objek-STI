package com.simplicity.Furniture;

public class TableAndChair extends Furniture {
    boolean isUsed;

    public TableAndChair(){
        super("Table And Chair");
    }

    public boolean getIsUsed(){
        return isUsed;
    }

    public void setIsUsed(boolean isUsed){
        this.isUsed = isUsed;
    }
}
