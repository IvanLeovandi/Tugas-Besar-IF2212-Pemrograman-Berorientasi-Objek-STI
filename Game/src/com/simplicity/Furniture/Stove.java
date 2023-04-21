package com.simplicity.Furniture;

public class Stove extends Furniture {
    private String type;
    private boolean isUsed;

    public Stove(String type){
        if(type.equals("Gas")) {
            super("Kompor Gas", new Point(2,1), 100);
        } else if(type.equals("Listrik")) {
            super("Kompor Listrik", new Point(1,1), 200);
        }
        this.type = type;
        isUsed = false;
    }

    public boolean getIsUsed() {
        return isUsed;
    }

    // method cook
}